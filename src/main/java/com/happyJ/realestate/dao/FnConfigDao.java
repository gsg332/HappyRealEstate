package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.schema.FnConfigDto;

@Repository
public interface FnConfigDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectFnConfigListCount 
	 *  @param fnConfigDto
	 *  @return
	 **********************************************/
	int selectFnConfigListCount(FnConfigDto fnConfigDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectFnConfigList 
	 *  @param fnConfigDto
	 *  @return
	 **********************************************/
	List<FnConfigDto> selectFnConfigList(FnConfigDto fnConfigDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertFnConfigInfo 
	 *  @param fnConfigDto
	 *  @return
	 **********************************************/
	int insertFnConfigInfo(FnConfigDto fnConfigDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateFnConfigInfo 
	 *  @param fnConfigDto
	 *  @return
	 **********************************************/
	int updateFnConfigInfo(FnConfigDto fnConfigDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method deleteFnConfigInfo 
	 *  @param fnConfigDto
	 *  @return
	 **********************************************/
	int deleteFnConfigInfo(FnConfigDto fnConfigDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectFnConfigListAll 
	 *  @param fnConfigDto
	 *  @return
	 **********************************************/
	List<FnConfigDto> selectFnConfigListAll(FnConfigDto fnConfigDto) throws DataAccessException;

}
