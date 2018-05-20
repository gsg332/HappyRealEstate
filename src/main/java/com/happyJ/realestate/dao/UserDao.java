/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 2. yongpal
*****************************************************************************/
package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.protocol.body.LoginBody;
import com.happyJ.realestate.model.protocol.body.RealPriceBody;
import com.happyJ.realestate.model.schema.RealPriceDto;
import com.happyJ.realestate.model.schema.UserDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : UserDao.java
 *  @author : yongpal
 *  @since 2016. 4. 2.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 2.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 2.        yongpal       create UserDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface UserDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectUserInfo 
	 *  @param authUserDto
	 *  @return
	 **********************************************/
	UserDto selectUserInfo(UserDto authUserDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectAllUserInfo 
	 *  @param authUserDto
	 *  @return
	 **********************************************/
	UserDto selectAllUserInfo(UserDto authUserDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 유저 ID/PW 찾기
	 *  </pre>
	 * 	@Method selectFindInfo 
	 *  @param authUserDto
	 *  @return
	 *  @throws DataAccessException
	 **********************************************/
	UserDto selectFindInfo(UserDto authUserDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateUserInfo 
	 *  @param userDto
	 * @return 
	 **********************************************/		
	int updateUserInfo(UserDto userDto) throws DataAccessException;
		
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertUserInfo 
	 *  @param userDto
	 **********************************************/
	void insertUserInfo(UserDto userDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method idDuplChkUser 
	 *  @param userDto
	 *  @return
	 **********************************************/	
	int idDuplChkUser(UserDto userDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectUserList 
	 *  @param userDto
	 *  @return
	 **********************************************/
	List<UserDto> selectUserList(UserDto userDto) throws DataAccessException;	

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectUsersCounts 
	 *  @param userDto
	 *  @return
	 **********************************************/
	int selectUsersCounts(UserDto userDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectAdminList 
	 *  @param userDto
	 *  @return
	 **********************************************/
	List<UserDto> selectAdminList(UserDto userDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectUserInfoForAPI 
	 *  @param userDto
	 *  @return
	 **********************************************/
	LoginBody selectUserInfoForAPI(UserDto userDto) throws DataAccessException;

	
	
	int insertNaverRealPriceForAPI(RealPriceDto realPriceDto) throws DataAccessException;
	
	
}
