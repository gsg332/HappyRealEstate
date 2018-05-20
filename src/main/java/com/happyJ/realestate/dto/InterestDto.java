package com.happyJ.realestate.dto;

import java.util.Date;

public class InterestDto extends ApartDto {

	private Long uInterestId;
	private Long uMemId;
	private Long uRegionId;
	private String uReId;
	private String type;
	private Integer level;
	private Date regDate;

	private String sido;
	private String sgg;
	private String emd;
	private String ri;
	private String reName;

	/**
	 * @return the uInterestId
	 */
	public Long getuInterestId() {
		return uInterestId;
	}

	/**
	 * @param uInterestId
	 *            the uInterestId to set
	 */
	public void setuInterestId(Long uInterestId) {
		this.uInterestId = uInterestId;
	}

	/**
	 * @return the uMemId
	 */
	public Long getuMemId() {
		return uMemId;
	}

	/**
	 * @param uMemId
	 *            the uMemId to set
	 */
	public void setuMemId(Long uMemId) {
		this.uMemId = uMemId;
	}

	/**
	 * @return the uRegionId
	 */
	public Long getuRegionId() {
		return uRegionId;
	}

	/**
	 * @param uRegionId
	 *            the uRegionId to set
	 */
	public void setuRegionId(Long uRegionId) {
		this.uRegionId = uRegionId;
	}

	/**
	 * @return the uReId
	 */
	public String getuReId() {
		return uReId;
	}

	/**
	 * @param uReId
	 *            the uReId to set
	 */
	public void setuReId(String uReId) {
		this.uReId = uReId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
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
	 * @return the reName
	 */
	public String getReName() {
		return reName;
	}

	/**
	 * @param reName
	 *            the reName to set
	 */
	public void setReName(String reName) {
		this.reName = reName;
	}

}
