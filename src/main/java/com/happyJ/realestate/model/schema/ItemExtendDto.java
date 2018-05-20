/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 25. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model
 *  @fileName : ItemExtendDto.java
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
 *     2016. 4. 25.        yongpal       create ItemExtendDto.java
 *  </pre>
 ******************************************************************************/
public class ItemExtendDto extends CommonDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idx;
	private String itemSerial;
	private String extReason;
	private String extDate;
	private String extAppDate;
	private String extStatus;
	
	
	/**
	 * @return the extReason
	 */
	public String getExtReason() {
		return extReason;
	}
	/**
	 * @param extReason the extReason to set
	 */
	public void setExtReason(String extReason) {
		this.extReason = extReason;
	}
	/**
	 * @return the extDate
	 */
	public String getExtDate() {
		return extDate;
	}
	/**
	 * @param extDate the extDate to set
	 */
	public void setExtDate(String extDate) {
		this.extDate = extDate;
	}
	/**
	 * @return the extAppDate
	 */
	public String getExtAppDate() {
		return extAppDate;
	}
	/**
	 * @param extAppDate the extAppDate to set
	 */
	public void setExtAppDate(String extAppDate) {
		this.extAppDate = extAppDate;
	}
	/**
	 * @return the extStatus
	 */
	public String getExtStatus() {
		return extStatus;
	}
	/**
	 * @param extStatus the extStatus to set
	 */
	public void setExtStatus(String extStatus) {
		this.extStatus = extStatus;
	}
	/**
	 * @return the idx
	 */
	public String getIdx() {
		return idx;
	}
	/**
	 * @param idx the idx to set
	 */
	public void setIdx(String idx) {
		this.idx = idx;
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
	
}
