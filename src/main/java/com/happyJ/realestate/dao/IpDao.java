/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 20. yongpal
*****************************************************************************/
package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.schema.IpDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : IpDao.java
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
 *     2016. 4. 20.        yongpal       create IpDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface IpDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectIpList 
	 *  @param ipDto
	 *  @return
	 **********************************************/
	List<IpDto> selectIpList(IpDto ipDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method ipListCount 
	 *  @param ipDto
	 *  @return
	 **********************************************/
	int ipListCount(IpDto ipDto) throws DataAccessException;	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateIpInfo 
	 *  @param ipDto
	 *  @return
	 **********************************************/
	int updateIpInfo(IpDto ipDto) throws DataAccessException;	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method deleteIpInfo 
	 *  @param ipDto
	 *  @return
	 **********************************************/
	int deleteIpInfo(IpDto ipDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertIpInfo 
	 *  @param codeDto
	 **********************************************/
	void insertIpInfo(IpDto ipDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectIpListAll 
	 *  @return
	 **********************************************/
	List<IpDto> selectIpListAll() throws DataAccessException;	
}
