/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 6. 3. yongpal
*****************************************************************************/
package com.happyJ.realestate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.dao.ActionHistoryDao;
import com.happyJ.realestate.model.schema.ActionHistoryDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.service
 *  @fileName : ActionHistoryService.java
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
 *     2016. 6. 3.        yongpal       create ActionHistoryService.java
 *  </pre>
 ******************************************************************************/
@Service
public class ActionHistoryService {
	
	@Autowired
	private ActionHistoryDao actionHistoryDao;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectActionHistoryList 
	 *  @param actionHistoryDto
	 *  @return
	 **********************************************/
	public List<ActionHistoryDto> selectActionHistoryList(ActionHistoryDto actionHistoryDto) {
		
		List<ActionHistoryDto> actionHistoryList = actionHistoryDao.selectActionHistoryList(actionHistoryDto);
		
		if (!actionHistoryList.isEmpty()){
			// 반려 목록 카운트
			int listCnt = actionHistoryDao.selectActionHistoryListCounts(actionHistoryDto);

			// 페이징 처리
			actionHistoryList.get(0).setCurrentPage(actionHistoryDto.getCurrentPage());
			actionHistoryList.get(0).setRowSize(actionHistoryDto.getRowSize());
			actionHistoryList.get(0).setListCnt(listCnt);
			actionHistoryList.get(0).setTotalPage((int)Math.ceil((double)listCnt / actionHistoryDto.getRowSize()));
		}
		
		return actionHistoryList;
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectActionHistoryListExcel 
	 *  @param actionHistoryDto
	 *  @return List<ActionHistoryDto>
	 **********************************************/
	public List<ActionHistoryDto> selectActionHistoryListExcel(ActionHistoryDto actionHistoryDto) {

		return actionHistoryDao.selectActionHistoryListExcel(actionHistoryDto);
	}
	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method joinHistoryList 
	 *  @param actionHistoryDto
	 *  @return
	 **********************************************/
	public List<ActionHistoryDto> joinHistoryList(ActionHistoryDto actionHistoryDto) {
		
		List<ActionHistoryDto> joinList = actionHistoryDao.joinHistoryList(actionHistoryDto);
		
		if (!joinList.isEmpty()){
			// 반려 목록 카운트
			int listCnt = actionHistoryDao.joinHistoryListCounts(actionHistoryDto);

			// 페이징 처리
			joinList.get(0).setCurrentPage(actionHistoryDto.getCurrentPage());
			joinList.get(0).setRowSize(actionHistoryDto.getRowSize());
			joinList.get(0).setListCnt(listCnt);
			joinList.get(0).setTotalPage((int)Math.ceil((double)listCnt / actionHistoryDto.getRowSize()));
		}
		
		return joinList;
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method joinHistoryListExcel 
	 *  @param actionHistoryDto
	 *  @return List<ActionHistoryDto>
	 **********************************************/
	public List<ActionHistoryDto> joinHistoryListExcel(ActionHistoryDto actionHistoryDto) {

		return actionHistoryDao.joinHistoryListExcel(actionHistoryDto);
	}	
	

}
