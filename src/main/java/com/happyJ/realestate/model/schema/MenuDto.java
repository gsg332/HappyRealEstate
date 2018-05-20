/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 5. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model
 *  @fileName : MenuDto.java
 *  @author : yongpal
 *  @since 2016. 4. 5.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 5.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 5.        yongpal       create MenuDto.java
 *  </pre>
 ******************************************************************************/
public class MenuDto extends CommonDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = -322154515133136774L;

	private String menuId;
	private String pMenuId;
	private String menuNm;
	private String menuUrl;
	private String menuDepth;
	private String menuAuth;
	private String useYn;
	private String regDate;
	private String regId;
	private String modDate;
	private String modId;
	
	/**
	 * @return the menuId
	 */
	public String getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	/**
	 * @return the pMenuId
	 */
	public String getpMenuId() {
		return pMenuId;
	}
	/**
	 * @param pMenuId the pMenuId to set
	 */
	public void setpMenuId(String pMenuId) {
		this.pMenuId = pMenuId;
	}
	/**
	 * @return the menuNm
	 */
	public String getMenuNm() {
		return menuNm;
	}
	/**
	 * @param menuNm the menuNm to set
	 */
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	/**
	 * @return the menuUrl
	 */
	public String getMenuUrl() {
		return menuUrl;
	}
	/**
	 * @param menuUrl the menuUrl to set
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	/**
	 * @return the menuDepth
	 */
	public String getMenuDepth() {
		return menuDepth;
	}
	/**
	 * @param menuDepth the menuDepth to set
	 */
	public void setMenuDepth(String menuDepth) {
		this.menuDepth = menuDepth;
	}
	/**
	 * @return the menuAuth
	 */
	public String getMenuAuth() {
		return menuAuth;
	}
	/**
	 * @param menuAuth the menuAuth to set
	 */
	public void setMenuAuth(String menuAuth) {
		this.menuAuth = menuAuth;
	}
	/**
	 * @return the useYn
	 */
	public String getUseYn() {
		return useYn;
	}
	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}
	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}
	/**
	 * @return the modDate
	 */
	public String getModDate() {
		return modDate;
	}
	/**
	 * @param modDate the modDate to set
	 */
	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
	/**
	 * @return the modId
	 */
	public String getModId() {
		return modId;
	}
	/**
	 * @param modId the modId to set
	 */
	public void setModId(String modId) {
		this.modId = modId;
	}
	
}
