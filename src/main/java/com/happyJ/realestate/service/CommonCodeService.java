/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 20. yongpal
*****************************************************************************/
package com.happyJ.realestate.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.dao.CodeDao;
import com.happyJ.realestate.model.schema.CodeDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.service
 *  @fileName : CommonCodeService.java
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
 *     2016. 4. 20.        yongpal       create CommonCodeService.java
 *  </pre>
 ******************************************************************************/
@Service
public class CommonCodeService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CodeDao codeDao;

	
	/**********************************************
	 *  <pre>
	 *  개요 : 공통 코드 리스트 조회.
	 *  </pre>
	 * 	@Method selectCodeList 
	 *  @param cdGroup : 코드 그룹
	 *  @param cdKey : 코드 구분 번호
	 *  @param fixedYn : 고정 코드 여부
	 *  @return
	 **********************************************/
	public List<CodeDto> selectCodeList(String cdGroup, String cdKey, String fixedYn) {
		// TODO Auto-generated method stub
		CodeDto codeDto = new CodeDto();
		codeDto.setCodeGroup(cdGroup);
		codeDto.setCodeKey(cdKey);
		codeDto.setCodeFixed(fixedYn);
		return codeDao.selectCodeList(codeDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectCodeGroupList 
	 *  @param CodeDto
	 *  @return List<CodeDto>
	 **********************************************/
	public List<CodeDto> selectCodeGroupList(CodeDto codeDto) {

		return codeDao.selectCodeGroupList(codeDto);
	}	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method searchCodeGroupList 
	 *  @param CodeDto
	 *  @return List<CodeDto>
	 **********************************************/
	public List<CodeDto> searchCodeGroupList(CodeDto codeDto) {

		List<CodeDto> searchGroupList = codeDao.searchCodeGroupList(codeDto);
		
		if (!searchGroupList.isEmpty()){
			int listCnt = codeDao.searchCodeGroupListCount(codeDto);
			
			// 페이징 처리
			searchGroupList.get(0).setCurrentPage(codeDto.getCurrentPage());
			searchGroupList.get(0).setRowSize(codeDto.getRowSize());
			searchGroupList.get(0).setListCnt(listCnt);
			searchGroupList.get(0).setTotalPage((int)Math.ceil((double)listCnt / codeDto.getRowSize()));
		}

		return searchGroupList;				
//		return codeDao.searchCodeGroupList(codeDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateCodeInfo 
	 *  @param codeDto
	 *  @return
	 **********************************************/
	public int updateCodeInfo(CodeDto codeDto) {
		// TODO Auto-generated method stub
		return codeDao.updateCodeInfo(codeDto);
	}	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method deleteCodeInfo 
	 *  @param codeDto
	 *  @return
	 **********************************************/
	public int deleteCodeInfo(CodeDto codeDto) {
		// TODO Auto-generated method stub
		return codeDao.deleteCodeInfo(codeDto);
	}	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertCodeInfo 
	 *  @param codeDto
	 *  @return
	 **********************************************/
	public void insertCodeInfo(CodeDto codeDto) {
		codeDao.insertCodeInfo(codeDto);
	}	
}
