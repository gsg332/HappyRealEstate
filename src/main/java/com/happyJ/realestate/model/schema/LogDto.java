/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 5. 31. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.schema
 *  @fileName : LogDto.java
 *  @author : yongpal
 *  @since 2016. 5. 31.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 5. 31.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 5. 31.        yongpal       create LogDto.java
 *  </pre>
 ******************************************************************************/
public class LogDto extends CommonDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String logSerial;
	private String itemSerial;
	private String playTime;
	private String userid;
	private String ipaddr;
	private String logType;
	private int playCount;
	
	/**
	 * @return the logSerial
	 */
	public String getLogSerial() {
		return logSerial;
	}
	/**
	 * @param logSerial the logSerial to set
	 */
	public void setLogSerial(String logSerial) {
		this.logSerial = logSerial;
	}
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
	 * @return the playTime
	 */
	public String getPlayTime() {
		return playTime;
	}
	/**
	 * @param playTime the playTime to set
	 */
	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return the ipaddr
	 */
	public String getIpaddr() {
		return ipaddr;
	}
	/**
	 * @param ipaddr the ipaddr to set
	 */
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	/**
	 * @return the logType
	 */
	public String getLogType() {
		return logType;
	}
	/**
	 * @param logType the logType to set
	 */
	public void setLogType(String logType) {
		this.logType = logType;
	}
	/**
	 * @return the playCount
	 */
	public int getPlayCount() {
		return playCount;
	}
	/**
	 * @param playCount the playCount to set
	 */
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}

}
