/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 28. yongpal
*****************************************************************************/
package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.custom.ApplyStatCustomDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : ApplyStatDao.java
 *  @author : yongpal
 *  @since 2016. 4. 28.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 28.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 28.        yongpal       create ApplyStatDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface ApplyStatDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method userMainTotalCounts 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	ApplyStatCustomDto userMainTotalCounts(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 사용자 신청현황 그래프 
	 *  </pre>
	 * 	@Method userMainDateGraphCounts 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> userMainDateGraphCounts(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method userMainResultCounts 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	ApplyStatCustomDto userMainResultCounts(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method userMainLimitDateCounts 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> userMainLimitDateList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method userMainLimitDateCounts 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	ApplyStatCustomDto userMainLimitDateCounts(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method userStatSolveCounts 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> userStatSolveList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;		
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method userStatReasonList 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> userStatReasonList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectDetailEtcReasonList 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> selectDetailEtcReasonList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectDetailEtcReasonListCount 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	int selectDetailEtcReasonListCount(ApplyStatCustomDto applyStatDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method userStatAllList 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	String[] userStatAllList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;		
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method userStatYears 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	String[] userStatYears(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;	
	

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectPartList 
	 *  @param applyStatDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> selectPartList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;		
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method resultStatApplyList 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> resultStatApplyList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;		

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method resultStatPositionList 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> resultStatPositionList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;	

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method resultStatDepartList 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> resultStatDepartList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;		

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method resultStatUserList 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> resultStatUserList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;	

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method resultStatCrimeList 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> resultStatCrimeList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;		

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method resultStatApprTimeList 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> resultStatApprTimeList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;		

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method resultStatPlayList 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> resultStatPlayList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;	

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method resultStatVolumeList 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> resultStatVolumeList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;		

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method resultStatTimeList 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> resultStatTimeList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method applyBusinessList 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	ApplyStatCustomDto applyBusinessList(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method itemUseResultDiff 
	 *  @param applyStatCustomDto
	 *  @return
	 **********************************************/
	List<ApplyStatCustomDto> itemUseResultDiff(ApplyStatCustomDto applyStatCustomDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method itemMinDate 
	 *  @return
	 **********************************************/
	String itemMinDate() throws DataAccessException;
	
}

