/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 6. 20. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.custom;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 * @packageName : com.happyJ.realestate.model.custom
 * @fileName : MapStatDto.java
 * @author : yongpal
 * @since 2016. 6. 20.
 * @version 1.0
 * @see :
 * @revision : 2016. 6. 20.
 * 
 * <pre>
*  << Modification Information >>
*    DATE	           NAME			DESC
*     -----------	 ----------   ---------------------------------------
*     2016. 6. 20.        yongpal       create MapStatDto.java
 * </pre>
 ******************************************************************************/
public class MapStatDto extends CommonDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String locationId;
	private String locationGu;
	private String locationDong;
	private String centerLat;
	private String centerLng;

	// areaStatList
	private int cctvCnt;
	private int crimeCnt;
	private int resultTotal;
	private int noon;
	private int evening;
	private int night;
	private int dawn;
	private float rate;

	// typeStatList
	private String resolutionCnt;
	private String resolutionPct;
	private String useCnt;
	private String usePct;
	private String murderCnt;
	private String murderResolutionCnt;
	private String murderUseCnt;
	private String murderResolutionPct;
	private String murderUsePct;
	private String robberCnt;
	private String robberResolutionCnt;
	private String robberUseCnt;
	private String robberResolutionPct;
	private String robberUsePct;
	private String rapeCnt;
	private String rapeResolutionCnt;
	private String rapeUseCnt;
	private String rapeResolutionPct;
	private String rapeUsePct;
	private String theftCnt;
	private String theftResolutionCnt;
	private String theftUseCnt;
	private String theftResolutionPct;
	private String theftUsePct;
	private String violenceCnt;
	private String violenceResolutionCnt;
	private String violenceUseCnt;
	private String violenceResolutionPct;
	private String violenceUsePct;
	private String accidentCnt;
	private String accidentResolutionCnt;
	private String accidentUseCnt;
	private String accidentResolutionPct;
	private String accidentUsePct;
	private String destroyCnt;
	private String destroyResolutionCnt;
	private String destroyUseCnt;
	private String destroyResolutionPct;
	private String destroyUsePct;

	// cctvInstStatList
	private String cctvType0;
	private String cctvType1;
	private String cctvType2;
	private String cctvType3;
	private String cctvType4;
	private String cctvType5;
	private String cctvType6;
	private String cctvType7;
	private String cctvType8;
	private String cctvType9;
	private String cctvType10;
	private String preventionCnt;
	private String preventionResolutionCnt;
	private String preventionUseCnt;
	private String preventionResolutionPct;
	private String preventionUsePct;
	private String parkingCnt;
	private String parkingResolutionCnt;
	private String parkingUseCnt;
	private String parkingResolutionPct;
	private String parkingUsePct;
	private String trackCnt;
	private String trackResolutionCnt;
	private String trackUseCnt;
	private String trackResolutionPct;
	private String trackUsePct;
	private String garbageCnt;
	private String garbageResolutionCnt;
	private String garbageUseCnt;
	private String garbageResolutionPct;
	private String garbageUsePct;
	private String multiCnt;
	private String multiResolutionCnt;
	private String multiUseCnt;
	private String multiResolutionPct;
	private String multiUsePct;
	private String fireCnt;
	private String fireResolutionCnt;
	private String fireUseCnt;
	private String fireResolutionPct;
	private String fireUsePct;
	private String mFireCnt;
	private String mFireResolutionCnt;
	private String mFireUseCnt;
	private String mFireResolutionPct;
	private String mFireUsePct;
	private String streetCnt;
	private String streetResolutionCnt;
	private String streetUseCnt;
	private String streetResolutionPct;
	private String streetUsePct;
	private String parkCnt;
	private String parkResolutionCnt;
	private String parkUseCnt;
	private String parkResolutionPct;
	private String parkUsePct;
	private String childCnt;
	private String childResolutionCnt;
	private String childUseCnt;
	private String childResolutionPct;
	private String childUsePct;

	private String etcCnt;
	private String etcResolutionCnt;
	private String etcUseCnt;
	private String etcResolutionPct;
	private String etcUsePct;

	// notUseCctvList
	private int rank;
	private String code1;
	private String address;
	private int notUseCnt;

	// cctv MarkerType
	private String cctvCount;
	private String cctvTypeCode;
	private String cctvTypeNm;
	private String cctvImg;

	// search
	private String searchStDate;
	private String searchEdDate;
	private String timeSlot00;
	private String timeSlot07;
	private String timeSlot18;
	private String timeSlot22;
	private String timeSlot03;

	private String videoId;

	/**
	 * @return the resolutionCnt
	 */
	public String getResolutionCnt() {
		return resolutionCnt;
	}

	/**
	 * @param resolutionCnt
	 *            the resolutionCnt to set
	 */
	public void setResolutionCnt(String resolutionCnt) {
		this.resolutionCnt = resolutionCnt;
	}

	/**
	 * @return the resolutionPct
	 */
	public String getResolutionPct() {
		return resolutionPct;
	}

	/**
	 * @param resolutionPct
	 *            the resolutionPct to set
	 */
	public void setResolutionPct(String resolutionPct) {
		this.resolutionPct = resolutionPct;
	}

	/**
	 * @return the useCnt
	 */
	public String getUseCnt() {
		return useCnt;
	}

	/**
	 * @param useCnt
	 *            the useCnt to set
	 */
	public void setUseCnt(String useCnt) {
		this.useCnt = useCnt;
	}

	/**
	 * @return the usePct
	 */
	public String getUsePct() {
		return usePct;
	}

	/**
	 * @param usePct
	 *            the usePct to set
	 */
	public void setUsePct(String usePct) {
		this.usePct = usePct;
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
	 * @return the murderResolutionCnt
	 */
	public String getMurderResolutionCnt() {
		return murderResolutionCnt;
	}

	/**
	 * @param murderResolutionCnt
	 *            the murderResolutionCnt to set
	 */
	public void setMurderResolutionCnt(String murderResolutionCnt) {
		this.murderResolutionCnt = murderResolutionCnt;
	}

	/**
	 * @return the murderUseCnt
	 */
	public String getMurderUseCnt() {
		return murderUseCnt;
	}

	/**
	 * @param murderUseCnt
	 *            the murderUseCnt to set
	 */
	public void setMurderUseCnt(String murderUseCnt) {
		this.murderUseCnt = murderUseCnt;
	}

	/**
	 * @return the murderResolutionPct
	 */
	public String getMurderResolutionPct() {
		return murderResolutionPct;
	}

	/**
	 * @param murderResolutionPct
	 *            the murderResolutionPct to set
	 */
	public void setMurderResolutionPct(String murderResolutionPct) {
		this.murderResolutionPct = murderResolutionPct;
	}

	/**
	 * @return the murderUsePct
	 */
	public String getMurderUsePct() {
		return murderUsePct;
	}

	/**
	 * @param murderUsePct
	 *            the murderUsePct to set
	 */
	public void setMurderUsePct(String murderUsePct) {
		this.murderUsePct = murderUsePct;
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
	 * @return the robberResolutionCnt
	 */
	public String getRobberResolutionCnt() {
		return robberResolutionCnt;
	}

	/**
	 * @param robberResolutionCnt
	 *            the robberResolutionCnt to set
	 */
	public void setRobberResolutionCnt(String robberResolutionCnt) {
		this.robberResolutionCnt = robberResolutionCnt;
	}

	/**
	 * @return the robberUseCnt
	 */
	public String getRobberUseCnt() {
		return robberUseCnt;
	}

	/**
	 * @param robberUseCnt
	 *            the robberUseCnt to set
	 */
	public void setRobberUseCnt(String robberUseCnt) {
		this.robberUseCnt = robberUseCnt;
	}

	/**
	 * @return the robberResolutionPct
	 */
	public String getRobberResolutionPct() {
		return robberResolutionPct;
	}

	/**
	 * @param robberResolutionPct
	 *            the robberResolutionPct to set
	 */
	public void setRobberResolutionPct(String robberResolutionPct) {
		this.robberResolutionPct = robberResolutionPct;
	}

	/**
	 * @return the robberUsePct
	 */
	public String getRobberUsePct() {
		return robberUsePct;
	}

	/**
	 * @param robberUsePct
	 *            the robberUsePct to set
	 */
	public void setRobberUsePct(String robberUsePct) {
		this.robberUsePct = robberUsePct;
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
	 * @return the rapeResolutionCnt
	 */
	public String getRapeResolutionCnt() {
		return rapeResolutionCnt;
	}

	/**
	 * @param rapeResolutionCnt
	 *            the rapeResolutionCnt to set
	 */
	public void setRapeResolutionCnt(String rapeResolutionCnt) {
		this.rapeResolutionCnt = rapeResolutionCnt;
	}

	/**
	 * @return the rapeUseCnt
	 */
	public String getRapeUseCnt() {
		return rapeUseCnt;
	}

	/**
	 * @param rapeUseCnt
	 *            the rapeUseCnt to set
	 */
	public void setRapeUseCnt(String rapeUseCnt) {
		this.rapeUseCnt = rapeUseCnt;
	}

	/**
	 * @return the rapeResolutionPct
	 */
	public String getRapeResolutionPct() {
		return rapeResolutionPct;
	}

	/**
	 * @param rapeResolutionPct
	 *            the rapeResolutionPct to set
	 */
	public void setRapeResolutionPct(String rapeResolutionPct) {
		this.rapeResolutionPct = rapeResolutionPct;
	}

	/**
	 * @return the rapeUsePct
	 */
	public String getRapeUsePct() {
		return rapeUsePct;
	}

	/**
	 * @param rapeUsePct
	 *            the rapeUsePct to set
	 */
	public void setRapeUsePct(String rapeUsePct) {
		this.rapeUsePct = rapeUsePct;
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
	 * @return the theftResolutionCnt
	 */
	public String getTheftResolutionCnt() {
		return theftResolutionCnt;
	}

	/**
	 * @param theftResolutionCnt
	 *            the theftResolutionCnt to set
	 */
	public void setTheftResolutionCnt(String theftResolutionCnt) {
		this.theftResolutionCnt = theftResolutionCnt;
	}

	/**
	 * @return the theftUseCnt
	 */
	public String getTheftUseCnt() {
		return theftUseCnt;
	}

	/**
	 * @param theftUseCnt
	 *            the theftUseCnt to set
	 */
	public void setTheftUseCnt(String theftUseCnt) {
		this.theftUseCnt = theftUseCnt;
	}

	/**
	 * @return the theftResolutionPct
	 */
	public String getTheftResolutionPct() {
		return theftResolutionPct;
	}

	/**
	 * @param theftResolutionPct
	 *            the theftResolutionPct to set
	 */
	public void setTheftResolutionPct(String theftResolutionPct) {
		this.theftResolutionPct = theftResolutionPct;
	}

	/**
	 * @return the theftUsePct
	 */
	public String getTheftUsePct() {
		return theftUsePct;
	}

	/**
	 * @param theftUsePct
	 *            the theftUsePct to set
	 */
	public void setTheftUsePct(String theftUsePct) {
		this.theftUsePct = theftUsePct;
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
	 * @return the violenceResolutionCnt
	 */
	public String getViolenceResolutionCnt() {
		return violenceResolutionCnt;
	}

	/**
	 * @param violenceResolutionCnt
	 *            the violenceResolutionCnt to set
	 */
	public void setViolenceResolutionCnt(String violenceResolutionCnt) {
		this.violenceResolutionCnt = violenceResolutionCnt;
	}

	/**
	 * @return the violenceUseCnt
	 */
	public String getViolenceUseCnt() {
		return violenceUseCnt;
	}

	/**
	 * @param violenceUseCnt
	 *            the violenceUseCnt to set
	 */
	public void setViolenceUseCnt(String violenceUseCnt) {
		this.violenceUseCnt = violenceUseCnt;
	}

	/**
	 * @return the violenceResolutionPct
	 */
	public String getViolenceResolutionPct() {
		return violenceResolutionPct;
	}

	/**
	 * @param violenceResolutionPct
	 *            the violenceResolutionPct to set
	 */
	public void setViolenceResolutionPct(String violenceResolutionPct) {
		this.violenceResolutionPct = violenceResolutionPct;
	}

	/**
	 * @return the violenceUsePct
	 */
	public String getViolenceUsePct() {
		return violenceUsePct;
	}

	/**
	 * @param violenceUsePct
	 *            the violenceUsePct to set
	 */
	public void setViolenceUsePct(String violenceUsePct) {
		this.violenceUsePct = violenceUsePct;
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
	 * @return the accidentResolutionCnt
	 */
	public String getAccidentResolutionCnt() {
		return accidentResolutionCnt;
	}

	/**
	 * @param accidentResolutionCnt
	 *            the accidentResolutionCnt to set
	 */
	public void setAccidentResolutionCnt(String accidentResolutionCnt) {
		this.accidentResolutionCnt = accidentResolutionCnt;
	}

	/**
	 * @return the accidentUseCnt
	 */
	public String getAccidentUseCnt() {
		return accidentUseCnt;
	}

	/**
	 * @param accidentUseCnt
	 *            the accidentUseCnt to set
	 */
	public void setAccidentUseCnt(String accidentUseCnt) {
		this.accidentUseCnt = accidentUseCnt;
	}

	/**
	 * @return the accidentResolutionPct
	 */
	public String getAccidentResolutionPct() {
		return accidentResolutionPct;
	}

	/**
	 * @param accidentResolutionPct
	 *            the accidentResolutionPct to set
	 */
	public void setAccidentResolutionPct(String accidentResolutionPct) {
		this.accidentResolutionPct = accidentResolutionPct;
	}

	/**
	 * @return the accidentUsePct
	 */
	public String getAccidentUsePct() {
		return accidentUsePct;
	}

	/**
	 * @param accidentUsePct
	 *            the accidentUsePct to set
	 */
	public void setAccidentUsePct(String accidentUsePct) {
		this.accidentUsePct = accidentUsePct;
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
	 * @return the destroyResolutionCnt
	 */
	public String getDestroyResolutionCnt() {
		return destroyResolutionCnt;
	}

	/**
	 * @param destroyResolutionCnt
	 *            the destroyResolutionCnt to set
	 */
	public void setDestroyResolutionCnt(String destroyResolutionCnt) {
		this.destroyResolutionCnt = destroyResolutionCnt;
	}

	/**
	 * @return the destroyUseCnt
	 */
	public String getDestroyUseCnt() {
		return destroyUseCnt;
	}

	/**
	 * @param destroyUseCnt
	 *            the destroyUseCnt to set
	 */
	public void setDestroyUseCnt(String destroyUseCnt) {
		this.destroyUseCnt = destroyUseCnt;
	}

	/**
	 * @return the destroyResolutionPct
	 */
	public String getDestroyResolutionPct() {
		return destroyResolutionPct;
	}

	/**
	 * @param destroyResolutionPct
	 *            the destroyResolutionPct to set
	 */
	public void setDestroyResolutionPct(String destroyResolutionPct) {
		this.destroyResolutionPct = destroyResolutionPct;
	}

	/**
	 * @return the destroyUsePct
	 */
	public String getDestroyUsePct() {
		return destroyUsePct;
	}

	/**
	 * @param destroyUsePct
	 *            the destroyUsePct to set
	 */
	public void setDestroyUsePct(String destroyUsePct) {
		this.destroyUsePct = destroyUsePct;
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
	 * @return the etcResolutionCnt
	 */
	public String getEtcResolutionCnt() {
		return etcResolutionCnt;
	}

	/**
	 * @param etcResolutionCnt
	 *            the etcResolutionCnt to set
	 */
	public void setEtcResolutionCnt(String etcResolutionCnt) {
		this.etcResolutionCnt = etcResolutionCnt;
	}

	/**
	 * @return the etcUseCnt
	 */
	public String getEtcUseCnt() {
		return etcUseCnt;
	}

	/**
	 * @param etcUseCnt
	 *            the etcUseCnt to set
	 */
	public void setEtcUseCnt(String etcUseCnt) {
		this.etcUseCnt = etcUseCnt;
	}

	/**
	 * @return the etcResolutionPct
	 */
	public String getEtcResolutionPct() {
		return etcResolutionPct;
	}

	/**
	 * @param etcResolutionPct
	 *            the etcResolutionPct to set
	 */
	public void setEtcResolutionPct(String etcResolutionPct) {
		this.etcResolutionPct = etcResolutionPct;
	}

	/**
	 * @return the etcUsePct
	 */
	public String getEtcUsePct() {
		return etcUsePct;
	}

	/**
	 * @param etcUsePct
	 *            the etcUsePct to set
	 */
	public void setEtcUsePct(String etcUsePct) {
		this.etcUsePct = etcUsePct;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNotUseCnt() {
		return notUseCnt;
	}

	public void setNotUseCnt(int notUseCnt) {
		this.notUseCnt = notUseCnt;
	}

	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId
	 *            the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the locationGu
	 */
	public String getLocationGu() {
		return locationGu;
	}

	/**
	 * @param locationGu
	 *            the locationGu to set
	 */
	public void setLocationGu(String locationGu) {
		this.locationGu = locationGu;
	}

	/**
	 * @return the locationDong
	 */
	public String getLocationDong() {
		return locationDong;
	}

	/**
	 * @param locationDong
	 *            the locationDong to set
	 */
	public void setLocationDong(String locationDong) {
		this.locationDong = locationDong;
	}

	/**
	 * @return the centerLat
	 */
	public String getCenterLat() {
		return centerLat;
	}

	/**
	 * @param centerLat
	 *            the centerLat to set
	 */
	public void setCenterLat(String centerLat) {
		this.centerLat = centerLat;
	}

	/**
	 * @return the centerLng
	 */
	public String getCenterLng() {
		return centerLng;
	}

	/**
	 * @param centerLng
	 *            the centerLng to set
	 */
	public void setCenterLng(String centerLng) {
		this.centerLng = centerLng;
	}

	/**
	 * @return the cctvCnt
	 */
	public int getCctvCnt() {
		return cctvCnt;
	}

	/**
	 * @param cctvCnt
	 *            the cctvCnt to set
	 */
	public void setCctvCnt(int cctvCnt) {
		this.cctvCnt = cctvCnt;
	}

	/**
	 * @return the crimeCnt
	 */
	public int getCrimeCnt() {
		return crimeCnt;
	}

	/**
	 * @param crimeCnt
	 *            the crimeCnt to set
	 */
	public void setCrimeCnt(int crimeCnt) {
		this.crimeCnt = crimeCnt;
	}

	/**
	 * @return the resultTotal
	 */
	public int getResultTotal() {
		return resultTotal;
	}

	/**
	 * @param resultTotal
	 *            the resultTotal to set
	 */
	public void setResultTotal(int resultTotal) {
		this.resultTotal = resultTotal;
	}

	/**
	 * @return the noon
	 */
	public int getNoon() {
		return noon;
	}

	/**
	 * @param noon
	 *            the noon to set
	 */
	public void setNoon(int noon) {
		this.noon = noon;
	}

	/**
	 * @return the evening
	 */
	public int getEvening() {
		return evening;
	}

	/**
	 * @param evening
	 *            the evening to set
	 */
	public void setEvening(int evening) {
		this.evening = evening;
	}

	/**
	 * @return the night
	 */
	public int getNight() {
		return night;
	}

	/**
	 * @param night
	 *            the night to set
	 */
	public void setNight(int night) {
		this.night = night;
	}

	/**
	 * @return the dawn
	 */
	public int getDawn() {
		return dawn;
	}

	/**
	 * @param dawn
	 *            the dawn to set
	 */
	public void setDawn(int dawn) {
		this.dawn = dawn;
	}

	/**
	 * @return the rate
	 */
	public float getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(float rate) {
		this.rate = rate;
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
	 * @return the cctvType0
	 */
	public String getCctvType0() {
		return cctvType0;
	}

	/**
	 * @param cctvType0
	 *            the cctvType0 to set
	 */
	public void setCctvType0(String cctvType0) {
		this.cctvType0 = cctvType0;
	}

	/**
	 * @return the cctvType1
	 */
	public String getCctvType1() {
		return cctvType1;
	}

	/**
	 * @param cctvType1
	 *            the cctvType1 to set
	 */
	public void setCctvType1(String cctvType1) {
		this.cctvType1 = cctvType1;
	}

	/**
	 * @return the cctvType2
	 */
	public String getCctvType2() {
		return cctvType2;
	}

	/**
	 * @param cctvType2
	 *            the cctvType2 to set
	 */
	public void setCctvType2(String cctvType2) {
		this.cctvType2 = cctvType2;
	}

	/**
	 * @return the cctvType3
	 */
	public String getCctvType3() {
		return cctvType3;
	}

	/**
	 * @param cctvType3
	 *            the cctvType3 to set
	 */
	public void setCctvType3(String cctvType3) {
		this.cctvType3 = cctvType3;
	}

	/**
	 * @return the cctvType4
	 */
	public String getCctvType4() {
		return cctvType4;
	}

	/**
	 * @param cctvType4
	 *            the cctvType4 to set
	 */
	public void setCctvType4(String cctvType4) {
		this.cctvType4 = cctvType4;
	}

	/**
	 * @return the cctvType5
	 */
	public String getCctvType5() {
		return cctvType5;
	}

	/**
	 * @param cctvType5
	 *            the cctvType5 to set
	 */
	public void setCctvType5(String cctvType5) {
		this.cctvType5 = cctvType5;
	}

	/**
	 * @return the cctvType6
	 */
	public String getCctvType6() {
		return cctvType6;
	}

	/**
	 * @param cctvType6
	 *            the cctvType6 to set
	 */
	public void setCctvType6(String cctvType6) {
		this.cctvType6 = cctvType6;
	}

	/**
	 * @return the cctvType7
	 */
	public String getCctvType7() {
		return cctvType7;
	}

	/**
	 * @param cctvType7
	 *            the cctvType7 to set
	 */
	public void setCctvType7(String cctvType7) {
		this.cctvType7 = cctvType7;
	}

	/**
	 * @return the cctvType8
	 */
	public String getCctvType8() {
		return cctvType8;
	}

	/**
	 * @param cctvType8
	 *            the cctvType8 to set
	 */
	public void setCctvType8(String cctvType8) {
		this.cctvType8 = cctvType8;
	}

	/**
	 * @return the cctvType9
	 */
	public String getCctvType9() {
		return cctvType9;
	}

	/**
	 * @param cctvType9
	 *            the cctvType9 to set
	 */
	public void setCctvType9(String cctvType9) {
		this.cctvType9 = cctvType9;
	}

	/**
	 * @return the cctvType10
	 */
	public String getCctvType10() {
		return cctvType10;
	}

	/**
	 * @param cctvType10
	 *            the cctvType10 to set
	 */
	public void setCctvType10(String cctvType10) {
		this.cctvType10 = cctvType10;
	}

	/**
	 * @return the videoId
	 */
	public String getVideoId() {
		return videoId;
	}

	/**
	 * @param videoId
	 *            the videoId to set
	 */
	public void setVideoId(String videoId) {
		this.videoId = videoId;
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
	 * @return the cctvCount
	 */
	public String getCctvCount() {
		return cctvCount;
	}

	/**
	 * @param cctvCount
	 *            the cctvCount to set
	 */
	public void setCctvCount(String cctvCount) {
		this.cctvCount = cctvCount;
	}

	/**
	 * @return the cctvTypeCode
	 */
	public String getCctvTypeCode() {
		return cctvTypeCode;
	}

	/**
	 * @param cctvTypeCode
	 *            the cctvTypeCode to set
	 */
	public void setCctvTypeCode(String cctvTypeCode) {
		this.cctvTypeCode = cctvTypeCode;
	}

	/**
	 * @return the cctvTypeNm
	 */
	public String getCctvTypeNm() {
		return cctvTypeNm;
	}

	/**
	 * @param cctvTypeNm
	 *            the cctvTypeNm to set
	 */
	public void setCctvTypeNm(String cctvTypeNm) {
		this.cctvTypeNm = cctvTypeNm;
	}

	/**
	 * @return the cctvImg
	 */
	public String getCctvImg() {
		return cctvImg;
	}

	/**
	 * @param cctvImg
	 *            the cctvImg to set
	 */
	public void setCctvImg(String cctvImg) {
		this.cctvImg = cctvImg;
	}

	public String getPreventionCnt() {
		return preventionCnt;
	}

	public void setPreventionCnt(String preventionCnt) {
		this.preventionCnt = preventionCnt;
	}

	public String getPreventionResolutionCnt() {
		return preventionResolutionCnt;
	}

	public void setPreventionResolutionCnt(String preventionResolutionCnt) {
		this.preventionResolutionCnt = preventionResolutionCnt;
	}

	public String getPreventionUseCnt() {
		return preventionUseCnt;
	}

	public void setPreventionUseCnt(String preventionUseCnt) {
		this.preventionUseCnt = preventionUseCnt;
	}

	public String getPreventionResolutionPct() {
		return preventionResolutionPct;
	}

	public void setPreventionResolutionPct(String preventionResolutionPct) {
		this.preventionResolutionPct = preventionResolutionPct;
	}

	public String getPreventionUsePct() {
		return preventionUsePct;
	}

	public void setPreventionUsePct(String preventionUsePct) {
		this.preventionUsePct = preventionUsePct;
	}

	public String getParkingCnt() {
		return parkingCnt;
	}

	public void setParkingCnt(String parkingCnt) {
		this.parkingCnt = parkingCnt;
	}

	public String getParkingResolutionCnt() {
		return parkingResolutionCnt;
	}

	public void setParkingResolutionCnt(String parkingResolutionCnt) {
		this.parkingResolutionCnt = parkingResolutionCnt;
	}

	public String getParkingUseCnt() {
		return parkingUseCnt;
	}

	public void setParkingUseCnt(String parkingUseCnt) {
		this.parkingUseCnt = parkingUseCnt;
	}

	public String getParkingResolutionPct() {
		return parkingResolutionPct;
	}

	public void setParkingResolutionPct(String parkingResolutionPct) {
		this.parkingResolutionPct = parkingResolutionPct;
	}

	public String getParkingUsePct() {
		return parkingUsePct;
	}

	public void setParkingUsePct(String parkingUsePct) {
		this.parkingUsePct = parkingUsePct;
	}

	public String getTrackCnt() {
		return trackCnt;
	}

	public void setTrackCnt(String trackCnt) {
		this.trackCnt = trackCnt;
	}

	public String getTrackResolutionCnt() {
		return trackResolutionCnt;
	}

	public void setTrackResolutionCnt(String trackResolutionCnt) {
		this.trackResolutionCnt = trackResolutionCnt;
	}

	public String getTrackUseCnt() {
		return trackUseCnt;
	}

	public void setTrackUseCnt(String trackUseCnt) {
		this.trackUseCnt = trackUseCnt;
	}

	public String getTrackResolutionPct() {
		return trackResolutionPct;
	}

	public void setTrackResolutionPct(String trackResolutionPct) {
		this.trackResolutionPct = trackResolutionPct;
	}

	public String getTrackUsePct() {
		return trackUsePct;
	}

	public void setTrackUsePct(String trackUsePct) {
		this.trackUsePct = trackUsePct;
	}

	public String getGarbageCnt() {
		return garbageCnt;
	}

	public void setGarbageCnt(String garbageCnt) {
		this.garbageCnt = garbageCnt;
	}

	public String getGarbageResolutionCnt() {
		return garbageResolutionCnt;
	}

	public void setGarbageResolutionCnt(String garbageResolutionCnt) {
		this.garbageResolutionCnt = garbageResolutionCnt;
	}

	public String getGarbageUseCnt() {
		return garbageUseCnt;
	}

	public void setGarbageUseCnt(String garbageUseCnt) {
		this.garbageUseCnt = garbageUseCnt;
	}

	public String getGarbageResolutionPct() {
		return garbageResolutionPct;
	}

	public void setGarbageResolutionPct(String garbageResolutionPct) {
		this.garbageResolutionPct = garbageResolutionPct;
	}

	public String getGarbageUsePct() {
		return garbageUsePct;
	}

	public void setGarbageUsePct(String garbageUsePct) {
		this.garbageUsePct = garbageUsePct;
	}

	public String getMultiCnt() {
		return multiCnt;
	}

	public void setMultiCnt(String multiCnt) {
		this.multiCnt = multiCnt;
	}

	public String getMultiResolutionCnt() {
		return multiResolutionCnt;
	}

	public void setMultiResolutionCnt(String multiResolutionCnt) {
		this.multiResolutionCnt = multiResolutionCnt;
	}

	public String getMultiUseCnt() {
		return multiUseCnt;
	}

	public void setMultiUseCnt(String multiUseCnt) {
		this.multiUseCnt = multiUseCnt;
	}

	public String getMultiResolutionPct() {
		return multiResolutionPct;
	}

	public void setMultiResolutionPct(String multiResolutionPct) {
		this.multiResolutionPct = multiResolutionPct;
	}

	public String getMultiUsePct() {
		return multiUsePct;
	}

	public void setMultiUsePct(String multiUsePct) {
		this.multiUsePct = multiUsePct;
	}

	public String getFireCnt() {
		return fireCnt;
	}

	public void setFireCnt(String fireCnt) {
		this.fireCnt = fireCnt;
	}

	public String getFireResolutionCnt() {
		return fireResolutionCnt;
	}

	public void setFireResolutionCnt(String fireResolutionCnt) {
		this.fireResolutionCnt = fireResolutionCnt;
	}

	public String getFireUseCnt() {
		return fireUseCnt;
	}

	public void setFireUseCnt(String fireUseCnt) {
		this.fireUseCnt = fireUseCnt;
	}

	public String getFireResolutionPct() {
		return fireResolutionPct;
	}

	public void setFireResolutionPct(String fireResolutionPct) {
		this.fireResolutionPct = fireResolutionPct;
	}

	public String getFireUsePct() {
		return fireUsePct;
	}

	public void setFireUsePct(String fireUsePct) {
		this.fireUsePct = fireUsePct;
	}

	public String getmFireCnt() {
		return mFireCnt;
	}

	public void setmFireCnt(String mFireCnt) {
		this.mFireCnt = mFireCnt;
	}

	public String getmFireResolutionCnt() {
		return mFireResolutionCnt;
	}

	public void setmFireResolutionCnt(String mFireResolutionCnt) {
		this.mFireResolutionCnt = mFireResolutionCnt;
	}

	public String getmFireUseCnt() {
		return mFireUseCnt;
	}

	public void setmFireUseCnt(String mFireUseCnt) {
		this.mFireUseCnt = mFireUseCnt;
	}

	public String getmFireResolutionPct() {
		return mFireResolutionPct;
	}

	public void setmFireResolutionPct(String mFireResolutionPct) {
		this.mFireResolutionPct = mFireResolutionPct;
	}

	public String getmFireUsePct() {
		return mFireUsePct;
	}

	public void setmFireUsePct(String mFireUsePct) {
		this.mFireUsePct = mFireUsePct;
	}

	public String getStreetCnt() {
		return streetCnt;
	}

	public void setStreetCnt(String streetCnt) {
		this.streetCnt = streetCnt;
	}

	public String getStreetResolutionCnt() {
		return streetResolutionCnt;
	}

	public void setStreetResolutionCnt(String streetResolutionCnt) {
		this.streetResolutionCnt = streetResolutionCnt;
	}

	public String getStreetUseCnt() {
		return streetUseCnt;
	}

	public void setStreetUseCnt(String streetUseCnt) {
		this.streetUseCnt = streetUseCnt;
	}

	public String getStreetResolutionPct() {
		return streetResolutionPct;
	}

	public void setStreetResolutionPct(String streetResolutionPct) {
		this.streetResolutionPct = streetResolutionPct;
	}

	public String getStreetUsePct() {
		return streetUsePct;
	}

	public void setStreetUsePct(String streetUsePct) {
		this.streetUsePct = streetUsePct;
	}

	public String getParkCnt() {
		return parkCnt;
	}

	public void setParkCnt(String parkCnt) {
		this.parkCnt = parkCnt;
	}

	public String getParkResolutionCnt() {
		return parkResolutionCnt;
	}

	public void setParkResolutionCnt(String parkResolutionCnt) {
		this.parkResolutionCnt = parkResolutionCnt;
	}

	public String getParkUseCnt() {
		return parkUseCnt;
	}

	public void setParkUseCnt(String parkUseCnt) {
		this.parkUseCnt = parkUseCnt;
	}

	public String getParkResolutionPct() {
		return parkResolutionPct;
	}

	public void setParkResolutionPct(String parkResolutionPct) {
		this.parkResolutionPct = parkResolutionPct;
	}

	public String getParkUsePct() {
		return parkUsePct;
	}

	public void setParkUsePct(String parkUsePct) {
		this.parkUsePct = parkUsePct;
	}

	public String getChildCnt() {
		return childCnt;
	}

	public void setChildCnt(String childCnt) {
		this.childCnt = childCnt;
	}

	public String getChildResolutionCnt() {
		return childResolutionCnt;
	}

	public void setChildResolutionCnt(String childResolutionCnt) {
		this.childResolutionCnt = childResolutionCnt;
	}

	public String getChildUseCnt() {
		return childUseCnt;
	}

	public void setChildUseCnt(String childUseCnt) {
		this.childUseCnt = childUseCnt;
	}

	public String getChildResolutionPct() {
		return childResolutionPct;
	}

	public void setChildResolutionPct(String childResolutionPct) {
		this.childResolutionPct = childResolutionPct;
	}

	public String getChildUsePct() {
		return childUsePct;
	}

	public void setChildUsePct(String childUsePct) {
		this.childUsePct = childUsePct;
	}

}
