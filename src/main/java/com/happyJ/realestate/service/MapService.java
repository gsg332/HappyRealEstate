/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 6. 8. yongpal
*****************************************************************************/
package com.happyJ.realestate.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.dao.CoordinateDao;
import com.happyJ.realestate.dao.LatlngDao;
import com.happyJ.realestate.dao.MapStatDao;
import com.happyJ.realestate.model.custom.MapStatDto;
import com.happyJ.realestate.model.schema.CoordinateDto;
import com.happyJ.realestate.model.schema.ItemLatlngDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.service
 *  @fileName : MapService.java
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
 *     2016. 6. 8.        yongpal       create MapService.java
 *  </pre>
 ******************************************************************************/
@Service
public class MapService {
	
	@Autowired
	private MapStatDao mapStatDao;
	
	@Autowired
	private CoordinateDao coordinateDao;
	
	@Autowired
	private LatlngDao latlngDao;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectCoordinateList 
	 *  @param coordinatesDto
	 *  @return
	 **********************************************/
	public List<CoordinateDto> selectCoordinateList(CoordinateDto coordinatesDto) {

		return coordinateDao.selectCoordinateList(coordinatesDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectMarkerList 
	 *  @param latlngDto
	 *  @return
	 **********************************************/
	public List<ItemLatlngDto> selectCctvMarkerList(ItemLatlngDto latlngDto) {

		return latlngDao.selectMarkerList(latlngDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectDongCenterLocationList 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	public List<HashMap<String, String>> selectDongCenterMarkerList(MapStatDto mapStatDto) {
		return latlngDao.selectDongCenterMarkerList(mapStatDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : cctv 마커 타입 리스트
	 *  </pre>
	 * 	@Method selectCctvMarkerTypeList 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	public List<MapStatDto> selectCctvMarkerTypeList(MapStatDto mapStatDto) {

		return mapStatDao.selectCctvMarkerTypeList(mapStatDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectNearMarkerList 
	 *  @param latlngDto
	 *  @return
	 **********************************************/
	public List<ItemLatlngDto> selectNearMarkerList(ItemLatlngDto latlngDto) {

		return latlngDao.selectNearMarkerList(latlngDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectAreaStatList 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	public List<MapStatDto> selectAreaStatList(MapStatDto mapStatDto) {

		return mapStatDao.selectAreaStatList(mapStatDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectTypeAddr2StatList 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	public MapStatDto selectTypeAddr2StatList(MapStatDto mapStatDto) {

		return mapStatDao.selectTypeAddr2StatList(mapStatDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectTypeAddr3StatList 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	public List<MapStatDto> selectTypeAddr3StatList(MapStatDto mapStatDto) {

		return mapStatDao.selectTypeAddr3StatList(mapStatDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectTypeAddr3StatInfo 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	public MapStatDto selectTypeAddr3StatInfo(MapStatDto mapStatDto) {

		return mapStatDao.selectTypeAddr3StatInfo(mapStatDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectCctvInstStatList 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	public List<MapStatDto> selectCctvInstStatList(MapStatDto mapStatDto) {

		return mapStatDao.selectCctvInstStatList(mapStatDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectCctvInstAddr2StatInfo 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	public MapStatDto selectCctvInstAddr2StatInfo(MapStatDto mapStatDto) {
		return mapStatDao.selectCctvInstAddr2StatInfo(mapStatDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectCctvInstAddr3StatInfo 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	public List<MapStatDto> selectCctvInstAddr3StatList(MapStatDto mapStatDto) {

		return mapStatDao.selectCctvInstAddr3StatList(mapStatDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectCctvInstAddr3StatInfo 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	public MapStatDto selectCctvInstAddr3StatInfo(MapStatDto mapStatDto) {
		return mapStatDao.selectCctvInstAddr3StatInfo(mapStatDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectNotUseCctvList 
	 *  @param mapStatDto
	 *  @return
	 **********************************************/
	public List<MapStatDto> selectNotUseCctvList(MapStatDto mapStatDto) {
		return mapStatDao.selectNotUseCctvList(mapStatDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method deleteMarkerInfo 
	 *  @param latlngDto
	 *  @return
	 **********************************************/
	public int deleteMarkerInfo(ItemLatlngDto latlngDto) {

		return latlngDao.deleteMarkerInfo(latlngDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertMarkerInfo 
	 *  @param latlngDto
	 *  @return
	 **********************************************/
	public int insertMarkerInfo(ItemLatlngDto latlngDto) {

		latlngDao.insertMarkerInfo(latlngDto);
		
		return latlngDto.getNo();
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method checkMarkerInfo 
	 *  @param latlngDto
	 *  @return
	 **********************************************/
	public int checkMarkerInfo(ItemLatlngDto latlngDto) {

		return latlngDao.checkMarkerInfo(latlngDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : GIS -> CCTV 마커 동기화시 좌표정보있는 데이터 추출
	 *  </pre>
	 * 	@Method selectCctvSyncList 
	 *  @param latlngDto
	 *  @return
	 **********************************************/
	public ItemLatlngDto selectCctvSyncList(ItemLatlngDto latlngDto) {

		return latlngDao.selectCctvSyncList(latlngDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : GIS -> CCTV 마커 동기화시 좌표값이 없는 목록 주소 업데이트
	 *  </pre>
	 * 	@Method updateCctvSyncAddress 
	 *  @param latlngDto
	 *  @return
	 **********************************************/
	public int updateCctvSyncAddress(ItemLatlngDto latlngDto) {

		return latlngDao.updateCctvSyncAddress(latlngDto);
	}
	
}
