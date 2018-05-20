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
 *  @fileName : IpDto.java
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
 *     2016. 4. 20.        yongpal       create IpDto.java
 *  </pre>
 ******************************************************************************/
public class IpDto extends CommonDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String no;
	private String title;
	private String startIp;
	private String endIp;
	private String writeUser;
	private String regDate;
	private String modifyDate;
	
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the startIp
	 */
	public String getStartIp() {
		return startIp;
	}
	/**
	 * @param startIp the startIp to set
	 */
	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}
	/**
	 * @return the endIp
	 */
	public String getEndIp() {
		return endIp;
	}
	/**
	 * @param endIp the endIp to set
	 */
	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}
	/**
	 * @return the writeUser
	 */
	public String getWriteUser() {
		return writeUser;
	}
	/**
	 * @param writeUser the writeUser to set
	 */
	public void setWriteUser(String writeUser) {
		this.writeUser = writeUser;
	}
	/**
	 * @return the reqDate
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param reqDate the reqDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	/**
	 * @return the modifyDate
	 */
	public String getModifyDate() {
		return modifyDate;
	}
	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}	

}
