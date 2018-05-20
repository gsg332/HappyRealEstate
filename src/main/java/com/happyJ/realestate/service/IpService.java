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

import com.happyJ.realestate.dao.IpDao;
import com.happyJ.realestate.model.schema.IpDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.service
 *  @fileName : IpService.java
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
 *     2016. 4. 20.        yongpal       create IpService.java
 *  </pre>
 ******************************************************************************/
@Service
public class IpService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IpDao ipDao;

	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectIpList 
	 *  @param ipDto
	 *  @return List<IpDto>
	 **********************************************/
	public List<IpDto> selectIpList(IpDto ipDto) {

		List<IpDto> ipList = ipDao.selectIpList(ipDto);
		
		if (!ipList.isEmpty()){
			int listCnt = ipDao.ipListCount(ipDto);
			
			// 페이징 처리
			ipList.get(0).setCurrentPage(ipDto.getCurrentPage());
			ipList.get(0).setRowSize(ipDto.getRowSize());
			ipList.get(0).setListCnt(listCnt);
			ipList.get(0).setTotalPage((int)Math.ceil((double)listCnt / ipDto.getRowSize()));
		}

		return ipList;				
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateIpInfo 
	 *  @param ipDto
	 *  @return
	 **********************************************/
	public int updateIpInfo(IpDto ipDto) {
		// TODO Auto-generated method stub
		return ipDao.updateIpInfo(ipDto);
	}	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method deleteIpInfo 
	 *  @param ipDto
	 *  @return
	 **********************************************/
	public int deleteIpInfo(IpDto ipDto) {
		// TODO Auto-generated method stub
		return ipDao.deleteIpInfo(ipDto);
	}	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertIpInfo 
	 *  @param ipDto
	 *  @return
	 **********************************************/
	public void insertIpInfo(IpDto ipDto) {
		ipDao.insertIpInfo(ipDto);
	}	
}
