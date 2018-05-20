/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 28. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.protocol.header;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.protocol
 *  @fileName : ResponseHeader.java
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
 *     2016. 7. 28.        yongpal       create ResponseHeader.java
 *  </pre>
 ******************************************************************************/
public class ResponseHeader{
	
	private int resultCode;
	private String resultMsg;
	
	
	/**
	 * @return the resultCode
	 */
	public int getResultCode() {
		return resultCode;
	}
	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	/**
	 * @return the resultMsg
	 */
	public String getResultMsg() {
		return resultMsg;
	}
	/**
	 * @param resultMsg the resultMsg to set
	 */
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
}
