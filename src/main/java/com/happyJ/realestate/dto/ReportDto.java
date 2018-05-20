package com.happyJ.realestate.dto;

import java.util.Date;

public class ReportDto {

	private Long uReReportId;
	private String reType;
	private String sido;
	private String sgg;
	private String emd;
	private String ri;
	private Date regDate;

	/**
	 * @return the uReReportId
	 */
	public Long getuReReportId() {
		return uReReportId;
	}

	/**
	 * @param uReReportId
	 *            the uReReportId to set
	 */
	public void setuReReportId(Long uReReportId) {
		this.uReReportId = uReReportId;
	}

	/**
	 * @return the reType
	 */
	public String getReType() {
		return reType;
	}

	/**
	 * @param reType
	 *            the reType to set
	 */
	public void setReType(String reType) {
		this.reType = reType;
	}

	/**
	 * @return the sido
	 */
	public String getSido() {
		return sido;
	}

	/**
	 * @param sido
	 *            the sido to set
	 */
	public void setSido(String sido) {
		this.sido = sido;
	}

	/**
	 * @return the sgg
	 */
	public String getSgg() {
		return sgg;
	}

	/**
	 * @param sgg
	 *            the sgg to set
	 */
	public void setSgg(String sgg) {
		this.sgg = sgg;
	}

	/**
	 * @return the emd
	 */
	public String getEmd() {
		return emd;
	}

	/**
	 * @param emd
	 *            the emd to set
	 */
	public void setEmd(String emd) {
		this.emd = emd;
	}

	/**
	 * @return the ri
	 */
	public String getRi() {
		return ri;
	}

	/**
	 * @param ri
	 *            the ri to set
	 */
	public void setRi(String ri) {
		this.ri = ri;
	}

	/**
	 * @return the regDate
	 */
	public Date getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate
	 *            the regDate to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
