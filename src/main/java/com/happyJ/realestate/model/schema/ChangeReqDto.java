package com.happyJ.realestate.model.schema;

import com.happyJ.realestate.model.CommonDto;

public class ChangeReqDto extends CommonDto {

	private static final long serialVersionUID = 1L;

	private long no;
	private String itemId;
	private String reqUserid;
	private String phoneNum;
	private String reqIp;
	private String reqDate;
	private String approvalDate;
	private String typeDepth1;
	private String typeDepth2;
	private String requestValues;
	private String unapprovalReason;
	private String status;

	/* 변경한 첨부파일 삭제 시 사용 */
	private String officialFileNo;
	private String officialFileNm;
	private String officialFileTempNm;
	private String pledgeFileNo;
	private String pledgeFileNm;
	private String pledgeFileTempNm;

	/* 검색 항목 */
	private String searchStatus;
	private String searchStDate;
	private String searchEdDate;
	private String searchTypeDepth1;
	private String searchPosition;
	private String searchDepart;
	private String searchTeam;
	private String searchUserid;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getReqUserid() {
		return reqUserid;
	}

	public void setReqUserid(String reqUserid) {
		this.reqUserid = reqUserid;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getReqIp() {
		return reqIp;
	}

	public void setReqIp(String reqIp) {
		this.reqIp = reqIp;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getTypeDepth1() {
		return typeDepth1;
	}

	public void setTypeDepth1(String typeDepth1) {
		this.typeDepth1 = typeDepth1;
	}

	public String getTypeDepth2() {
		return typeDepth2;
	}

	public void setTypeDepth2(String typeDepth2) {
		this.typeDepth2 = typeDepth2;
	}

	public String getRequestValues() {
		return requestValues;
	}

	public void setRequestValues(String requestValues) {
		this.requestValues = requestValues;
	}

	public String getUnapprovalReason() {
		return unapprovalReason;
	}

	public void setUnapprovalReason(String unapprovalReason) {
		this.unapprovalReason = unapprovalReason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOfficialFileNo() {
		return officialFileNo;
	}

	public void setOfficialFileNo(String officialFileNo) {
		this.officialFileNo = officialFileNo;
	}

	public String getOfficialFileNm() {
		return officialFileNm;
	}

	public void setOfficialFileNm(String officialFileNm) {
		this.officialFileNm = officialFileNm;
	}

	public String getOfficialFileTempNm() {
		return officialFileTempNm;
	}

	public void setOfficialFileTempNm(String officialFileTempNm) {
		this.officialFileTempNm = officialFileTempNm;
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

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	public String getSearchStDate() {
		return searchStDate;
	}

	public void setSearchStDate(String searchStDate) {
		this.searchStDate = searchStDate;
	}

	public String getSearchEdDate() {
		return searchEdDate;
	}

	public void setSearchEdDate(String searchEdDate) {
		this.searchEdDate = searchEdDate;
	}

	public String getSearchTypeDepth1() {
		return searchTypeDepth1;
	}

	public void setSearchTypeDepth1(String searchTypeDepth1) {
		this.searchTypeDepth1 = searchTypeDepth1;
	}

	public String getSearchPosition() {
		return searchPosition;
	}

	public void setSearchPosition(String searchPosition) {
		this.searchPosition = searchPosition;
	}

	public String getSearchDepart() {
		return searchDepart;
	}

	public void setSearchDepart(String searchDepart) {
		this.searchDepart = searchDepart;
	}

	public String getSearchTeam() {
		return searchTeam;
	}

	public void setSearchTeam(String searchTeam) {
		this.searchTeam = searchTeam;
	}

	public String getSearchUserid() {
		return searchUserid;
	}

	public void setSearchUserid(String searchUserid) {
		this.searchUserid = searchUserid;
	}

}
