/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 19. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 * @packageName : com.happyJ.realestate.model
 * @fileName : FileDto.java
 * @author : yongpal
 * @since 2016. 4. 19.
 * @version 1.0
 * @see :
 * @revision : 2016. 4. 19.
 * 
 *           <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 19.        yongpal       create FileDto.java
 *           </pre>
 ******************************************************************************/
public class FileDto extends CommonDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int FILE_TYPE_OFFICIAL = 1001;
	public static final int FILE_TYPE_PLEDGE = 1002;

	private String itemNo;
	private String itemSerial;
	private String userid;
	private String itemType;
	private int fileType;
	private String tempFilename;
	private String orgFilename;
	private String uploadTime;
	private String downloadCount;
	private String eviItemNo;

	/**
	 * @return the itemNo
	 */
	public String getItemNo() {
		return itemNo;
	}

	/**
	 * @param itemNo the itemNo to set
	 */
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * @param itemType the itemType to set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the tempFilename
	 */
	public String getTempFilename() {
		return tempFilename;
	}

	/**
	 * @param tempFilename the tempFilename to set
	 */
	public void setTempFilename(String tempFilename) {
		this.tempFilename = tempFilename;
	}

	/**
	 * @return the orgFilename
	 */
	public String getOrgFilename() {
		return orgFilename;
	}

	/**
	 * @param orgFilename the orgFilename to set
	 */
	public void setOrgFilename(String orgFilename) {
		this.orgFilename = orgFilename;
	}

	/**
	 * @return the uploadTime
	 */
	public String getUploadTime() {
		return uploadTime;
	}

	/**
	 * @param uploadTime the uploadTime to set
	 */
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	/**
	 * @return the downloadCount
	 */
	public String getDownloadCount() {
		return downloadCount;
	}

	/**
	 * @param downloadCount the downloadCount to set
	 */
	public void setDownloadCount(String downloadCount) {
		this.downloadCount = downloadCount;
	}

	/**
	 * @return the eviItemNo
	 */
	public String getEviItemNo() {
		return eviItemNo;
	}

	/**
	 * @param eviItemNo the eviItemNo to set
	 */
	public void setEviItemNo(String eviItemNo) {
		this.eviItemNo = eviItemNo;
	}

}
