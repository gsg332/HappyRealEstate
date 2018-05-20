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

import com.happyJ.realestate.model.schema.MenuDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : MenuDao.java
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
 *     2016. 4. 2.        yongpal       create MenuDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface MenuDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectMenuList 
	 *  @param menuDto
	 *  @return
	 **********************************************/
	List<MenuDto> selectMenuList(MenuDto menuDto) throws DataAccessException;

}
