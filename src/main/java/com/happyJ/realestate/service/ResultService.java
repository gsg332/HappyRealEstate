/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.dao.ResultDao;
import com.happyJ.realestate.model.schema.ItemResultDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.service
 *  @fileName : EvidenceService.java
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
 *     2016. 4. 25.        yongpal       create EvidenceService.java
 *  </pre>
 ******************************************************************************/
@Service
public class ResultService {
	
	@Autowired
	private ResultDao resultDao;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertResultInfo 
	 *  @param resultDto
	 *  @return
	 **********************************************/
	public void insertResultInfo(ItemResultDto resultDto) {
		resultDao.insertResultInfo(resultDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectResultInfo 
	 *  @param resultDto
	 *  @return
	 **********************************************/
	public ItemResultDto selectResultInfo(ItemResultDto resultDto) {
		return resultDao.selectResultInfo(resultDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateResultInfo 
	 *  @param resultDto
	 *  @return
	 **********************************************/
	public int updateResultInfo(ItemResultDto resultDto) {
		return resultDao.updateResultInfo(resultDto);
	}
	
}
