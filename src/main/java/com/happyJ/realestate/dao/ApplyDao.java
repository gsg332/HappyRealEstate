/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.custom.ApplyCustomDto;
import com.happyJ.realestate.model.protocol.body.ItemList;
import com.happyJ.realestate.model.schema.FileDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : ApplyDao.java
 *  @author : yongpal
 *  @since 2016. 4. 11.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 11.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 11.        yongpal       create ApplyDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface ApplyDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectApplyList 
	 *  @param applyCustomDto
	 *  @return
	 **********************************************/
	List<ApplyCustomDto> selectApplyList(ApplyCustomDto applyCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectTotalCounts 
	 *  @param applyCustomDto
	 *  @return
	 **********************************************/
	ApplyCustomDto selectTotalCounts(ApplyCustomDto applyCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectAttachFileList 
	 *  @param apply
	 *  @return
	 **********************************************/
	List<FileDto> selectAttachFileList(ApplyCustomDto applyCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectApplyListCount 
	 *  @param applyCustomDto
	 *  @return
	 **********************************************/
	int selectApplyListCount(ApplyCustomDto applyCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectApplyInfo 
	 *  @param applyCustomDto
	 *  @return
	 **********************************************/
	ApplyCustomDto selectApplyInfo(ApplyCustomDto applyCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectApplyListExcel 
	 *  @param applyCustomDto
	 *  @return
	 **********************************************/
	List<ApplyCustomDto> selectApplyListExcel(ApplyCustomDto applyCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertApplyInfo 
	 *  @param applyCustomDto
	 **********************************************/
	Integer insertApplyInfo(ApplyCustomDto applyCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectRejectList 
	 *  @param applyDto
	 *  @return
	 **********************************************/
	List<ApplyCustomDto> selectRejectList(ApplyCustomDto applyDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectRejectListCount 
	 *  @param applyDto
	 *  @return
	 **********************************************/
	int selectRejectListCounts(ApplyCustomDto applyDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectRejectListExcel 
	 *  @param applyDto
	 *  @return
	 **********************************************/
	List<ApplyCustomDto> selectRejectListExcel(ApplyCustomDto applyDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateApply 
	 *  @param applyDto
	 *  @return
	 **********************************************/
	int updateApplyInfo(ApplyCustomDto applyDto) throws DataAccessException;	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectResultList 
	 *  @param applyDto
	 *  @return
	 **********************************************/
	List<ApplyCustomDto> selectResultList(ApplyCustomDto applyDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectResultListCounts 
	 *  @param applyDto
	 *  @return
	 **********************************************/
	int selectResultListCounts(ApplyCustomDto applyDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectResultListExcel 
	 *  @param applyDto
	 *  @return
	 **********************************************/
	List<ApplyCustomDto> selectResultListExcel(ApplyCustomDto applyDto) throws DataAccessException;	

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectMarkerList 
	 *  @param applyDto
	 *  @return
	 **********************************************/
	List<ApplyCustomDto> selectMarkerList(ApplyCustomDto applyDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectApplyListForVMS 
	 *  @param itemDto
	 *  @return
	 **********************************************/
	List<ItemList> selectApplyListForAPI(ApplyCustomDto applyDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectUserIdForFile 
	 *  @param itemDto
	 *  @return
	 **********************************************/
	ApplyCustomDto selectUserIdForFile(String itemNo) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectDownloadLimitCount 
	 *  @param applyDto
	 *  @return
	 *  @throws DataAccessException
	 **********************************************/
	Integer selectDownloadLimitCount(ApplyCustomDto applyDto) throws DataAccessException;
	
}
