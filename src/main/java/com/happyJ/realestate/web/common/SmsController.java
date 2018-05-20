/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 21. yongpal
*****************************************************************************/
package com.happyJ.realestate.web.common;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.happyJ.realestate.common.message.SmsMessageEnum;
import com.happyJ.realestate.common.util.StringUtil;
import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.service.SmsSendService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.common
 *  @fileName : SmsController.java
 *  @author : yongpal
 *  @since 2016. 7. 21.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 7. 21.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 7. 21.        yongpal       create SmsController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class SmsController {
	
	@Autowired
	private SmsSendService smsSendService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/sms/auth/create.json")
	public String createSmsAuthNo(@RequestParam String userId, @RequestParam String phoneNum, HttpServletRequest request, Model model){
		
		Random random = new Random();
		String authNo = String.format("%06d", random.nextInt(999999));
		String callback = null;
		for(ConfigDto configDto : Config.configList){
			if("SMS_CALLBACK".equals(configDto.getItemName())){
				callback = configDto.getItemValue();
			}
		}
		
		smsSendService.processSendSms(SmsMessageEnum.SMS_CERT, userId, phoneNum, authNo, 2, callback);
		
		model.addAttribute("authNo", authNo);
		
		return "jsonView"; 
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/sms/result/resultRegReqSend.json")
	public String sendSmsResultUser(HttpServletRequest request, Model model) {
		
		String callback = null;
		for(ConfigDto configDto : Config.configList){
			if("SMS_CALLBACK".equals(configDto.getItemName())){
				callback = configDto.getItemValue();
			}
		}
		
		JSONArray userArray = JSONArray.fromObject(JSONSerializer.toJSON(request.getParameter("jsonUserArray")));
		
		for(Object obj : userArray){
			JSONObject user = JSONObject.fromObject(obj);
			smsSendService.resultRegReqSendSms(SmsMessageEnum.RST_NOTI, user.get("userid").toString(), user.get("phoneNum").toString(), user.get("itemSerial").toString(), callback);
		}
		
		model.addAttribute("result", "SUCCESS");
		
		return "jsonView";
	}

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/sms/allow/send.json")
	public String sendSmsAllowAdmin(@RequestParam String title, @RequestParam String startIp, @RequestParam String endIp,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String callback = null;
		for(ConfigDto configDto : Config.configList){
			if("SMS_CALLBACK".equals(configDto.getItemName())){
				callback = configDto.getItemValue();
			}
		}
		model.addAttribute("result", StringUtil
				.successYn(smsSendService.processSendSms(SmsMessageEnum.ALLOW_IP, "", "", title + " , IP : " + startIp + " ~ " + endIp, 1, callback)));		
		return "jsonView";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/sms/result/sendUnapprovalReason.json")
	public String sendUnapprovalReason(HttpServletRequest request, Model model){

		String callback = null;
		for(ConfigDto configDto : Config.configList){
			if("SMS_CALLBACK".equals(configDto.getItemName())){
				callback = configDto.getItemValue();
			}
		}

		JSONArray userArray = JSONArray.fromObject(JSONSerializer.toJSON(request.getParameter("jsonUserArray")));
		
		for(Object obj : userArray){
			JSONObject user = JSONObject.fromObject(obj);
			smsSendService.unapprovalSendSms(SmsMessageEnum.SMS_NOTI, user.get("reqUserid").toString(), user.get("phoneNum").toString(), user.get("unapprovalReason").toString(), 2, callback);
		}

		model.addAttribute("result", "SUCCESS");

		return "jsonView"; 
	}
}
