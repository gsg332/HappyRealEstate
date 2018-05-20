/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 5. 31. yongpal
*****************************************************************************/
package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.schema.LogDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : LogDao.java
 *  @author : yongpal
 *  @since 2016. 5. 31.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 5. 31.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 5. 31.        yongpal       create LogDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface LogDao {
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectLogList 
	 *  @param logDto
	 *  @return
	 **********************************************/
	List<LogDto> selectLogList(LogDto logDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectLogListCount 
	 *  @param logDto
	 *  @return
	 **********************************************/
	int selectLogListCount(LogDto logDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectLogInfo 
	 *  @param logDto
	 *  @return
	 **********************************************/
	LogDto selectLogInfo(LogDto logDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertLogInfo 
	 *  @param logDto
	 **********************************************/
	void insertLogInfo(LogDto logDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateLogInfo 
	 *  @param logDto
	 *  @return
	 **********************************************/
	int updateLogInfo(LogDto logDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method searchLogListExcel 
	 *  @param logDto
	 *  @return
	 **********************************************/
	List<LogDto> searchLogListExcel(LogDto logDto) throws DataAccessException;


}
