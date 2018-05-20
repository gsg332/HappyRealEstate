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
 * @packageName : com.happyJ.realestate.model.schema
 * @fileName : ConfigSendDto.java
 * @author : yongpal
 * @since 2016. 6. 2.
 * @version 1.0
 * @see :
 * @revision : 2016. 6. 2.
 * 
 *           <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 6. 2.        yongpal       create ConfigSendDto.java
 *           </pre>
 ******************************************************************************/
public class ConfigSendDto extends CommonDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String vmsLink;
	private String realMaskingMode;
	private String realInterlockingVMSUserPass;
	private String realInterlockingVMSUserID;
	private String realInterlockingVMSPort;
	private String realInterlockingVMSHost;
	private String realInterlockingVMSCode;
	private String realFTPUserID;
	private String realFTPPort;
	private String realFTPPass;
	private String realFTPHost;
	private String realExportAutoMode;
	private String exportManagerPollingPeriod;
	private String cameraSelect;

	/**
	 * @return the vmsLink
	 */
	public String getVmsLink() {
		return vmsLink;
	}

	/**
	 * @param vmsLink
	 *            the vmsLink to set
	 */
	public void setVmsLink(String vmsLink) {
		this.vmsLink = vmsLink;
	}

	/**
	 * @return the realMaskingMode
	 */
	public String getRealMaskingMode() {
		return realMaskingMode;
	}

	/**
	 * @param realMaskingMode
	 *            the realMaskingMode to set
	 */
	public void setRealMaskingMode(String realMaskingMode) {
		this.realMaskingMode = realMaskingMode;
	}

	/**
	 * @return the realInterlockingVMSUserPass
	 */
	public String getRealInterlockingVMSUserPass() {
		return realInterlockingVMSUserPass;
	}

	/**
	 * @param realInterlockingVMSUserPass
	 *            the realInterlockingVMSUserPass to set
	 */
	public void setRealInterlockingVMSUserPass(String realInterlockingVMSUserPass) {
		this.realInterlockingVMSUserPass = realInterlockingVMSUserPass;
	}

	/**
	 * @return the realInterlockingVMSUserID
	 */
	public String getRealInterlockingVMSUserID() {
		return realInterlockingVMSUserID;
	}

	/**
	 * @param realInterlockingVMSUserID
	 *            the realInterlockingVMSUserID to set
	 */
	public void setRealInterlockingVMSUserID(String realInterlockingVMSUserID) {
		this.realInterlockingVMSUserID = realInterlockingVMSUserID;
	}

	/**
	 * @return the realInterlockingVMSPort
	 */
	public String getRealInterlockingVMSPort() {
		return realInterlockingVMSPort;
	}

	/**
	 * @param realInterlockingVMSPort
	 *            the realInterlockingVMSPort to set
	 */
	public void setRealInterlockingVMSPort(String realInterlockingVMSPort) {
		this.realInterlockingVMSPort = realInterlockingVMSPort;
	}

	/**
	 * @return the realInterlockingVMSHost
	 */
	public String getRealInterlockingVMSHost() {
		return realInterlockingVMSHost;
	}

	/**
	 * @param realInterlockingVMSHost
	 *            the realInterlockingVMSHost to set
	 */
	public void setRealInterlockingVMSHost(String realInterlockingVMSHost) {
		this.realInterlockingVMSHost = realInterlockingVMSHost;
	}

	/**
	 * @return the realInterlockingVMSCode
	 */
	public String getRealInterlockingVMSCode() {
		return realInterlockingVMSCode;
	}

	/**
	 * @param realInterlockingVMSCode
	 *            the realInterlockingVMSCode to set
	 */
	public void setRealInterlockingVMSCode(String realInterlockingVMSCode) {
		this.realInterlockingVMSCode = realInterlockingVMSCode;
	}

	/**
	 * @return the realFTPUserID
	 */
	public String getRealFTPUserID() {
		return realFTPUserID;
	}

	/**
	 * @param realFTPUserID
	 *            the realFTPUserID to set
	 */
	public void setRealFTPUserID(String realFTPUserID) {
		this.realFTPUserID = realFTPUserID;
	}

	/**
	 * @return the realFTPPort
	 */
	public String getRealFTPPort() {
		return realFTPPort;
	}

	/**
	 * @param realFTPPort
	 *            the realFTPPort to set
	 */
	public void setRealFTPPort(String realFTPPort) {
		this.realFTPPort = realFTPPort;
	}

	/**
	 * @return the realFTPPass
	 */
	public String getRealFTPPass() {
		return realFTPPass;
	}

	/**
	 * @param realFTPPass
	 *            the realFTPPass to set
	 */
	public void setRealFTPPass(String realFTPPass) {
		this.realFTPPass = realFTPPass;
	}

	/**
	 * @return the realFTPHost
	 */
	public String getRealFTPHost() {
		return realFTPHost;
	}

	/**
	 * @param realFTPHost
	 *            the realFTPHost to set
	 */
	public void setRealFTPHost(String realFTPHost) {
		this.realFTPHost = realFTPHost;
	}

	/**
	 * @return the realExportAutoMode
	 */
	public String getRealExportAutoMode() {
		return realExportAutoMode;
	}

	/**
	 * @param realExportAutoMode
	 *            the realExportAutoMode to set
	 */
	public void setRealExportAutoMode(String realExportAutoMode) {
		this.realExportAutoMode = realExportAutoMode;
	}

	/**
	 * @return the exportManagerPollingPeriod
	 */
	public String getExportManagerPollingPeriod() {
		return exportManagerPollingPeriod;
	}

	/**
	 * @param exportManagerPollingPeriod
	 *            the exportManagerPollingPeriod to set
	 */
	public void setExportManagerPollingPeriod(String exportManagerPollingPeriod) {
		this.exportManagerPollingPeriod = exportManagerPollingPeriod;
	}

	/**
	 * @return the cameraSelect
	 */
	public String getCameraSelect() {
		return cameraSelect;
	}

	/**
	 * @param cameraSelect
	 *            the cameraSelect to set
	 */
	public void setCameraSelect(String cameraSelect) {
		this.cameraSelect = cameraSelect;
	}

}
