/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.custom;

import com.happyJ.realestate.model.schema.ItemDto;

/*****************************************************************************
 * 
 * @packageName : com.happyJ.realestate.model
 * @fileName : ApplyStatCustomDto.java
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
 *     2016. 4. 11.        yongpal       create ApplyStatCustomDto.java
 *           </pre>
 ******************************************************************************/
public class ApplyStatCustomDto extends ItemDto {

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
	private String itemResult;
	private String itemUse;
	private String resultCode;
	private String resultMemo;
	private String resultTime;
	private String statusNm;
	private String procTime;

	/**
	 * 신청 Insert 시 생성된 시퀀스 값 리턴 받는 변수 *컬럼명 그대로 사용해야 함
	 */
	private int item_serial;

	/* 검색 항목 */
	private String searchStDate;
	private String searchEdDate;
	private String searchDept1;
	private String searchDept2;
	private String searchDept3;
	private String searchStatus;
	private String searchKind;
	private String searchWord;
	private String searchCrime;
	private String dateFlag;
	private String yearly;
	private String monthly;

	private String searchPosition;
	private String searchDepart;
	private String searchTeam;

	/* 신청 목록 내 종합 카운트 관련 */
	private String totalCnt;
	private String yearCnt;
	private String waitingCnt;
	private String procCnt;
	private String allowCnt;
	private String deniedCnt;

	/* 신청현황 월별 그래프 관련 */
	private String applyDate;

	/* 활용 결과 입력 관련 */
	private String resultTotalCnt;
	private String notResultCnt;
	private String resultCnt;

	/* 만료 예정 영상 관련 */
	private String limitDateTotalCnt;

	/* 기여율(해결사건) 관련 */
	private String solveCnt;
	private String useSolveCnt;
	private String notUseSolveCnt;

	/* 범죄유형 관련 */
	private String totalSolveCnt;
	private String totalNotSolveCnt;
	private String totalIngCnt;
	private String totalNotAnswerCnt;
	private String murderCnt;
	private String murderSolveCnt;
	private String murderNotSolveCnt;
	private String murderIngCnt;
	private String murderNotAnswerCnt;
	private String robberCnt;
	private String robberSolveCnt;
	private String robberNotSolveCnt;
	private String robberIngCnt;
	private String robberNotAnswerCnt;
	private String rapeCnt;
	private String rapeSolveCnt;
	private String rapeNotSolveCnt;
	private String rapeIngCnt;
	private String rapeNotAnswerCnt;
	private String theftCnt;
	private String theftSolveCnt;
	private String theftNotSolveCnt;
	private String theftIngCnt;
	private String theftNotAnswerCnt;
	private String violenceCnt;
	private String violenceSolveCnt;
	private String violenceNotSolveCnt;
	private String violenceIngCnt;
	private String violenceNotAnswerCnt;
	private String accidentCnt;
	private String accidentSolveCnt;
	private String accidentNotSolveCnt;
	private String accidentIngCnt;
	private String accidentNotAnswerCnt;
	private String destroyCnt;
	private String destroySolveCnt;
	private String destroyNotSolveCnt;
	private String destroyIngCnt;
	private String destroyNotAnswerCnt;
	private String etcCnt;
	private String etcSolveCnt;
	private String etcNotSolveCnt;
	private String etcIngCnt;
	private String etcNotAnswerCnt;

	/* 미활용 사유 년도 관련 */
	private String[] years;

	private String year_0;
	private String year_1;
	private String year_2;
	private String year_3;
	private String year_4;
	private String year_5;
	private String year_6;
	private String year_7;
	private String year_8;
	private String year_9;

	private String maxVal;
	private String minVal;
	private String avgVal;

	private String maxValTr;
	private String minValTr;
	private String avgValTr;

	private String applyYear;

	/* 범죄발생 시간 관련 */
	private String time00_03;
	private String time03_06;
	private String time06_09;
	private String time09_12;
	private String time12_15;
	private String time15_18;
	private String time18_21;
	private String time21_24;
	private String timeH07_18;
	private String timeH18_07;

	/* 업무처리 현황 관련 */
	private String busiApplyReqCnt;
	private String busiApplyProcCnt;
	private String busiExtendReqCnt;
	private String busiExtendApplyCnt;
	private String busiChangeReqCnt;
	private String busiChangeApplyCnt;

	/* CCTV 미활용 차트 */
	private String useResDiff;
	private String useResDiffCnt;

	/* 범죄유형별 순위 */
	private Integer murderRank;
	private Integer robberRank;
	private Integer rapeRank;
	private Integer theftRank;
	private Integer violenceRank;
	private Integer accidentRank;
	private Integer destroyRank;
	private Integer etcRank;

	/*사용자 구분 user/admin */
	private String userGb;
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
	 * @return the resultTotalCnt
	 */
	public String getResultTotalCnt() {
		return resultTotalCnt;
	}

	/**
	 * @param resultTotalCnt
	 *            the resultTotalCnt to set
	 */
	public void setResultTotalCnt(String resultTotalCnt) {
		this.resultTotalCnt = resultTotalCnt;
	}

	/**
	 * @return the notResultCnt
	 */
	public String getNotResultCnt() {
		return notResultCnt;
	}

	/**
	 * @param notResultCnt
	 *            the notResultCnt to set
	 */
	public void setNotResultCnt(String notResultCnt) {
		this.notResultCnt = notResultCnt;
	}

	/**
	 * @return the resultCnt
	 */
	public String getResultCnt() {
		return resultCnt;
	}

	/**
	 * @param resultCnt
	 *            the resultCnt to set
	 */
	public void setResultCnt(String resultCnt) {
		this.resultCnt = resultCnt;
	}

	/**
	 * @return the limitDateTotalCnt
	 */
	public String getLimitDateTotalCnt() {
		return limitDateTotalCnt;
	}

	/**
	 * @param limitDateTotalCnt
	 *            the limitDateTotalCnt to set
	 */
	public void setLimitDateTotalCnt(String limitDateTotalCnt) {
		this.limitDateTotalCnt = limitDateTotalCnt;
	}

	/**
	 * @return the solveCnt
	 */
	public String getSolveCnt() {
		return solveCnt;
	}

	/**
	 * @param solveCnt
	 *            the solveCnt to set
	 */
	public void setSolveCnt(String solveCnt) {
		this.solveCnt = solveCnt;
	}

	/**
	 * @return the useSolveCnt
	 */
	public String getUseSolveCnt() {
		return useSolveCnt;
	}

	/**
	 * @param useSolveCnt
	 *            the useSolveCnt to set
	 */
	public void setUseSolveCnt(String useSolveCnt) {
		this.useSolveCnt = useSolveCnt;
	}

	/**
	 * @return the notUseSolveCnt
	 */
	public String getNotUseSolveCnt() {
		return notUseSolveCnt;
	}

	/**
	 * @param notUseSolveCnt
	 *            the notUseSolveCnt to set
	 */
	public void setNotUseSolveCnt(String notUseSolveCnt) {
		this.notUseSolveCnt = notUseSolveCnt;
	}

	/**
	 * @return the years
	 */
	public String[] getYears() {
		return years;
	}

	/**
	 * @param years
	 *            the years to set
	 */
	public void setYears(String[] years) {
		this.years = years;
	}

	/**
	 * @return the year_0
	 */
	public String getYear_0() {
		return year_0;
	}

	/**
	 * @param year_0
	 *            the year_0 to set
	 */
	public void setYear_0(String year_0) {
		this.year_0 = year_0;
	}

	/**
	 * @return the year_1
	 */
	public String getYear_1() {
		return year_1;
	}

	/**
	 * @param year_1
	 *            the year_1 to set
	 */
	public void setYear_1(String year_1) {
		this.year_1 = year_1;
	}

	/**
	 * @return the year_2
	 */
	public String getYear_2() {
		return year_2;
	}

	/**
	 * @param year_2
	 *            the year_2 to set
	 */
	public void setYear_2(String year_2) {
		this.year_2 = year_2;
	}

	/**
	 * @return the year_3
	 */
	public String getYear_3() {
		return year_3;
	}

	/**
	 * @param year_3
	 *            the year_3 to set
	 */
	public void setYear_3(String year_3) {
		this.year_3 = year_3;
	}

	/**
	 * @return the year_4
	 */
	public String getYear_4() {
		return year_4;
	}

	/**
	 * @param year_4
	 *            the year_4 to set
	 */
	public void setYear_4(String year_4) {
		this.year_4 = year_4;
	}

	/**
	 * @return the year_5
	 */
	public String getYear_5() {
		return year_5;
	}

	/**
	 * @param year_5
	 *            the year_5 to set
	 */
	public void setYear_5(String year_5) {
		this.year_5 = year_5;
	}

	/**
	 * @return the year_6
	 */
	public String getYear_6() {
		return year_6;
	}

	/**
	 * @param year_6
	 *            the year_6 to set
	 */
	public void setYear_6(String year_6) {
		this.year_6 = year_6;
	}

	/**
	 * @return the year_7
	 */
	public String getYear_7() {
		return year_7;
	}

	/**
	 * @param year_7
	 *            the year_7 to set
	 */
	public void setYear_7(String year_7) {
		this.year_7 = year_7;
	}

	/**
	 * @return the year_8
	 */
	public String getYear_8() {
		return year_8;
	}

	/**
	 * @param year_8
	 *            the year_8 to set
	 */
	public void setYear_8(String year_8) {
		this.year_8 = year_8;
	}

	/**
	 * @return the year_9
	 */
	public String getYear_9() {
		return year_9;
	}

	/**
	 * @param year_9
	 *            the year_9 to set
	 */
	public void setYear_9(String year_9) {
		this.year_9 = year_9;
	}

	/**
	 * @return the dateFlag
	 */
	public String getDateFlag() {
		return dateFlag;
	}

	/**
	 * @param dateFlag
	 *            the dateFlag to set
	 */
	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}

	/**
	 * @return the yearly
	 */
	public String getYearly() {
		return yearly;
	}

	/**
	 * @param yearly
	 *            the yearly to set
	 */
	public void setYearly(String yearly) {
		this.yearly = yearly;
	}

	/**
	 * @return the monthly
	 */
	public String getMonthly() {
		return monthly;
	}

	/**
	 * @param monthly
	 *            the monthly to set
	 */
	public void setMonthly(String monthly) {
		this.monthly = monthly;
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

	/**
	 * @return the totalSolveCnt
	 */
	public String getTotalSolveCnt() {
		return totalSolveCnt;
	}

	/**
	 * @param totalSolveCnt
	 *            the totalSolveCnt to set
	 */
	public void setTotalSolveCnt(String totalSolveCnt) {
		this.totalSolveCnt = totalSolveCnt;
	}

	/**
	 * @return the totalNotSolveCnt
	 */
	public String getTotalNotSolveCnt() {
		return totalNotSolveCnt;
	}

	/**
	 * @param totalNotSolveCnt
	 *            the totalNotSolveCnt to set
	 */
	public void setTotalNotSolveCnt(String totalNotSolveCnt) {
		this.totalNotSolveCnt = totalNotSolveCnt;
	}

	/**
	 * @return the totalIngCnt
	 */
	public String getTotalIngCnt() {
		return totalIngCnt;
	}

	/**
	 * @param totalIngCnt
	 *            the totalIngCnt to set
	 */
	public void setTotalIngCnt(String totalIngCnt) {
		this.totalIngCnt = totalIngCnt;
	}

	/**
	 * @return the totalNotAnswerCnt
	 */
	public String getTotalNotAnswerCnt() {
		return totalNotAnswerCnt;
	}

	/**
	 * @param totalNotAnswerCnt
	 *            the totalNotAnswerCnt to set
	 */
	public void setTotalNotAnswerCnt(String totalNotAnswerCnt) {
		this.totalNotAnswerCnt = totalNotAnswerCnt;
	}

	/**
	 * @return the murderCnt
	 */
	public String getMurderCnt() {
		return murderCnt;
	}

	/**
	 * @param murderCnt
	 *            the murderCnt to set
	 */
	public void setMurderCnt(String murderCnt) {
		this.murderCnt = murderCnt;
	}

	/**
	 * @return the murderSolveCnt
	 */
	public String getMurderSolveCnt() {
		return murderSolveCnt;
	}

	/**
	 * @param murderSolveCnt
	 *            the murderSolveCnt to set
	 */
	public void setMurderSolveCnt(String murderSolveCnt) {
		this.murderSolveCnt = murderSolveCnt;
	}

	/**
	 * @return the murderNotSolveCnt
	 */
	public String getMurderNotSolveCnt() {
		return murderNotSolveCnt;
	}

	/**
	 * @param murderNotSolveCnt
	 *            the murderNotSolveCnt to set
	 */
	public void setMurderNotSolveCnt(String murderNotSolveCnt) {
		this.murderNotSolveCnt = murderNotSolveCnt;
	}

	/**
	 * @return the murderIngCnt
	 */
	public String getMurderIngCnt() {
		return murderIngCnt;
	}

	/**
	 * @param murderIngCnt
	 *            the murderIngCnt to set
	 */
	public void setMurderIngCnt(String murderIngCnt) {
		this.murderIngCnt = murderIngCnt;
	}

	/**
	 * @return the murderNotAnswerCnt
	 */
	public String getMurderNotAnswerCnt() {
		return murderNotAnswerCnt;
	}

	/**
	 * @param murderNotAnswerCnt
	 *            the murderNotAnswerCnt to set
	 */
	public void setMurderNotAnswerCnt(String murderNotAnswerCnt) {
		this.murderNotAnswerCnt = murderNotAnswerCnt;
	}

	/**
	 * @return the robberCnt
	 */
	public String getRobberCnt() {
		return robberCnt;
	}

	/**
	 * @param robberCnt
	 *            the robberCnt to set
	 */
	public void setRobberCnt(String robberCnt) {
		this.robberCnt = robberCnt;
	}

	/**
	 * @return the robberSolveCnt
	 */
	public String getRobberSolveCnt() {
		return robberSolveCnt;
	}

	/**
	 * @param robberSolveCnt
	 *            the robberSolveCnt to set
	 */
	public void setRobberSolveCnt(String robberSolveCnt) {
		this.robberSolveCnt = robberSolveCnt;
	}

	/**
	 * @return the robberNotSolveCnt
	 */
	public String getRobberNotSolveCnt() {
		return robberNotSolveCnt;
	}

	/**
	 * @param robberNotSolveCnt
	 *            the robberNotSolveCnt to set
	 */
	public void setRobberNotSolveCnt(String robberNotSolveCnt) {
		this.robberNotSolveCnt = robberNotSolveCnt;
	}

	/**
	 * @return the robberIngCnt
	 */
	public String getRobberIngCnt() {
		return robberIngCnt;
	}

	/**
	 * @param robberIngCnt
	 *            the robberIngCnt to set
	 */
	public void setRobberIngCnt(String robberIngCnt) {
		this.robberIngCnt = robberIngCnt;
	}

	/**
	 * @return the robberNotAnswerCnt
	 */
	public String getRobberNotAnswerCnt() {
		return robberNotAnswerCnt;
	}

	/**
	 * @param robberNotAnswerCnt
	 *            the robberNotAnswerCnt to set
	 */
	public void setRobberNotAnswerCnt(String robberNotAnswerCnt) {
		this.robberNotAnswerCnt = robberNotAnswerCnt;
	}

	/**
	 * @return the rapeCnt
	 */
	public String getRapeCnt() {
		return rapeCnt;
	}

	/**
	 * @param rapeCnt
	 *            the rapeCnt to set
	 */
	public void setRapeCnt(String rapeCnt) {
		this.rapeCnt = rapeCnt;
	}

	/**
	 * @return the rapeSolveCnt
	 */
	public String getRapeSolveCnt() {
		return rapeSolveCnt;
	}

	/**
	 * @param rapeSolveCnt
	 *            the rapeSolveCnt to set
	 */
	public void setRapeSolveCnt(String rapeSolveCnt) {
		this.rapeSolveCnt = rapeSolveCnt;
	}

	/**
	 * @return the rapeNotSolveCnt
	 */
	public String getRapeNotSolveCnt() {
		return rapeNotSolveCnt;
	}

	/**
	 * @param rapeNotSolveCnt
	 *            the rapeNotSolveCnt to set
	 */
	public void setRapeNotSolveCnt(String rapeNotSolveCnt) {
		this.rapeNotSolveCnt = rapeNotSolveCnt;
	}

	/**
	 * @return the rapeIngCnt
	 */
	public String getRapeIngCnt() {
		return rapeIngCnt;
	}

	/**
	 * @param rapeIngCnt
	 *            the rapeIngCnt to set
	 */
	public void setRapeIngCnt(String rapeIngCnt) {
		this.rapeIngCnt = rapeIngCnt;
	}

	/**
	 * @return the rapeNotAnswerCnt
	 */
	public String getRapeNotAnswerCnt() {
		return rapeNotAnswerCnt;
	}

	/**
	 * @param rapeNotAnswerCnt
	 *            the rapeNotAnswerCnt to set
	 */
	public void setRapeNotAnswerCnt(String rapeNotAnswerCnt) {
		this.rapeNotAnswerCnt = rapeNotAnswerCnt;
	}

	/**
	 * @return the theftCnt
	 */
	public String getTheftCnt() {
		return theftCnt;
	}

	/**
	 * @param theftCnt
	 *            the theftCnt to set
	 */
	public void setTheftCnt(String theftCnt) {
		this.theftCnt = theftCnt;
	}

	/**
	 * @return the theftSolveCnt
	 */
	public String getTheftSolveCnt() {
		return theftSolveCnt;
	}

	/**
	 * @param theftSolveCnt
	 *            the theftSolveCnt to set
	 */
	public void setTheftSolveCnt(String theftSolveCnt) {
		this.theftSolveCnt = theftSolveCnt;
	}

	/**
	 * @return the theftNotSolveCnt
	 */
	public String getTheftNotSolveCnt() {
		return theftNotSolveCnt;
	}

	/**
	 * @param theftNotSolveCnt
	 *            the theftNotSolveCnt to set
	 */
	public void setTheftNotSolveCnt(String theftNotSolveCnt) {
		this.theftNotSolveCnt = theftNotSolveCnt;
	}

	/**
	 * @return the theftIngCnt
	 */
	public String getTheftIngCnt() {
		return theftIngCnt;
	}

	/**
	 * @param theftIngCnt
	 *            the theftIngCnt to set
	 */
	public void setTheftIngCnt(String theftIngCnt) {
		this.theftIngCnt = theftIngCnt;
	}

	/**
	 * @return the theftNotAnswerCnt
	 */
	public String getTheftNotAnswerCnt() {
		return theftNotAnswerCnt;
	}

	/**
	 * @param theftNotAnswerCnt
	 *            the theftNotAnswerCnt to set
	 */
	public void setTheftNotAnswerCnt(String theftNotAnswerCnt) {
		this.theftNotAnswerCnt = theftNotAnswerCnt;
	}

	/**
	 * @return the violenceCnt
	 */
	public String getViolenceCnt() {
		return violenceCnt;
	}

	/**
	 * @param violenceCnt
	 *            the violenceCnt to set
	 */
	public void setViolenceCnt(String violenceCnt) {
		this.violenceCnt = violenceCnt;
	}

	/**
	 * @return the violenceSolveCnt
	 */
	public String getViolenceSolveCnt() {
		return violenceSolveCnt;
	}

	/**
	 * @param violenceSolveCnt
	 *            the violenceSolveCnt to set
	 */
	public void setViolenceSolveCnt(String violenceSolveCnt) {
		this.violenceSolveCnt = violenceSolveCnt;
	}

	/**
	 * @return the violenceNotSolveCnt
	 */
	public String getViolenceNotSolveCnt() {
		return violenceNotSolveCnt;
	}

	/**
	 * @param violenceNotSolveCnt
	 *            the violenceNotSolveCnt to set
	 */
	public void setViolenceNotSolveCnt(String violenceNotSolveCnt) {
		this.violenceNotSolveCnt = violenceNotSolveCnt;
	}

	/**
	 * @return the violenceIngCnt
	 */
	public String getViolenceIngCnt() {
		return violenceIngCnt;
	}

	/**
	 * @param violenceIngCnt
	 *            the violenceIngCnt to set
	 */
	public void setViolenceIngCnt(String violenceIngCnt) {
		this.violenceIngCnt = violenceIngCnt;
	}

	/**
	 * @return the violenceNotAnswerCnt
	 */
	public String getViolenceNotAnswerCnt() {
		return violenceNotAnswerCnt;
	}

	/**
	 * @param violenceNotAnswerCnt
	 *            the violenceNotAnswerCnt to set
	 */
	public void setViolenceNotAnswerCnt(String violenceNotAnswerCnt) {
		this.violenceNotAnswerCnt = violenceNotAnswerCnt;
	}

	/**
	 * @return the accidentCnt
	 */
	public String getAccidentCnt() {
		return accidentCnt;
	}

	/**
	 * @param accidentCnt
	 *            the accidentCnt to set
	 */
	public void setAccidentCnt(String accidentCnt) {
		this.accidentCnt = accidentCnt;
	}

	/**
	 * @return the accidentSolveCnt
	 */
	public String getAccidentSolveCnt() {
		return accidentSolveCnt;
	}

	/**
	 * @param accidentSolveCnt
	 *            the accidentSolveCnt to set
	 */
	public void setAccidentSolveCnt(String accidentSolveCnt) {
		this.accidentSolveCnt = accidentSolveCnt;
	}

	/**
	 * @return the accidentNotSolveCnt
	 */
	public String getAccidentNotSolveCnt() {
		return accidentNotSolveCnt;
	}

	/**
	 * @param accidentNotSolveCnt
	 *            the accidentNotSolveCnt to set
	 */
	public void setAccidentNotSolveCnt(String accidentNotSolveCnt) {
		this.accidentNotSolveCnt = accidentNotSolveCnt;
	}

	/**
	 * @return the accidentIngCnt
	 */
	public String getAccidentIngCnt() {
		return accidentIngCnt;
	}

	/**
	 * @param accidentIngCnt
	 *            the accidentIngCnt to set
	 */
	public void setAccidentIngCnt(String accidentIngCnt) {
		this.accidentIngCnt = accidentIngCnt;
	}

	/**
	 * @return the accidentNotAnswerCnt
	 */
	public String getAccidentNotAnswerCnt() {
		return accidentNotAnswerCnt;
	}

	/**
	 * @param accidentNotAnswerCnt
	 *            the accidentNotAnswerCnt to set
	 */
	public void setAccidentNotAnswerCnt(String accidentNotAnswerCnt) {
		this.accidentNotAnswerCnt = accidentNotAnswerCnt;
	}

	/**
	 * @return the destroyCnt
	 */
	public String getDestroyCnt() {
		return destroyCnt;
	}

	/**
	 * @param destroyCnt
	 *            the destroyCnt to set
	 */
	public void setDestroyCnt(String destroyCnt) {
		this.destroyCnt = destroyCnt;
	}

	/**
	 * @return the destroySolveCnt
	 */
	public String getDestroySolveCnt() {
		return destroySolveCnt;
	}

	/**
	 * @param destroySolveCnt
	 *            the destroySolveCnt to set
	 */
	public void setDestroySolveCnt(String destroySolveCnt) {
		this.destroySolveCnt = destroySolveCnt;
	}

	/**
	 * @return the destroyNotSolveCnt
	 */
	public String getDestroyNotSolveCnt() {
		return destroyNotSolveCnt;
	}

	/**
	 * @param destroyNotSolveCnt
	 *            the destroyNotSolveCnt to set
	 */
	public void setDestroyNotSolveCnt(String destroyNotSolveCnt) {
		this.destroyNotSolveCnt = destroyNotSolveCnt;
	}

	/**
	 * @return the destroyIngCnt
	 */
	public String getDestroyIngCnt() {
		return destroyIngCnt;
	}

	/**
	 * @param destroyIngCnt
	 *            the destroyIngCnt to set
	 */
	public void setDestroyIngCnt(String destroyIngCnt) {
		this.destroyIngCnt = destroyIngCnt;
	}

	/**
	 * @return the destroyNotAnswerCnt
	 */
	public String getDestroyNotAnswerCnt() {
		return destroyNotAnswerCnt;
	}

	/**
	 * @param destroyNotAnswerCnt
	 *            the destroyNotAnswerCnt to set
	 */
	public void setDestroyNotAnswerCnt(String destroyNotAnswerCnt) {
		this.destroyNotAnswerCnt = destroyNotAnswerCnt;
	}

	/**
	 * @return the etcCnt
	 */
	public String getEtcCnt() {
		return etcCnt;
	}

	/**
	 * @param etcCnt
	 *            the etcCnt to set
	 */
	public void setEtcCnt(String etcCnt) {
		this.etcCnt = etcCnt;
	}

	/**
	 * @return the etcSolveCnt
	 */
	public String getEtcSolveCnt() {
		return etcSolveCnt;
	}

	/**
	 * @param etcSolveCnt
	 *            the etcSolveCnt to set
	 */
	public void setEtcSolveCnt(String etcSolveCnt) {
		this.etcSolveCnt = etcSolveCnt;
	}

	/**
	 * @return the etcNotSolveCnt
	 */
	public String getEtcNotSolveCnt() {
		return etcNotSolveCnt;
	}

	/**
	 * @param etcNotSolveCnt
	 *            the etcNotSolveCnt to set
	 */
	public void setEtcNotSolveCnt(String etcNotSolveCnt) {
		this.etcNotSolveCnt = etcNotSolveCnt;
	}

	/**
	 * @return the etcIngCnt
	 */
	public String getEtcIngCnt() {
		return etcIngCnt;
	}

	/**
	 * @param etcIngCnt
	 *            the etcIngCnt to set
	 */
	public void setEtcIngCnt(String etcIngCnt) {
		this.etcIngCnt = etcIngCnt;
	}

	/**
	 * @return the etcNotAnswerCnt
	 */
	public String getEtcNotAnswerCnt() {
		return etcNotAnswerCnt;
	}

	/**
	 * @param etcNotAnswerCnt
	 *            the etcNotAnswerCnt to set
	 */
	public void setEtcNotAnswerCnt(String etcNotAnswerCnt) {
		this.etcNotAnswerCnt = etcNotAnswerCnt;
	}

	/**
	 * @return the maxVal
	 */
	public String getMaxVal() {
		return maxVal;
	}

	/**
	 * @param maxVal
	 *            the maxVal to set
	 */
	public void setMaxVal(String maxVal) {
		this.maxVal = maxVal;
	}

	/**
	 * @return the minVal
	 */
	public String getMinVal() {
		return minVal;
	}

	/**
	 * @param minVal
	 *            the minVal to set
	 */
	public void setMinVal(String minVal) {
		this.minVal = minVal;
	}

	/**
	 * @return the avgVal
	 */
	public String getAvgVal() {
		return avgVal;
	}

	/**
	 * @param avgVal
	 *            the avgVal to set
	 */
	public void setAvgVal(String avgVal) {
		this.avgVal = avgVal;
	}

	/**
	 * @return the maxValTr
	 */
	public String getMaxValTr() {
		return maxValTr;
	}

	/**
	 * @param maxValTr
	 *            the maxValTr to set
	 */
	public void setMaxValTr(String maxValTr) {
		this.maxValTr = maxValTr;
	}

	/**
	 * @return the minValTr
	 */
	public String getMinValTr() {
		return minValTr;
	}

	/**
	 * @param minValTr
	 *            the minValTr to set
	 */
	public void setMinValTr(String minValTr) {
		this.minValTr = minValTr;
	}

	/**
	 * @return the avgValTr
	 */
	public String getAvgValTr() {
		return avgValTr;
	}

	/**
	 * @param avgValTr
	 *            the avgValTr to set
	 */
	public void setAvgValTr(String avgValTr) {
		this.avgValTr = avgValTr;
	}

	public String getApplyYear() {
		return applyYear;
	}

	public void setApplyYear(String applyYear) {
		this.applyYear = applyYear;
	}

	/**
	 * @return the time00_03
	 */
	public String getTime00_03() {
		return time00_03;
	}

	/**
	 * @param time00_03
	 *            the time00_03 to set
	 */
	public void setTime00_03(String time00_03) {
		this.time00_03 = time00_03;
	}

	/**
	 * @return the time03_06
	 */
	public String getTime03_06() {
		return time03_06;
	}

	/**
	 * @param time03_06
	 *            the time03_06 to set
	 */
	public void setTime03_06(String time03_06) {
		this.time03_06 = time03_06;
	}

	/**
	 * @return the time06_09
	 */
	public String getTime06_09() {
		return time06_09;
	}

	/**
	 * @param time06_09
	 *            the time06_09 to set
	 */
	public void setTime06_09(String time06_09) {
		this.time06_09 = time06_09;
	}

	/**
	 * @return the time09_12
	 */
	public String getTime09_12() {
		return time09_12;
	}

	/**
	 * @param time09_12
	 *            the time09_12 to set
	 */
	public void setTime09_12(String time09_12) {
		this.time09_12 = time09_12;
	}

	/**
	 * @return the time12_15
	 */
	public String getTime12_15() {
		return time12_15;
	}

	/**
	 * @param time12_15
	 *            the time12_15 to set
	 */
	public void setTime12_15(String time12_15) {
		this.time12_15 = time12_15;
	}

	/**
	 * @return the time15_18
	 */
	public String getTime15_18() {
		return time15_18;
	}

	/**
	 * @param time15_18
	 *            the time15_18 to set
	 */
	public void setTime15_18(String time15_18) {
		this.time15_18 = time15_18;
	}

	/**
	 * @return the time18_21
	 */
	public String getTime18_21() {
		return time18_21;
	}

	/**
	 * @param time18_21
	 *            the time18_21 to set
	 */
	public void setTime18_21(String time18_21) {
		this.time18_21 = time18_21;
	}

	/**
	 * @return the time21_24
	 */
	public String getTime21_24() {
		return time21_24;
	}

	/**
	 * @param time21_24
	 *            the time21_24 to set
	 */
	public void setTime21_24(String time21_24) {
		this.time21_24 = time21_24;
	}

	/**
	 * @return the applyDate
	 */
	public String getApplyDate() {
		return applyDate;
	}

	/**
	 * @param applyDate
	 *            the applyDate to set
	 */
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	/**
	 * @return the busiApplyReqCnt
	 */
	public String getBusiApplyReqCnt() {
		return busiApplyReqCnt;
	}

	/**
	 * @param busiApplyReqCnt
	 *            the busiApplyReqCnt to set
	 */
	public void setBusiApplyReqCnt(String busiApplyReqCnt) {
		this.busiApplyReqCnt = busiApplyReqCnt;
	}

	/**
	 * @return the busiApplyProcCnt
	 */
	public String getBusiApplyProcCnt() {
		return busiApplyProcCnt;
	}

	/**
	 * @param busiApplyProcCnt
	 *            the busiApplyProcCnt to set
	 */
	public void setBusiApplyProcCnt(String busiApplyProcCnt) {
		this.busiApplyProcCnt = busiApplyProcCnt;
	}

	/**
	 * @return the busiExtendReqCnt
	 */
	public String getBusiExtendReqCnt() {
		return busiExtendReqCnt;
	}

	/**
	 * @param busiExtendReqCnt
	 *            the busiExtendReqCnt to set
	 */
	public void setBusiExtendReqCnt(String busiExtendReqCnt) {
		this.busiExtendReqCnt = busiExtendReqCnt;
	}

	/**
	 * @return the busiExtendApplyCnt
	 */
	public String getBusiExtendApplyCnt() {
		return busiExtendApplyCnt;
	}

	/**
	 * @param busiExtendApplyCnt
	 *            the busiExtendApplyCnt to set
	 */
	public void setBusiExtendApplyCnt(String busiExtendApplyCnt) {
		this.busiExtendApplyCnt = busiExtendApplyCnt;
	}

	/**
	 * @return the busiChangeReqCnt
	 */
	public String getBusiChangeReqCnt() {
		return busiChangeReqCnt;
	}

	/**
	 * @param busiChangeReqCnt
	 *            the busiChangeReqCnt to set
	 */
	public void setBusiChangeReqCnt(String busiChangeReqCnt) {
		this.busiChangeReqCnt = busiChangeReqCnt;
	}

	/**
	 * @return the busiChangeApplyCnt
	 */
	public String getBusiChangeApplyCnt() {
		return busiChangeApplyCnt;
	}

	/**
	 * @param busiChangeApplyCnt
	 *            the busiChangeApplyCnt to set
	 */
	public void setBusiChangeApplyCnt(String busiChangeApplyCnt) {
		this.busiChangeApplyCnt = busiChangeApplyCnt;
	}

	/**
	 * @return the useResDiff
	 */
	public String getUseResDiff() {
		return useResDiff;
	}

	/**
	 * @param useResDiff
	 *            the useResDiff to set
	 */
	public void setUseResDiff(String useResDiff) {
		this.useResDiff = useResDiff;
	}

	/**
	 * @return the useResDiffCnt
	 */
	public String getUseResDiffCnt() {
		return useResDiffCnt;
	}

	/**
	 * @param useResDiffCnt
	 *            the useResDiffCnt to set
	 */
	public void setUseResDiffCnt(String useResDiffCnt) {
		this.useResDiffCnt = useResDiffCnt;
	}

	/**
	 * @return the timeH07_18
	 */
	public String getTimeH07_18() {
		return timeH07_18;
	}

	/**
	 * @param timeH07_18
	 *            the timeH07_18 to set
	 */
	public void setTimeH07_18(String timeH07_18) {
		this.timeH07_18 = timeH07_18;
	}

	/**
	 * @return the timeH18_07
	 */
	public String getTimeH18_07() {
		return timeH18_07;
	}

	/**
	 * @param timeH18_07
	 *            the timeH18_07 to set
	 */
	public void setTimeH18_07(String timeH18_07) {
		this.timeH18_07 = timeH18_07;
	}

	public Integer getMurderRank() {
		return murderRank;
	}

	public void setMurderRank(Integer murderRank) {
		this.murderRank = murderRank;
	}

	public Integer getRobberRank() {
		return robberRank;
	}

	public void setRobberRank(Integer robberRank) {
		this.robberRank = robberRank;
	}

	public Integer getRapeRank() {
		return rapeRank;
	}

	public void setRapeRank(Integer rapeRank) {
		this.rapeRank = rapeRank;
	}

	public Integer getTheftRank() {
		return theftRank;
	}

	public void setTheftRank(Integer theftRank) {
		this.theftRank = theftRank;
	}

	public Integer getViolenceRank() {
		return violenceRank;
	}

	public void setViolenceRank(Integer violenceRank) {
		this.violenceRank = violenceRank;
	}

	public Integer getAccidentRank() {
		return accidentRank;
	}

	public void setAccidentRank(Integer accidentRank) {
		this.accidentRank = accidentRank;
	}

	public Integer getDestroyRank() {
		return destroyRank;
	}

	public void setDestroyRank(Integer destroyRank) {
		this.destroyRank = destroyRank;
	}

	public Integer getEtcRank() {
		return etcRank;
	}

	public void setEtcRank(Integer etcRank) {
		this.etcRank = etcRank;
	}

	/**
	 * @return the userGb
	 */
	public String getUserGb() {
		return userGb;
	}

	/**
	 * @param userGb the userGb to set
	 */
	public void setUserGb(String userGb) {
		this.userGb = userGb;
	}

}
