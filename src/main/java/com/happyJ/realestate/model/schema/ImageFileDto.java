/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 12. 5. jincheol
*****************************************************************************/
package com.happyJ.realestate.model.schema;

import org.springframework.web.multipart.MultipartFile;

import com.happyJ.realestate.model.CommonDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.schema
 *  @fileName : ImageFileDto.java
 *  @author : jincheol
 *  @since 2016. 12. 5.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 12. 5.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 12. 5.   jincheol       create ImageFileDto.java
 *  </pre>
 ******************************************************************************/
public class ImageFileDto extends CommonDto {

	private static final long serialVersionUID = 1L;
	private String manageCode;
	private String imageName;
	private MultipartFile imageFile;
	
	/**
	 * @return the manageCode
	 */
	public String getManageCode() {
		return manageCode;
	}
	/**
	 * @param manageCode the manageCode to set
	 */
	public void setManageCode(String manageCode) {
		this.manageCode = manageCode;
	}
	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}
	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	/**
	 * @return the imageFile
	 */
	public MultipartFile getImageFile() {
		return imageFile;
	}
	/**
	 * @param imageFile the imageFile to set
	 */
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
	
	
}
