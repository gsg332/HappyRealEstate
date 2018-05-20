/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 6. 3. yongpal
*****************************************************************************/
package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.schema.ActionHistoryDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : ActionHistoryDao.java
 *  @author : yongpal
 *  @since 2016. 6. 3.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 6. 3.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 6. 3.        yongpal       create ActionHistoryDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface ActionHistoryDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectActionHistoryList 
	 *  @param actionHistoryDto
	 *  @return
	 **********************************************/
	List<ActionHistoryDto> selectActionHistoryList(ActionHistoryDto actionHistoryDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectActionHistoryListCounts 
	 *  @param actionHistoryDto
	 *  @return
	 **********************************************/
	int selectActionHistoryListCounts(ActionHistoryDto applyDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectActionHistoryListExcel 
	 *  @param actionHistoryDto
	 *  @return
	 **********************************************/
	List<ActionHistoryDto> selectActionHistoryListExcel(ActionHistoryDto applyDto) throws DataAccessException;	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method joinHistoryList 
	 *  @param actionHistoryDto
	 *  @return
	 **********************************************/
	List<ActionHistoryDto> joinHistoryList(ActionHistoryDto actionHistoryDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method joinHistoryListCounts 
	 *  @param actionHistoryDto
	 *  @return
	 **********************************************/
	int joinHistoryListCounts(ActionHistoryDto applyDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method joinHistoryListExcel 
	 *  @param actionHistoryDto
	 *  @return
	 **********************************************/
	List<ActionHistoryDto> joinHistoryListExcel(ActionHistoryDto applyDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertActionHistoryInfo 
	 *  @param actionHistoryDto
	 **********************************************/
	void insertActionHistoryInfo(ActionHistoryDto actionHistoryDto);
}
