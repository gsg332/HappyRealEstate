/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 20. yongpal
*****************************************************************************/
package com.happyJ.realestate.common.message;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.common.util
 *  @fileName : SmsMessageEnum.java
 *  @author : yongpal
 *  @since 2016. 7. 20.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 7. 20.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 7. 20.        yongpal       create SmsMessageEnum.java
 *  </pre>
 ******************************************************************************/
public enum SmsMessageEnum {
	NEW_JOIN("가입신청", "[HappyRealEstate] 사용자 등록 신청이 접수되었습니다. 사용자ID : "),
	APP_JOIN("가입승인", "[HappyRealEstate-가입승인] 가입이 승인되었습니다. ID : "),
	REJ_JOIN("가입반려", "[HappyRealEstate-가입반려] 가입이 반려되었습니다. ID : "),
	REQ_VIDEO("영상반출신청", "[HappyRealEstate] 영상정보 신청서가 접수되었습니다. 신청번호 : "),
	READ_VIDEO("영상열람신청", "[HappyRealEstate] 영상정보 신청서가 접수되었습니다. 신청번호 : "),
	APP_VIDEO("영상신청승인", "[HappyRealEstate-영상신청] 신청하신 영상정보가 승인처리 되었습니다. 신청번호 : "),
	REJ_VIDEO("영상신청반려", "[HappyRealEstate-영상신청] 신청하신 영상정보가 반려처리 되었습니다. 신청번호 : "),
	SMS_CERT("문자인증", ""),
	SMS_CERT_MSG1("문자인증", "[HappyRealEstate-문자인증] 인증번호는 ["),
	SMS_CERT_MSG2("문자인증", "] 입니다. 정확하게 입력해주세요. "),
	SMS_NOTI("변경요청결과", ""),
	SMS_NOTI_MSG1("변경요청결과", "[HappyRealEstate-변경요청결과] \""),
	SMS_NOTI_MSG2("변경요청결과", "\" 사유로 미승인처리 되었습니다. "),
	ALLOW_IP("허용IP요청", "[HappyRealEstate] IP허용요청이 접수되었습니다. 사용기관 : "),
	EXT_VIDEO("연장신청", "[HappyRealEstate] 연장신청이 접수되었습니다. 신청번호 : "),
	RST_NOTI("활용결과입력", "[HappyRealEstate] 활용결과 미입력 건이 있습니다. 활용결과를 입력하시기 바랍니다. 신청번호 : ");
	
	private String subject;
	private String smsMsg;
	
	/**
	 * 
	 */
	private SmsMessageEnum(String subject, String msg) {

		this.subject = subject;
		this.smsMsg = msg;
	}
	
	public String getSubject(){
		return this.subject;
	}
	
	public String getSmsMsg(){
		return this.smsMsg;
	}
	
}
