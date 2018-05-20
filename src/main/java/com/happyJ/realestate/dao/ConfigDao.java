/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 6. 2. yongpal
*****************************************************************************/
package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.schema.ConfigDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : ConfigDao.java
 *  @author : yongpal
 *  @since 2016. 6. 2.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 6. 2.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 6. 2.        yongpal       create ConfigDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface ConfigDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectConfigList 
	 *  @param configDto
	 *  @return
	 **********************************************/
	List<ConfigDto> selectConfigList(ConfigDto configDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectConfigListCount 
	 *  @param configDto
	 *  @return
	 **********************************************/
	int selectConfigListCount(ConfigDto configDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateConfigInfo 
	 *  @param configDto
	 *  @return
	 **********************************************/
	int updateConfigInfo(ConfigDto configDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectConfigListAll 
	 *  @param configDto
	 *  @return
	 **********************************************/
	List<ConfigDto> selectConfigListAll(ConfigDto configDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectConfigListForAPI 
	 *  @param configDto
	 *  @return
	 **********************************************/
	List<ConfigDto> selectConfigListForAPI(ConfigDto configDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectConfigInfo 
	 *  @param configDto
	 *  @return
	 **********************************************/
	ConfigDto selectConfigInfo(ConfigDto configDto) throws DataAccessException;

}
