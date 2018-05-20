/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 4. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 * @packageName : com.happyJ.realestate.model
 * @fileName : UserDto.java
 * @author : yongpal
 * @since 2016. 4. 4.
 * @version 1.0
 * @see :
 * @revision : 2016. 4. 4.
 * 
 *           <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 4.        yongpal       create UserDto.java
 *           </pre>
 ******************************************************************************/
public class RealPriceDto extends CommonDto {

	private static final long serialVersionUID = 8119601823717738611L;

	private Long uNo;
	private String dealType;
	private String regDate;
	private String goodsName;
	private String size;
	private String dong;
	private String floor;
	private String price;
	private String phoneNumber;
	private String etc;

	/**
	 * @return the uNo
	 */
	public Long getuNo() {
		return uNo;
	}

	/**
	 * @param uNo
	 *            the uNo to set
	 */
	public void setuNo(Long uNo) {
		this.uNo = uNo;
	}

	/**
	 * @return the dealType
	 */
	public String getDealType() {
		return dealType;
	}

	/**
	 * @param dealType
	 *            the dealType to set
	 */
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate
	 *            the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * @param goodsName
	 *            the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the dong
	 */
	public String getDong() {
		return dong;
	}

	/**
	 * @param dong
	 *            the dong to set
	 */
	public void setDong(String dong) {
		this.dong = dong;
	}

	/**
	 * @return the floor
	 */
	public String getFloor() {
		return floor;
	}

	/**
	 * @param floor
	 *            the floor to set
	 */
	public void setFloor(String floor) {
		this.floor = floor;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the etc
	 */
	public String getEtc() {
		return etc;
	}

	/**
	 * @param etc
	 *            the etc to set
	 */
	public void setEtc(String etc) {
		this.etc = etc;
	}

}
