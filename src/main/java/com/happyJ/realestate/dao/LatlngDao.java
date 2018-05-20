/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 6. 8. yongpal
*****************************************************************************/
package com.happyJ.realestate.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.custom.MapStatDto;
import com.happyJ.realestate.model.schema.ItemLatlngDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.dao
 *  @fileName : LatlngDao.java
 *  @author : yongpal
 *  @since 2016. 6. 8.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 6. 8.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 6. 8.        yongpal       create LatlngDao.java
 *  </pre>
 ******************************************************************************/
@Repository
public interface LatlngDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectMarkerList 
	 *  @param latlngDto
	 *  @return
	 **********************************************/
	List<ItemLatlngDto> selectMarkerList(ItemLatlngDto latlngDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectDongCenterLocationList 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	List<HashMap<String, String>> selectDongCenterMarkerList(MapStatDto mapStatDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectNearMarkerList 
	 *  @param latlngDto
	 *  @return
	 **********************************************/
	List<ItemLatlngDto> selectNearMarkerList(ItemLatlngDto latlngDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method deleteMarkerAll 
	 *  @return
	 **********************************************/
	int deleteMarkerInfo(ItemLatlngDto latlngDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertMarkerInfo 
	 *  @param latlngDto
	 *  @return
	 **********************************************/
	Integer insertMarkerInfo(ItemLatlngDto latlngDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method checkMarkerInfo 
	 *  @param latlngDto
	 *  @return
	 **********************************************/
	int checkMarkerInfo(ItemLatlngDto latlngDto) throws DataAccessException;

	/**********************************************
	 *  <pre>
	 *  개요 : GIS -> CCTV 마커 동기화시 좌표정보있는 데이터 추출
	 *  </pre>
	 * 	@Method selectCctvSyncList 
	 *  @param latlngDto
	 *  @return
	 *  @throws DataAccessException
	 **********************************************/
	ItemLatlngDto selectCctvSyncList(ItemLatlngDto latlngDto) throws DataAccessException;
	
	/**********************************************
	 *  <pre>
	 *  개요 : GIS -> CCTV 마커 동기화시 좌표값이 없는 목록 주소 업데이트
	 *  </pre>
	 * 	@Method updateCctvSyncAddress 
	 *  @param latlngDto
	 *  @return
	 *  @throws DataAccessException
	 **********************************************/
	int updateCctvSyncAddress(ItemLatlngDto latlngDto) throws DataAccessException;

}
