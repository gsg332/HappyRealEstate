/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.custom;

import org.springframework.web.multipart.MultipartFile;

import com.happyJ.realestate.model.schema.ItemDto;

/*****************************************************************************
 * 
 * @packageName : com.happyJ.realestate.model
 * @fileName : ApplyCustomDto.java
 * @author : yongpal
 * @since 2016. 4. 11.
 * @version 1.0
 * @see :
 * @revision : 2016. 4. 11.
 * 
 *           <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 11.        yongpal       create ApplyCustomDto.java
 *           </pre>
 ******************************************************************************/
public class ApplyCustomDto extends ItemDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private String phoneNum;
	private String level;
	private String expired;
	private String codeVal;
	private String modLimit;
	private String playCnt;
	private String itemResult;
	private String itemUse;
	private String resultCode;
	private String resultMemo;
	private String resultTime;
	private String statusNm;
	private String applyStatusNm;
	private String procTime;
	private String itemLatitude;
	private String itemLongitude;
	private String itemType;
	private String code1;
	private String code2;
	private String imageSrc;
	private String pastDate;
	private int veiDnLimitCountMinus;
	private String itemExportPollingIp;
	private String isDualVMS;
	private String unregistedType;

	/**
	 * 신청 Insert 시 생성된 시퀀스 값 리턴 받는 변수 *컬럼명 그대로 사용해야 함
	 */
	private int item_serial;

	/* 첨부파일 수정 시 사용 */
	private MultipartFile officialFile;
	private String officialFileNo;
	private String officialFileNm;
	private String officialFileTempNm;

	private MultipartFile pledgeFile;
	private String pledgeFileNo;
	private String pledgeFileNm;
	private String pledgeFileTempNm;

	/**
	 * true : 연장, false : 재신청
	 */
	private boolean extendFlag;

	/* 첨부파일 관련 조회 */
	private String[] attachFileNo;
	private int[] attachFileType;
	private String[] attachFileNm;
	private String[] attachFileTempNm;

	/* 검색 항목 */
	private String searchStDate;
	private String searchEdDate;
	private String searchDept1;
	private String searchDept2;
	private String searchDept3;
	private String searchStatus;
	private String searchApplyStatus;
	private String searchKind;
	private String searchWord;
	private String searchCrime;
	private String searchPosition;
	private String searchDepart;
	private String searchTeam;
	private String searchType;
	private String timeSlot00;
	private String timeSlot07;
	private String timeSlot18;
	private String timeSlot22;
	private String timeSlot03;

	/* 신청 목록 내 종합 카운트 관련 */
	private String totalCnt;
	private String yearCnt;
	private String waitingCnt;
	private String procCnt;
	private String allowCnt;
	private String deniedCnt;

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
	 * @return the codeVal
	 */
	public String getCodeVal() {
		return codeVal;
	}

	/**
	 * @param codeVal
	 *            the codeVal to set
	 */
	public void setCodeVal(String codeVal) {
		this.codeVal = codeVal;
	}

	/**
	 * @return the modLimit
	 */
	public String getModLimit() {
		return modLimit;
	}

	/**
	 * @param modLimit
	 *            the modLimit to set
	 */
	public void setModLimit(String modLimit) {
		this.modLimit = modLimit;
	}

	/**
	 * @return the itemResult
	 */
	public String getItemResult() {
		return itemResult;
	}

	/**
	 * @param itemResult
	 *            the itemResult to set
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
	 * @param itemUse
	 *            the itemUse to set
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
	 * @param resultCode
	 *            the resultCode to set
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
	 * @param resultMemo
	 *            the resultMemo to set
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
	 * @param resultTime
	 *            the resultTime to set
	 */
	public void setResultTime(String resultTime) {
		this.resultTime = resultTime;
	}

	/**
	 * @return the totalCnt
	 */
	public String getTotalCnt() {
		return totalCnt;
	}

	/**
	 * @param totalCnt
	 *            the totalCnt to set
	 */
	public void setTotalCnt(String totalCnt) {
		this.totalCnt = totalCnt;
	}

	/**
	 * @return the waitingCnt
	 */
	public String getWaitingCnt() {
		return waitingCnt;
	}

	/**
	 * @param waitingCnt
	 *            the waitingCnt to set
	 */
	public void setWaitingCnt(String waitingCnt) {
		this.waitingCnt = waitingCnt;
	}

	/**
	 * @return the procCnt
	 */
	public String getProcCnt() {
		return procCnt;
	}

	/**
	 * @param procCnt
	 *            the procCnt to set
	 */
	public void setProcCnt(String procCnt) {
		this.procCnt = procCnt;
	}

	/**
	 * @return the allowCnt
	 */
	public String getAllowCnt() {
		return allowCnt;
	}

	/**
	 * @param allowCnt
	 *            the allowCnt to set
	 */
	public void setAllowCnt(String allowCnt) {
		this.allowCnt = allowCnt;
	}

	/**
	 * @return the deniedCnt
	 */
	public String getDeniedCnt() {
		return deniedCnt;
	}

	/**
	 * @param deniedCnt
	 *            the deniedCnt to set
	 */
	public void setDeniedCnt(String deniedCnt) {
		this.deniedCnt = deniedCnt;
	}

	/**
	 * @return the attachFileNm
	 */
	public String[] getAttachFileNm() {
		return attachFileNm;
	}

	/**
	 * @param attachFileNm
	 *            the attachFileNm to set
	 */
	public void setAttachFileNm(String[] attachFileNm) {
		this.attachFileNm = attachFileNm;
	}

	/**
	 * @return the attachFileTempNm
	 */
	public String[] getAttachFileTempNm() {
		return attachFileTempNm;
	}

	/**
	 * @param attachFileTempNm
	 *            the attachFileTempNm to set
	 */
	public void setAttachFileTempNm(String[] attachFileTempNm) {
		this.attachFileTempNm = attachFileTempNm;
	}

	/**
	 * @return the yearCnt
	 */
	public String getYearCnt() {
		return yearCnt;
	}

	/**
	 * @param yearCnt
	 *            the yearCnt to set
	 */
	public void setYearCnt(String yearCnt) {
		this.yearCnt = yearCnt;
	}

	/**
	 * @return the searchStDate
	 */
	public String getSearchStDate() {
		return searchStDate;
	}

	/**
	 * @param searchStDate
	 *            the searchStDate to set
	 */
	public void setSearchStDate(String searchStDate) {
		this.searchStDate = searchStDate;
	}

	/**
	 * @return the searchEdDate
	 */
	public String getSearchEdDate() {
		return searchEdDate;
	}

	/**
	 * @param searchEdDate
	 *            the searchEdDate to set
	 */
	public void setSearchEdDate(String searchEdDate) {
		this.searchEdDate = searchEdDate;
	}

	/**
	 * @return the searchDept1
	 */
	public String getSearchDept1() {
		return searchDept1;
	}

	/**
	 * @param searchDept1
	 *            the searchDept1 to set
	 */
	public void setSearchDept1(String searchDept1) {
		this.searchDept1 = searchDept1;
	}

	/**
	 * @return the searchDept2
	 */
	public String getSearchDept2() {
		return searchDept2;
	}

	/**
	 * @param searchDept2
	 *            the searchDept2 to set
	 */
	public void setSearchDept2(String searchDept2) {
		this.searchDept2 = searchDept2;
	}

	/**
	 * @return the searchDept3
	 */
	public String getSearchDept3() {
		return searchDept3;
	}

	/**
	 * @param searchDept3
	 *            the searchDept3 to set
	 */
	public void setSearchDept3(String searchDept3) {
		this.searchDept3 = searchDept3;
	}

	/**
	 * @return the searchStatus
	 */
	public String getSearchStatus() {
		return searchStatus;
	}

	/**
	 * @param searchStatus
	 *            the searchStatus to set
	 */
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	/**
	 * @return the searchCrime
	 */
	public String getSearchCrime() {
		return searchCrime;
	}

	/**
	 * @param searchCrime
	 *            the searchCrime to set
	 */
	public void setSearchCrime(String searchCrime) {
		this.searchCrime = searchCrime;
	}

	/**
	 * @return the statusNm
	 */
	public String getStatusNm() {
		return statusNm;
	}

	/**
	 * @param statusNm
	 *            the statusNm to set
	 */
	public void setStatusNm(String statusNm) {
		this.statusNm = statusNm;
	}

	/**
	 * @return the extendFlag
	 */
	public boolean isExtendFlag() {
		return extendFlag;
	}

	/**
	 * @param extendFlag
	 *            the extendFlag to set
	 */
	public void setExtendFlag(boolean extendFlag) {
		this.extendFlag = extendFlag;
	}

	/**
	 * @return the item_serial
	 */
	public int getItem_serial() {
		return item_serial;
	}

	/**
	 * @param item_serial
	 *            the item_serial to set
	 */
	public void setItem_serial(int item_serial) {
		this.item_serial = item_serial;
	}

	/**
	 * @return the officialFile
	 */
	public MultipartFile getOfficialFile() {
		return officialFile;
	}

	/**
	 * @param officialFile
	 *            the officialFile to set
	 */
	public void setOfficialFile(MultipartFile officialFile) {
		this.officialFile = officialFile;
	}

	/**
	 * @return the pledgeFile
	 */
	public MultipartFile getPledgeFile() {
		return pledgeFile;
	}

	/**
	 * @param pledgeFile
	 *            the pledgeFile to set
	 */
	public void setPledgeFile(MultipartFile pledgeFile) {
		this.pledgeFile = pledgeFile;
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

	/**
	 * @return the procTime
	 */
	public String getProcTime() {
		return procTime;
	}

	/**
	 * @param procTime
	 *            the procTime to set
	 */
	public void setProcTime(String procTime) {
		this.procTime = procTime;
	}

	/**
	 * @return the attachFileNo
	 */
	public String[] getAttachFileNo() {
		return attachFileNo;
	}

	/**
	 * @param attachFileNo
	 *            the attachFileNo to set
	 */
	public void setAttachFileNo(String[] attachFileNo) {
		this.attachFileNo = attachFileNo;
	}

	public int[] getAttachFileType() {
		return attachFileType;
	}

	public void setAttachFileType(int[] attachFileType) {
		this.attachFileType = attachFileType;
	}

	/**
	 * @return the officialFileNo
	 */
	public String getOfficialFileNo() {
		return officialFileNo;
	}

	/**
	 * @param officialFileNo
	 *            the officialFileNo to set
	 */
	public void setOfficialFileNo(String officialFileNo) {
		this.officialFileNo = officialFileNo;
	}

	/**
	 * @return the pledgeFileNo
	 */
	public String getPledgeFileNo() {
		return pledgeFileNo;
	}

	/**
	 * @param pledgeFileNo
	 *            the pledgeFileNo to set
	 */
	public void setPledgeFileNo(String pledgeFileNo) {
		this.pledgeFileNo = pledgeFileNo;
	}

	/**
	 * @return the officialFileTempNm
	 */
	public String getOfficialFileTempNm() {
		return officialFileTempNm;
	}

	/**
	 * @param officialFileTempNm
	 *            the officialFileTempNm to set
	 */
	public void setOfficialFileTempNm(String officialFileTempNm) {
		this.officialFileTempNm = officialFileTempNm;
	}

	/**
	 * @return the pledgeFileTempNm
	 */
	public String getPledgeFileTempNm() {
		return pledgeFileTempNm;
	}

	/**
	 * @param pledgeFileTempNm
	 *            the pledgeFileTempNm to set
	 */
	public void setPledgeFileTempNm(String pledgeFileTempNm) {
		this.pledgeFileTempNm = pledgeFileTempNm;
	}

	/**
	 * @return the officialFileNm
	 */
	public String getOfficialFileNm() {
		return officialFileNm;
	}

	/**
	 * @param officialFileNm
	 *            the officialFileNm to set
	 */
	public void setOfficialFileNm(String officialFileNm) {
		this.officialFileNm = officialFileNm;
	}

	/**
	 * @return the pledgeFileNm
	 */
	public String getPledgeFileNm() {
		return pledgeFileNm;
	}

	/**
	 * @param pledgeFileNm
	 *            the pledgeFileNm to set
	 */
	public void setPledgeFileNm(String pledgeFileNm) {
		this.pledgeFileNm = pledgeFileNm;
	}

	/**
	 * @return the itemLatitude
	 */
	public String getItemLatitude() {
		return itemLatitude;
	}

	/**
	 * @param itemLatitude
	 *            the itemLatitude to set
	 */
	public void setItemLatitude(String itemLatitude) {
		this.itemLatitude = itemLatitude;
	}

	/**
	 * @return the itemLongitude
	 */
	public String getItemLongitude() {
		return itemLongitude;
	}

	/**
	 * @param itemLongitude
	 *            the itemLongitude to set
	 */
	public void setItemLongitude(String itemLongitude) {
		this.itemLongitude = itemLongitude;
	}

	/**
	 * @return the code1
	 */
	public String getCode1() {
		return code1;
	}

	/**
	 * @param code1
	 *            the code1 to set
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
	 * @param code2
	 *            the code2 to set
	 */
	public void setCode2(String code2) {
		this.code2 = code2;
	}

	/**
	 * @return the imageSrc
	 */
	public String getImageSrc() {
		return imageSrc;
	}

	/**
	 * @param imageSrc
	 *            the imageSrc to set
	 */
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * @param itemType
	 *            the itemType to set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	/**
	 * @return the pastDate
	 */
	public String getPastDate() {
		return pastDate;
	}

	/**
	 * @param pastDate
	 *            the pastDate to set
	 */
	public void setPastDate(String pastDate) {
		this.pastDate = pastDate;
	}

	/**
	 * @return the searchPosition
	 */
	public String getSearchPosition() {
		return searchPosition;
	}

	/**
	 * @param searchPosition
	 *            the searchPosition to set
	 */
	public void setSearchPosition(String searchPosition) {
		this.searchPosition = searchPosition;
	}

	/**
	 * @return the searchDepart
	 */
	public String getSearchDepart() {
		return searchDepart;
	}

	/**
	 * @param searchDepart
	 *            the searchDepart to set
	 */
	public void setSearchDepart(String searchDepart) {
		this.searchDepart = searchDepart;
	}

	/**
	 * @return the searchTeam
	 */
	public String getSearchTeam() {
		return searchTeam;
	}

	/**
	 * @param searchTeam
	 *            the searchTeam to set
	 */
	public void setSearchTeam(String searchTeam) {
		this.searchTeam = searchTeam;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/**
	 * @return the playCnt
	 */
	public String getPlayCnt() {
		return playCnt;
	}

	/**
	 * @param playCnt
	 *            the playCnt to set
	 */
	public void setPlayCnt(String playCnt) {
		this.playCnt = playCnt;
	}

	/**
	 * @return the veiDnLimitCountMinus
	 */
	public int getVeiDnLimitCountMinus() {
		return veiDnLimitCountMinus;
	}

	/**
	 * @param veiDnLimitCountMinus
	 *            the veiDnLimitCountMinus to set
	 */
	public void setVeiDnLimitCountMinus(int veiDnLimitCountMinus) {
		this.veiDnLimitCountMinus = veiDnLimitCountMinus;
	}

	/**
	 * @return the timeSlot00
	 */
	public String getTimeSlot00() {
		return timeSlot00;
	}

	/**
	 * @param timeSlot00
	 *            the timeSlot00 to set
	 */
	public void setTimeSlot00(String timeSlot00) {
		this.timeSlot00 = timeSlot00;
	}

	/**
	 * @return the timeSlot07
	 */
	public String getTimeSlot07() {
		return timeSlot07;
	}

	/**
	 * @param timeSlot07
	 *            the timeSlot07 to set
	 */
	public void setTimeSlot07(String timeSlot07) {
		this.timeSlot07 = timeSlot07;
	}

	/**
	 * @return the timeSlot18
	 */
	public String getTimeSlot18() {
		return timeSlot18;
	}

	/**
	 * @param timeSlot18
	 *            the timeSlot18 to set
	 */
	public void setTimeSlot18(String timeSlot18) {
		this.timeSlot18 = timeSlot18;
	}

	/**
	 * @return the timeSlot22
	 */
	public String getTimeSlot22() {
		return timeSlot22;
	}

	/**
	 * @param timeSlot22
	 *            the timeSlot22 to set
	 */
	public void setTimeSlot22(String timeSlot22) {
		this.timeSlot22 = timeSlot22;
	}

	/**
	 * @return the timeSlot03
	 */
	public String getTimeSlot03() {
		return timeSlot03;
	}

	/**
	 * @param timeSlot03
	 *            the timeSlot03 to set
	 */
	public void setTimeSlot03(String timeSlot03) {
		this.timeSlot03 = timeSlot03;
	}

	/**
	 * @return the applyStatusNm
	 */
	public String getApplyStatusNm() {
		return applyStatusNm;
	}

	/**
	 * @param applyStatusNm
	 *            the applyStatusNm to set
	 */
	public void setApplyStatusNm(String applyStatusNm) {
		this.applyStatusNm = applyStatusNm;
	}

	/**
	 * @return the searchApplyStatus
	 */
	public String getSearchApplyStatus() {
		return searchApplyStatus;
	}

	/**
	 * @param searchApplyStatus
	 *            the searchApplyStatus to set
	 */
	public void setSearchApplyStatus(String searchApplyStatus) {
		this.searchApplyStatus = searchApplyStatus;
	}

	/**
	 * @return the itemExportPollingIp
	 */
	public String getItemExportPollingIp() {
		return itemExportPollingIp;
	}

	/**
	 * @param itemExportPollingIp
	 *            the itemExportPollingIp to set
	 */
	public void setItemExportPollingIp(String itemExportPollingIp) {
		this.itemExportPollingIp = itemExportPollingIp;
	}

	public String getIsDualVMS() {
		return isDualVMS;
	}

	public void setIsDualVMS(String isDualVMS) {
		this.isDualVMS = isDualVMS;
	}

	public String getUnregistedType() {
		return unregistedType;
	}

	public void setUnregistedType(String unregistedType) {
		this.unregistedType = unregistedType;
	}

}
