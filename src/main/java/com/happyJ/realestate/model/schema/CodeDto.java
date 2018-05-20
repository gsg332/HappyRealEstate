/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 20. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model
 *  @fileName : CodeDto.java
 *  @author : yongpal
 *  @since 2016. 4. 20.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 20.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 20.        yongpal       create CodeDto.java
 *  </pre>
 ******************************************************************************/
public class CodeDto extends CommonDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codeGroup;
	private String codeKey;
	private String codeFixed;
	private String codeVal;
	private String codeDesc;
	
	/**
	 * @return the codeGroup
	 */
	public String getCodeGroup() {
		return codeGroup;
	}
	/**
	 * @param codeGroup the codeGroup to set
	 */
	public void setCodeGroup(String codeGroup) {
		this.codeGroup = codeGroup;
	}
	/**
	 * @return the codeKey
	 */
	public String getCodeKey() {
		return codeKey;
	}
	/**
	 * @param codeKey the codeKey to set
	 */
	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}
	/**
	 * @return the codeFixed
	 */
	public String getCodeFixed() {
		return codeFixed;
	}
	/**
	 * @param codeFixed the codeFixed to set
	 */
	public void setCodeFixed(String codeFixed) {
		this.codeFixed = codeFixed;
	}
	/**
	 * @return the codeVal
	 */
	public String getCodeVal() {
		return codeVal;
	}
	/**
	 * @param codeVal the codeVal to set
	 */
	public void setCodeVal(String codeVal) {
		this.codeVal = codeVal;
	}
	/**
	 * @return the codeDesc
	 */
	public String getCodeDesc() {
		return codeDesc;
	}
	/**
	 * @param codeDesc the codeDesc to set
	 */
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

}
