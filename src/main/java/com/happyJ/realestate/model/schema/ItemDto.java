/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model
 *  @fileName : VesItemDto.java
 *  @author : yongpal
 *  @since 2016. 4. 11.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 11.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 11.        yongpal       create VesItemDto.java
 *  </pre>
 ******************************************************************************/
public class ItemDto extends CommonDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4738051218833357753L;
	
	private String itemSerial;
	private String userId;
	private String reqUserId;
	private String reqDate;
	private String position;
	private String depart;
	private String team;
	private String reqUsername;
	private String reqPhone;
	private String reqReasonCode;
	private String reqReasoncodeReason;
	private String reqReason;
	private String reqPosition;
	private String videoId;
	private String videoGroup;
	private String videoStart;
	private String videoEnd;
	private String videoPosition;
	private String mediaCode;
	private String veiLicenseCode;
	private int veiLimitCount;
	private String veiLimitDatetime;
	private String veiLimitDays;
	private String veiCryptKey;
	private String veiStatus;
	private String veiApplyStatus;
	private String veiDnLimitDate;
	private int veiDnLimitCount;
	private String investigatingDate;
	private String veiRejectReason;
	private String addr1;
	private String addr2;
	private String addr3;
	private String addr4;
	private double lat;
	private double lng;
	private String veiDocNo;
	private String veiReqType;
	private String viewerBirthday;
	private String viewerAddress;
	private String viewerReason;
	private String extentionApply;
	private String orgItemSerial;
	private String reapplyReason;
	private String approvalDate;
	private String approvingDate;
	private String rejectDate;
	private String processDate;
	private String fileuploadVol;
	private String fileuploadSpd;
	private String filedownloadDate;
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
	 * @return the reqUserId
	 */
	public String getReqUserId() {
		return reqUserId;
	}
	/**
	 * @param reqUserId the reqUserId to set
	 */
	public void setReqUserId(String reqUserId) {
		this.reqUserId = reqUserId;
	}
	/**
	 * @return the reqDate
	 */
	public String getReqDate() {
		return reqDate;
	}
	/**
	 * @param reqDate the reqDate to set
	 */
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the depart
	 */
	public String getDepart() {
		return depart;
	}
	/**
	 * @param depart the depart to set
	 */
	public void setDepart(String depart) {
		this.depart = depart;
	}
	/**
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}
	/**
	 * @param team the team to set
	 */
	public void setTeam(String team) {
		this.team = team;
	}
	/**
	 * @return the reqUsername
	 */
	public String getReqUsername() {
		return reqUsername;
	}
	/**
	 * @param reqUsername the reqUsername to set
	 */
	public void setReqUsername(String reqUsername) {
		this.reqUsername = reqUsername;
	}
	/**
	 * @return the reqPhone
	 */
	public String getReqPhone() {
		return reqPhone;
	}
	/**
	 * @param reqPhone the reqUhone to set
	 */
	public void setReqPhone(String reqPhone) {
		this.reqPhone = reqPhone;
	}
	/**
	 * @return the reqReasoncodeReason
	 */
	public String getReqReasoncodeReason() {
		return reqReasoncodeReason;
	}
	/**
	 * @param reqReasoncodeReason the reqReasoncodeReason to set
	 */
	public void setReqReasoncodeReason(String reqReasoncodeReason) {
		this.reqReasoncodeReason = reqReasoncodeReason;
	}
	/**
	 * @return the reqReason
	 */
	public String getReqReason() {
		return reqReason;
	}
	/**
	 * @param reqReason the reqReason to set
	 */
	public void setReqReason(String reqReason) {
		this.reqReason = reqReason;
	}
	/**
	 * @return the reqPosition
	 */
	public String getReqPosition() {
		return reqPosition;
	}
	/**
	 * @param reqPosition the reqPosition to set
	 */
	public void setReqPosition(String reqPosition) {
		this.reqPosition = reqPosition;
	}
	/**
	 * @return the videoId
	 */
	public String getVideoId() {
		return videoId;
	}
	/**
	 * @param videoId the videoId to set
	 */
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	/**
	 * @return the videoGroup
	 */
	public String getVideoGroup() {
		return videoGroup;
	}
	/**
	 * @param videoGroup the videoGroup to set
	 */
	public void setVideoGroup(String videoGroup) {
		this.videoGroup = videoGroup;
	}
	/**
	 * @return the videoStart
	 */
	public String getVideoStart() {
		return videoStart;
	}
	/**
	 * @param videoStart the videoStart to set
	 */
	public void setVideoStart(String videoStart) {
		this.videoStart = videoStart;
	}
	/**
	 * @return the videoEnd
	 */
	public String getVideoEnd() {
		return videoEnd;
	}
	/**
	 * @param videoEnd the videoEnd to set
	 */
	public void setVideoEnd(String videoEnd) {
		this.videoEnd = videoEnd;
	}
	/**
	 * @return the videoPosition
	 */
	public String getVideoPosition() {
		return videoPosition;
	}
	/**
	 * @param videoPosition the videoPosition to set
	 */
	public void setVideoPosition(String videoPosition) {
		this.videoPosition = videoPosition;
	}
	/**
	 * @return the mediaCode
	 */
	public String getMediaCode() {
		return mediaCode;
	}
	/**
	 * @param mediaCode the mediaCode to set
	 */
	public void setMediaCode(String mediaCode) {
		this.mediaCode = mediaCode;
	}
	/**
	 * @return the veiLicenseCode
	 */
	public String getVeiLicenseCode() {
		return veiLicenseCode;
	}
	/**
	 * @param veiLicenseCode the veiLicenseCode to set
	 */
	public void setVeiLicenseCode(String veiLicenseCode) {
		this.veiLicenseCode = veiLicenseCode;
	}
	/**
	 * @return the veiLimitCount
	 */
	public int getVeiLimitCount() {
		return veiLimitCount;
	}
	/**
	 * @param veiLimitCount the veiLimitCount to set
	 */
	public void setVeiLimitCount(int veiLimitCount) {
		this.veiLimitCount = veiLimitCount;
	}
	/**
	 * @return the veiLimitDatetime
	 */
	public String getVeiLimitDatetime() {
		return veiLimitDatetime;
	}
	/**
	 * @param veiLimitDatetime the veiLimitDatetime to set
	 */
	public void setVeiLimitDatetime(String veiLimitDatetime) {
		this.veiLimitDatetime = veiLimitDatetime;
	}
	public String getVeiLimitDays() {
		return veiLimitDays;
	}
	public void setVeiLimitDays(String veiLimitDays) {
		this.veiLimitDays = veiLimitDays;
	}
	/**
	 * @return the veiCryptKey
	 */
	public String getVeiCryptKey() {
		return veiCryptKey;
	}
	/**
	 * @param veiCryptKey the veiCryptKey to set
	 */
	public void setVeiCryptKey(String veiCryptKey) {
		this.veiCryptKey = veiCryptKey;
	}
	/**
	 * @return the veiStatus
	 */
	public String getVeiStatus() {
		return veiStatus;
	}
	/**
	 * @param veiStatus the veiStatus to set
	 */
	public void setVeiStatus(String veiStatus) {
		this.veiStatus = veiStatus;
	}
	/**
	 * @return the veiDnLimitDate
	 */
	public String getVeiDnLimitDate() {
		return veiDnLimitDate;
	}
	/**
	 * @param veiDnLimitDate the veiDnLimitDate to set
	 */
	public void setVeiDnLimitDate(String veiDnLimitDate) {
		this.veiDnLimitDate = veiDnLimitDate;
	}
	/**
	 * @return the veiDnLimitCount
	 */
	public int getVeiDnLimitCount() {
		return veiDnLimitCount;
	}
	/**
	 * @param veiDnLimitCount the veiDnLimitCount to set
	 */
	public void setVeiDnLimitCount(int veiDnLimitCount) {
		this.veiDnLimitCount = veiDnLimitCount;
	}
	public String getInvestigatingDate() {
		return investigatingDate;
	}
	public void setInvestigatingDate(String investigatingDate) {
		this.investigatingDate = investigatingDate;
	}
	/**
	 * @return the veiRejectReason
	 */
	public String getVeiRejectReason() {
		return veiRejectReason;
	}
	/**
	 * @param veiRejectReason the veiRejectReason to set
	 */
	public void setVeiRejectReason(String veiRejectReason) {
		this.veiRejectReason = veiRejectReason;
	}
	/**
	 * @return the addr1
	 */
	public String getAddr1() {
		return addr1;
	}
	/**
	 * @param addr1 the addr1 to set
	 */
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	/**
	 * @return the addr2
	 */
	public String getAddr2() {
		return addr2;
	}
	/**
	 * @param addr2 the addr2 to set
	 */
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	/**
	 * @return the addr3
	 */
	public String getAddr3() {
		return addr3;
	}
	/**
	 * @param addr3 the addr3 to set
	 */
	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}
	/**
	 * @return the addr4
	 */
	public String getAddr4() {
		return addr4;
	}
	/**
	 * @param addr4 the addr4 to set
	 */
	public void setAddr4(String addr4) {
		this.addr4 = addr4;
	}
	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
	/**
	 * @return the lng
	 */
	public double getLng() {
		return lng;
	}
	/**
	 * @param lng the lng to set
	 */
	public void setLng(double lng) {
		this.lng = lng;
	}
	/**
	 * @return the veiDocNo
	 */
	public String getVeiDocNo() {
		return veiDocNo;
	}
	/**
	 * @param veiDocNo the veiDocNo to set
	 */
	public void setVeiDocNo(String veiDocNo) {
		this.veiDocNo = veiDocNo;
	}
	/**
	 * @return the veiReqType
	 */
	public String getVeiReqType() {
		return veiReqType;
	}
	/**
	 * @param veiReqType the veiReqType to set
	 */
	public void setVeiReqType(String veiReqType) {
		this.veiReqType = veiReqType;
	}
	/**
	 * @return the viewerBirthday
	 */
	public String getViewerBirthday() {
		return viewerBirthday;
	}
	/**
	 * @param viewerBirthday the viewerBirthday to set
	 */
	public void setViewerBirthday(String viewerBirthday) {
		this.viewerBirthday = viewerBirthday;
	}
	/**
	 * @return the viewerAddress
	 */
	public String getViewerAddress() {
		return viewerAddress;
	}
	/**
	 * @param viewerAddress the viewerAddress to set
	 */
	public void setViewerAddress(String viewerAddress) {
		this.viewerAddress = viewerAddress;
	}
	/**
	 * @return the viewerReason
	 */
	public String getViewerReason() {
		return viewerReason;
	}
	/**
	 * @param viewerReason the viewerReason to set
	 */
	public void setViewerReason(String viewerReason) {
		this.viewerReason = viewerReason;
	}
	/**
	 * @return the extentionApply
	 */
	public String getExtentionApply() {
		return extentionApply;
	}
	/**
	 * @param extentionApply the extentionApply to set
	 */
	public void setExtentionApply(String extentionApply) {
		this.extentionApply = extentionApply;
	}
	/**
	 * @return the orgItemSerial
	 */
	public String getOrgItemSerial() {
		return orgItemSerial;
	}
	/**
	 * @param orgItemSerial the orgItemSerial to set
	 */
	public void setOrgItemSerial(String orgItemSerial) {
		this.orgItemSerial = orgItemSerial;
	}
	/**
	 * @return the reapplyReason
	 */
	public String getReapplyReason() {
		return reapplyReason;
	}
	/**
	 * @param reapplyReason the reapplyReason to set
	 */
	public void setReapplyReason(String reapplyReason) {
		this.reapplyReason = reapplyReason;
	}
	/**
	 * @return the approvalDate
	 */
	public String getApprovalDate() {
		return approvalDate;
	}
	/**
	 * @param approvalDate the approvalDate to set
	 */
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}
	
	/**
	 * @return the approvingDate
	 */
	public String getApprovingDate() {
		return approvingDate;
	}
	/**
	 * @param approvingDate the approvingDate to set
	 */
	public void setApprovingDate(String approvingDate) {
		this.approvingDate = approvingDate;
	}
	/**
	 * @return the rejectDate
	 */
	public String getRejectDate() {
		return rejectDate;
	}
	/**
	 * @param rejectDate the rejectDate to set
	 */
	public void setRejectDate(String rejectDate) {
		this.rejectDate = rejectDate;
	}
	/**
	 * @return the processDate
	 */
	public String getProcessDate() {
		return processDate;
	}
	/**
	 * @param processDate the processDate to set
	 */
	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}
	/**
	 * @return the fileuploadVol
	 */
	public String getFileuploadVol() {
		return fileuploadVol;
	}
	/**
	 * @param fileuploadVol the fileuploadVol to set
	 */
	public void setFileuploadVol(String fileuploadVol) {
		this.fileuploadVol = fileuploadVol;
	}
	/**
	 * @return the fileuploadSpd
	 */
	public String getFileuploadSpd() {
		return fileuploadSpd;
	}
	/**
	 * @param fileuploadSpd the fileuploadSpd to set
	 */
	public void setFileuploadSpd(String fileuploadSpd) {
		this.fileuploadSpd = fileuploadSpd;
	}
	/**
	 * @return the filedownloadDate
	 */
	public String getFiledownloadDate() {
		return filedownloadDate;
	}
	/**
	 * @param filedownloadDate the filedownloadDate to set
	 */
	public void setFiledownloadDate(String filedownloadDate) {
		this.filedownloadDate = filedownloadDate;
	}
	/**
	 * @return the reqReasonCode
	 */
	public String getReqReasonCode() {
		return reqReasonCode;
	}
	/**
	 * @param reqReasonCode the reqReasonCode to set
	 */
	public void setReqReasonCode(String reqReasonCode) {
		this.reqReasonCode = reqReasonCode;
	}
	/**
	 * @return the veiApplyStatus
	 */
	public String getVeiApplyStatus() {
		return veiApplyStatus;
	}
	/**
	 * @param veiApplyStatus the veiApplyStatus to set
	 */
	public void setVeiApplyStatus(String veiApplyStatus) {
		this.veiApplyStatus = veiApplyStatus;
	}
	
}
