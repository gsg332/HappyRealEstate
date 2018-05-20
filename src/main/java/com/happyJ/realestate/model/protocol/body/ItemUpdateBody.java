/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 28. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.protocol.body;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.protocol.body
 *  @fileName : ItemUpdate.java
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
 *     2016. 7. 28.        yongpal       create ItemUpdate.java
 *  </pre>
 ******************************************************************************/
public class ItemUpdateBody {

	private String itemSerial;
	private String cryptKey;
	private String videoId;
	private String videoStart;
	private String videoEnd;
	
	
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
	 * @return the videoId
	 */
	public String getVideoId() {
		return videoId;
	}
	/**
	 * @param videoId the videoId to set
	 */
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	/**
	 * @return the videoStart
	 */
	public String getVideoStart() {
		return videoStart;
	}
	/**
	 * @param videoStart the videoStart to set
	 */
	public void setVideoStart(String videoStart) {
		this.videoStart = videoStart;
	}
	/**
	 * @return the videoEnd
	 */
	public String getVideoEnd() {
		return videoEnd;
	}
	/**
	 * @param videoEnd the videoEnd to set
	 */
	public void setVideoEnd(String videoEnd) {
		this.videoEnd = videoEnd;
	}
}
