/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.dao.LogDao;
import com.happyJ.realestate.model.schema.LogDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.service
 *  @fileName : LogService.java
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
 *     2016. 5. 31.        yongpal       create LogService.java
 *  </pre>
 ******************************************************************************/
@Service
public class LogService {
	
	@Autowired
	private LogDao logDao;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectLogList 
	 *  @param logDto
	 *  @return
	 **********************************************/
	public List<LogDto> selectLogList(LogDto logDto) {
		
		List<LogDto> logList = logDao.selectLogList(logDto);
		
		if (!logList.isEmpty()){
			// 재생 기록 목록 카운트
			int listCnt = logDao.selectLogListCount(logDto);

			// 페이징 처리
			logList.get(0).setCurrentPage(logDto.getCurrentPage());
			logList.get(0).setRowSize(logDto.getRowSize());
			logList.get(0).setListCnt(listCnt);
			logList.get(0).setTotalPage((int)Math.ceil((double)listCnt / logDto.getRowSize()));
		}
		
		return logList;
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectLogInfo 
	 *  @param logDto
	 *  @return
	 **********************************************/
	public LogDto selectLogInfo(LogDto logDto) {
		return logDao.selectLogInfo(logDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertLogInfo 
	 *  @param logDto
	 **********************************************/
	public void insertLogInfo(LogDto logDto) {
		logDao.insertLogInfo(logDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateLogInfo 
	 *  @param logDto
	 *  @return
	 **********************************************/
	public int updateLogInfo(LogDto logDto) {
		return logDao.updateLogInfo(logDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectPlayListExcel 
	 *  @param logDto
	 *  @return
	 **********************************************/
	public List<LogDto> selectLogListExcel(LogDto logDto) {
		return logDao.searchLogListExcel(logDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectLogListCount 
	 *  @param logDto
	 *  @return
	 **********************************************/
	public int selectLogListCount(LogDto logDto) {

		return logDao.selectLogListCount(logDto);
	}

}
