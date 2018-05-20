/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 6. 2. yongpal
*****************************************************************************/
package com.happyJ.realestate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.dao.ConfigDao;
import com.happyJ.realestate.model.schema.ConfigDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.service
 *  @fileName : ConfigService.java
 *  @author : yongpal
 *  @since 2016. 6. 2.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 6. 2.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 6. 2.        yongpal       create ConfigService.java
 *  </pre>
 ******************************************************************************/
@Service
public class ConfigService {
	
	@Autowired
	private ConfigDao configDao;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectConfigList 
	 *  @param configDto
	 *  @return
	 **********************************************/
	public List<ConfigDto> selectConfigList(ConfigDto configDto) {
		List<ConfigDto> configList = configDao.selectConfigList(configDto);
		
		if (!configList.isEmpty()){
			int listCnt = configDao.selectConfigListCount(configDto);
			
			// 페이징 처리
			configList.get(0).setCurrentPage(configDto.getCurrentPage());
			configList.get(0).setRowSize(configDto.getRowSize());
			configList.get(0).setListCnt(listCnt);
			configList.get(0).setTotalPage((int)Math.ceil((double)listCnt / configDto.getRowSize()));
		}

		return configList;
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateConfigInfo 
	 *  @param configDto
	 *  @return
	 **********************************************/
	public int updateConfigInfo(ConfigDto configDto) {

		return configDao.updateConfigInfo(configDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectConfigListAll 
	 *  @param configDto
	 *  @return
	 **********************************************/
	public List<ConfigDto> selectConfigListAll(ConfigDto configDto) {

		return configDao.selectConfigListAll(configDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectConfigListForAPI 
	 *  @param configDto
	 *  @return
	 **********************************************/
	public List<ConfigDto> selectConfigListForAPI(ConfigDto configDto) {
		return configDao.selectConfigListForAPI(configDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectConfigInfo 
	 *  @param configDto
	 *  @return
	 **********************************************/
	public ConfigDto selectConfigInfo(ConfigDto configDto) {
		
		return configDao.selectConfigInfo(configDto);
	}
	
}
