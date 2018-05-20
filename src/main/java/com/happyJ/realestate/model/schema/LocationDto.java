/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 6. 8. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.schema
 *  @fileName : LocationsDto.java
 *  @author : yongpal
 *  @since 2016. 6. 8.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 6. 8.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 6. 8.        yongpal       create LocationsDto.java
 *  </pre>
 ******************************************************************************/
public class LocationDto extends CommonDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ordering;
	private String locationsId;
	private String locationGu;
	private String locationDong;
	private String locationCity;
	private String description;
	private String centerLongitude;
	private String centerLatitude;
	
	/**
	 * @return the ordering
	 */
	public String getOrdering() {
		return ordering;
	}
	/**
	 * @param ordering the ordering to set
	 */
	public void setOrdering(String ordering) {
		this.ordering = ordering;
	}
	/**
	 * @return the locationsId
	 */
	public String getLocationsId() {
		return locationsId;
	}
	/**
	 * @param locationsId the locationsId to set
	 */
	public void setLocationsId(String locationsId) {
		this.locationsId = locationsId;
	}
	/**
	 * @return the locationGu
	 */
	public String getLocationGu() {
		return locationGu;
	}
	/**
	 * @param locationGu the locationGu to set
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
	 * @param locationDong the locationDong to set
	 */
	public void setLocationDong(String locationDong) {
		this.locationDong = locationDong;
	}
	/**
	 * @return the locationCity
	 */
	public String getLocationCity() {
		return locationCity;
	}
	/**
	 * @param locationCity the locationCity to set
	 */
	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the centerLongitude
	 */
	public String getCenterLongitude() {
		return centerLongitude;
	}
	/**
	 * @param centerLongitude the centerLongitude to set
	 */
	public void setCenterLongitude(String centerLongitude) {
		this.centerLongitude = centerLongitude;
	}
	/**
	 * @return the centerLatitude
	 */
	public String getCenterLatitude() {
		return centerLatitude;
	}
	/**
	 * @param centerLatitude the centerLatitude to set
	 */
	public void setCenterLatitude(String centerLatitude) {
		this.centerLatitude = centerLatitude;
	}
	
}
