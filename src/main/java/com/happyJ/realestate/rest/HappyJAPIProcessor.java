/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 26. yongpal
*****************************************************************************/
package com.happyJ.realestate.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.happyJ.realestate.common.message.ResultCodeEnum;
import com.happyJ.realestate.common.message.SmsMessageEnum;
import com.happyJ.realestate.common.util.FileUploadUtils;
import com.happyJ.realestate.dao.FileDao;
import com.happyJ.realestate.model.custom.ApplyCustomDto;
import com.happyJ.realestate.model.custom.EvidenceCustomDto;
import com.happyJ.realestate.model.protocol.APILoginResponse;
import com.happyJ.realestate.model.protocol.GetConfigResponse;
import com.happyJ.realestate.model.protocol.ItemCheckResponse;
import com.happyJ.realestate.model.protocol.ItemListResponse;
import com.happyJ.realestate.model.protocol.ItemUpdateResponse;
import com.happyJ.realestate.model.protocol.SyncCctvResponse;
import com.happyJ.realestate.model.protocol.body.ItemCheckBody;
import com.happyJ.realestate.model.protocol.body.ItemList;
import com.happyJ.realestate.model.protocol.body.ItemUpdateBody;
import com.happyJ.realestate.model.protocol.body.LoginBody;
import com.happyJ.realestate.model.protocol.body.RealPriceBody;
import com.happyJ.realestate.model.protocol.body.SyncCctvBody;
import com.happyJ.realestate.model.protocol.body.VideoInfos;
import com.happyJ.realestate.model.protocol.header.ResponseHeader;
import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.model.schema.ImageFileDto;
import com.happyJ.realestate.model.schema.ItemLatlngDto;
import com.happyJ.realestate.model.schema.LogDto;
import com.happyJ.realestate.model.schema.RealPriceDto;
import com.happyJ.realestate.model.schema.UserDto;
import com.happyJ.realestate.service.ApplyService;
import com.happyJ.realestate.service.ConfigService;
import com.happyJ.realestate.service.EvidenceService;
import com.happyJ.realestate.service.LogService;
import com.happyJ.realestate.service.MapService;
import com.happyJ.realestate.service.SmsSendService;
import com.happyJ.realestate.service.UserService;
import com.happyJ.realestate.web.common.Config;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.rest
 *  @fileName : VMSLinkProcessor.java
 *  @author : yongpal
 *  @since 2016. 7. 26.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 7. 26.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 7. 26.        yongpal       create VMSLinkProcessor.java
 *  </pre>
 ******************************************************************************/
@Controller
public class HappyJAPIProcessor {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("#{config['video.file.path']}")
	private String videoFilePath;
	
	@Value("#{config['use.location.code']}")
	private String location;
	
	@Value("#{config['cctvimage.file.path']}")
	private String cctvimagefilePath;
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private EvidenceService evidenceService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SmsSendService smsService;
	
	@Autowired
	private ConfigService configService;
	
	@Autowired
	private MapService mapService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private FileDao fileDao;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method vmsLogin 
	 *  @param userId
	 *  @param passwd
	 *  @return
	 **********************************************/
	@RequestMapping(value="/api/login.do")
	public @ResponseBody APILoginResponse vmsLogin(@RequestParam String userId, @RequestParam String passwd){
		
		APILoginResponse response = new APILoginResponse();
		ResponseHeader header = new ResponseHeader();
		
		UserDto userDto = new UserDto();
		userDto.setUserId(userId);
		userDto.setPassword(passwd);
		userDto.setLevel("8");
		
		try {
			LoginBody body = userService.selectUserInfoForAPI(userDto);
			if (body != null){
				
				response.setBody(body);
				
				header.setResultCode(ResultCodeEnum.RESULT_SUCCESS.getResultCode());
				header.setResultMsg(ResultCodeEnum.RESULT_SUCCESS.getReusltMsg());
				
			} else {
				header.setResultCode(ResultCodeEnum.RESULT_PERM_DENY.getResultCode());
				header.setResultMsg(ResultCodeEnum.RESULT_PERM_DENY.getReusltMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			header.setResultCode(ResultCodeEnum.RESULT_LOGIN_FAIL.getResultCode());
			header.setResultMsg(ResultCodeEnum.RESULT_LOGIN_FAIL.getReusltMsg());
		}
		
		response.setHeader(header);
		
		return response;
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method getItemList 
	 *  @param userId
	 *  @param passwd
	 *  @return
	 **********************************************/
	@RequestMapping(value="/api/itemList.do")
	public synchronized @ResponseBody ItemListResponse getItemList(HttpServletRequest request, @RequestParam String userId, @RequestParam String passwd,
			@RequestParam(value="server",required=false , defaultValue="") String server){
		
		ItemListResponse response = new ItemListResponse();
		ResponseHeader header = new ResponseHeader();
		
		try {
			
			UserDto userDto = new UserDto();
			userDto.setUserId(userId);
			userDto.setPassword(passwd);
			userDto.setLevel("9");
			
			LoginBody userInfo = userService.selectUserInfoForAPI(userDto);

			if (userInfo != null){
				ApplyCustomDto selectApplyDto = new ApplyCustomDto();
				selectApplyDto.setItemExportPollingIp(server);
				selectApplyDto.setIsDualVMS(Config.getFnConfig("DUAL_VMS","Value"));
				List<ItemList> itemList = applyService.selectApplyListForAPI(selectApplyDto);
				
				//ExportMonitorPolling 시간 업데이트
				String toDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
				ConfigDto configDto = new ConfigDto();
				configDto.setItemName("ExportMonitorPollingLastDate");
				configDto.setItemValue(toDay);
				configService.updateConfigInfo(configDto);
				
				if (itemList.isEmpty()){
					header.setResultCode(ResultCodeEnum.RESULT_SUCCESS.getResultCode());
					header.setResultMsg(ResultCodeEnum.RESULT_SUCCESS.getReusltMsg());
					
				} else {
					
					for(ItemList item : itemList){
						
						List<VideoInfos> videoInfosList = new ArrayList<VideoInfos>();
						String[] videoId = item.getVideoInfo().split(",");
						
						for(int i = 0; i < videoId.length; i++){
							String[] video = videoId[i].split("\\|");
//							logger.debug("videoId split : "+video[0]);
							VideoInfos videoInfos = new VideoInfos();
							videoInfos.setVideoId(video[1]);
							videoInfos.setVideoName(video[0]);
							videoInfosList.add(videoInfos);
						} 
						
						//접근한 아이피 업데이트
						ApplyCustomDto updateApplyDto = new ApplyCustomDto();
						updateApplyDto.setItemSerial(item.getItemSerial());
						updateApplyDto.setItemExportPollingIp(server);
						applyService.updateApplyInfo(updateApplyDto);
						
						item.setVideoInfos(videoInfosList);
					}
					
					response.setList(itemList);
					
					header.setResultCode(ResultCodeEnum.RESULT_SUCCESS.getResultCode());
					header.setResultMsg(ResultCodeEnum.RESULT_SUCCESS.getReusltMsg());
				}
			} else {
				header.setResultCode(ResultCodeEnum.RESULT_PERM_DENY.getResultCode());
				header.setResultMsg(ResultCodeEnum.RESULT_PERM_DENY.getReusltMsg());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			header.setResultCode(ResultCodeEnum.RESULT_LOGIN_FAIL.getResultCode());
			header.setResultMsg(ResultCodeEnum.RESULT_LOGIN_FAIL.getReusltMsg());
		}
		
		response.setHeader(header);
		
		return response;
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method modifyItemStatus 
	 *  @param userId
	 *  @param passwd
	 *  @param itemSerial
	 *  @param status
	 *  @return
	 **********************************************/
	@RequestMapping(value="/api/itemUpdate.do")
	public @ResponseBody ItemUpdateResponse modifyItemStatus(
			@RequestParam String userId, @RequestParam String passwd,
			@RequestParam String itemSerial, @RequestParam String status, @RequestParam String manageCodes) {
		
		ItemUpdateResponse response = new ItemUpdateResponse();
		ResponseHeader header = new ResponseHeader();
		
		UserDto userDto = new UserDto();
		userDto.setUserId(userId);
		userDto.setPassword(passwd);
		
		LoginBody userInfo = userService.selectUserInfoForAPI(userDto);
		
		if (userInfo != null){
			// update에 사용될 DTO
			ApplyCustomDto updateApplyDto = new ApplyCustomDto();
			updateApplyDto.setItemSerial(itemSerial);
			updateApplyDto.setVeiStatus(status);
			
			// 전달 받은 itemSerial의 영상 신청 정보 조회
			ApplyCustomDto applyInfo = applyService.selectApplyInfo(updateApplyDto);
			
			if (applyInfo != null){		// 신청정보가 있는지 확인
				
				try {
					
					ConfigDto configDto = new ConfigDto();
					configDto.setItemType(location);
					configDto.setItemName("SMS_CALLBACK");
					configDto = configService.selectConfigInfo(configDto);
					
					
					// 처리 상태에 따라 구분 처리
					switch (Integer.parseInt(status)) {
					case 2:	// 승인
						
						File videoFileDir = new File(videoFilePath+applyInfo.getItemSerial());
						
						if (videoFileDir.exists()){	// 영상 파일 저장 디렉토리가 존재하면
							
							// 1. Directory 전체 용량(파일크기) 확인
							final AtomicLong size = new AtomicLong();
							Path folder = Paths.get(videoFileDir.getPath());
							Files.walkFileTree(folder, new SimpleFileVisitor<Path>(){
								/* (non-Javadoc)
								 * @see java.nio.file.SimpleFileVisitor#visitFile(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
								 */
								@Override
								public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
									size.addAndGet(attrs.size());
									return FileVisitResult.CONTINUE;
								}
							});
							
							updateApplyDto.setFileuploadVol(size.toString());
							
							// 2. 영상 파일 업로드 속도 계산
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							
							Date procDate = sdf.parse(applyInfo.getProcessDate());
							Date today = new Date();
							
							long diff = today.getTime() - procDate.getTime();	// 현재 시간 - 상태 변경 시간 = 경과 시간(milliseconds)
							long speed = size.get() / (diff / 1000);	// 파일 용량 / (경과시간(milliseconds) / 1000)
							updateApplyDto.setFileuploadSpd(Long.toString(speed));
							/*
							if ((speed / 1024) > 1){
								
								updateApplyDto.setFileuploadSpd((speed / 1024)+"KByte/s");
							} else {
								updateApplyDto.setFileuploadSpd(speed+"Byte/s");
							}
							*/
							
							ConfigDto confDto = new ConfigDto();
							confDto.setItemType(location);
							confDto.setItemName("veiLimitDatetime");
							confDto = configService.selectConfigInfo(confDto);
							 
							int limitDay = 14;
							if (confDto != null) {
								limitDay = Integer.parseInt(confDto.getItemValue());
							}
							
							Calendar cal = new GregorianCalendar(Locale.KOREA);
							cal.setTime(today);
							cal.add(Calendar.DAY_OF_YEAR, limitDay); //현재일자 + limitDate
							
							SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
							updateApplyDto.setVeiDnLimitDate(sdf2.format(cal.getTime())+" 23:59:59");
							updateApplyDto.setVeiLimitDatetime(sdf2.format(cal.getTime())+" 23:59:59");
							updateApplyDto.setVeiDnLimitCount(1);
							
							// 3. SMS 전송 to User
							smsService.processSendSms(SmsMessageEnum.APP_VIDEO, applyInfo.getUserId(), applyInfo.getPhoneNum(), applyInfo.getItemSerial(), 0, configDto.getItemValue());
							
						} else {
							header.setResultCode(ResultCodeEnum.RESULT_FILE_NOT_EXSITS.getResultCode());
							header.setResultMsg(ResultCodeEnum.RESULT_FILE_NOT_EXSITS.getReusltMsg());
						}
						
						break;
					case 3:	// 반려
						updateApplyDto.setVeiRejectReason("[VMS] 반려");
						
						// SMS 전송 to User
						smsService.processSendSms(SmsMessageEnum.REJ_VIDEO, applyInfo.getUserId(), applyInfo.getPhoneNum(), applyInfo.getItemSerial(), 0, configDto.getItemValue());
						
						break;
					}
					
					if (header.getResultCode() != ResultCodeEnum.RESULT_FILE_NOT_EXSITS.getResultCode()){
						
						if (applyService.updateApplyInfo(updateApplyDto) > 0){
							
							ItemUpdateBody resItem = new ItemUpdateBody();
							resItem.setItemSerial(applyInfo.getItemSerial());
							resItem.setCryptKey(applyInfo.getVeiCryptKey());
							resItem.setVideoId(applyInfo.getVideoId());
							resItem.setVideoStart(applyInfo.getVideoStart());
							resItem.setVideoEnd(applyInfo.getVideoEnd());
							
							response.setBody(resItem);
							
							header.setResultCode(ResultCodeEnum.RESULT_SUCCESS.getResultCode());
							header.setResultMsg(ResultCodeEnum.RESULT_SUCCESS.getReusltMsg());
							
						} else {
							header.setResultCode(ResultCodeEnum.RESULT_UPDATE_FAIL.getResultCode());
							header.setResultMsg(ResultCodeEnum.RESULT_UPDATE_FAIL.getReusltMsg());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					header.setResultCode(ResultCodeEnum.RESULT_INTERNAL_ERROR.getResultCode());
					header.setResultMsg(ResultCodeEnum.RESULT_INTERNAL_ERROR.getReusltMsg());
				}
			} else {
				header.setResultCode(ResultCodeEnum.RESULT_SELECT_FAIL.getResultCode());
				header.setResultMsg(ResultCodeEnum.RESULT_SELECT_FAIL.getReusltMsg());
			}
		} else {
			header.setResultCode(ResultCodeEnum.RESULT_LOGIN_FAIL.getResultCode());
			header.setResultMsg(ResultCodeEnum.RESULT_LOGIN_FAIL.getReusltMsg());
		}
		
		response.setHeader(header);
		
		return response;
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method getConfigInfo 
	 *  @param userId
	 *  @param passwd
	 *  @return
	 **********************************************/
	@RequestMapping(value="/api/config.do")
	public @ResponseBody GetConfigResponse getConfigInfo(@RequestParam String userId, @RequestParam String passwd){
		
		GetConfigResponse response = new GetConfigResponse();
		ResponseHeader header = new ResponseHeader();
		
		UserDto userDto = new UserDto();
		userDto.setUserId(userId);
		userDto.setPassword(passwd);
		userDto.setLevel("9");
		
		LoginBody userInfo = userService.selectUserInfoForAPI(userDto);
		
		if (userInfo != null){
			
			ConfigDto configDto = new ConfigDto();
			configDto.setItemType(location);
			
			List<ConfigDto> configList = configService.selectConfigListForAPI(configDto);
			Map<String, String> map = new HashMap<String, String>();
			
			for(ConfigDto configInfo : configList){
				map.put(configInfo.getItemName(), configInfo.getItemValue());
			}
			
			response.setBody(map);
			
			header.setResultCode(ResultCodeEnum.RESULT_SUCCESS.getResultCode());
			header.setResultMsg(ResultCodeEnum.RESULT_SUCCESS.getReusltMsg());
			
		} else {
			header.setResultCode(ResultCodeEnum.RESULT_PERM_DENY.getResultCode());
			header.setResultMsg(ResultCodeEnum.RESULT_PERM_DENY.getReusltMsg());
		}
		
		response.setHeader(header);
		
		return response;
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method syncCctvImage 
	 *  @param imageFileDto
	 *  @return
	 *  @throws Exception
	 **********************************************/
	@RequestMapping(value="/api/cctvImageSync.do")
	public @ResponseBody SyncCctvResponse syncCctvImage(
			//@RequestParam String manageCode,
			//@RequestPart(value="imageFile",required=false ) MultipartFile imageFile
			ImageFileDto imageFileDto) throws Exception{
		
		SyncCctvResponse response = new SyncCctvResponse();
		ResponseHeader header = new ResponseHeader();
		
	
		if (imageFileDto.getImageFile() != null) {
			if (imageFileDto.getImageFile().getSize() > 0) {
				try {
					SyncCctvBody body = new SyncCctvBody();
					
					HashMap<String, String> fileInfo;
					fileInfo = FileUploadUtils.uploadCctvImageFile(imageFileDto.getImageFile(), cctvimagefilePath, imageFileDto.getManageCode());

					/*
					ImageFileDto updateImageFileDto = new ImageFileDto();
					updateImageFileDto.setManageCode( imageFileDto.getManageCode());
					updateImageFileDto.setImageName(fileInfo.get("uploadFileName"));
					
					// ves_item_latlng update
					if (fileDao.updateCctvImageFile(updateImageFileDto) > 0) {
						body.setManageCode( imageFileDto.getManageCode());
						body.setImageName(fileInfo.get("uploadFileName"));
						
						header.setResultCode(ResultCodeEnum.RESULT_SUCCESS.getResultCode());
						header.setResultMsg(ResultCodeEnum.RESULT_SUCCESS.getReusltMsg());
						
					} else {
						body.setManageCode( imageFileDto.getManageCode());
						
						header.setResultCode(ResultCodeEnum.RESULT_UPDATE_FAIL.getResultCode());
						header.setResultMsg(ResultCodeEnum.RESULT_UPDATE_FAIL.getReusltMsg());
					}
					*/
					
					// 그냥 파일만 받는다...
					body.setImageName(fileInfo.get("uploadFileName"));
					response.setBody(body);
					header.setResultCode(ResultCodeEnum.RESULT_SUCCESS.getResultCode());
					header.setResultMsg(ResultCodeEnum.RESULT_SUCCESS.getReusltMsg());
	
				} catch (Exception e) {
					e.printStackTrace();
					header.setResultCode(ResultCodeEnum.RESULT_FILE_ERROR.getResultCode());
					header.setResultMsg(ResultCodeEnum.RESULT_FILE_ERROR.getReusltMsg());
				}
			} else {
				/*
				//파일이 없으면 null 업데이트
				ImageFileDto updateImageFileDto = new ImageFileDto();
				updateImageFileDto.setManageCode( imageFileDto.getManageCode());
				updateImageFileDto.setImageName(null);
				fileDao.updateCctvImageFile(updateImageFileDto);
				*/
				
				header.setResultCode(ResultCodeEnum.RESULT_SUCCESS.getResultCode());
				header.setResultMsg(ResultCodeEnum.RESULT_SUCCESS.getReusltMsg());
			}
		} else {
			/*
			//파일이 없으면 null 업데이트
			ImageFileDto updateImageFileDto = new ImageFileDto();
			updateImageFileDto.setManageCode( imageFileDto.getManageCode());
			updateImageFileDto.setImageName(null);
			fileDao.updateCctvImageFile(updateImageFileDto);
			*/
			
			header.setResultCode(ResultCodeEnum.RESULT_SUCCESS.getResultCode());
			header.setResultMsg(ResultCodeEnum.RESULT_SUCCESS.getReusltMsg());
		}
		
		response.setHeader(header);
		
		return response;
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method syncCctvInfo 
	 *  @param mode
	 *  @param cameraType
	 *  @param manageCode
	 *  @param code1
	 *  @param code2
	 *  @param lat
	 *  @param lng
	 *  @param ptzYn
	 *  @param direction
	 *  @return
	 **********************************************/
	@RequestMapping(value="/api/cctvSync.do")
	public @ResponseBody SyncCctvResponse syncCctvInfo(
			@RequestParam(value="mode",required=false , defaultValue="") String mode,
			@RequestParam(value="cameraType",required=false , defaultValue="") String cameraType,
			@RequestParam(value="manageCode",required=false , defaultValue="") String manageCode,
			@RequestParam(value="code1",required=false , defaultValue="") String code1,
			@RequestParam(value="code2",required=false , defaultValue="") String code2,
			@RequestParam(value="lat",required=false , defaultValue="0") String lat,
			@RequestParam(value="lng",required=false , defaultValue="0") String lng,
			@RequestParam(value="ptzYn",required=false , defaultValue="") String ptzYn,
			@RequestParam(value="direction",required=false , defaultValue="-1") String direction
			) {
		
		SyncCctvResponse response = new SyncCctvResponse();
		ResponseHeader header = new ResponseHeader();
		
		ItemLatlngDto latlngDto = new ItemLatlngDto();
		latlngDto.setItemType(cameraType);
		latlngDto.setManagecode(manageCode);
		latlngDto.setCode1(code1);
		latlngDto.setCode2(code2);
		
		switch (Integer.parseInt(mode)) {
		case 0: //add
			
			if (mapService.checkMarkerInfo(latlngDto) <= 0){
				
				ConfigDto configDto = new ConfigDto();
				configDto.setItemType(location);
				configDto.setItemName("DAUM_MAP_PUBLIC_KEY");
				configDto = configService.selectConfigInfo(configDto);
				
				try {
					if (lat.equals("0")|| lng.equals("0")) {
						if( code1.equals("") == false){
							if (mapService.selectCctvSyncList(latlngDto) != null) {
								ItemLatlngDto selectLatlngDto = mapService.selectCctvSyncList(latlngDto);
								latlngDto.setAddr1(selectLatlngDto.getAddr1());
								latlngDto.setAddr2(selectLatlngDto.getAddr2());
								latlngDto.setAddr3(selectLatlngDto.getAddr3());
								latlngDto.setAddr4(selectLatlngDto.getAddr4());
								latlngDto.setAddress(selectLatlngDto.getAddress());
							}
						}
					} else {
						JSONObject jsonObj = getCoordToAddr(lat, lng, configDto.getItemValue());
						latlngDto.setAddress((String) jsonObj.get("fullName"));
						latlngDto.setAddr1((String) jsonObj.get("name1"));
						latlngDto.setAddr2((String) jsonObj.get("name2"));
						latlngDto.setAddr3((String) jsonObj.get("name3"));
					}
					latlngDto.setItemLatitude(lat);
					latlngDto.setItemLongitude(lng);
					latlngDto.setPtzYn(ptzYn);
					latlngDto.setDirection(direction);
					
					if (mapService.insertMarkerInfo(latlngDto) > 0){
						
						//인서트 된 후 주소 갱신
						SyncCctvAddressUpdate(latlngDto);
						
						SyncCctvBody body = new SyncCctvBody();
						body.setManageCode(manageCode);
						body.setCode1(code1);
						body.setCode2(code2);
						
						response.setBody(body);
						
						header.setResultCode(ResultCodeEnum.RESULT_SUCCESS.getResultCode());
						header.setResultMsg(ResultCodeEnum.RESULT_SUCCESS.getReusltMsg());
					
					} else {
						header.setResultCode(ResultCodeEnum.RESULT_INSERT_FAIL.getResultCode());
						header.setResultMsg(ResultCodeEnum.RESULT_INSERT_FAIL.getReusltMsg());
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					header.setResultCode(ResultCodeEnum.RESULT_COORD_TRANS_ERROR.getResultCode());
					header.setResultMsg(ResultCodeEnum.RESULT_COORD_TRANS_ERROR.getReusltMsg());
				}
			} else {
				header.setResultCode(ResultCodeEnum.RESULT_CCTV_DUPLICATE.getResultCode());
				header.setResultMsg(ResultCodeEnum.RESULT_CCTV_DUPLICATE.getReusltMsg());
			}
			
			break;
			
		case 1: //del
			
			if (mapService.deleteMarkerInfo(latlngDto) > 0){
				SyncCctvBody body = new SyncCctvBody();
				body.setManageCode(manageCode);
				body.setCode1(code1);
				body.setCode2(code2);
				
				response.setBody(body);
				
				header.setResultCode(ResultCodeEnum.RESULT_SUCCESS.getResultCode());
				header.setResultMsg(ResultCodeEnum.RESULT_SUCCESS.getReusltMsg());
			
			} else {
				header.setResultCode(ResultCodeEnum.RESULT_DELETE_FAIL.getResultCode());
				header.setResultMsg(ResultCodeEnum.RESULT_DELETE_FAIL.getReusltMsg());
			}
			
			break;
			
		case 2:		// ves_item_latlng table all delete
			if (mapService.deleteMarkerInfo(new ItemLatlngDto()) > 0){
				
				SyncCctvBody body = new SyncCctvBody();
				body.setManageCode(manageCode);
				body.setCode1(code1);
				body.setCode2(code2);
				
				response.setBody(body);
				
				header.setResultCode(ResultCodeEnum.RESULT_SUCCESS.getResultCode());
				header.setResultMsg(ResultCodeEnum.RESULT_SUCCESS.getReusltMsg());
				
			} else {
				header.setResultCode(ResultCodeEnum.RESULT_DELETE_FAIL.getResultCode());
				header.setResultMsg(ResultCodeEnum.RESULT_DELETE_FAIL.getReusltMsg());
			}
			
			break;

		default:
			break;
		}
		
		response.setHeader(header);
		
		return response;
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : CCTV Sync 좌표값이 없는 데이터 주소 업데이트
	 *  </pre>
	 * 	@Method SyncCctvAddressUpdate 
	 *  @param latlngDto
	 **********************************************/
	public void SyncCctvAddressUpdate(ItemLatlngDto latlngDto){
		if (mapService.selectCctvSyncList(latlngDto) != null) {
			ItemLatlngDto selectLatlngDto = mapService.selectCctvSyncList(latlngDto);
			
			ItemLatlngDto updateLatlngDto = new ItemLatlngDto();
			updateLatlngDto.setAddr1(selectLatlngDto.getAddr1());
			updateLatlngDto.setAddr2(selectLatlngDto.getAddr2());
			updateLatlngDto.setAddr3(selectLatlngDto.getAddr3());
			updateLatlngDto.setAddr4(selectLatlngDto.getAddr4());
			updateLatlngDto.setAddress(selectLatlngDto.getAddress());
			
			updateLatlngDto.setCode1(selectLatlngDto.getCode1());
			
			mapService.updateCctvSyncAddress(updateLatlngDto);
		}
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : BackupViewer CGI 통신 , 엑티브X CGI 통신
	 *  </pre>
	 * 	@Method cgiItemCheckInfo 
	 *  @param request
	 *  @param downItemSerial
	 *  @param itemSerial
	 *  @param userId
	 *  @param passwd
	 *  @return
	 **********************************************/
	@RequestMapping(value="/api/itemCheck.do")
	public @ResponseBody ItemCheckResponse cgiItemCheckInfo(
			HttpServletRequest request,
			@RequestParam(value="downItemSerial",required=false , defaultValue="") String downItemSerial,
			@RequestParam(value="itemSerial",required=false , defaultValue="") String itemSerial,
			@RequestParam(value="userId",required=false , defaultValue="") String userId,
			@RequestParam(value="passwd",required=false , defaultValue="") String passwd){
		
		
		ItemCheckResponse response = new ItemCheckResponse();
		ResponseHeader header = new ResponseHeader();
		
		if (downItemSerial.equals("") == false) {
			/**
			 * 다운로드 엑티브 X
			 */
			
			ApplyCustomDto applyCustomDto = new ApplyCustomDto();
			applyCustomDto.setItemSerial(downItemSerial);
			//applyCustomDto.setVeiDnLimitCount(1);
			
			// 전달 받은 itemSerial의 영상 신청 정보 조회
			ApplyCustomDto applyInfo = applyService.selectApplyInfo(applyCustomDto);
			
			if (applyInfo != null){		// 신청정보가 있는지 확인
				try {
					String downDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
					
					// 다운로드 카운트 0으로 업데이트
					ApplyCustomDto updateApplyDto = new ApplyCustomDto();
					updateApplyDto.setItemSerial(downItemSerial);
					updateApplyDto.setVeiDnLimitCountMinus(1);
					updateApplyDto.setFiledownloadDate(downDate.toString());

					if (applyService.updateApplyInfo(updateApplyDto) > 0){
						
						header.setResultCode(ResultCodeEnum.RESULT_SUCCESS.getResultCode());
						header.setResultMsg(ResultCodeEnum.RESULT_SUCCESS.getReusltMsg());
					} else {
						header.setResultCode(ResultCodeEnum.RESULT_UPDATE_FAIL.getResultCode());
						header.setResultMsg(ResultCodeEnum.RESULT_UPDATE_FAIL.getReusltMsg());
					}
				} catch (Exception e) {
					e.printStackTrace();
					header.setResultCode(ResultCodeEnum.RESULT_INTERNAL_ERROR.getResultCode());
					header.setResultMsg(ResultCodeEnum.RESULT_INTERNAL_ERROR.getReusltMsg());
				}
			} else {
				header.setResultCode(ResultCodeEnum.RESULT_SELECT_FAIL.getResultCode());
				header.setResultMsg(ResultCodeEnum.RESULT_SELECT_FAIL.getReusltMsg());
			}
		} else if (itemSerial.equals("") == false && userId.equals("") == false ) {
			/**
			 * VESBackupViewer CGI
			 */
			try {
				UserDto userDto = new UserDto();
				userDto.setUserId(userId);
				userDto.setPassword(passwd);
				
				LoginBody userInfo = userService.selectUserInfoForAPI(userDto);

				ApplyCustomDto applyCustomDto = new ApplyCustomDto();
				applyCustomDto.setItemSerial(itemSerial);
				
				if (userInfo != null) {
					if ( ! userInfo.getUserId().equals("w4admin")) {
						applyCustomDto.setUserId(userId);
					}
				}
				
				ApplyCustomDto applyItemInfo = applyService.selectApplyInfo(applyCustomDto);
				
				Date limitDate, today;
				int headerResCode;
				String headerResMsg;
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String toDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
				today = sdf.parse(toDate);
				
				// 기본 유저
				if (userInfo != null && applyItemInfo != null) {
					
					ItemCheckBody itemCheckbody = new ItemCheckBody();
					limitDate = sdf.parse(applyItemInfo.getVeiLimitDatetime().toString());
					
					if ( ! applyItemInfo.getUserId().equals(applyItemInfo.getReqUserId())) {
						//ERROR_DENY_USER
						//RESULT_LOGIN_FAIL
						headerResCode = ResultCodeEnum.RESULT_LOGIN_FAIL.getResultCode();
						headerResMsg = String.valueOf(ResultCodeEnum.RESULT_LOGIN_FAIL.getReusltMsg());
						
						itemCheckbody.setErrorMsg("발급받으신 계정으로 로그인하시기 바랍니다.");
						response.setBody(itemCheckbody);
					}else if (Integer.parseInt(applyItemInfo.getModLimit().toString()) <= 0 && ! userInfo.getUserId().equals("w4admin")) {
						//ERROR_EXPIRE_COUNT;
						//RESULT_PLAY_CNT_OVER
						headerResCode = ResultCodeEnum.RESULT_PLAY_CNT_OVER.getResultCode();
						headerResMsg = String.valueOf(ResultCodeEnum.RESULT_PLAY_CNT_OVER.getReusltMsg());
						
						itemCheckbody.setErrorMsg("재생횟수를 초과했습니다.");
						response.setBody(itemCheckbody);
					}else if (today.compareTo(limitDate) > 0) {
						//ERROR_EXPIRE_DATE
						//RESULT_PLAY_DATE_OVER
						headerResCode = ResultCodeEnum.RESULT_PLAY_DATE_OVER.getResultCode();
						headerResMsg = String.valueOf(ResultCodeEnum.RESULT_PLAY_DATE_OVER.getReusltMsg());
						
						itemCheckbody.setErrorMsg("재생기간을 초과했습니다.");
						response.setBody(itemCheckbody);
					} else {
						//loginsert
						LogDto logDto = new LogDto();
						logDto.setItemSerial(applyItemInfo.getItemSerial());
						logDto.setIpaddr(request.getRemoteAddr());
						logDto.setLogType("0");
						
						if ( ! userInfo.getUserId().equals("w4admin")) {
							logService.insertLogInfo(logDto);
						}

						itemCheckbody.setCryptKey(applyItemInfo.getVeiCryptKey());
						itemCheckbody.setLimitPlayCount(String.valueOf(applyItemInfo.getVeiLimitCount()));
						itemCheckbody.setPlayLimitDate(applyItemInfo.getVeiLimitDatetime());
						itemCheckbody.setPlayCount(applyItemInfo.getPlayCnt());
						response.setBody(itemCheckbody);
						
						headerResCode = ResultCodeEnum.RESULT_SUCCESS.getResultCode();
						headerResMsg = String.valueOf(ResultCodeEnum.RESULT_SUCCESS.getReusltMsg());
					}
					itemCheckbody.setParameterInfo("itemSerial="+itemSerial+", userId="+userId+", passwd="+passwd);
					header.setResultCode(headerResCode);
					header.setResultMsg(headerResMsg.toString());
					
				} else {
					EvidenceCustomDto evidenceCustomDto = new EvidenceCustomDto();
					evidenceCustomDto.setItemSerial(itemSerial);
					evidenceCustomDto.setEviUserid(userId);
					evidenceCustomDto.setEviUserpass(passwd);
					evidenceCustomDto.setPermit("1"); //승인
					EvidenceCustomDto EvidenceInfo = evidenceService.selectEvidenceInfo(evidenceCustomDto);
					
					ItemCheckBody itemCheckbody = new ItemCheckBody();
					// 증거 자료 제출 유져
					if (EvidenceInfo != null) {
						
						limitDate = sdf.parse(EvidenceInfo.getEviLimitDate().toString());
						if (today.compareTo(limitDate) > 0) {
							//ERROR_EXPIRE_DATE
							//RESULT_PLAY_DATE_OVER
							headerResCode = ResultCodeEnum.RESULT_PLAY_DATE_OVER.getResultCode();
							headerResMsg = String.valueOf(ResultCodeEnum.RESULT_PLAY_DATE_OVER.getReusltMsg());
							itemCheckbody.setErrorMsg("재생기간을 초과했습니다.");
							response.setBody(itemCheckbody);
						} else {
							LogDto logDto = new LogDto();
							logDto.setItemSerial(EvidenceInfo.getItemSerial());
							logDto.setIpaddr(request.getRemoteAddr());
							logDto.setLogType("1");
							logService.insertLogInfo(logDto);
							
							ItemCheckBody itemCheck = new ItemCheckBody();
							itemCheck.setCryptKey(EvidenceInfo.getVeiCryptKey());
							itemCheck.setLimitPlayCount(EvidenceInfo.getModLimit());
							itemCheck.setPlayCount(EvidenceInfo.getPlayCount());
							itemCheck.setPlayLimitDate(EvidenceInfo.getEviLimitDate());
							
							response.setBody(itemCheck);
							
							headerResCode = ResultCodeEnum.RESULT_SUCCESS.getResultCode();
							headerResMsg = String.valueOf(ResultCodeEnum.RESULT_SUCCESS.getReusltMsg());
						}
					} else {
						headerResCode = ResultCodeEnum.RESULT_LOGIN_FAIL.getResultCode();
						headerResMsg = String.valueOf(ResultCodeEnum.RESULT_LOGIN_FAIL.getReusltMsg());

						itemCheckbody.setErrorMsg("발급받으신 계정으로 로그인하시기 바랍니다.");
						
						itemCheckbody.setParameterInfo("itemSerial="+itemSerial+", userId="+userId+", passwd="+passwd);
						response.setBody(itemCheckbody);
						
					}
					header.setResultCode(headerResCode);
					header.setResultMsg(headerResMsg.toString());
				}

			} catch (Exception e){
				e.printStackTrace();
				ItemCheckBody itemCheckbody = new ItemCheckBody();
				//RESULT_LOGIN_FAIL
				itemCheckbody.setErrorMsg("발급받으신 계정으로 로그인하시기 바랍니다.");
				
				itemCheckbody.setParameterInfo("itemSerial="+itemSerial+", userId="+userId+", passwd="+passwd);
				response.setBody(itemCheckbody);
				
				header.setResultCode(ResultCodeEnum.RESULT_INTERNAL_ERROR.getResultCode());
				header.setResultMsg(ResultCodeEnum.RESULT_INTERNAL_ERROR.getReusltMsg());
			}
		} else {
			// 파라메터 누락
			ItemCheckBody itemCheckbody = new ItemCheckBody();
			itemCheckbody.setErrorMsg("입력한 자료가 불충분합니다.");
			
			itemCheckbody.setParameterInfo("itemSerial="+itemSerial+", userId="+userId+", passwd="+passwd);
			response.setBody(itemCheckbody);

			header.setResultCode(ResultCodeEnum.RESULT_SELECT_FAIL.getResultCode());
			header.setResultMsg(ResultCodeEnum.RESULT_SELECT_FAIL.getReusltMsg());
		}

		response.setHeader(header);
		
		return response;
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method getCoordToAddr 
	 *  @param lat
	 *  @param lng
	 *  @param appKey
	 *  @return
	 **********************************************/
	private JSONObject getCoordToAddr(String lat, String lng, String appKey){
		JSONObject jsonObj = new JSONObject();
		
		try {
			String urlStr = "https://apis.daum.net/local/geo/coord2addr";
			urlStr += "?apikey="+appKey;
			urlStr += "&latitude="+lat;
			urlStr += "&longitude="+lng;
			urlStr += "&inputCoordSystem=WGS84&output=json";
			
			URL url = new URL(urlStr);
				
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			JSONParser jsonParser = new JSONParser();
			
			jsonObj = (JSONObject) jsonParser.parse(isr);
				
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		
		return jsonObj;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/api/saveNaverRealPrice.do")
	public String saveNaverRealPrice(Model model){
		

		try {
			
			HttpPost http = new HttpPost("http://land.naver.com/article/articleList.nhn?rletTypeCd=A01&tradeTypeCd=A1&rletNo=5957&cortarNo=3017011200&hscpTypeCd=A01%3AA03%3AA04&mapX=127.3920215&mapY=36.3594112&mapLevel=13&page=&articlePage=&ptpNo=&rltrId=&mnex=&bildNo=&articleOrderCode=3&cpId=&period=&prodTab=&atclNo=&atclRletTypeCd=&location=&bbs_tp_cd=&sort=&siteOrderCode=&schlCd=&tradYy=&exclsSpc=&splySpcR=&cmplYn=");
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = httpClient.execute(http);
			HttpEntity entity = response.getEntity();
			ContentType contentType = ContentType.getOrDefault(entity);
			Charset charset = contentType.getCharset();
			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
			StringBuffer sb = new StringBuffer();
			String line = "";

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			Document doc = Jsoup.parse(sb.toString());
			
			Elements thElements = doc.getElementsByTag("thead").get(0).getElementsByTag("th");
			Elements trElements = doc.getElementsByTag("tbody").get(0).getElementsByTag("tr"); 
			
			String[] crawlerInfo = {"거래","확인일자","매물명","면적","동","층","매물가(만원)","연락처"};
					
			JSONArray jsonArr = new JSONArray();
			RealPriceDto realPriceDto = new RealPriceDto();
			
			int result = 0;
			
			for(int i=0; i<trElements.size(); i+=2){
				JSONObject jsonObj = new JSONObject();
				String infoDetail = null;
				for(int y=0; y<thElements.size(); y++){
					for(String info : crawlerInfo){
						if(thElements.get(y).text().trim().indexOf(info) > -1){
							int tdIndex = y;
							if(tdIndex > 2){
								tdIndex--;
							}
							jsonObj.put(info, trElements.get(i).getElementsByTag("td").get(tdIndex).text());
							
							infoDetail = trElements.get(i).getElementsByTag("td").get(tdIndex).text();
							
							switch(info){
							case "거래" : realPriceDto.setDealType(infoDetail);
								break; 
							case "확인일자" : realPriceDto.setRegDate(infoDetail);
								break;
							case "매물명" : realPriceDto.setGoodsName(infoDetail);
								break;
							case "면적" : realPriceDto.setSize(infoDetail.split(" ")[0]);
								break;
							case "동" : realPriceDto.setDong(infoDetail);
								break;
							case "층" : realPriceDto.setFloor(infoDetail);
								break;
							case "매물가(만원)" : realPriceDto.setPrice(infoDetail.split(" ")[0]);
								break;
							case "연락처" : realPriceDto.setPhoneNumber(infoDetail);
								break;
							}
						}
					}
				}
				
				infoDetail = trElements.get(i+1).getElementsByTag("td").get(0).text();
				jsonObj.put("비고", trElements.get(i+1).getElementsByTag("td").get(0).text());
				realPriceDto.setEtc(infoDetail);
			
				System.out.println(jsonObj.toJSONString());
				
				jsonArr.add(jsonObj);
				
				result = userService.insertNaverRealPriceForAPI(realPriceDto);
			}
			System.out.println(jsonArr.toJSONString());
			
			
			if(result > 0){
				model.addAttribute("RESULT", "SUCCESS");
			}else{
				model.addAttribute("RESULT", "FAIL");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "jsonView";
	}
	
}
