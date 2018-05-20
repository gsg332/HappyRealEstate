/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 29. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.protocol.body;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.protocol.body
 *  @fileName : ItemCheck.java
 *  @author : yongpal
 *  @since 2016. 7. 29.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 7. 29.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 7. 29.        yongpal       create ItemCheck.java
 *  </pre>
 ******************************************************************************/
public class ItemCheckBody {

	/**
	 * 미사용 함..
	 */
	private String cryptKey;
	private String limitPlayCount;
	private String playLimitDate;
	private String playCount;
	private String errorMsg;
	private String parameterInfo;
	/**
	 * @return the cryptKey
	 */
	public String getCryptKey() {
		return cryptKey;
	}
	/**
	 * @param cryptKey the cryptKey to set
	 */
	public void setCryptKey(String cryptKey) {
		this.cryptKey = cryptKey;
	}
	/**
	 * @return the limitPlayCount
	 */
	public String getLimitPlayCount() {
		return limitPlayCount;
	}
	/**
	 * @param limitPlayCount the limitPlayCount to set
	 */
	public void setLimitPlayCount(String limitPlayCount) {
		this.limitPlayCount = limitPlayCount;
	}
	/**
	 * @return the playLimitDate
	 */
	public String getPlayLimitDate() {
		return playLimitDate;
	}
	/**
	 * @param playLimitDate the playLimitDate to set
	 */
	public void setPlayLimitDate(String playLimitDate) {
		this.playLimitDate = playLimitDate;
	}
	/**
	 * @return the playCount
	 */
	public String getPlayCount() {
		return playCount;
	}
	/**
	 * @param playCount the playCount to set
	 */
	public void setPlayCount(String playCount) {
		this.playCount = playCount;
	}
	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	/**
	 * @return the parameterInfo
	 */
	public String getParameterInfo() {
		return parameterInfo;
	}
	/**
	 * @param parameterInfo the parameterInfo to set
	 */
	public void setParameterInfo(String parameterInfo) {
		this.parameterInfo = parameterInfo;
	}
	

}
