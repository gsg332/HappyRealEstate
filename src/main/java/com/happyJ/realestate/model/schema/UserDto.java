/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 4. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

import org.springframework.web.multipart.MultipartFile;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 * @packageName : com.happyJ.realestate.model
 * @fileName : UserDto.java
 * @author : yongpal
 * @since 2016. 4. 4.
 * @version 1.0
 * @see :
 * @revision : 2016. 4. 4.
 * 
 *           <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 4.        yongpal       create UserDto.java
 *           </pre>
 ******************************************************************************/
public class UserDto extends CommonDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4310949842905358546L;

	private String userId;
	private String password;
	private String level;
	private String expired;
	private String position;
	private String depart;
	private String team;
	private String userName;
	private String phoneNum;
	private String permit;
	private String rejectReason;
	private String useLimitDate;
	private String passwordLimitDate;
	private String smsReceive;
	private String departModifyingYn;
	private String quittingYn;
	private String chgDepart;
	private String chgPasswordFlag;

	/* 검색 항목 */
	private String searchKind;
	private String searchWord;

	private MultipartFile pledgeFile;
	private String pledgeFileNo;
	private String pledgeFileNm;
	private String pledgeFileTempNm;

	/* 첨부파일 관련 조회 */
	private String[] attachFileNo;
	private int[] attachFileType;
	private String[] attachFileNm;
	private String[] attachFileTempNm;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the expired
	 */
	public String getExpired() {
		return expired;
	}

	/**
	 * @param expired
	 *            the expired to set
	 */
	public void setExpired(String expired) {
		this.expired = expired;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
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
	 * @param depart
	 *            the depart to set
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
	 * @param team
	 *            the team to set
	 */
	public void setTeam(String team) {
		this.team = team;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
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
	 * @param phoneNum
	 *            the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * @return the permit
	 */
	public String getPermit() {
		return permit;
	}

	/**
	 * @param permit
	 *            the permit to set
	 */
	public void setPermit(String permit) {
		this.permit = permit;
	}

	/**
	 * @return the rejectReason
	 */
	public String getRejectReason() {
		return rejectReason;
	}

	/**
	 * @param rejectReason
	 *            the rejectReason to set
	 */
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	/**
	 * @return the useLimitDate
	 */
	public String getUseLimitDate() {
		return useLimitDate;
	}

	/**
	 * @param useLimitDate
	 *            the useLimitDate to set
	 */
	public void setUseLimitDate(String useLimitDate) {
		this.useLimitDate = useLimitDate;
	}

	/**
	 * @return the passwordLimitDate
	 */
	public String getPasswordLimitDate() {
		return passwordLimitDate;
	}

	/**
	 * @param passwordLimitDate
	 *            the passwordLimitDate to set
	 */
	public void setPasswordLimitDate(String passwordLimitDate) {
		this.passwordLimitDate = passwordLimitDate;
	}

	/**
	 * @return the smsReceive
	 */
	public String getSmsReceive() {
		return smsReceive;
	}

	/**
	 * @param smsReceive
	 *            the smsReceive to set
	 */
	public void setSmsReceive(String smsReceive) {
		this.smsReceive = smsReceive;
	}

	/**
	 * @return the departModifyingYn
	 */
	public String getDepartModifyingYn() {
		return departModifyingYn;
	}

	/**
	 * @param departModifyingYn
	 *            the departModifyingYn to set
	 */
	public void setDepartModifyingYn(String departModifyingYn) {
		this.departModifyingYn = departModifyingYn;
	}

	/**
	 * @return the quittingYn
	 */
	public String getQuittingYn() {
		return quittingYn;
	}

	/**
	 * @param quittingYn
	 *            the quittingYn to set
	 */
	public void setQuittingYn(String quittingYn) {
		this.quittingYn = quittingYn;
	}

	/**
	 * @return the chgDepart
	 */
	public String getChgDepart() {
		return chgDepart;
	}

	/**
	 * @param chgDepart
	 *            the chgDepart to set
	 */
	public void setChgDepart(String chgDepart) {
		this.chgDepart = chgDepart;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the searchKind
	 */
	public String getSearchKind() {
		return searchKind;
	}

	/**
	 * @param searchKind
	 *            the searchKind to set
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
	 * @param searchWord
	 *            the searchWord to set
	 */
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public MultipartFile getPledgeFile() {
		return pledgeFile;
	}

	public void setPledgeFile(MultipartFile pledgeFile) {
		this.pledgeFile = pledgeFile;
	}

	public String getPledgeFileNo() {
		return pledgeFileNo;
	}

	public void setPledgeFileNo(String pledgeFileNo) {
		this.pledgeFileNo = pledgeFileNo;
	}

	public String getPledgeFileNm() {
		return pledgeFileNm;
	}

	public void setPledgeFileNm(String pledgeFileNm) {
		this.pledgeFileNm = pledgeFileNm;
	}

	public String getPledgeFileTempNm() {
		return pledgeFileTempNm;
	}

	public void setPledgeFileTempNm(String pledgeFileTempNm) {
		this.pledgeFileTempNm = pledgeFileTempNm;
	}

	public String[] getAttachFileNo() {
		return attachFileNo;
	}

	public void setAttachFileNo(String[] attachFileNo) {
		this.attachFileNo = attachFileNo;
	}

	public int[] getAttachFileType() {
		return attachFileType;
	}

	public void setAttachFileType(int[] attachFileType) {
		this.attachFileType = attachFileType;
	}

	public String[] getAttachFileNm() {
		return attachFileNm;
	}

	public void setAttachFileNm(String[] attachFileNm) {
		this.attachFileNm = attachFileNm;
	}

	public String[] getAttachFileTempNm() {
		return attachFileTempNm;
	}

	public void setAttachFileTempNm(String[] attachFileTempNm) {
		this.attachFileTempNm = attachFileTempNm;
	}

	/**
	 * @return the chgPasswordFlag
	 */
	public String getChgPasswordFlag() {
		return chgPasswordFlag;
	}

	/**
	 * @param chgPasswordFlag the chgPasswordFlag to set
	 */
	public void setChgPasswordFlag(String chgPasswordFlag) {
		this.chgPasswordFlag = chgPasswordFlag;
	}

	

}
