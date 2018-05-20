/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 28. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.protocol.body;

import java.util.Date;

/*****************************************************************************
 * 
 * @packageName : com.happyJ.realestate.model.protocol.body
 * @fileName : LoginBody.java
 * @author : yongpal
 * @since 2016. 7. 28.
 * @version 1.0
 * @see :
 * @revision : 2016. 7. 28.
 * 
 *           <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 7. 28.        yongpal       create LoginBody.java
 *           </pre>
 ******************************************************************************/
public class RealPriceBody {

	private Long uNo;
	private String dealType;
	private Date regDate;
	private String goods_name;
	private Integer size;
	private String dong;
	private Integer floor;
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
	public Date getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate
	 *            the regDate to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the goods_name
	 */
	public String getGoods_name() {
		return goods_name;
	}

	/**
	 * @param goods_name
	 *            the goods_name to set
	 */
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Integer size) {
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
	public Integer getFloor() {
		return floor;
	}

	/**
	 * @param floor
	 *            the floor to set
	 */
	public void setFloor(Integer floor) {
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
