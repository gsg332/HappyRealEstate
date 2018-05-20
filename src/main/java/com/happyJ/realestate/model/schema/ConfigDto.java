/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 6. 2. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.schema
 *  @fileName : ConfigDto.java
 *  @author : yongpal
 *  @since 2016. 6. 2.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 6. 2.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 6. 2.        yongpal       create ConfigDto.java
 *  </pre>
 ******************************************************************************/
public class ConfigDto extends CommonDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String itemVisible;
	private String itemValue;
	private String itemType;
	private String itemName;
	private String itemIndex;
	private String itemEtc;
	private String itemDescription;
	/**
	 * @return the itemVisible
	 */
	public String getItemVisible() {
		return itemVisible;
	}
	/**
	 * @param itemVisible the itemVisible to set
	 */
	public void setItemVisible(String itemVisible) {
		this.itemVisible = itemVisible;
	}
	/**
	 * @return the itemValue
	 */
	public String getItemValue() {
		return itemValue;
	}
	/**
	 * @param itemValue the itemValue to set
	 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
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
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the itemIndex
	 */
	public String getItemIndex() {
		return itemIndex;
	}
	/**
	 * @param itemIndex the itemIndex to set
	 */
	public void setItemIndex(String itemIndex) {
		this.itemIndex = itemIndex;
	}
	/**
	 * @return the itemEtc
	 */
	public String getItemEtc() {
		return itemEtc;
	}
	/**
	 * @param itemEtc the itemEtc to set
	 */
	public void setItemEtc(String itemEtc) {
		this.itemEtc = itemEtc;
	}
	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}
	/**
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
}
