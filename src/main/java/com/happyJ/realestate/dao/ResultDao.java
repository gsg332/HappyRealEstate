/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 27. yongpal
*****************************************************************************/
package com.happyJ.realestate.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.schema.ItemResultDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : ResultDao.java
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
 *     2016. 4. 27.        yongpal       create ResultDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface ResultDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertResultInfo 
	 *  @param resultDto
	 **********************************************/
	void insertResultInfo(ItemResultDto resultDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectResultInfo 
	 *  @param resultDto
	 *  @return
	 **********************************************/
	ItemResultDto selectResultInfo(ItemResultDto resultDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateResultInfo 
	 *  @param resultDto
	 *  @return
	 **********************************************/
	int updateResultInfo(ItemResultDto resultDto) throws DataAccessException;

}
