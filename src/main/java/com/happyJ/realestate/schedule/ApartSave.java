package com.happyJ.realestate.schedule;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.stereotype.Service;

import com.happyJ.realestate.dto.ApartDto;
import com.happyJ.realestate.dto.RegionDto;
import com.happyJ.realestate.service.ApartService;
import com.happyJ.realestate.service.RegionService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

//@Repository
@Service
public class ApartSave  extends QuartzJobBean{

	private RegionService regionService;
	private ApartService apartService;
	private ApplicationContext ctx;
	

	@Override
	protected void executeInternal(JobExecutionContext ex) throws JobExecutionException {
		System.out.println("전국 모든 지역 및 모든 아파트 Insert 잡  : " + System.currentTimeMillis());
		
		ctx = (ApplicationContext)ex.getJobDetail().getJobDataMap().get("applicationContext2");
		regionService = (RegionService)ctx.getBean("RegionService");  
		apartService = (ApartService)ctx.getBean("ApartService");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String currYyyyMM = sdf.format(new Date());
		
		try{
			JSONObject sidoJsonList = getRegionOrApartList("region", null, "SIDO", null);
			JSONArray sidoJsonArray = JSONArray.fromObject(JSONSerializer.toJSON(sidoJsonList.get("resultList")));        
	        for(int sidoIndex=0; sidoIndex<sidoJsonArray.size(); sidoIndex++){
	            JSONObject sidoJson = JSONObject.fromObject(sidoJsonArray.get(sidoIndex));
	            RegionDto sidoRegionDto = new RegionDto();
	            sidoRegionDto.setuRegionId(sidoJson.getLong("CODE"));
	            sidoRegionDto.setSido(sidoJson.getString("CODE_VALUE"));
	            regionService.insertRegion(sidoRegionDto);
	            
	            JSONObject sggJsonList = getRegionOrApartList("region", sidoRegionDto.getuRegionId(), "SGG", null);
	            JSONArray sggJsonArray = JSONArray.fromObject(JSONSerializer.toJSON(sggJsonList.get("resultList")));        
	            for(int sggIndex=0; sggIndex<sggJsonArray.size(); sggIndex++){
		            JSONObject sggJson = JSONObject.fromObject(sggJsonArray.get(sggIndex));
		            RegionDto sggRegionDto = new RegionDto();
		            sggRegionDto.setuRegionId(Long.parseLong(sidoJson.getString("CODE") + sggJson.getString("CODE")));
		            sggRegionDto.setpURegionId(sidoRegionDto.getuRegionId());
		            sggRegionDto.setSido(sidoJson.getString("CODE_VALUE"));
		            sggRegionDto.setSgg(sggJson.getString("CODE_VALUE"));
		            regionService.insertRegion(sggRegionDto);
		            
		            JSONObject emdJsonList = getRegionOrApartList("region", sggRegionDto.getuRegionId(), "EMD", null);
		            JSONArray emdJsonArray = JSONArray.fromObject(JSONSerializer.toJSON(emdJsonList.get("resultList")));        
		            for(int emdIndex=0; emdIndex<emdJsonArray.size(); emdIndex++){
			            JSONObject emdJson = JSONObject.fromObject(emdJsonArray.get(emdIndex));
			            RegionDto emdRegionDto = new RegionDto();
			            emdRegionDto.setuRegionId(Long.parseLong(sidoJson.getString("CODE") + sggJson.getString("CODE") + emdJson.getString("CODE")));
			            emdRegionDto.setpURegionId(sggRegionDto.getuRegionId());
			            emdRegionDto.setSido(sidoJson.getString("CODE_VALUE"));
			            emdRegionDto.setSgg(sggJson.getString("CODE_VALUE"));
			            emdRegionDto.setEmd(emdJson.getString("CODE_VALUE"));
			            regionService.insertRegion(emdRegionDto);
			            
			            JSONObject riJsonList = getRegionOrApartList("region", emdRegionDto.getuRegionId(), "RI", null);
			            JSONArray riJsonArray = JSONArray.fromObject(JSONSerializer.toJSON(riJsonList.get("resultList")));        
			            if(riJsonArray.size() > 0){
			            	for(int riIndex=0; riIndex<riJsonArray.size(); riIndex++){
					            JSONObject riJson = JSONObject.fromObject(riJsonArray.get(riIndex));
					            RegionDto riRegionDto = new RegionDto();
					            riRegionDto.setuRegionId(Long.parseLong(sidoJson.getString("CODE") + sggJson.getString("CODE") + emdJson.getString("CODE") + riJson.getString("CODE")));
					            riRegionDto.setpURegionId(emdRegionDto.getuRegionId());
					            riRegionDto.setSido(sidoJson.getString("CODE_VALUE"));
					            riRegionDto.setSgg(sggJson.getString("CODE_VALUE"));
					            riRegionDto.setEmd(emdJson.getString("CODE_VALUE"));
					            riRegionDto.setRi(riJson.getString("CODE_VALUE"));
					            regionService.insertRegion(riRegionDto);
					            
					            JSONObject apartJsontList = getRegionOrApartList("apart", riRegionDto.getuRegionId(), null, currYyyyMM);
					        	JSONArray apartJsonArray = JSONArray.fromObject(JSONSerializer.toJSON(apartJsontList.get("resultList")));
					        	
					        	System.out.println("apartJsonArray.size() : " + apartJsonArray.size());
					        	
								for(int apartIndex=0; apartIndex<apartJsonArray.size(); apartIndex++){
									
						            JSONObject apart = JSONObject.fromObject(apartJsonArray.get(apartIndex));
						            
						            ApartDto apartDto = new ApartDto();
						            apartDto.setuReId(apart.getString("KAPT_CODE"));
						            apartDto.setpURegionId(apart.getLong("BJD_CODE"));
						            String[] regionUint = apart.getString("BJD_NAME").split(" ");
						            apartDto.setSido(regionUint[0]);
						            apartDto.setSgg(regionUint[1]);
						            apartDto.setEmd(regionUint[2]);
						            if(regionUint.length > 3){
						            	apartDto.setRi(regionUint[3]);
						            }
						            apartDto.setReName(apart.getString("KAPT_NAME"));
						            apartDto.setReType("A");
						            
						            //TODO 공동주택관리정보시스템에서는 면적에 대한 상세정보를 제공해 주지 않으므로 다른 것을 찾아 나중에 세팅하도록 한다. 나중에 kapt_code컬럼과 주차장을 지상,지하로 분리했는데 필요 없다면 해당 컬럼을 하나로 통일한다. 아파트 추가 정보는 이 쿼츠말고다른 쿼츠에서 실행하는 것이 더 안 전할 것 같다.안 그러면 데이터 양이 너무 많아져서 뻗어버릴 지도..
						            JSONObject apartDetailjson = getRegionOrApartList("apartDetail", apartDto.getuReId(), null, null);
						            
						            if(apartDetailjson != null){
						            	JSONObject apartDetail1 = JSONObject.fromObject(apartDetailjson.get("resultMap_kapt"));
						            	JSONArray apartDetail2JsonArray = JSONArray.fromObject(apartDetailjson.get("resultMap_kapt_areacnt"));
						            	
						            	if(apartDetail1 != null){
						            		apartDto.setUpParkingCnt(apartDetail1.getString("KAPTD_PCNT"));
						            		apartDto.setDownParkingCnt(apartDetail1.getString("KAPTD_PCNTU"));
						            		apartDto.setDongCnt(apartDetail1.getString("KAPT_DONG_CNT"));
						            	}
						            	
							            apartDto.setHeatingType(apartDetail1.getString("CODE_HEAT"));
							            //apartDto.setHeatingFuel(heatingFuel);
							            apartDto.setConstructionFirm(apartDetail1.getString("KAPT_ACOMPANY"));
							            
							            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							            Date date = dateFormat.parse(apartDetail1.getString("KAPT_USEDATE"));
							            apartDto.setCompletionDate(date);
							            
							            int houseCnt = 0;
							            StringBuffer sb = new StringBuffer();
							            for(int areaIndex=0; areaIndex<apartDetail2JsonArray.size(); areaIndex++){
						            		switch (areaIndex) {
											case 0:
												sb.append("60m2 이하 : ");
												break;
											case 1:
												sb.append("60~85m2 이하 : ");
												break;
											case 2:
												sb.append("85~135m2 이하 : ");
												break;
											case 3:
												sb.append("135m2 초과 : ");
												break;
											default:
												break;
											}
						            		
						            		String kaptdaCnt = ((JSONObject)apartDetail2JsonArray.get(areaIndex)).getString("KAPTDA_CNT");
						            		
						            		if("null".equals(kaptdaCnt)){
						            			sb.append("없음" + (areaIndex!=(apartDetail2JsonArray.size()-1) ?  ", " : ""));
						            		}else{
						            			sb.append(kaptdaCnt + (areaIndex!=(apartDetail2JsonArray.size()-1) ?  ", " : ""));
						            			houseCnt += Integer.parseInt(kaptdaCnt);
						            		}
							            }
							            
							            apartDto.setHouseCnt(houseCnt + "");
							            
							            //apartDto.setX(x);
							            //apartDto.setY(x);
							            apartDto.setHallType(apartDetail1.getString("CODE_HALL"));

							            apartDto.setExtent(sb.toString());
							            //apartDto.setFloorAreaRatio();//용적률
							            //apartDto.setCoverRatio(apartDetail1.getInt("KAPT_DONG_CNT")); /건폐율
							            //apartDto.setTopFloor(apartDetail1.getInt("KAPT_DONG_CNT"));
							            //apartDto.setBottomFloor(apartDetail1.getInt("KAPT_DONG_CNT"));
							            apartDto.setManageOfficePhone(apartDetail1.getString("KAPT_TEL"));
						            }
						            
						            apartService.insertApart(apartDto);
						        }
					            
					        }
			            }else{
			            	JSONObject apartJsontList = getRegionOrApartList("apart", emdRegionDto.getuRegionId(), null, currYyyyMM);
				        	JSONArray apartJsonArray = JSONArray.fromObject(JSONSerializer.toJSON(apartJsontList.get("resultList")));        
						    
							for(int apartIndex=0; apartIndex<apartJsonArray.size(); apartIndex++){
					            JSONObject apart = JSONObject.fromObject(apartJsonArray.get(apartIndex));
					            
					            ApartDto apartDto = new ApartDto();
					            apartDto.setuReId(apart.getString("KAPT_CODE"));
					            apartDto.setpURegionId(apart.getLong("BJD_CODE"));
					            String[] regionUint = apart.getString("BJD_NAME").split(" ");
					            apartDto.setSido(regionUint[0]);
					            apartDto.setSgg(regionUint[1]);
					            apartDto.setEmd(regionUint[2]);
					            if(regionUint.length > 3){
					            	apartDto.setRi(regionUint[3]);
					            }
					            
					            apartDto.setReName(apart.getString("KAPT_NAME"));
					            apartDto.setReType("A");

					          //TODO 공동주택관리정보시스템에서는 면적에 대한 상세정보를 제공해 주지 않으므로 다른 것을 찾아 나중에 세팅하도록 한다. 나중에 kapt_code컬럼과 주차장을 지상,지하로 분리했는데 필요 없다면 해당 컬럼을 하나로 통일한다. 아파트 추가 정보는 이 쿼츠말고다른 쿼츠에서 실행하는 것이 더 안 전할 것 같다.안 그러면 데이터 양이 너무 많아져서 뻗어버릴 지도..
					            JSONObject apartDetailjson = getRegionOrApartList("apartDetail", apartDto.getuReId(), null, null);
					            
					            if(apartDetailjson != null){
					            	JSONObject apartDetail1 = JSONObject.fromObject(apartDetailjson.get("resultMap_kapt"));
					            	JSONArray apartDetail2JsonArray = JSONArray.fromObject(apartDetailjson.get("resultMap_kapt_areacnt"));	
					            	
					            	if(apartDetail1 != null){
					            		apartDto.setUpParkingCnt(apartDetail1.getString("KAPTD_PCNT"));
					            		apartDto.setDownParkingCnt(apartDetail1.getString("KAPTD_PCNTU"));
					            		apartDto.setDongCnt(apartDetail1.getString("KAPT_DONG_CNT"));
					            	}
						            
						            apartDto.setHeatingType(apartDetail1.getString("CODE_HEAT"));
						            //apartDto.setHeatingFuel(heatingFuel);
						            apartDto.setConstructionFirm(apartDetail1.getString("KAPT_ACOMPANY"));
						            
						            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						            Date date = dateFormat.parse(apartDetail1.getString("KAPT_USEDATE"));
						            apartDto.setCompletionDate(date);
						            
						            int houseCnt = 0;
						            StringBuffer sb = new StringBuffer();
						            for(int areaIndex=0; areaIndex<apartDetail2JsonArray.size(); areaIndex++){
					            		switch (areaIndex) {
										case 0:
											sb.append("60m2 이하 : ");
											break;
										case 1:
											sb.append("60~85m2 이하 : ");
											break;
										case 2:
											sb.append("85~135m2 이하 : ");
											break;
										case 3:
											sb.append("135m2 초과 : ");
											break;
										default:
											break;
										}
					            		
					            		String kaptdaCnt = ((JSONObject)apartDetail2JsonArray.get(areaIndex)).getString("KAPTDA_CNT");
					            		
					            		if("null".equals(kaptdaCnt)){
					            			sb.append("없음" + (areaIndex!=(apartDetail2JsonArray.size()-1) ?  ", " : ""));
					            		}else{
					            			sb.append(kaptdaCnt + (areaIndex!=(apartDetail2JsonArray.size()-1) ?  ", " : ""));
					            			houseCnt += Integer.parseInt(kaptdaCnt);
					            		}
						            }
						            
						            apartDto.setHouseCnt(houseCnt + "");
						            apartDto.setExtent(sb.toString());
						            
						            //apartDto.setX(x);
						            //apartDto.setY(x);
						            apartDto.setHallType(apartDetail1.getString("CODE_HALL"));
						            
						            //apartDto.setFloorAreaRatio();//용적률
						            //apartDto.setCoverRatio(apartDetail1.getInt("KAPT_DONG_CNT")); /건폐율
						            //apartDto.setTopFloor(apartDetail1.getInt("KAPT_DONG_CNT"));
						            //apartDto.setBottomFloor(apartDetail1.getInt("KAPT_DONG_CNT"));
						            apartDto.setManageOfficePhone(apartDetail1.getString("KAPT_TEL"));
					            }
					            
					            apartService.insertApart(apartDto);
					        }
			            }
			        }
		        }
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public JSONObject getRegionOrApartList(String type, Object code, String regionUnit, String searchDate){
		
		JSONObject returnValue = null;
		
		try{
			StringBuffer sb = new StringBuffer();
			
			String url = "";
			if(type.equals("region")){ //지역
				url = "http://www.k-apt.go.kr/cmmn/bjd/getBjdList.do?bjd_code=" + (code==null?"":(Long)code) + "&bjd_gbn=" + (regionUnit==null?"":regionUnit);
			}else if(type.equals("apart")){ // 아파트 목록
				url = "http://www.k-apt.go.kr/kaptinfo/getKaptList.do?bjd_code=" + (code==null?"":(Long)code) + "&search_date=" + (searchDate==null?"":searchDate);
				System.out.println("url : " + url);
			}else{ //아파트 상세보기
				url = "http://www.k-apt.go.kr/kaptinfo/getKaptInfo_detail.do?kapt_code=" + (code==null?"":(String)code);
				
				System.out.println("url : " + url);
			}
			
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
			
			returnValue = JSONObject.fromObject(sb.toString());
		}catch(Exception e){
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			return getRegionOrApartList(type, code, regionUnit, searchDate);
		}
		
		return returnValue;
	}

}
