/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 19. yongpal
*****************************************************************************/
package com.happyJ.realestate.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.happyJ.realestate.common.message.SmsMessageEnum;
import com.happyJ.realestate.dao.UserDao;
import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.model.schema.UserDto;
import com.happyJ.realestate.sms.SmsSendDao;
import com.happyJ.realestate.sms.SmsSendDto;

/*****************************************************************************
 * 
 ******************************************************************************/
@Service
public class SmsSendService {
	
	private static Logger logger = LoggerFactory.getLogger(SmsSendService.class);
	
	@Autowired
	private SmsSendDao smsDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ConfigService configService;
	
	@Value("#{config['use.location.code']}")
	private String location;
	
	/**********************************************
	 *  <pre>
	 *  개요 : SMS 전송 서비스
	 *  </pre>
	 * 	@Method sendSmsInfo 
	 *  @param request : 사용자 권한 및 회신번호 추출을 위한 세션 객체를 인자로 받는다.
	 *  @param subject : 메시지 제목, SmsMessageEnum의 전송 타입에 따른 제목을 인자로 받는다.
	 *  @param sendType : 발송 타입, 0: to User / 1: to Admin
	 *  @return
	 **********************************************/
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method processSendSms 
	 *  @param smsObj : 전송 타입에 맞는 SmsMessageEnum의 상수 객체
	 *  @param userId : 수신자 ID  *관리자에게 보내는 경우 사용하지 않음
	 *  @param phoneNum : 수신자 전화번호  *관리자에게 보내는 경우 사용하지 않음
	 *  @param appendMsg : 기본 메시지 뒤에 추가할 메시지. ex) 사용자ID, 신청번호 등 
	 *  @param sendType : 전송 타입, 0: to User, 1: to Admin, 2: ETC(문자인증, 활용결과 미등록자)
	 *  @return
	 **********************************************/
	public int processSendSms(SmsMessageEnum smsObj, String userId, String phoneNum, String appendMsg, int sendType, String callback) {
		
		ConfigDto configDto = new ConfigDto();
		configDto.setItemType(location);
		List<ConfigDto> configDto_ = configService.selectConfigListAll(configDto);
		
		for(ConfigDto confDto : (List<ConfigDto>) configDto_){
			if("SMS_USE".equals(confDto.getItemName())){
				if ("1".equals(confDto.getItemValue())) {
					return 1;
				}
			}
		}
		
		SmsSendDto smsDto = new SmsSendDto();
		smsDto.setUserId("vessms");
		smsDto.setCallback(callback);
		
		// SMS 제목 및 내용 
		for(SmsMessageEnum smsMsgEnum : SmsMessageEnum.values()){
			if (smsMsgEnum.equals(smsObj)){

				smsDto.setSubject(smsMsgEnum.getSubject());
				
				if (SmsMessageEnum.SMS_CERT.equals(smsMsgEnum)){
					String message = "";
					for(ConfigDto confDto : (List<ConfigDto>) configDto_){
						if("SMS_AUTH".equals(confDto.getItemName())){
							message = confDto.getItemValue(); break;
						}
					}

					if(!StringUtils.isEmpty(message)){
						int startBracket = message.indexOf("{");
						int endBracket = message.indexOf("}");
						
						while(startBracket > -1 && endBracket > -1){
							String type = message.substring(startBracket+1, endBracket);
							switch(type){
								case "authNum":
									message = message.replace("{" + type + "}", appendMsg);
									break;
							}
							startBracket = message.indexOf("{");
							endBracket = message.indexOf("}");
						}
						smsDto.setSmsMsg(message);
					}else{
						smsDto.setSmsMsg(SmsMessageEnum.SMS_CERT_MSG1.getSmsMsg()+appendMsg+SmsMessageEnum.SMS_CERT_MSG2.getSmsMsg());
					}
				} else {
					smsDto.setSmsMsg(smsMsgEnum.getSmsMsg()+appendMsg);
				}
			}
		}
		
		UserDto userDto = new UserDto();

		switch (sendType) {
		case 0:
			// 사용자에게 보낼 경우
			userDto.setUserId(userId);
			userDto = userDao.selectAllUserInfo(userDto);
			
			smsDto.setDestInfo(userDto.getUserName() + "^" +userDto.getPhoneNum());
			smsDto.setDestCount(1);
			
			break;
		case 1:

			List<UserDto> userList = userDao.selectAdminList(userDto);	// 관리자 리스트 조회
			
			if (!userList.isEmpty()){
				
				String strDest = "";
				
				for(int i = 0; i < userList.size(); i++){
					userDto = userList.get(i);
					if (i == userList.size()){
						strDest += userDto.getUserName() + "^" +userDto.getPhoneNum();
					} else {
						strDest += userDto.getUserName() + "^" +userDto.getPhoneNum() + "|";
					}
				}
				
				smsDto.setDestInfo(strDest.toString());
				smsDto.setDestCount(userList.size());
			}
			
			break;
		default : 
			
			smsDto.setDestInfo(userId + "^" +phoneNum);
			smsDto.setDestCount(1);
			
			break;
		}
		
		return smsDao.insertSmsSendInfo(smsDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method resultRegReqSendSms 
	 *  @param smsObj
	 *  @param userId
	 *  @param phoneNum
	 *  @param appendMsg
	 *  @param sendType
	 *  @param callback
	 *  @return
	 **********************************************/
	public int resultRegReqSendSms(SmsMessageEnum smsObj, String userId, String phoneNum, String appendMsg, String callback) {
		
		ConfigDto configDto = new ConfigDto();
		configDto.setItemType(location);
		List<ConfigDto> configDto_ = configService.selectConfigListAll(configDto);
		
		for(ConfigDto confDto : (List<ConfigDto>) configDto_){
			if("SMS_USE".equals(confDto.getItemName())){
				if ("1".equals(confDto.getItemValue())) {
					return 1;
				}
			}
		}
		
		SmsSendDto smsDto = new SmsSendDto();
		smsDto.setUserId("vessms");
		smsDto.setCallback(callback);
		
		// SMS 제목 및 내용 
		for(SmsMessageEnum smsMsgEnum : SmsMessageEnum.values()){
			if (smsMsgEnum.equals(smsObj)){

				smsDto.setSubject(smsMsgEnum.getSubject());
				
				if (SmsMessageEnum.SMS_CERT.equals(smsMsgEnum)){
					String message = "";
					for(ConfigDto confDto : (List<ConfigDto>) configDto_){
						if("SMS_AUTH".equals(confDto.getItemName())){
							message = confDto.getItemValue(); break;
						}
					}

					if(!StringUtils.isEmpty(message)){
						int startBracket = message.indexOf("{");
						int endBracket = message.indexOf("}");
						
						while(startBracket > -1 && endBracket > -1){
							String type = message.substring(startBracket+1, endBracket);
							switch(type){
								case "authNum":
									message = message.replace("{" + type + "}", appendMsg);
									break;
							}
							startBracket = message.indexOf("{");
							endBracket = message.indexOf("}");
						}
						smsDto.setSmsMsg(message);
					}else{
						smsDto.setSmsMsg(SmsMessageEnum.SMS_CERT_MSG1.getSmsMsg()+appendMsg+SmsMessageEnum.SMS_CERT_MSG2.getSmsMsg());
					}
				} else {
					String message = "";
					for(ConfigDto confDto : (List<ConfigDto>) configDto_){
						if("SMS_RESULT_UNREGISTERED".equals(confDto.getItemName())){
							message = confDto.getItemValue(); break;
						}
					}

					if(!StringUtils.isEmpty(message)){
						int startBracket = message.indexOf("{");
						int endBracket = message.indexOf("}");
						
						while(startBracket > -1 && endBracket > -1){
							String type = message.substring(startBracket+1, endBracket);
							switch(type){
								case "userId":
									message = message.replace("{" + type + "}", userId);
									break;
								case "itemSerial":
									message = message.replace("{" + type + "}", appendMsg);
									break;
							}
							startBracket = message.indexOf("{");
							endBracket = message.indexOf("}");
						}
						smsDto.setSmsMsg(message);
					}else{
						smsDto.setSmsMsg(smsMsgEnum.getSmsMsg()+appendMsg);
					}
				}
			}
		}
		
		smsDto.setDestInfo(userId + "^" +phoneNum);
		smsDto.setDestCount(1);
		
		return smsDao.insertSmsSendInfo(smsDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method unapprovalSendSms 
	 *  @param smsObj
	 *  @param userId
	 *  @param phoneNum
	 *  @param appendMsg
	 *  @param sendType
	 *  @param callback
	 *  @return
	 **********************************************/
	public int unapprovalSendSms(SmsMessageEnum smsObj, String userId, String phoneNum, String appendMsg, int sendType, String callback) {
		
		ConfigDto configDto = new ConfigDto();
		configDto.setItemType(location);
		List<ConfigDto> configDto_ = configService.selectConfigListAll(configDto);
		
		for(ConfigDto confDto : (List<ConfigDto>) configDto_){
			if("SMS_USE".equals(confDto.getItemName())){
				if ("1".equals(confDto.getItemValue())) {
					return 1;
				}
			}
		}
		
		SmsSendDto smsDto = new SmsSendDto();
		smsDto.setUserId("vessms");
		smsDto.setCallback(callback);
		
		// SMS 제목 및 내용 
		for(SmsMessageEnum smsMsgEnum : SmsMessageEnum.values()){
			if (smsMsgEnum.equals(smsObj)){

				smsDto.setSubject(smsMsgEnum.getSubject());
				
				if (SmsMessageEnum.SMS_NOTI.equals(smsMsgEnum)){
					String message = "";
					for(ConfigDto confDto : (List<ConfigDto>) configDto_){
						if("SMS_REQ_CHANGE".equals(confDto.getItemName())){
							message = confDto.getItemValue(); break;
						}
					}

					if(!StringUtils.isEmpty(message)){
						int startBracket = message.indexOf("{");
						int endBracket = message.indexOf("}");
						
						while(startBracket > -1 && endBracket > -1){
							String type = message.substring(startBracket+1, endBracket);
							switch(type){
								case "userId":
									message = message.replace("{" + type + "}", userId);
									break;
								case "reason":
									message = message.replace("{" + type + "}", appendMsg);
									break;
							}
							startBracket = message.indexOf("{");
							endBracket = message.indexOf("}");
						}
						smsDto.setSmsMsg(message);
					}else{
						smsDto.setSmsMsg(SmsMessageEnum.SMS_NOTI_MSG1.getSmsMsg()+appendMsg+SmsMessageEnum.SMS_NOTI_MSG2.getSmsMsg());
					}
				}
			}
		}
		
		UserDto userDto = new UserDto();

		switch (sendType) {
		case 0:
			// 사용자에게 보낼 경우
			userDto.setUserId(userId);
			userDto = userDao.selectUserInfo(userDto);
			
			smsDto.setDestInfo(userDto.getUserName() + "^" +userDto.getPhoneNum());
			smsDto.setDestCount(1);
			
			break;
		case 1:

			List<UserDto> userList = userDao.selectAdminList(userDto);	// 관리자 리스트 조회
			
			if (!userList.isEmpty()){
				
				String strDest = "";
				
				for(int i = 0; i < userList.size(); i++){
					userDto = userList.get(i);
					if (i == userList.size()){
						strDest += userDto.getUserName() + "^" +userDto.getPhoneNum();
					} else {
						strDest += userDto.getUserName() + "^" +userDto.getPhoneNum() + "|";
					}
				}
				
				smsDto.setDestInfo(strDest.toString());
				smsDto.setDestCount(userList.size());
			}
			break;
		default : 
			smsDto.setDestInfo(userId + "^" +phoneNum);
			smsDto.setDestCount(1);
			break;
		}
		
		return smsDao.insertSmsSendInfo(smsDto);
	}
	
}
