/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 5. 17. yongpal
*****************************************************************************/
package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.schema.FileDto;
import com.happyJ.realestate.model.schema.ImageFileDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : FileDao.java
 *  @author : yongpal
 *  @since 2016. 5. 17.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 5. 17.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 5. 17.        yongpal       create FileDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface FileDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectFileList 
	 *  @param fileDto
	 *  @return
	 **********************************************/
	List<FileDto> selectFileList(FileDto fileDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertFileInfo 
	 *  @param fileDto
	 **********************************************/
	void insertFileInfo(FileDto fileDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateFileInfo 
	 *  @param fileDto
	 **********************************************/
	//int updateFileInfo(FileDto fileDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : cctv image 업데이트
	 *  </pre>
	 * 	@Method updateCctvImageFile 
	 *  @param imageFileDto
	 *  @return
	 **********************************************/
	int updateCctvImageFile(ImageFileDto imageFileDto) throws DataAccessException;

}
