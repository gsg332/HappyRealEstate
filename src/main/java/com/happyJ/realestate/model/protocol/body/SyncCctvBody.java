/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 8. 2. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.protocol.body;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.protocol.body
 *  @fileName : SyncCctvBody.java
 *  @author : yongpal
 *  @since 2016. 8. 2.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 8. 2.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 8. 2.        yongpal       create SyncCctvBody.java
 *  </pre>
 ******************************************************************************/
public class SyncCctvBody {

	private String manageCode;
	private String code1;
	private String code2;
	private String imageName;
	
	
	/**
	 * @return the manageCode
	 */
	public String getManageCode() {
		return manageCode;
	}
	/**
	 * @param manageCode the manageCode to set
	 */
	public void setManageCode(String manageCode) {
		this.manageCode = manageCode;
	}
	/**
	 * @return the code1
	 */
	public String getCode1() {
		return code1;
	}
	/**
	 * @param code1 the code1 to set
	 */
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	/**
	 * @return the code2
	 */
	public String getCode2() {
		return code2;
	}
	/**
	 * @param code2 the code2 to set
	 */
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}
	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
}
