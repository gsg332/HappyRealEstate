package com.happyJ.realestate.schedule;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.StringUtils;

import com.happyJ.realestate.dto.InterestDto;
import com.happyJ.realestate.dto.UrgentSaleDto;
import com.happyJ.realestate.service.InterestService;
import com.happyJ.realestate.service.UrgentSaleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class UrgentSaleSave  extends QuartzJobBean{

	private InterestService interestService;
	private UrgentSaleService urgentSaleService;
	private ApplicationContext ctx;
	
	
	@Override
	protected void executeInternal(JobExecutionContext ex) throws JobExecutionException {
		System.out.println("급매 잡: " + System.currentTimeMillis());
		
		ctx = (ApplicationContext)ex.getJobDetail().getJobDataMap().get("applicationContext1");
		interestService = (InterestService)ctx.getBean("InterestService");
		urgentSaleService = (UrgentSaleService)ctx.getBean("UrgentSaleService");
		
		/*
			1. 모든 회원의 관심 지역 및 아파트를 목록을 가지고 오기.
			2. 관심 지역 및 아파트를 네이버 검색 목록에 태워서 실제 매몰목록을 가지고 올 수 있는 파라미터를 가지고 옴.
			3. 파라미터를 태워서 최신순으로 1페이지 목록을 가지고 온다.
			4. 1페이지 목록에서 오늘 날짜 목록만 뽑아내야 하는데 모든 페이지가 오늘날짜이면 2페이지도 이어서호출하여 2페이지 첫번째 목록이 오늘날짜인지를 확인해서 오늘날짜의 모든 목록을 가져올 와 평균가격과 비교하여 급매인지 판단한다.
			   이 때, 1페이지에서 가져온 목록중 최신부터 20목록을 가져와 평균을 내어 급매인지를 판단할 수 있는 기준가로 삼는다. 만약 20개가 되지 않는다면 다음 2페이지를 요청하여 20개가 될 때까지 3페이지, 4페이지... 로 계속 요청한다. 단 2개월 이내의 데이터로만 가져오고 20개가 안 되고 
			 3개 이상이라면 그 값을 평균 낸다. 또한 이 때, 5층 이하 가격 6층이상 목록이 최소 3개~10개를 평균 낸 값을 기준값으로 본다. 
			 3개 이하일 경우에는 급매로 판단하기 힘든 것으로 판단하다로고 한다. 따라서. 급매 목록에서 제외한다.  
		*/
		
		try {
			InterestDto interestDto = new InterestDto();
			
			List<InterestDto> interestList = interestService.selectGroupInterestList(interestDto); //모든 회원 모든 관심 목록 가져오기.
			
			for(int i=0; i<interestList.size(); i++){
				
				InterestDto interest = interestList.get(i);
				
				if(!StringUtils.isEmpty(interest.getReName())){
					StringBuffer apart = new StringBuffer();
					apart.append(interest.getSido());
					apart.append(" ");
					apart.append(interest.getSgg());
					apart.append(" ");
					apart.append(interest.getEmd());
					if(!StringUtils.isEmpty(interest.getRi())){
						apart.append(" ");
						apart.append(interest.getRi());
					}
					apart.append(" ");
					apart.append(interest.getReName());
					
					//System.out.println("index : " + i);
					JSONObject paramForSale = getParamValForSaleList(apart.toString());
					//System.out.println("paramForSaleList : " + paramForSaleList);
					
					
					if(StringUtils.isEmpty(paramForSale.get("complexQuery"))){ // 이값이 없으면 해당 아파트에 대한 네이버 검색 결과가 없다는 뜻???? 없으면 다른 동네에 있는 수많은 아파트들이 조회되고 이 값이 비어 있는 것 같다.
						continue;
					}
					
					
					
					List<UrgentSaleDto> urgentSaleList = new ArrayList<UrgentSaleDto>();
					Map<String, Boolean> separateExtentForFloorMap = new HashMap<String, Boolean>(); //시세를 구하기 위해 목록을 가져올 때 층,면적별  목록 중 어떤 목록을 가져오야 하는지 구분.
					/*separateExtentForFloorMap.put("oneFloor", false);
	            	separateExtentForFloorMap.put("lowFloor", false);
	            	separateExtentForFloorMap.put("royalFloor", false);
	            	separateExtentForFloorMap.put("topFloor", false);*/
	            	
					int cfmArticleCount = 0;
					int karArticleCount = 0;
					int page = 1;
					int defaultdisplayListCnt = 0;
					int totalPage = 0;
					
					// 아파트 급매목록 후보인 오늘날짜(현재 테스트는 7일목록을 가져옴) 목록을 가지고 온다.
					do{
						JSONObject saleList = getSaleList(paramForSale, page, "A1", "D7"); //A1:B1:B2 매매:전세:월세  빈값이면 전체를 가져온다(날짜,매물 공통)
						//System.out.println("saleList : " + saleList);
						//여기서 가져온 목록을 그대로 저장하고 상세보기버튼을 달아 네이버 게시글로 이동할 수 있도록 하기. 이 때 아티클 번호를 저장하고 있어야 이동 가능하니 같이 저장해야 할 것 같다.
						
						//JSONObject articleInfo = (JSONObject) JSONSerializer.toJSON(saleList.get("articleInfo"));

						
						JSONObject articleInfo = JSONObject.fromObject(saleList.get("articleInfo"));
						
						if(page == 1){
							cfmArticleCount = articleInfo.getInt("cfmArticleCount");
							karArticleCount = articleInfo.getInt("karArticleCount");
							defaultdisplayListCnt = articleInfo.getInt("display"); // 또는 itemCount로도 가져올 수 있는 것 같다.
							totalPage = (int) Math.ceil(((double)(cfmArticleCount+karArticleCount) / (double) defaultdisplayListCnt));
						}
						
						
						
						JSONArray cfmArticleList = JSONArray.fromObject(JSONSerializer.toJSON(articleInfo.get("cfmArticleList")));
			            for(int cfmArticleIndex=0; cfmArticleIndex<cfmArticleList.size(); cfmArticleIndex++){
				            JSONObject cfmArticleJson = JSONObject.fromObject(cfmArticleList.get(cfmArticleIndex));
				            
				            if("0".equals(cfmArticleJson.getString("tradeCompletion"))){ //0:거래안됨, 1:거래완료
				            	UrgentSaleDto urgentSaleDto = new UrgentSaleDto();
					            urgentSaleDto.setArticleNum(cfmArticleJson.getLong("articleNumber"));
					            urgentSaleDto.setLinkUrl(cfmArticleJson.getString("linkUrl"));
					            urgentSaleDto.setReType("A");
					            urgentSaleDto.setSido(cfmArticleJson.getString("cityName"));
					            urgentSaleDto.setSgg(cfmArticleJson.getString("dvsnName"));
					            urgentSaleDto.setEmd(cfmArticleJson.getString("secName"));
					            urgentSaleDto.setRi("");
					            urgentSaleDto.setReName(cfmArticleJson.getString("articleName"));
					            urgentSaleDto.setFloor(cfmArticleJson.getString("floor"));
					            urgentSaleDto.setDong(cfmArticleJson.getString("building"));
					            urgentSaleDto.setUrgentPrice(Integer.parseInt(cfmArticleJson.getString("price").replaceAll(",", "")));
					            urgentSaleDto.setMarketPrice(null);
					            urgentSaleDto.setLowestPrice(null);
					            urgentSaleDto.setHighestPrice(null);
					            urgentSaleDto.setAveragePrice(null);
					            urgentSaleDto.setSupplyExtent(cfmArticleJson.getInt("size1"));
					            urgentSaleDto.setExclusiveExtent(cfmArticleJson.getInt("size2"));
					            urgentSaleDto.setAgentName(cfmArticleJson.getString("realterName"));
					            urgentSaleDto.setAgentPhone(cfmArticleJson.getString("realterTelNo"));
					            
					            // 날짜가 20170329와 2017.03.29 타입 두가지로 다르게 들어올 때가 있는건가??
					            String registYmd = cfmArticleJson.getString("registYmd");
					            if(registYmd.indexOf(".") == -1){
					            	registYmd = registYmd.substring(2,4) + "." + registYmd.substring(4,6) + "." + registYmd.substring(6,8);
					            }
					            SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd");
					            Date date = sdf.parse(registYmd);
					            urgentSaleDto.setRegDate(date);
					            
					            urgentSaleList.add(urgentSaleDto);
					            
					            //숫자 뿐만 아니라 저/15, 중/15, 고/15, -/15 인경우가 있음.
					            //System.out.println(cfmArticleJson.getString("floor"));
					            
					            
					            if(!StringUtils.isEmpty(cfmArticleJson.getString("floor"))){
						            Integer figureFloor = 0;
						            String letterFloor = "";
						            boolean isOnlyFigure = Pattern.compile("(^[0-9]*$)").matcher(cfmArticleJson.getString("floor").split("/")[0]).find();
						            
						            //System.out.println("층 : " + cfmArticleJson.getString("floor"));
						            if(isOnlyFigure){
						            	figureFloor = Integer.parseInt(cfmArticleJson.getString("floor").split("/")[0]);
						            }else{
						            	letterFloor = cfmArticleJson.getString("floor").split("/")[0];
						            }
	
						            //현재 DB 아파트 부동산 정보에 층에 대한 정보가 없어서 탑층은 계산에 넣지 않고 7층이상이면 무조건 로얄층으로 보기로 한다.
						            if("-".equals(letterFloor)){
						            }else if(figureFloor == 1 || "저".equals(letterFloor)){
						            	separateExtentForFloorMap.put(cfmArticleJson.getInt("size2") + "OneFloor", true);
						            }else if(figureFloor < 7 || "중".equals(letterFloor)){
						            	separateExtentForFloorMap.put(cfmArticleJson.getInt("size2") + "LowFloor", true);
						            }else if(figureFloor >= 7 || "고".equals(letterFloor)){
						            	separateExtentForFloorMap.put(cfmArticleJson.getInt("size2") + "RoyalFloor", true);
						            }
						            /*}else if(interestList.get(i).getTopFloor() != null && figureFloor < Integer.parseInt(interestList.get(i).getTopFloor()) || "고".equals(letterFloor)){
						            	separateFloorMap.put("royalFloor", true);
					            	}else{ //탑층
						            	separateFloorMap.put("topFloor", true);
						            }*/
					            }
				            }
			            }
			            
			            JSONArray karArticleList = JSONArray.fromObject(JSONSerializer.toJSON(articleInfo.get("karArticleList")));
			            for(int karArticleIndex=0; karArticleIndex<karArticleList.size(); karArticleIndex++){
				            JSONObject karArticleJson = JSONObject.fromObject(karArticleList.get(karArticleIndex));
				           
				            if("0".equals(karArticleJson.getString("tradeCompletion"))){ //0:거래안됨, 1:거래완료
					            UrgentSaleDto urgentSaleDto = new UrgentSaleDto();
					            urgentSaleDto.setArticleNum(karArticleJson.getLong("articleNumber"));
					            urgentSaleDto.setLinkUrl(karArticleJson.getString("linkUrl"));
					            urgentSaleDto.setReType("A");
					            urgentSaleDto.setSido(karArticleJson.getString("cityName"));
					            urgentSaleDto.setSgg(karArticleJson.getString("dvsnName"));
					            urgentSaleDto.setEmd(karArticleJson.getString("secName"));
					            urgentSaleDto.setRi("");
					            urgentSaleDto.setReName(karArticleJson.getString("articleName"));
					            urgentSaleDto.setFloor(karArticleJson.getString("floor"));
					            urgentSaleDto.setDong(karArticleJson.getString("building"));
					            urgentSaleDto.setUrgentPrice(Integer.parseInt(karArticleJson.getString("price").replaceAll(",", "")));
					            urgentSaleDto.setMarketPrice(null);
					            urgentSaleDto.setLowestPrice(null);
					            urgentSaleDto.setHighestPrice(null);
					            urgentSaleDto.setAveragePrice(null);
					            urgentSaleDto.setSupplyExtent(karArticleJson.getInt("size1"));
					            urgentSaleDto.setExclusiveExtent(karArticleJson.getInt("size2"));
					            urgentSaleDto.setAgentName(karArticleJson.getString("realterName"));
					            urgentSaleDto.setAgentPhone(karArticleJson.getString("realterTelNo"));
					            
					            // 날짜가 20170329와 2017.03.29 타입 두가지로 다르게 들어올 때가 있는건가??
					            String registYmd = karArticleJson.getString("registYmd");
					            if(registYmd.indexOf(".") == -1){
					            	registYmd = registYmd.substring(2,4) + "." + registYmd.substring(4,6) + "." + registYmd.substring(6,8);
					            }
					            SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd");
					            Date date = sdf.parse(registYmd);
					            urgentSaleDto.setRegDate(date);
					            
					            urgentSaleList.add(urgentSaleDto);
					            
					            if(!StringUtils.isEmpty(karArticleJson.getString("floor"))){
						            Integer figureFloor = 0;
						            String letterFloor = "";
						            boolean isOnlyFigure = Pattern.compile("(^[0-9]*$)").matcher(karArticleJson.getString("floor").split("/")[0]).find();
						            
						            if(isOnlyFigure){
						            	figureFloor = Integer.parseInt(karArticleJson.getString("floor").split("/")[0]);
						            }else{
						            	letterFloor = karArticleJson.getString("floor").split("/")[0];
						            }
		
						            //현재 DB 아파트 부동산 정보에 층에 대한 정보가 없어서 탑층은 계산에 넣지 않고 7층이상이면 무조건 로얄층으로 보기로 한다.
						            if("-".equals(letterFloor)){
						            }else if(figureFloor == 1 || "저".equals(letterFloor)){
						            	separateExtentForFloorMap.put(karArticleJson.getInt("size2") + "OneFloor", true);
						            }else if(figureFloor < 7 || "중".equals(letterFloor)){
						            	separateExtentForFloorMap.put(karArticleJson.getInt("size2") + "LowFloor", true);
						            }else if(figureFloor >= 7 || "고".equals(letterFloor)){
						            	separateExtentForFloorMap.put(karArticleJson.getInt("size2") + "RoyalFloor", true);
						            }
						            /*}else if(interestList.get(i).getTopFloor() != null && figureFloor < Integer.parseInt(interestList.get(i).getTopFloor()) || "고".equals(letterFloor)){
						            	separateFloorMap.put("royalFloor", true);
					            	}else{ //탑층
						            	separateFloorMap.put("topFloor", true);
						            }*/
					            }
				            }
			            }
				         
			            page++;
			            
					}while(page <= totalPage);
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					// 아파트에 대한 급매목록이 있으면  시세정보를 위해 층별로 아파트 정보를 모아온다.
					if(urgentSaleList.size() > 0){
						/*
						List<UrgentSaleDto> oneFloorSaleList = new ArrayList<UrgentSaleDto>();
						List<UrgentSaleDto> lowFloorSaleList = new ArrayList<UrgentSaleDto>();
						List<UrgentSaleDto> royalFloorSaleList = new ArrayList<UrgentSaleDto>();
						List<UrgentSaleDto> topFloorSaleList = new ArrayList<UrgentSaleDto>();
						List<UrgentSaleDto> oneFloorJunseList = new ArrayList<UrgentSaleDto>();
						List<UrgentSaleDto> lowFloorJunseList = new ArrayList<UrgentSaleDto>();
						List<UrgentSaleDto> royalFloorJunseList = new ArrayList<UrgentSaleDto>();
						List<UrgentSaleDto> topFloorJunseList = new ArrayList<UrgentSaleDto>();
						*/
						
						Map<String, List<UrgentSaleDto>> extentForFloorSaleJunseMap = new HashMap<String, List<UrgentSaleDto>>(); //층,면적별 아파트 목록을 저장
						
						
						boolean isOverCfmArticleDate = false; // 기본 목록 검색 시 2달 이상 검색되었는지여부
						boolean isOverKarArticleDate = false; // 공인중개사 협회 목록검색 시 2달 이상 검색되었는지 여부
						
						page = 1;
						
						
						//시세를 가져오기 위해 2개월 안쪽으로 층별 목록을 최대 10개 최소 3개 매매와 전세 목록을 가지고 온다. 
						do{
							
							boolean isFullList = true;
							Set keySet = extentForFloorSaleJunseMap.keySet();
							for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
								String key = (String) iterator.next();
								List<UrgentSaleDto> value = (List<UrgentSaleDto>) extentForFloorSaleJunseMap.get(key);
								
								if(extentForFloorSaleJunseMap.get(key).size() < 10){
									isFullList = false;
									break;
								}
							}
							
							if(extentForFloorSaleJunseMap.size() != 0 && isFullList){
								break;
							}
							
							if(isOverCfmArticleDate && isOverKarArticleDate){ // 일반목록, 공인중개사협회 목록 모두 2달 넘게 조회했으면 이제 벗어남.
								break;
							}
							
							JSONObject saleList = getSaleList(paramForSale, page, "", ""); //A1:B1:B2 매매:전세:월세; 빈값이면 전체를 가져온다(날짜,매물 공통) 이 때, A1:B1 이런식으로 매매와 전세만은 못 불러오는 것 같거 A1, B1, B2를 따로 가져오거나 아무걷소 입력하지 않아서 전부 가져와야 한다.
							//System.out.println("saleList : " + saleList);
							//여기서 가져온 목록을 그대로 저장하고 상세보기버튼을 달아 네이버 게시글로 이동할 수 있도록 하기. 이 때 아티클 번호를 저장하고 있어야 이동 가능하니 같이 저장해야 할 것 같다.
							
							//JSONObject articleInfo = (JSONObject) JSONSerializer.toJSON(saleList.get("articleInfo"));
	
							
							JSONObject articleInfo = JSONObject.fromObject(saleList.get("articleInfo"));
							
							if(page == 1){
								cfmArticleCount = articleInfo.getInt("cfmArticleCount");
								karArticleCount = articleInfo.getInt("karArticleCount");
								defaultdisplayListCnt = articleInfo.getInt("display"); // 또는 itemCount로도 가져올 수 있는 것 같다.
								totalPage = (int) Math.ceil(((double)(cfmArticleCount+karArticleCount) / (double) defaultdisplayListCnt));
							}
							
							JSONArray cfmArticleList = JSONArray.fromObject(JSONSerializer.toJSON(articleInfo.get("cfmArticleList")));
				            for(int cfmArticleIndex=0; cfmArticleIndex<cfmArticleList.size(); cfmArticleIndex++){
					            JSONObject cfmArticleJson = JSONObject.fromObject(cfmArticleList.get(cfmArticleIndex));
					            
					            if("A1".equals(cfmArticleJson.getString("tradeTypeCode")) || "B1".equals(cfmArticleJson.getString("tradeTypeCode"))){ // A1 : 매매인 경우, B1 : 전세인 경우.
						            
						            // 날짜가 20170329와 2017.03.29 타입 두가지로 다르게 들어올 때가 있는건가??
						            String registYmd = cfmArticleJson.getString("registYmd");
						            if(registYmd.indexOf(".") == -1){
						            	registYmd = registYmd.substring(2,4) + "." + registYmd.substring(4,6) + "." + registYmd.substring(6,8);
						            }
						            SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd");
						            Date regDate = sdf.parse(registYmd); 
						            
						            if((System.currentTimeMillis() - regDate.getTime()) <= ((long) 1000 * 60 * 60 * 24 * 30)){ //날짜가 30일이 안 지났다면.
						            	
							            UrgentSaleDto saleAndJunseDto = new UrgentSaleDto();
							            saleAndJunseDto.setArticleNum(cfmArticleJson.getLong("articleNumber"));
							            saleAndJunseDto.setLinkUrl(cfmArticleJson.getString("linkUrl"));
							            saleAndJunseDto.setReType("A");
							            saleAndJunseDto.setSido(cfmArticleJson.getString("cityName"));
							            saleAndJunseDto.setSgg(cfmArticleJson.getString("dvsnName"));
							            saleAndJunseDto.setEmd(cfmArticleJson.getString("secName"));
							            saleAndJunseDto.setRi("");
							            saleAndJunseDto.setReName(cfmArticleJson.getString("articleName"));
							            saleAndJunseDto.setFloor(cfmArticleJson.getString("floor"));
							            saleAndJunseDto.setDong(cfmArticleJson.getString("building"));
							            saleAndJunseDto.setUrgentPrice(Integer.parseInt(cfmArticleJson.getString("price").replaceAll(",", "")));
							            saleAndJunseDto.setMarketPrice(null);
							            saleAndJunseDto.setLowestPrice(null);
							            saleAndJunseDto.setHighestPrice(null);
							            saleAndJunseDto.setAveragePrice(null);
							            saleAndJunseDto.setSupplyExtent(cfmArticleJson.getInt("size1"));
							            saleAndJunseDto.setExclusiveExtent(cfmArticleJson.getInt("size2"));
							            saleAndJunseDto.setAgentName(cfmArticleJson.getString("realterName"));
							            saleAndJunseDto.setAgentPhone(cfmArticleJson.getString("realterTelNo"));
							            saleAndJunseDto.setRegDate(regDate);
								        
							            
							            
							            
							            if(!StringUtils.isEmpty(cfmArticleJson.getString("floor"))){
							            	
								            Integer figureFloor = 0;
								            String letterFloor = "";
								            boolean isOnlyFigure = Pattern.compile("(^[0-9]*$)").matcher(cfmArticleJson.getString("floor").split("/")[0]).find();
								            
								            if(isOnlyFigure){
								            	figureFloor = Integer.parseInt(cfmArticleJson.getString("floor").split("/")[0]);
								            }else{
								            	letterFloor = cfmArticleJson.getString("floor").split("/")[0];
								            }
		
								            
							            	if(separateExtentForFloorMap.get(cfmArticleJson.getInt("size2") + "OneFloor") != null && separateExtentForFloorMap.get(cfmArticleJson.getInt("size2") + "OneFloor") && (figureFloor == 1 || "저".equals(letterFloor)) && "매매".equals(cfmArticleJson.getString("tradeTypeCodeName"))){ // "A1".equals(cfmArticleJson.getString("tradeTypeCd"))도 가능 할 듯
							            		if(extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "OneFloorSaleList") == null){
							            			List<UrgentSaleDto> extendForFloorSaleList = new ArrayList<UrgentSaleDto>();
							            			extendForFloorSaleList.add(saleAndJunseDto);
							            			extentForFloorSaleJunseMap.put(cfmArticleJson.getInt("size2") + "OneFloorSaleList", extendForFloorSaleList);
							            		}else{
							            			if(extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "OneFloorSaleList").size() < 10){ 
							            				extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "OneFloorSaleList").add(saleAndJunseDto);
							            			}
							            		}
							            	}else if(separateExtentForFloorMap.get(cfmArticleJson.getInt("size2") + "LowFloor") != null && separateExtentForFloorMap.get(cfmArticleJson.getInt("size2") + "LowFloor") && (figureFloor < 7 || "중".equals(letterFloor)) && "매매".equals(cfmArticleJson.getString("tradeTypeCodeName"))){ // "A1".equals(cfmArticleJson.getString("tradeTypeCd"))도 가능 할 듯
							            		if(extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "LowFloorSaleList") == null){
							            			List<UrgentSaleDto> extendForFloorSaleList = new ArrayList<UrgentSaleDto>();
							            			extendForFloorSaleList.add(saleAndJunseDto);
							            			extentForFloorSaleJunseMap.put(cfmArticleJson.getInt("size2") + "LowFloorSaleList", extendForFloorSaleList);
							            		}else{
							            			if(extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "LowFloorSaleList").size() < 10){
							            				extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "LowFloorSaleList").add(saleAndJunseDto);
							            			}
							            		}
							            	}else if(separateExtentForFloorMap.get(cfmArticleJson.getInt("size2") + "RoyalFloor") != null && separateExtentForFloorMap.get(cfmArticleJson.getInt("size2") + "RoyalFloor") && (figureFloor >= 7 || "고".equals(letterFloor)) && "매매".equals(cfmArticleJson.getString("tradeTypeCodeName"))){ // "A1".equals(cfmArticleJson.getString("tradeTypeCd"))도 가능 할 듯
							            		if(extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "RoyalFloorSaleList") == null){
							            			List<UrgentSaleDto> extendForFloorSaleList = new ArrayList<UrgentSaleDto>();
							            			extendForFloorSaleList.add(saleAndJunseDto);
							            			extentForFloorSaleJunseMap.put(cfmArticleJson.getInt("size2") + "RoyalFloorSaleList", extendForFloorSaleList);
							            		}else{
							            			if(extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "RoyalFloorSaleList").size() < 10){
							            				extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "RoyalFloorSaleList").add(saleAndJunseDto);
							            			}
							            		}
							            	}else if(separateExtentForFloorMap.get(cfmArticleJson.getInt("size2") + "OneFloor") != null && separateExtentForFloorMap.get(cfmArticleJson.getInt("size2") + "OneFloor") && (figureFloor == 1 || "저".equals(letterFloor)) && "전세".equals(cfmArticleJson.getString("tradeTypeCodeName"))){ // "A1".equals(cfmArticleJson.getString("tradeTypeCd"))도 가능 할 듯
							            		if(extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "OneFloorJunseList") == null){
							            			List<UrgentSaleDto> extendForFloorSaleList = new ArrayList<UrgentSaleDto>();
							            			extendForFloorSaleList.add(saleAndJunseDto);
							            			extentForFloorSaleJunseMap.put(cfmArticleJson.getInt("size2") + "OneFloorJunseList", extendForFloorSaleList);
							            		}else{
							            			if(extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "OneFloorJunseList").size() < 10){
							            				extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "OneFloorJunseList").add(saleAndJunseDto);
							            			}
							            		}
							            	}else if(separateExtentForFloorMap.get(cfmArticleJson.getInt("size2") + "LowFloor") != null && separateExtentForFloorMap.get(cfmArticleJson.getInt("size2") + "LowFloor") && (figureFloor < 7 || "중".equals(letterFloor)) && "전세".equals(cfmArticleJson.getString("tradeTypeCodeName"))){ // "A1".equals(cfmArticleJson.getString("tradeTypeCd"))도 가능 할 듯
							            		if(extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "LowFloorJunseList") == null){
							            			List<UrgentSaleDto> extendForFloorSaleList = new ArrayList<UrgentSaleDto>();
							            			extendForFloorSaleList.add(saleAndJunseDto);
							            			extentForFloorSaleJunseMap.put(cfmArticleJson.getInt("size2") + "LowFloorJunseList", extendForFloorSaleList);
							            		}else{
							            			if(extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "LowFloorJunseList").size() < 10){
							            				extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "LowFloorJunseList").add(saleAndJunseDto);
							            			}
							            		}
							            	}else if(separateExtentForFloorMap.get(cfmArticleJson.getInt("size2") + "RoyalFloor") != null && separateExtentForFloorMap.get(cfmArticleJson.getInt("size2") + "RoyalFloor") && (figureFloor >= 7 || "고".equals(letterFloor)) && "전세".equals(cfmArticleJson.getString("tradeTypeCodeName"))){ // "A1".equals(cfmArticleJson.getString("tradeTypeCd"))도 가능 할 듯
							            		if(extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "RoyalFloorJunseList") == null){
							            			List<UrgentSaleDto> extendForFloorSaleList = new ArrayList<UrgentSaleDto>();
							            			extendForFloorSaleList.add(saleAndJunseDto);
							            			extentForFloorSaleJunseMap.put(cfmArticleJson.getInt("size2") + "RoyalFloorJunseList", extendForFloorSaleList);
							            		}else{
							            			if(extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "RoyalFloorJunseList").size() < 10){
							            				extentForFloorSaleJunseMap.get(cfmArticleJson.getInt("size2") + "RoyalFloorJunseList").add(saleAndJunseDto);
							            			}
							            		}
							            	}
							            }
						            }else{
						            	isOverCfmArticleDate = true;
						            	break;
						            }
					            }
				            }
				            
			            	JSONArray karArticleList = JSONArray.fromObject(JSONSerializer.toJSON(articleInfo.get("karArticleList")));
				            for(int karArticleIndex=0; karArticleIndex<karArticleList.size(); karArticleIndex++){
					            JSONObject karArticleJson = JSONObject.fromObject(karArticleList.get(karArticleIndex));
					            
					            if("A1".equals(karArticleJson.getString("tradeTypeCode")) || "B1".equals(karArticleJson.getString("tradeTypeCode"))){ // A1 : 매매인 경우, B1 : 전세인 경우.
					            
						            // 날짜가 20170329와 2017.03.29 타입 두가지로 다르게 들어올 때가 있는건가??
						            String registYmd = karArticleJson.getString("registYmd");
						            if(registYmd.indexOf(".") == -1){
						            	registYmd = registYmd.substring(2,4) + "." + registYmd.substring(4,6) + "." + registYmd.substring(6,8);
						            }
						            SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd");
						            Date regDate = sdf.parse(registYmd); 
						            
						            if((System.currentTimeMillis() - regDate.getTime()) <= ((long) 1000 * 60 * 60 * 24 * 30)){ //날짜가 30일이 안 지났다면
						            	
							            UrgentSaleDto saleAndJunseDto = new UrgentSaleDto();
							            saleAndJunseDto.setArticleNum(karArticleJson.getLong("articleNumber"));
							            saleAndJunseDto.setLinkUrl(karArticleJson.getString("linkUrl"));
							            saleAndJunseDto.setReType("A");
							            saleAndJunseDto.setSido(karArticleJson.getString("cityName"));
							            saleAndJunseDto.setSgg(karArticleJson.getString("dvsnName"));
							            saleAndJunseDto.setEmd(karArticleJson.getString("secName"));
							            saleAndJunseDto.setRi("");
							            saleAndJunseDto.setReName(karArticleJson.getString("articleName"));
							            saleAndJunseDto.setFloor(karArticleJson.getString("floor"));
							            saleAndJunseDto.setDong(karArticleJson.getString("building"));
							            saleAndJunseDto.setUrgentPrice(Integer.parseInt(karArticleJson.getString("price").replaceAll(",", "")));
							            saleAndJunseDto.setMarketPrice(null);
							            saleAndJunseDto.setLowestPrice(null);
							            saleAndJunseDto.setHighestPrice(null);
							            saleAndJunseDto.setAveragePrice(null);
							            saleAndJunseDto.setSupplyExtent(karArticleJson.getInt("size1"));
							            saleAndJunseDto.setExclusiveExtent(karArticleJson.getInt("size2"));
							            saleAndJunseDto.setAgentName(karArticleJson.getString("realterName"));
							            saleAndJunseDto.setAgentPhone(karArticleJson.getString("realterTelNo"));
							            saleAndJunseDto.setRegDate(regDate);
								            
							            if(!StringUtils.isEmpty(karArticleJson.getString("floor"))){
							            	
								            Integer figureFloor = 0;
								            String letterFloor = "";
								            boolean isOnlyFigure = Pattern.compile("(^[0-9]*$)").matcher(karArticleJson.getString("floor").split("/")[0]).find();
								            
								            if(isOnlyFigure){
								            	figureFloor = Integer.parseInt(karArticleJson.getString("floor").split("/")[0]);
								            }else{
								            	letterFloor = karArticleJson.getString("floor").split("/")[0];
								            }
	
								            if(separateExtentForFloorMap.get(karArticleJson.getInt("size2") + "OneFloor") != null && separateExtentForFloorMap.get(karArticleJson.getInt("size2") + "OneFloor") && (figureFloor == 1 || "저".equals(letterFloor)) && "매매".equals(karArticleJson.getString("tradeTypeCodeName"))){ // "A1".equals(karArticleJson.getString("tradeTypeCd"))도 가능 할 듯
							            		if(extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "OneFloorSaleList") == null){
							            			List<UrgentSaleDto> extendForFloorSaleList = new ArrayList<UrgentSaleDto>();
							            			extendForFloorSaleList.add(saleAndJunseDto);
							            			extentForFloorSaleJunseMap.put(karArticleJson.getInt("size2") + "OneFloorSaleList", extendForFloorSaleList);
							            		}else{
							            			if(extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "OneFloorSaleList").size() < 10){ 
							            				extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "OneFloorSaleList").add(saleAndJunseDto);
							            			}
							            		}
							            	}else if(separateExtentForFloorMap.get(karArticleJson.getInt("size2") + "LowFloor") != null && separateExtentForFloorMap.get(karArticleJson.getInt("size2") + "LowFloor") && (figureFloor < 7 || "중".equals(letterFloor)) && "매매".equals(karArticleJson.getString("tradeTypeCodeName"))){ // "A1".equals(karArticleJson.getString("tradeTypeCd"))도 가능 할 듯
							            		if(extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "LowFloorSaleList") == null){
							            			List<UrgentSaleDto> extendForFloorSaleList = new ArrayList<UrgentSaleDto>();
							            			extendForFloorSaleList.add(saleAndJunseDto);
							            			extentForFloorSaleJunseMap.put(karArticleJson.getInt("size2") + "LowFloorSaleList", extendForFloorSaleList);
							            		}else{
							            			if(extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "LowFloorSaleList").size() < 10){
							            				extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "LowFloorSaleList").add(saleAndJunseDto);
							            			}
							            		}
							            	}else if(separateExtentForFloorMap.get(karArticleJson.getInt("size2") + "RoyalFloor") != null && separateExtentForFloorMap.get(karArticleJson.getInt("size2") + "RoyalFloor") && (figureFloor >= 7 || "고".equals(letterFloor)) && "매매".equals(karArticleJson.getString("tradeTypeCodeName"))){ // "A1".equals(karArticleJson.getString("tradeTypeCd"))도 가능 할 듯
							            		if(extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "RoyalFloorSaleList") == null){
							            			List<UrgentSaleDto> extendForFloorSaleList = new ArrayList<UrgentSaleDto>();
							            			extendForFloorSaleList.add(saleAndJunseDto);
							            			extentForFloorSaleJunseMap.put(karArticleJson.getInt("size2") + "RoyalFloorSaleList", extendForFloorSaleList);
							            		}else{
							            			if(extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "RoyalFloorSaleList").size() < 10){
							            				extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "RoyalFloorSaleList").add(saleAndJunseDto);
							            			}
							            		}
							            	}else if(separateExtentForFloorMap.get(karArticleJson.getInt("size2") + "OneFloor") != null && separateExtentForFloorMap.get(karArticleJson.getInt("size2") + "OneFloor") && (figureFloor == 1 || "저".equals(letterFloor)) && "전세".equals(karArticleJson.getString("tradeTypeCodeName"))){ // "A1".equals(karArticleJson.getString("tradeTypeCd"))도 가능 할 듯
							            		if(extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "OneFloorJunseList") == null){
							            			List<UrgentSaleDto> extendForFloorSaleList = new ArrayList<UrgentSaleDto>();
							            			extendForFloorSaleList.add(saleAndJunseDto);
							            			extentForFloorSaleJunseMap.put(karArticleJson.getInt("size2") + "OneFloorJunseList", extendForFloorSaleList);
							            		}else{
							            			if(extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "OneFloorJunseList").size() < 10){
							            				extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "OneFloorJunseList").add(saleAndJunseDto);
							            			}
							            		}
							            	}else if(separateExtentForFloorMap.get(karArticleJson.getInt("size2") + "LowFloor") != null && separateExtentForFloorMap.get(karArticleJson.getInt("size2") + "LowFloor") && (figureFloor < 7 || "중".equals(letterFloor)) && "전세".equals(karArticleJson.getString("tradeTypeCodeName"))){ // "A1".equals(karArticleJson.getString("tradeTypeCd"))도 가능 할 듯
							            		if(extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "LowFloorJunseList") == null){
							            			List<UrgentSaleDto> extendForFloorSaleList = new ArrayList<UrgentSaleDto>();
							            			extendForFloorSaleList.add(saleAndJunseDto);
							            			extentForFloorSaleJunseMap.put(karArticleJson.getInt("size2") + "LowFloorJunseList", extendForFloorSaleList);
							            		}else{
							            			if(extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "LowFloorJunseList").size() < 10){
							            				extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "LowFloorJunseList").add(saleAndJunseDto);
							            			}
							            		}
							            	}else if(separateExtentForFloorMap.get(karArticleJson.getInt("size2") + "RoyalFloor") != null && separateExtentForFloorMap.get(karArticleJson.getInt("size2") + "RoyalFloor") && (figureFloor >= 7 || "고".equals(letterFloor)) && "전세".equals(karArticleJson.getString("tradeTypeCodeName"))){ // "A1".equals(karArticleJson.getString("tradeTypeCd"))도 가능 할 듯
							            		if(extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "RoyalFloorJunseList") == null){
							            			List<UrgentSaleDto> extendForFloorSaleList = new ArrayList<UrgentSaleDto>();
							            			extendForFloorSaleList.add(saleAndJunseDto);
							            			extentForFloorSaleJunseMap.put(karArticleJson.getInt("size2") + "RoyalFloorJunseList", extendForFloorSaleList);
							            		}else{
							            			if(extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "RoyalFloorJunseList").size() < 10){
							            				extentForFloorSaleJunseMap.get(karArticleJson.getInt("size2") + "RoyalFloorJunseList").add(saleAndJunseDto);
							            			}
							            		}
							            	}
							            }
						            }else{
						            	isOverKarArticleDate = true;
						            	break;
						            }
					            }
				            }
				            
				            page++;
				            
						}while(page <= totalPage);
				           
						//여기서 리스트 목록 10개 평균
						
					       //층별 매매, 전세 평균 구하기.
			            
						/*
						int oneFloorSaleMarketPrice = 0;
						int lowFloorSaleMarketPrice = 0;
						int royalFloorSaleMarketPrice = 0;
						int topFloorSaleMarketPrice = 0;
						int oneFloorJunseMarketPrice = 0;
						int lowFloorJunseMarketPrice = 0;
						int royalFloorJunseMarketPrice = 0;
						int topFloorJunseMarketPrice = 0;*/
						
						//층,면적별 평균 매매, 전세 시세 구하기.
						Map<String,Integer> extentForFloorMarketPriceMap = new HashMap<String, Integer>();
						Set keySet = separateExtentForFloorMap.keySet();
						for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
							String key = (String) iterator.next();
							Boolean separateExtentForFloorVal = separateExtentForFloorMap.get(key);
							
							if(separateExtentForFloorVal){
								int listSize;
								if(extentForFloorSaleJunseMap.get(key + "SaleList") != null){
									listSize = extentForFloorSaleJunseMap.get(key + "SaleList").size();
									if(listSize >= 3){
										if(extentForFloorMarketPriceMap.get(key + "SaleList") == null){
											extentForFloorMarketPriceMap.put(key + "SaleList", 0);
										}
										for(UrgentSaleDto saleDto : extentForFloorSaleJunseMap.get(key + "SaleList")){
											extentForFloorMarketPriceMap.put(key + "SaleList", extentForFloorMarketPriceMap.get(key + "SaleList") + saleDto.getUrgentPrice());
										}
										extentForFloorMarketPriceMap.put(key + "SaleList", extentForFloorMarketPriceMap.get(key + "SaleList") / listSize);
									}
								}
								if(extentForFloorSaleJunseMap.get(key + "JunseList") != null){
									listSize = extentForFloorSaleJunseMap.get(key + "JunseList").size();
									if(listSize >= 3){
										if(extentForFloorMarketPriceMap.get(key + "JunseList") == null){
											extentForFloorMarketPriceMap.put(key + "JunseList", 0);
										}
										for(UrgentSaleDto saleDto : extentForFloorSaleJunseMap.get(key + "JunseList")){
											extentForFloorMarketPriceMap.put(key + "JunseList", extentForFloorMarketPriceMap.get(key + "JunseList") + saleDto.getUrgentPrice());
										}
										extentForFloorMarketPriceMap.put(key + "JunseList", extentForFloorMarketPriceMap.get(key + "JunseList") / listSize);
									}
								}
							}
						}
						
						// 급매인지 판단하고 저장.
						keySet = extentForFloorMarketPriceMap.keySet();
			            for(UrgentSaleDto urgentSaleDto : urgentSaleList){
			            	for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
								String key = (String) iterator.next();
								//Integer extentForFloorMarketPriceVal = extentForFloorMarketPriceMap.get(key);
								
								Integer figureFloor = 0;
					            String letterFloor = "";
					            boolean isOnlyFigure = Pattern.compile("(^[0-9]*$)").matcher(urgentSaleDto.getFloor().split("/")[0]).find();
					            
					            if(isOnlyFigure){
					            	figureFloor = Integer.parseInt(urgentSaleDto.getFloor().split("/")[0]);
					            }else{
					            	letterFloor = urgentSaleDto.getFloor().split("/")[0];
					            }
								
								if((figureFloor == 1 || "저".equals(letterFloor)) && key.indexOf("OneFloor") > -1 && key.indexOf(urgentSaleDto.getExclusiveExtent().toString()) > -1){
									if((urgentSaleDto.getUrgentPrice() + 1000) <= extentForFloorMarketPriceMap.get(key)){ // 시세 보다 낮으면(급매가차이가 1000만원 이상 날경우) insert
										if(key.indexOf("SaleList") > -1){
											urgentSaleDto.setMarketPrice(extentForFloorMarketPriceMap.get(key));
										}
										if(key.indexOf("JunseList") > -1){
											urgentSaleDto.setJunsePrice(extentForFloorMarketPriceMap.get(key));
										}
							            break;
						            }
								}else if((figureFloor < 7 || "중".equals(letterFloor)) && key.indexOf("LowFloor") > -1 && key.indexOf(urgentSaleDto.getExclusiveExtent().toString()) > -1){
									if((urgentSaleDto.getUrgentPrice() + 1000) <= extentForFloorMarketPriceMap.get(key)){ // 시세 보다 낮으면(급매가차이가 1000만원 이상 날경우) insert
										if(key.indexOf("SaleList") > -1){
											urgentSaleDto.setMarketPrice(extentForFloorMarketPriceMap.get(key));
										}
										if(key.indexOf("JunseList") > -1){
											urgentSaleDto.setJunsePrice(extentForFloorMarketPriceMap.get(key));
										}
							            break;
						            }
								}else if((figureFloor >= 7 || "고".equals(letterFloor)) && key.indexOf("RoyalFloor") > -1 && key.indexOf(urgentSaleDto.getExclusiveExtent().toString()) > -1){
									if((urgentSaleDto.getUrgentPrice() + 1000) <= extentForFloorMarketPriceMap.get(key)){ // 시세 보다 낮으면(급매가차이가 1000만원 이상 날경우) insert
										if(key.indexOf("SaleList") > -1){
											urgentSaleDto.setMarketPrice(extentForFloorMarketPriceMap.get(key));
										}
										if(key.indexOf("JunseList") > -1){
											urgentSaleDto.setJunsePrice(extentForFloorMarketPriceMap.get(key));
										}
							            urgentSaleService.insertUrgentSale(urgentSaleDto);
							            break;
						            }
								}
							}
			            }
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public JSONObject getParamValForSaleList(String apart){
		
		JSONObject returnValue = null;
		
		try{
			apart = URLEncoder.encode(apart, "utf-8");
			
			StringBuffer sb = new StringBuffer();
			
			String url = "http://land.naver.com/search/search.nhn?query=" + apart;
			
			HttpPost http = new HttpPost(url);
			
			 RequestConfig requestConfig = RequestConfig.custom()
					 .setConnectionRequestTimeout(1000 * 60 * 2)
				     .setConnectTimeout(1000 * 60 * 2)
				     .setSocketTimeout(1000 * 60 * 2)
				     .build();
			 
			 //http.setConfig(requestConfig);
			 
			  // headers
			  List<Header> headers = new ArrayList<Header>();
			  headers.add(new BasicHeader("Accept-Charset","utf-8"));
			  headers.add(new BasicHeader("Accept-Language","ko, en;q=0.8"));
			  headers.add(new BasicHeader("User-Agent","My Http Client 0.1"));
			  // create client
			  HttpClient httpClient = HttpClientBuilder.create()
			      .setDefaultRequestConfig(requestConfig)
			      .setDefaultHeaders(headers).build();
			
			//{"resultList":[{"CODE":"42","CODE_VALUE":"강원도"},{"CODE":"41","CODE_VALUE":"경기도"},{"CODE":"48","CODE_VALUE":"경상남도"},{"CODE":"47","CODE_VALUE":"경상북도"},{"CODE":"29","CODE_VALUE":"광주광역시"},{"CODE":"27","CODE_VALUE":"대구광역시"},{"CODE":"30","CODE_VALUE":"대전광역시"},{"CODE":"26","CODE_VALUE":"부산광역시"},{"CODE":"11","CODE_VALUE":"서울특별시"},{"CODE":"36","CODE_VALUE":"세종특별자치시"},{"CODE":"31","CODE_VALUE":"울산광역시"},{"CODE":"28","CODE_VALUE":"인천광역시"},{"CODE":"46","CODE_VALUE":"전라남도"},{"CODE":"45","CODE_VALUE":"전라북도"},{"CODE":"50","CODE_VALUE":"제주특별자치도"},{"CODE":"44","CODE_VALUE":"충청남도"},{"CODE":"43","CODE_VALUE":"충청북도"}]}
			//HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = httpClient.execute(http);
			HttpEntity entity = response.getEntity();
			ContentType contentType = ContentType.getOrDefault(entity);
			Charset charset = contentType.getCharset();
			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
			
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			JSONObject jsonObject = new JSONObject();
			
			String queryKey = "\"query\":";
			if(sb.toString().indexOf(queryKey) > -1){
				int startQueryVal = sb.toString().indexOf(queryKey) + queryKey.length() + 1;
				int endQueryVal = startQueryVal + sb.toString().substring(startQueryVal).indexOf("\"");
				String queryVal = sb.substring(startQueryVal, endQueryVal);
				jsonObject.put("query", queryVal);
			}else{
				jsonObject.put("query", "");
			}
			
			String complexCodeKey = "\"complexCode\":";
			if(sb.toString().indexOf(complexCodeKey) > -1){
				int startComplexCodeVal = sb.toString().indexOf(complexCodeKey) + complexCodeKey.length() + 1;
				int endComplexCodeVal = startComplexCodeVal + sb.toString().substring(startComplexCodeVal).indexOf("\"");
				String complexCodeVal = sb.substring(startComplexCodeVal, endComplexCodeVal);
				jsonObject.put("complexCode", complexCodeVal);
			}else{
				jsonObject.put("complexCode", "");
			}
			
			String complexQueryKey = "\"complexQuery\":";
			if(sb.toString().indexOf(complexQueryKey) > -1){
				int startComplexQueryVal = sb.toString().indexOf(complexQueryKey) + complexQueryKey.length() + 1;
				int endComplexQueryVal = startComplexQueryVal + sb.toString().substring(startComplexQueryVal).indexOf("\"");
				String complexQueryVal = sb.substring(startComplexQueryVal, endComplexQueryVal);
				jsonObject.put("complexQuery", complexQueryVal);
			}else{
				jsonObject.put("complexQuery", "");
			}
			
			String regionCodeKey = "\"regionCode\":";
			if(sb.toString().indexOf(regionCodeKey) > -1){
				int startRegionCodeVal = sb.toString().indexOf(regionCodeKey) + regionCodeKey.length() + 1;
				int endRegionCodeVal = startRegionCodeVal + sb.toString().substring(startRegionCodeVal).indexOf("\"");
				String regionCodeVal = sb.substring(startRegionCodeVal, endRegionCodeVal);
				jsonObject.put("regionCode", regionCodeVal);
			}else{
				jsonObject.put("regionCode", "");
			}
			
			String regionTypeKey = "\"regionType\":";
			if(sb.toString().indexOf(regionTypeKey) > -1){
				int startRegionTypeVal = sb.toString().indexOf(regionTypeKey) + regionTypeKey.length() + 1;
				int endRegionTypeVal = startRegionTypeVal + sb.toString().substring(startRegionTypeVal).indexOf("\"");
				String regionTypeVal = sb.substring(startRegionTypeVal, endRegionTypeVal);
				jsonObject.put("regionType", regionTypeVal);
			}else{
				jsonObject.put("regionType", "");
			}
			
			String isFirstSiteArticleTrue = "\"isFirstSiteArticle\":true";
			String isFirstSiteArticleFalse = "\"isFirstSiteArticle\":false";
			if(sb.indexOf(isFirstSiteArticleTrue) > -1){
				jsonObject.put("isFirstSiteArticle", true);
			}else if(sb.indexOf(isFirstSiteArticleFalse) > -1){
				jsonObject.put("isFirstSiteArticle", false);
			}
			
			String isComplexRegionTrue = "\"isComplexRegion\":true";
			String isComplexRegionFalse = "\"isComplexRegion\":false";
			if(sb.indexOf(isComplexRegionTrue) > -1){
				jsonObject.put("isComplexRegion", true);
			}else if(sb.indexOf(isComplexRegionFalse) > -1){
				jsonObject.put("isComplexRegion", false);
			}
			
			String isSpotSearchTrue = "\"isSpotSearch\":true";
			String isSpotSearchFalse = "\"isSpotSearch\":false";
			if(sb.indexOf(isSpotSearchTrue) > -1){
				jsonObject.put("isSpotSearch", true);
			}else if(sb.indexOf(isSpotSearchFalse) > -1){
				jsonObject.put("isSpotSearch", false);
			}
			
			String spotListKey = "\"spotList\":";
			if(sb.toString().indexOf(spotListKey) > -1){
				int startSpotListVal = sb.toString().indexOf(spotListKey) + spotListKey.length() + 1;
				int endSpotListVal = startSpotListVal + sb.toString().substring(startSpotListVal).indexOf("\"");
				String spotListVal = sb.substring(startSpotListVal, endSpotListVal);
				jsonObject.put("spotList", spotListVal);
			}else{
				jsonObject.put("spotList", "");
			}
			
			String rletTypeKey = "\"rletType\":";
			if(sb.toString().indexOf(rletTypeKey) > -1){
				int startRletTypeVal = sb.toString().indexOf(rletTypeKey) + rletTypeKey.length() + 1;
				int endRletTypeVal = startRletTypeVal + sb.toString().substring(startRletTypeVal).indexOf("\"");
				String rletTypeVal = sb.substring(startRletTypeVal, endRletTypeVal);
				jsonObject.put("rletType", rletTypeVal);
			}else{
				jsonObject.put("rletType", "");
			}
			
			String tradeTypeKey = "\"tradeType\":";
			if(sb.toString().indexOf(tradeTypeKey) > -1){
				int startTradeTypeVal = sb.toString().indexOf(tradeTypeKey) + tradeTypeKey.length() + 1;
				int endTradeTypeVal = startTradeTypeVal + sb.toString().substring(startTradeTypeVal).indexOf("\"");
				String tradeTypeVal = sb.substring(startTradeTypeVal, endTradeTypeVal);
				jsonObject.put("tradeType", tradeTypeVal);
			}else{
				jsonObject.put("tradeType", "");
			}
			
			String confirmDateKey = "\"confirmDate\":";
			if(sb.toString().indexOf(confirmDateKey) > -1){
				int startConfirmDateVal = sb.toString().indexOf(confirmDateKey) + confirmDateKey.length() + 1;
				int endConfirmDateVal = startConfirmDateVal + sb.toString().substring(startConfirmDateVal).indexOf("\"");
				String confirmDateVal = sb.substring(startConfirmDateVal, endConfirmDateVal);
				jsonObject.put("confirmDate", confirmDateVal);
			}else{
				jsonObject.put("confirmDate", "");
			}
			
			
			
			
			//System.out.println(sb.toString());
			
			returnValue = jsonObject;
		}catch(Exception e){
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			return getParamValForSaleList(apart);
		}
		
		return returnValue;
	}
	
	public JSONObject getSaleList(JSONObject paramForSaleList, Integer page, String tradeType, String confirmDate){
	
		JSONObject returnValue = null;
		
		try{
			StringBuffer sb = new StringBuffer();
			
			
			/*
			 	confirmDate=
				D0 오늘
				D7 일주일
				M1  1달
				없음 :   전체
			*/
			
			//지금 confirmDate 디폴트 값으로 7일 목록을 가지고 오도록 되어 있음.
			
			String url = "http://land.naver.com/search/articleSearch.nhn?query=" + URLEncoder.encode(paramForSaleList.getString("query"), "utf-8") + "&regionCode=" + paramForSaleList.getString("regionCode") + "&regionType=" + paramForSaleList.getString("regionType") + "&sortOption=confirmDate.dsc&isFirstSiteArticle=" + paramForSaleList.getString("isFirstSiteArticle") + "&isComplexRegion=" + paramForSaleList.getString("isComplexRegion") + "&isSpotSearch=" + paramForSaleList.getString("isSpotSearch") + "&spotList=" + paramForSaleList.getString("spotList") + "&complexQuery=" + URLEncoder.encode(paramForSaleList.getString("complexQuery"), "utf-8") + "&tradeType=" + tradeType + "&page=" + page + "&confirmDate=" + confirmDate;
			//url += "&price=self&minPrice=1000&maxPrice=10000000&size=S02" 여기서 사이즈는 부동산 면적이다. 네이버 참고;
			//http://land.naver.com/search/article.nhn?query=%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C%20%EC%9A%A9%EC%82%B0%EA%B5%AC%20%EC%9D%B4%EC%B4%8C%EB%8F%99%20%EC%9D%B4%EC%B4%8C%EC%9A%B0%EC%84%B1#%7B%22tradeType%22%3A%22A1%22%2C%22price%22%3A%22%22%2C%22size%22%3A%22%22%2C%22confirmDate%22%3A%22D7%22%7D
			
			System.out.println(url);
			
			HttpPost http = new HttpPost(url);
			
			 RequestConfig requestConfig = RequestConfig.custom()
					 .setConnectionRequestTimeout(1000 * 60 * 2)
				     .setConnectTimeout(1000 * 60 * 2)
				     .setSocketTimeout(1000 * 60 * 2)
				     .build();
			 
			 //http.setConfig(requestConfig);
			 
			 // headers
			  List<Header> headers = new ArrayList<Header>();
			  headers.add(new BasicHeader("Accept-Charset","utf-8"));
			  headers.add(new BasicHeader("Accept-Language","ko, en;q=0.8"));
			  headers.add(new BasicHeader("User-Agent","My Http Client 0.1"));
			  // create client
			  HttpClient httpClient = HttpClientBuilder.create()
			      .setDefaultRequestConfig(requestConfig)
			      .setDefaultHeaders(headers).build();
			
			//{"resultList":[{"CODE":"42","CODE_VALUE":"강원도"},{"CODE":"41","CODE_VALUE":"경기도"},{"CODE":"48","CODE_VALUE":"경상남도"},{"CODE":"47","CODE_VALUE":"경상북도"},{"CODE":"29","CODE_VALUE":"광주광역시"},{"CODE":"27","CODE_VALUE":"대구광역시"},{"CODE":"30","CODE_VALUE":"대전광역시"},{"CODE":"26","CODE_VALUE":"부산광역시"},{"CODE":"11","CODE_VALUE":"서울특별시"},{"CODE":"36","CODE_VALUE":"세종특별자치시"},{"CODE":"31","CODE_VALUE":"울산광역시"},{"CODE":"28","CODE_VALUE":"인천광역시"},{"CODE":"46","CODE_VALUE":"전라남도"},{"CODE":"45","CODE_VALUE":"전라북도"},{"CODE":"50","CODE_VALUE":"제주특별자치도"},{"CODE":"44","CODE_VALUE":"충청남도"},{"CODE":"43","CODE_VALUE":"충청북도"}]}
			//HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = httpClient.execute(http);
			HttpEntity entity = response.getEntity();
			ContentType contentType = ContentType.getOrDefault(entity);
			Charset charset = contentType.getCharset();
			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
			
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			
			System.out.println(paramForSaleList.getString("query"));
			
			//System.out.println(sb.toString());
			
			returnValue = JSONObject.fromObject(sb.toString());
		}catch(Exception e){
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			return getSaleList(paramForSaleList, page, tradeType, confirmDate);
		}
		
		return returnValue;
	}


}
