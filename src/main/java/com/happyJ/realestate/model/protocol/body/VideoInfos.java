/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 8. 31. jincheol
*****************************************************************************/
package com.happyJ.realestate.model.protocol.body;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.protocol.body
 *  @fileName : VideoInfos.java
 *  @author : jincheol
 *  @since 2016. 8. 31.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 8. 31.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE				NAME			DESC
 *    -----------		----------		---------------------------------------
 *    2016. 8. 31.		jincheol		create VideoInfos.java
 *  </pre>
 ******************************************************************************/
public class VideoInfos {
	private String videoId;
	private String videoName;
	/**
	 * @return the videoId
	 */
	public String getVideoId() {
		return videoId;
	}
	/**
	 * @param videoId the videoId to set
	 */
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	/**
	 * @return the videoName
	 */
	public String getVideoName() {
		return videoName;
	}
	/**
	 * @param videoName the videoName to set
	 */
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
}
