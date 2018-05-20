/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 28. yongpal
*****************************************************************************/
package com.happyJ.realestate.common.message;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.protocol
 *  @fileName : ResultCodeEnum.java
 *  @author : yongpal
 *  @since 2016. 7. 28.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 7. 28.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 7. 28.        yongpal       create ResultCodeEnum.java
 *  </pre>
 ******************************************************************************/
public enum ResultCodeEnum {
	
	RESULT_SUCCESS(200, "SUCCESS"),
	RESULT_LOGIN_FAIL(301, "LOGIN_FAIL"),
	RESULT_PERM_DENY(302, "PERM_DENY"),
	RESULT_PLAY_CNT_OVER(303, "PLAY_COUNT_OVER"),
	RESULT_PLAY_DATE_OVER(304, "PLAY_LIMITDATE_OVER"),
	RESULT_CCTV_DUPLICATE(305, "CCTV_DUPLICATE"),
	RESULT_INSERT_FAIL(401, "INSERT_FAIL"),
	RESULT_UPDATE_FAIL(402, "UPDATE_FAIL"),
	RESULT_DELETE_FAIL(403, "DELETE_FAIL"),
	RESULT_SELECT_FAIL(404, "SELECT_FAIL"),
	RESULT_FILE_NOT_EXSITS(501, "FILE_NOT_EXSITS"),
	RESULT_FILE_ERROR(502, "FILE_ERROR"),
	RESULT_COORD_TRANS_ERROR(503, "COORD_TRANS_ERROR"),
	RESULT_INTERNAL_ERROR(901, "INTERNAL_ERROR");
	
	private int resultCode;
	private String reusltMsg;
	
	/**
	 * 
	 */
	private ResultCodeEnum(int resultCd, String resultMsg) {
		this.resultCode = resultCd;
		this.reusltMsg = resultMsg;
	}

	/**
	 * @return the resultCode
	 */
	public int getResultCode() {
		return this.resultCode;
	}

	/**
	 * @return the reusltMsg
	 */
	public String getReusltMsg() {
		return this.reusltMsg;
	}
	
}
