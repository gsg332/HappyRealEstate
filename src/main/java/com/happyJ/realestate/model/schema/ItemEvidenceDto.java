/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 25. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model
 *  @fileName : ItemEvidenceDto.java
 *  @author : yongpal
 *  @since 2016. 4. 25.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 25.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 25.        yongpal       create ItemEvidenceDto.java
 *  </pre>
 ******************************************************************************/
public class ItemEvidenceDto extends ItemDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String eviItemNo;
	private String eviItemDate;
	private String eviReqUserid;
	private String eviUserid;
	private String eviUserpass;
	private String eviLimitCount;
	private String eviLimitDate;
	private String permit;
	
	
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
	/**
	 * @return the eviItemDate
	 */
	public String getEviItemDate() {
		return eviItemDate;
	}
	/**
	 * @param eviItemDate the eviItemDate to set
	 */
	public void setEviItemDate(String eviItemDate) {
		this.eviItemDate = eviItemDate;
	}
	/**
	 * @return the eviReqUserid
	 */
	public String getEviReqUserid() {
		return eviReqUserid;
	}
	/**
	 * @param eviReqUserid the eviReqUserid to set
	 */
	public void setEviReqUserid(String eviReqUserid) {
		this.eviReqUserid = eviReqUserid;
	}
	/**
	 * @return the eviUserid
	 */
	public String getEviUserid() {
		return eviUserid;
	}
	/**
	 * @param eviUserid the eviUserid to set
	 */
	public void setEviUserid(String eviUserid) {
		this.eviUserid = eviUserid;
	}
	/**
	 * @return the eviUserpass
	 */
	public String getEviUserpass() {
		return eviUserpass;
	}
	/**
	 * @param eviUserpass the eviUserpass to set
	 */
	public void setEviUserpass(String eviUserpass) {
		this.eviUserpass = eviUserpass;
	}
	/**
	 * @return the eviLimitCount
	 */
	public String getEviLimitCount() {
		return eviLimitCount;
	}
	/**
	 * @param eviLimitCount the eviLimitCount to set
	 */
	public void setEviLimitCount(String eviLimitCount) {
		this.eviLimitCount = eviLimitCount;
	}
	/**
	 * @return the eviLimitDate
	 */
	public String getEviLimitDate() {
		return eviLimitDate;
	}
	/**
	 * @param eviLimitDate the eviLimitDate to set
	 */
	public void setEviLimitDate(String eviLimitDate) {
		this.eviLimitDate = eviLimitDate;
	}
	/**
	 * @return the permit
	 */
	public String getPermit() {
		return permit;
	}
	/**
	 * @param permit the permit to set
	 */
	public void setPermit(String permit) {
		this.permit = permit;
	}

}
