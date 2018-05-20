package com.happyJ.realestate.model.schema;

import java.util.List;

import com.happyJ.realestate.model.CommonDto;

public class FnConfigDto extends CommonDto {

	private static final long serialVersionUID = 1L;

	private String itemIndex;
	private String pItemIndex;
	private String itemCode;
	private String itemVisible;
	private String itemValue;
	private String itemDescription;
	private List<FnConfigDto> subConfigList;

	public String getItemIndex() {
		return itemIndex;
	}

	public void setItemIndex(String itemIndex) {
		this.itemIndex = itemIndex;
	}

	public String getpItemIndex() {
		return pItemIndex;
	}

	public void setpItemIndex(String pItemIndex) {
		this.pItemIndex = pItemIndex;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemVisible() {
		return itemVisible;
	}

	public void setItemVisible(String itemVisible) {
		this.itemVisible = itemVisible;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public List<FnConfigDto> getSubConfigList() {
		return subConfigList;
	}

	public void setSubConfigList(List<FnConfigDto> subConfigList) {
		this.subConfigList = subConfigList;
	}

}
