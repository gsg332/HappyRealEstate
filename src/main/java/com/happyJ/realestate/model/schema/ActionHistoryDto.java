/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 6. 3. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.schema
 *  @fileName : ActionHistoryDto.java
 *  @author : yongpal
 *  @since 2016. 6. 3.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 6. 3.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 6. 3.        yongpal       create ActionHistoryDto.java
 *  </pre>
 ******************************************************************************/
public class ActionHistoryDto extends CommonDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String result;
	private String menu;
	private String memo;
	private String idx;
	private String description;
	private String actionTime;
	private String action;
	private String accessIp;
	
	/* 검색 항목 */
	private String searchKind;
	private String searchWord;	
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the menu
	 */
	public String getMenu() {
		return menu;
	}
	/**
	 * @param menu the menu to set
	 */
	public void setMenu(String menu) {
		this.menu = menu;
	}
	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the actionTime
	 */
	public String getActionTime() {
		return actionTime;
	}
	/**
	 * @param actionTime the actionTime to set
	 */
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return the accessIp
	 */
	public String getAccessIp() {
		return accessIp;
	}
	/**
	 * @param accessIp the accessIp to set
	 */
	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}
	/**
	 * @return the searchKind
	 */
	public String getSearchKind() {
		return searchKind;
	}
	/**
	 * @param searchKind the searchKind to set
	 */
	public void setSearchKind(String searchKind) {
		this.searchKind = searchKind;
	}
	/**
	 * @return the searchWord
	 */
	public String getSearchWord() {
		return searchWord;
	}
	/**
	 * @param searchWord the searchWord to set
	 */
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
}
