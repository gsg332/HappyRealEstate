/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 6. 8. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.schema;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.schema
 *  @fileName : LocationCoordinatesDto.java
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
 *     2016. 6. 8.        yongpal       create LocationCoordinatesDto.java
 *  </pre>
 ******************************************************************************/
public class CoordinateDto extends LocationDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String locationLongitude;
	private String locationLatitude;
	private String locationCoordinateId;
	private String locationAltitude;
	
	/**
	 * @return the locationLongitude
	 */
	public String getLocationLongitude() {
		return locationLongitude;
	}
	/**
	 * @param locationLongitude the locationLongitude to set
	 */
	public void setLocationLongitude(String locationLongitude) {
		this.locationLongitude = locationLongitude;
	}
	/**
	 * @return the locationLatitude
	 */
	public String getLocationLatitude() {
		return locationLatitude;
	}
	/**
	 * @param locationLatitude the locationLatitude to set
	 */
	public void setLocationLatitude(String locationLatitude) {
		this.locationLatitude = locationLatitude;
	}
	/**
	 * @return the locationCoordinateId
	 */
	public String getLocationCoordinateId() {
		return locationCoordinateId;
	}
	/**
	 * @param locationCoordinateId the locationCoordinateId to set
	 */
	public void setLocationCoordinateId(String locationCoordinateId) {
		this.locationCoordinateId = locationCoordinateId;
	}
	/**
	 * @return the locationAltitude
	 */
	public String getLocationAltitude() {
		return locationAltitude;
	}
	/**
	 * @param locationAltitude the locationAltitude to set
	 */
	public void setLocationAltitude(String locationAltitude) {
		this.locationAltitude = locationAltitude;
	}
	
}
