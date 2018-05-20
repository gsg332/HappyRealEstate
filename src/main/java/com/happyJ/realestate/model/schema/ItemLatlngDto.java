/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 6. 8. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.schema
 *  @fileName : ItemLatlngDto.java
 *  @author : yongpal
 *  @since 2016. 6. 8.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 6. 8.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 6. 8.        yongpal       create ItemLatlngDto.java
 *  </pre>
 ******************************************************************************/
public class ItemLatlngDto extends CommonDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String managecode;
	private String itemType;
	private String itemLongitude;
	private String itemLatitude;
	private String code2;
	private String code1;
	private String address;
	private String addr4;
	private String addr3;
	private String addr2;
	private String addr1;
	private String ptzYn;
	private String direction;
	
	private String imageSrc;
	private String directionImg;
	private String directionDesc;
	private String videoId;
	private String[] arrVideoId;
	
	private String radius;
	
	
	/**
	 * @return the no
	 */
	public int getNo() {
		return no;
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(int no) {
		this.no = no;
	}
	/**
	 * @return the managecode
	 */
	public String getManagecode() {
		return managecode;
	}
	/**
	 * @param managecode the managecode to set
	 */
	public void setManagecode(String managecode) {
		this.managecode = managecode;
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
	/**
	 * @return the itemLongitude
	 */
	public String getItemLongitude() {
		return itemLongitude;
	}
	/**
	 * @param itemLongitude the itemLongitude to set
	 */
	public void setItemLongitude(String itemLongitude) {
		this.itemLongitude = itemLongitude;
	}
	/**
	 * @return the itemLatitude
	 */
	public String getItemLatitude() {
		return itemLatitude;
	}
	/**
	 * @param itemLatitude the itemLatitude to set
	 */
	public void setItemLatitude(String itemLatitude) {
		this.itemLatitude = itemLatitude;
	}
	/**
	 * @return the code2
	 */
	public String getCode2() {
		return code2;
	}
	/**
	 * @param code2 the code2 to set
	 */
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	/**
	 * @return the code1
	 */
	public String getCode1() {
		return code1;
	}
	/**
	 * @param code1 the code1 to set
	 */
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the addr4
	 */
	public String getAddr4() {
		return addr4;
	}
	/**
	 * @param addr4 the addr4 to set
	 */
	public void setAddr4(String addr4) {
		this.addr4 = addr4;
	}
	/**
	 * @return the addr3
	 */
	public String getAddr3() {
		return addr3;
	}
	/**
	 * @param addr3 the addr3 to set
	 */
	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}
	/**
	 * @return the addr2
	 */
	public String getAddr2() {
		return addr2;
	}
	/**
	 * @param addr2 the addr2 to set
	 */
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	/**
	 * @return the addr1
	 */
	public String getAddr1() {
		return addr1;
	}
	/**
	 * @param addr1 the addr1 to set
	 */
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	/**
	 * @return the imageSrc
	 */
	public String getImageSrc() {
		return imageSrc;
	}
	/**
	 * @param imageSrc the imageSrc to set
	 */
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	/**
	 * @return the arrVideoId
	 */
	public String[] getArrVideoId() {
		return arrVideoId;
	}
	/**
	 * @param arrVideoId the arrVideoId to set
	 */
	public void setArrVideoId(String[] arrVideoId) {
		this.arrVideoId = arrVideoId;
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
	 * @return the ptzYn
	 */
	public String getPtzYn() {
		return ptzYn;
	}
	/**
	 * @param ptzYn the ptzYn to set
	 */
	public void setPtzYn(String ptzYn) {
		this.ptzYn = ptzYn;
	}
	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	/**
	 * @return the directionImg
	 */
	public String getDirectionImg() {
		return directionImg;
	}
	/**
	 * @param directionImg the directionImg to set
	 */
	public void setDirectionImg(String directionImg) {
		this.directionImg = directionImg;
	}
	/**
	 * @return the directionDesc
	 */
	public String getDirectionDesc() {
		return directionDesc;
	}
	/**
	 * @param directionDesc the directionDesc to set
	 */
	public void setDirectionDesc(String directionDesc) {
		this.directionDesc = directionDesc;
	}
	
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
	}
	
}
