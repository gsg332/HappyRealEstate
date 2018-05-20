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

import com.happyJ.realestate.model.custom.EvidenceCustomDto;
import com.happyJ.realestate.model.schema.ItemEvidenceDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : EvidenceDao.java
 *  @author : yongpal
 *  @since 2016. 4. 25.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 25.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 25.        yongpal       create EvidenceDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface EvidenceDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectEvidenceList 
	 *  @param evidenceCustomDto
	 *  @return
	 **********************************************/
	List<ItemEvidenceDto> selectEvidenceList(EvidenceCustomDto evidenceCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectEvidenceListCount 
	 *  @param evidenceCustomDto
	 *  @return
	 **********************************************/
	int selectEvidenceListCount(EvidenceCustomDto evidenceCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectEvidenceListExcel 
	 *  @param evidenceCustomDto
	 *  @return
	 **********************************************/
	List<EvidenceCustomDto> selectEvidenceListExcel(EvidenceCustomDto evidenceCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateEvidenceInfo 
	 *  @param evidenceDto
	 * @return 
	 **********************************************/	
	int updateEvidenceInfo(ItemEvidenceDto evidenceDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertEvidenceInfo 
	 *  @param evidenceCustomDto
	 *  @return
	 **********************************************/
	int insertEvidenceInfo(EvidenceCustomDto evidenceCustomDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectEvidenceInfo 
	 *  @param evidenceCustomDto
	 *  @return
	 **********************************************/
	EvidenceCustomDto selectEvidenceInfo(EvidenceCustomDto evidenceCustomDto) throws DataAccessException;
	
}
