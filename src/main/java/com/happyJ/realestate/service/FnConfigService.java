package com.happyJ.realestate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.dao.FnConfigDao;
import com.happyJ.realestate.model.schema.FnConfigDto;

@Service
public class FnConfigService {
	
	@Autowired
	private FnConfigDao fnConfigDao;
	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectFnConfigList 
	 *  @param fnConfigDto
	 *  @return
	 **********************************************/
	public List<FnConfigDto> selectFnConfigList(FnConfigDto fnConfigDto) {
		List<FnConfigDto> fnConfigList = fnConfigDao.selectFnConfigList(fnConfigDto);
		
		if (!fnConfigList.isEmpty()){
			int listCnt = fnConfigDao.selectFnConfigListCount(fnConfigDto);
			
			// 페이징 처리
			fnConfigList.get(0).setCurrentPage(fnConfigDto.getCurrentPage());
			fnConfigList.get(0).setRowSize(fnConfigDto.getRowSize());
			fnConfigList.get(0).setListCnt(listCnt);
			fnConfigList.get(0).setTotalPage((int)Math.ceil((double)listCnt / fnConfigDto.getRowSize()));
		}

		return fnConfigList;
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertFnConfigInfo 
	 *  @param fnConfigDto
	 *  @return
	 **********************************************/
	public int insertFnConfigInfo(FnConfigDto fnConfigDto) {
		return fnConfigDao.insertFnConfigInfo(fnConfigDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateFnConfigInfo 
	 *  @param fnConfigDto
	 *  @return
	 **********************************************/
	public int updateFnConfigInfo(FnConfigDto fnConfigDto) {
		return fnConfigDao.updateFnConfigInfo(fnConfigDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method deleteFnConfigInfo 
	 *  @param fnConfigDto
	 *  @return
	 **********************************************/
	public int deleteFnConfigInfo(FnConfigDto fnConfigDto) {
		return fnConfigDao.deleteFnConfigInfo(fnConfigDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectFnConfigListAll 
	 *  @param fnConfigDto
	 *  @return
	 **********************************************/
	public List<FnConfigDto> selectFnConfigListAll(FnConfigDto fnConfigDto) {
		return fnConfigDao.selectFnConfigListAll(fnConfigDto);
	}
	
}
