/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 20. yongpal
*****************************************************************************/
package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.schema.CodeDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : CodeDao.java
 *  @author : yongpal
 *  @since 2016. 4. 20.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 20.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 20.        yongpal       create CodeDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface CodeDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectCodeList 
	 *  @param codeDto
	 *  @return
	 **********************************************/
	List<CodeDto> selectCodeList(CodeDto codeDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectCodeGroupList 
	 *  @param codeDto
	 *  @return
	 **********************************************/
	List<CodeDto> selectCodeGroupList(CodeDto codeDto) throws DataAccessException;	

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method searchCodeGroupList 
	 *  @param codeDto
	 *  @return
	 **********************************************/
	List<CodeDto> searchCodeGroupList(CodeDto codeDto) throws DataAccessException;	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method searchCodeGroupListCount 
	 *  @param codeDto
	 *  @return
	 **********************************************/
	int searchCodeGroupListCount(CodeDto codeDto) throws DataAccessException;	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateCodeInfo 
	 *  @param codeDto
	 *  @return
	 **********************************************/
	int updateCodeInfo(CodeDto codeDto) throws DataAccessException;	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method deleteCodeInfo 
	 *  @param codeDto
	 *  @return
	 **********************************************/
	int deleteCodeInfo(CodeDto codeDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertCodeInfo 
	 *  @param codeDto
	 **********************************************/
	void insertCodeInfo(CodeDto codeDto) throws DataAccessException;	
}
