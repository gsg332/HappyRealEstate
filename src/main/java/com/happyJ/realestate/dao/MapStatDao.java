/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 6. 20. yongpal
*****************************************************************************/
package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.custom.MapStatDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : MapStatDao.java
 *  @author : yongpal
 *  @since 2016. 6. 20.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 6. 20.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 6. 20.        yongpal       create MapStatDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface MapStatDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectAreaStatList 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	List<MapStatDto> selectAreaStatList(MapStatDto mapStatDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectTypeAddr2StatList 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	MapStatDto selectTypeAddr2StatList(MapStatDto mapStatDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectTypeAddr3StatList 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	List<MapStatDto> selectTypeAddr3StatList(MapStatDto mapStatDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectTypeAddr3StatInfo 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	MapStatDto selectTypeAddr3StatInfo(MapStatDto mapStatDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectCctvInstStatList 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	List<MapStatDto> selectCctvInstStatList(MapStatDto mapStatDto) throws DataAccessException;

	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectCctvInstAddr2StatInfo 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	MapStatDto selectCctvInstAddr2StatInfo(MapStatDto mapStatDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectCctvInstAddr3StatInfo 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	List<MapStatDto> selectCctvInstAddr3StatList(MapStatDto mapStatDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectCctvInstAddr3StatInfo 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	MapStatDto selectCctvInstAddr3StatInfo(MapStatDto mapStatDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectNotUseCctvList 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	List<MapStatDto> selectNotUseCctvList(MapStatDto mapStatDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectCctvMarkerTypeList 
	 *  @param latlngDto
	 *  @return
	 **********************************************/
	List<MapStatDto> selectCctvMarkerTypeList(MapStatDto mapStatDto) throws DataAccessException;
}
