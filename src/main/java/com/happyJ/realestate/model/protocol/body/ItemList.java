/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 28. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.protocol.body;

import java.util.List;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.protocol.body
 *  @fileName : ItemList.java
 *  @author : yongpal
 *  @since 2016. 7. 28.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 7. 28.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 7. 28.        yongpal       create ItemList.java
 *  </pre>
 ******************************************************************************/
public class ItemList {

	private String itemSerial;
	private String userId;
	private String userName;
	private String phoneNum;
	private int level;
	private String position;
	private String depart;
	private String team;
	private String expired;
	private String codeVal;
	private String reqUserId;
	private String reqDate;
	private String reqUsername;
	private String reqReasonCode;
	private String reqReason;
	private String reqPosition;
	private String videoInfo;
	private List<VideoInfos> videoInfos;
	private String videoStart;
	private String videoEnd;
	private String videoPosition;
	private String veiLicenseCode;
	private int veiLimitCount;
	private String veiLimitDatetime;
	private String veiCryptKey;
	private String veiStatus;
	private String veiStatusNm;
	private String veiRejectReason;
	private String veiDnLimitDate;
	private int veiDnLimitCount;
	private String veiReqType;
	private String extentionApply;
	private int modLimit;
	private String itemResult;
	private String itemUse;
	private String resultCode;
	private String resultMemo;
	private String resultTime;
	
	
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
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
	 * @return the expired
	 */
	public String getExpired() {
		return expired;
	}
	/**
	 * @param expired the expired to set
	 */
	public void setExpired(String expired) {
		this.expired = expired;
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
	 * @return the veiStatusNm
	 */
	public String getVeiStatusNm() {
		return veiStatusNm;
	}
	/**
	 * @param veiStatusNm the veiStatusNm to set
	 */
	public void setVeiStatusNm(String veiStatusNm) {
		this.veiStatusNm = veiStatusNm;
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
	 * @return the modLimit
	 */
	public int getModLimit() {
		return modLimit;
	}
	/**
	 * @param modLimit the modLimit to set
	 */
	public void setModLimit(int modLimit) {
		this.modLimit = modLimit;
	}
	/**
	 * @return the itemResult
	 */
	public String getItemResult() {
		return itemResult;
	}
	/**
	 * @param itemResult the itemResult to set
	 */
	public void setItemResult(String itemResult) {
		this.itemResult = itemResult;
	}
	/**
	 * @return the itemUse
	 */
	public String getItemUse() {
		return itemUse;
	}
	/**
	 * @param itemUse the itemUse to set
	 */
	public void setItemUse(String itemUse) {
		this.itemUse = itemUse;
	}
	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}
	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	/**
	 * @return the resultMemo
	 */
	public String getResultMemo() {
		return resultMemo;
	}
	/**
	 * @param resultMemo the resultMemo to set
	 */
	public void setResultMemo(String resultMemo) {
		this.resultMemo = resultMemo;
	}
	/**
	 * @return the resultTime
	 */
	public String getResultTime() {
		return resultTime;
	}
	/**
	 * @param resultTime the resultTime to set
	 */
	public void setResultTime(String resultTime) {
		this.resultTime = resultTime;
	}
	/**
	 * @return the videoInfo
	 */
	public String getVideoInfo() {
		return videoInfo;
	}
	/**
	 * @param videoInfo the videoInfo to set
	 */
	public void setVideoInfo(String videoInfo) {
		this.videoInfo = videoInfo;
	}
	/**
	 * @return the videoInfos
	 */
	public List<VideoInfos> getVideoInfos() {
		return videoInfos;
	}
	/**
	 * @param videoInfos the videoInfos to set
	 */
	public void setVideoInfos(List<VideoInfos> videoInfos) {
		this.videoInfos = videoInfos;
	}

}
