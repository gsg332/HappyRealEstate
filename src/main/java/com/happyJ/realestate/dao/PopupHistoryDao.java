/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 5. 17. yongpal
*****************************************************************************/
package com.happyJ.realestate.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.schema.PopupHIstoryDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : PopupHistoryDao.java
 *  @author : yongpal
 *  @since 2016. 5. 17.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 5. 17.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 5. 17.        yongpal       create PopupHistoryDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface PopupHistoryDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertPopHistoryInfo 
	 *  @param popDto
	 **********************************************/
	int insertPopupHistory(PopupHIstoryDto popDto) throws DataAccessException;

}
