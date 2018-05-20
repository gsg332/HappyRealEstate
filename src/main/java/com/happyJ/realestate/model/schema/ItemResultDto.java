/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 5. 23. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.schema
 *  @fileName : ItemResultDto.java
 *  @author : yongpal
 *  @since 2016. 5. 23.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 5. 23.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 5. 23.        yongpal       create ItemResultDto.java
 *  </pre>
 ******************************************************************************/
public class ItemResultDto extends CommonDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String itemSerial;
	private String itemUse;
	private String itemResult;
	private String resultCode;
	private String resultMemo;
	private String resultDisposal;
	private String resultTime;
	private String usedVideoId;
	private String usedVideoStart;
	private String usedVideoEnd;
	
	/**
	 * @return the itemSerial
	 */
	public String getItemSerial() {
		return itemSerial;
	}
	/**
	 * @param itemSerial the itemSerial to set
	 */
	public void setItemSerial(String itemSerial) {
		this.itemSerial = itemSerial;
	}
	/**
	 * @return the itemUse
	 */
	public String getItemUse() {
		return itemUse;
	}
	/**
	 * @param itemUse the itemUse to set
	 */
	public void setItemUse(String itemUse) {
		this.itemUse = itemUse;
	}
	/**
	 * @return the itemResult
	 */
	public String getItemResult() {
		return itemResult;
	}
	/**
	 * @param itemResult the itemResult to set
	 */
	public void setItemResult(String itemResult) {
		this.itemResult = itemResult;
	}
	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}
	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	/**
	 * @return the resultMemo
	 */
	public String getResultMemo() {
		return resultMemo;
	}
	/**
	 * @param resultMemo the resultMemo to set
	 */
	public void setResultMemo(String resultMemo) {
		this.resultMemo = resultMemo;
	}
	/**
	 * @return the resultDisposal
	 */
	public String getResultDisposal() {
		return resultDisposal;
	}
	/**
	 * @param resultDisposal the resultDisposal to set
	 */
	public void setResultDisposal(String resultDisposal) {
		this.resultDisposal = resultDisposal;
	}
	/**
	 * @return the resultTime
	 */
	public String getResultTime() {
		return resultTime;
	}
	/**
	 * @param resultTime the resultTime to set
	 */
	public void setResultTime(String resultTime) {
		this.resultTime = resultTime;
	}
	/**
	 * @return the usedVideoId
	 */
	public String getUsedVideoId() {
		return usedVideoId;
	}
	/**
	 * @param usedVideoId the usedVideoId to set
	 */
	public void setUsedVideoId(String usedVideoId) {
		this.usedVideoId = usedVideoId;
	}
	/**
	 * @return the usedVideoStart
	 */
	public String getUsedVideoStart() {
		return usedVideoStart;
	}
	/**
	 * @param usedVideoStart the usedVideoStart to set
	 */
	public void setUsedVideoStart(String usedVideoStart) {
		this.usedVideoStart = usedVideoStart;
	}
	/**
	 * @return the usedVideoEnd
	 */
	public String getUsedVideoEnd() {
		return usedVideoEnd;
	}
	/**
	 * @param usedVideoEnd the usedVideoEnd to set
	 */
	public void setUsedVideoEnd(String usedVideoEnd) {
		this.usedVideoEnd = usedVideoEnd;
	}
	
}
