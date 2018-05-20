/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 27. yongpal
*****************************************************************************/
package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.custom.ExtendCustomDto;
import com.happyJ.realestate.model.schema.ItemExtendDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : ExtendDao.java
 *  @author : yongpal
 *  @since 2016. 4. 27.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 27.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 27.        yongpal       create ExtendDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface ExtendDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectExtendList 
	 *  @param evidenceCustomDto
	 *  @return
	 **********************************************/
	List<ItemExtendDto> selectExtendList(ExtendCustomDto extendCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectExtendListCount 
	 *  @param extendCustomDto
	 *  @return
	 **********************************************/
	int selectExtendListCount(ExtendCustomDto extendCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectExtendListExcel 
	 *  @param extendCustomDto
	 *  @return
	 **********************************************/
	List<ExtendCustomDto> selectExtendListExcel(ExtendCustomDto extendCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertExtendInfo 
	 *  @param extendDto
	 **********************************************/
	void insertExtendInfo(ItemExtendDto extendDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateExtendInfo 
	 *  @param extendDto
	 * @return 
	 **********************************************/	
	int updateExtendInfo(ExtendCustomDto extendDto) throws DataAccessException;	

}
