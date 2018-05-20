/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 25. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.custom;

import com.happyJ.realestate.model.schema.ItemExtendDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model
 *  @fileName : ExtendCustomDto.java
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
 *     2016. 4. 25.        yongpal       create ExtendCustomDto.java
 *  </pre>
 ******************************************************************************/
public class ExtendCustomDto extends ItemExtendDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String level;
	
	private String permitNm;
	
	private String searchPermit;
	
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
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the permitNm
	 */
	public String getPermitNm() {
		return permitNm;
	}

	/**
	 * @param permitNm the permitNm to set
	 */
	public void setPermitNm(String permitNm) {
		this.permitNm = permitNm;
	}

	/**
	 * @return the searchPermit
	 */
	public String getSearchPermit() {
		return searchPermit;
	}

	/**
	 * @param searchPermit the searchPermit to set
	 */
	public void setSearchPermit(String searchPermit) {
		this.searchPermit = searchPermit;
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
