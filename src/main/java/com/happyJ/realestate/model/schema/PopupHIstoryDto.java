/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 5. 17. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.schema
 *  @fileName : PopupHIstoryDto.java
 *  @author : yongpal
 *  @since 2016. 5. 17.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 5. 17.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 5. 17.        yongpal       create PopupHIstoryDto.java
 *  </pre>
 ******************************************************************************/
public class PopupHIstoryDto extends CommonDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String no;
	private String popupType;
	private String itemSerial;
	private String subject;
	private String contents;
	private String isPopup;
	private String checkDate;
	private String regDate;
	
	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * @return the popupType
	 */
	public String getPopupType() {
		return popupType;
	}
	/**
	 * @param popupType the popupType to set
	 */
	public void setPopupType(String popupType) {
		this.popupType = popupType;
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
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}
	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	/**
	 * @return the isPopup
	 */
	public String getIsPopup() {
		return isPopup;
	}
	/**
	 * @param isPopup the isPopup to set
	 */
	public void setIsPopup(String isPopup) {
		this.isPopup = isPopup;
	}
	/**
	 * @return the checkDate
	 */
	public String getCheckDate() {
		return checkDate;
	}
	/**
	 * @param checkDate the checkDate to set
	 */
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
}
