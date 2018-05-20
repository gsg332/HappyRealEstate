/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 5. 20. yongpal
*****************************************************************************/
package com.happyJ.realestate.web.map;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.happyJ.realestate.dao.CoordinateDao;
import com.happyJ.realestate.model.custom.ApplyCustomDto;
import com.happyJ.realestate.model.custom.MapStatDto;
import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.model.schema.CoordinateDto;
import com.happyJ.realestate.model.schema.ItemLatlngDto;
import com.happyJ.realestate.service.ApplyService;
import com.happyJ.realestate.service.CommonCodeService;
import com.happyJ.realestate.service.MapService;
import com.happyJ.realestate.web.common.Config;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.map
 *  @fileName : MapController.java
 *  @author : yongpal
 *  @since 2016. 5. 20.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 5. 20.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 5. 20.        yongpal       create MapController.java
 *  </pre>
 ******************************************************************************/
@Controller
@SuppressWarnings("unchecked")
public class MapController {

	private static Logger logger = LoggerFactory.getLogger(MapController.class);
	
	@Autowired
	private MapService mapService;
	
	@Autowired
	private CommonCodeService codeService;
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private CoordinateDao coordinateDao;
	
	@RequestMapping(value="/map/map.do")
	public String viewMapMain(Model model){
		
		// 범죄유형 코드 리스트 조회
		model.addAttribute("crimeTypeList", codeService.selectCodeList("101", null, null));
		// 지도 마커 타입 코드
		model.addAttribute("cctvTypeList", codeService.selectCodeList("115", null, null));
		
		return "map/map";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method popApplyCctv 
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/cctvPop.do")
	public String popApplyCctv(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		// 지도 마커 타입 코드
		model.addAttribute("cctvTypeList", codeService.selectCodeList("115", null, null));
		
		return "map/cctvPop";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method popCrimeArea 
	 *  @param applyCustomDto
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/crimeAreaPop.do")
	public String popCrimeArea(MapStatDto mapStatDto, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		// 지도 마커 타입 코드
		model.addAttribute("cctvTypeList", codeService.selectCodeList("115", null, null));
		model.addAttribute("lat", mapStatDto.getCenterLat());
		model.addAttribute("lng", mapStatDto.getCenterLng());
		model.addAttribute("videoId", mapStatDto.getVideoId());
		
		return "map/crimeAreaPop";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 지역별위험지수 리스트
	 *  </pre>
	 * 	@Method searchAreaStatList 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/area/stat.json")
	public String searchAreaStatList(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		model.addAttribute("areaStatList", mapService.selectAreaStatList(mapStatDto));
		
		return "map/areaStatListTb";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 지역별위험지수 엑셀
	 *  </pre>
	 * 	@Method searchAreaStatExcel 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/area/excel.do")
	public String searchAreaStatExcel(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		
		model.addAttribute("title", "지역별 위험지수 리스트");
		model.addAttribute("excelList", mapService.selectAreaStatList(mapStatDto));
		
		return "areaStatListExcelView";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 유형별 범죄현황 구단위
	 *  </pre>
	 * 	@Method searchTypeAddr2StatInfo 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/type/addr2stat.json")
	public String searchTypeAddr2StatInfo(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		model.addAttribute("typeStatInfo", mapService.selectTypeAddr2StatList(mapStatDto));
		
		return "map/typeAddr2StatListTb";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 유형별 범죄현황 구단위 엑셀
	 *  </pre>
	 * 	@Method searchTypeAddr2StatInfoExcel 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/type/addr2statExcel.do")
	public String searchTypeAddr2StatInfoExcel(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		model.addAttribute("title", "유형별 범죄현황 구단위 리스트");
		model.addAttribute("excelList", mapService.selectTypeAddr2StatList(mapStatDto));
		
		return "typeAddr2StatInfoExcelView";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 유형별 범죄현황 동단위 정보
	 *  </pre>
	 * 	@Method searchTypeAddr3StatInfo 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/type/addr3stat.json")
	public String searchTypeAddr3StatInfo(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		
		ApplyCustomDto applyDto = new ApplyCustomDto();
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
				
				applyDto.setAddr2(configDto.getItemValue());
				applyDto.setAddr3(mapStatDto.getLocationDong());
				applyDto.setSearchStDate(mapStatDto.getSearchStDate());
				applyDto.setSearchEdDate(mapStatDto.getSearchEdDate());
				applyDto.setTimeSlot00(mapStatDto.getTimeSlot00());
				applyDto.setTimeSlot07(mapStatDto.getTimeSlot07());
				applyDto.setTimeSlot18(mapStatDto.getTimeSlot18());
				applyDto.setTimeSlot22(mapStatDto.getTimeSlot22());
				applyDto.setTimeSlot03(mapStatDto.getTimeSlot03());
			}
		}
		model.addAttribute("typeStatInfo", mapService.selectTypeAddr3StatInfo(mapStatDto));
		model.addAttribute("crimeMarkerList", applyService.selectCrimeMarkerList(applyDto));
		
		return "map/typeAddr3StatInfoTb";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 유형별 범죄현황 동단위 세부 정보 엑셀
	 *  </pre>
	 * 	@Method searchTypeAddr3StatInfoExcel 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/type/addr3statInfoExcel.do")
	public String searchTypeAddr3StatInfoExcel(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		model.addAttribute("title", "유형별 범죄현황 동단위 정보");
		model.addAttribute("excelList", mapService.selectTypeAddr3StatInfo(mapStatDto));
		
		return "typeAddr3StatInfoExcelView";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 유형별 범죄현황 동단위 리스트
	 *  </pre>
	 * 	@Method searchTypeAddr3StatList 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/type/addr3stat/list.json")
	public String searchTypeAddr3StatList(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		model.addAttribute("typeStatList", mapService.selectTypeAddr3StatList(mapStatDto));
		
		return "map/typeAddr3StatListTb";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 유형별 범죄현황 동단위 리스트 엑셀
	 *  </pre>
	 * 	@Method searchTypeAddr3StatListExcel 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/type/addr3stat/Excel.do")
	public String searchTypeAddr3StatListExcel(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		
		model.addAttribute("title", "유형별 범죄현황 동단위 리스트");
		model.addAttribute("excelList", mapService.selectTypeAddr3StatList(mapStatDto));
		
		return "typeAddr3StatListExcelView";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 목적별 CCTV 구단위 설치 현황
	 *  </pre>
	 * 	@Method searchCctvInstAddr2StatInfo 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/cctv/instStat/addr2stat.json")
	public String searchCctvInstAddr2StatInfo(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		model.addAttribute("cctvInstStatInfo", mapService.selectCctvInstAddr2StatInfo(mapStatDto));
		
		return "map/cctvInstAddr2StatInfoTb";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 목적별 CCTV 설치현황 구단위 리스트 엑셀
	 *  </pre>
	 * 	@Method searchCctvInstAddr2StatInfoExcel 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/cctv/instStat/addr2statExcel.do")
	public String searchCctvInstAddr2StatInfoExcel(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		model.addAttribute("title", "목적별 CCTV 설치현황 구단위 리스트");
		model.addAttribute("excelList", mapService.selectCctvInstAddr2StatInfo(mapStatDto));
		
		return "cctvInstAddr2StatInfoExcelView";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 목적별 CCTV 현황 동단위 리스트
	 *  </pre>
	 * 	@Method searchCctvInstAddr3StatList 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/cctv/instStat/addr3stat/list.json")
	public String searchCctvInstAddr3StatList(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		model.addAttribute("cctvInstStatList", mapService.selectCctvInstAddr3StatList(mapStatDto));
		
		return "map/cctvInstAddr3StatListTb";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 목적별 CCTV 현황 동단위 리스트 엑셀
	 *  </pre>
	 * 	@Method searchCctvInstAddr3StatListExcel 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/cctv/instStat/addr3stat/listExcel.do")
	public String searchCctvInstAddr3StatListExcel(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		model.addAttribute("title", "목적별 CCTV 설치현황 동단위 리스트");
		model.addAttribute("excelList", mapService.selectCctvInstAddr3StatList(mapStatDto));
		
		return "cctvInstAddr3StatListExcelView";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 목적별 CCTV 현황 동단위 세부 정보
	 *  </pre>
	 * 	@Method searchCctvInstAddr3StatInfo 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/cctv/instStat/addr3stat.json")
	public String searchCctvInstAddr3StatInfo(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		model.addAttribute("cctvInstStatInfo", mapService.selectCctvInstAddr3StatInfo(mapStatDto));
		
		return "map/cctvInstAddr3StatInfoTb";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 목적별 CCTV 설치현황 동단위 세부 정보 엑셀
	 *  </pre>
	 * 	@Method searchCctvInstAddr3StatInfoExcel 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/cctv/instStat/addr3statExcel.do")
	public String searchCctvInstAddr3StatInfoExcel(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		model.addAttribute("title", "목적별 CCTV 설치현황 동단위 세부 정보");
		model.addAttribute("excelList", mapService.selectCctvInstAddr3StatInfo(mapStatDto));
		
		return "cctvInstAddr3StatInfoExcelView";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method searchNotUseCctvList 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/cctv/notUse/list.json")
	public String searchNotUseCctvList(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		model.addAttribute("notUseCctvList", mapService.selectNotUseCctvList(mapStatDto));
		
		return "map/notUseCctvListTb";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method getCoordinateList 
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/coordinate/list.json")
	public String getCoordinateList(HttpServletRequest request, Model model){
		
		CoordinateDto coordinatesDto = new CoordinateDto();
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				coordinatesDto.setLocationGu(configDto.getItemValue());
			}
		}
		
		model.addAttribute("coordsList", mapService.selectCoordinateList(coordinatesDto));
		
		return "jsonView";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method getCctvMarkerList 
	 *  @param latlngDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/marker/cctv/list.json")
	public String getCctvMarkerList(ItemLatlngDto latlngDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				latlngDto.setAddr2(configDto.getItemValue());
			}
		}
		
		if (latlngDto.getVideoId() != null){
			latlngDto.setArrVideoId(latlngDto.getVideoId().split(","));
		}

		model.addAttribute("cctvMarkerList", mapService.selectCctvMarkerList(latlngDto));
		return "jsonView";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method getDongCenterMarkerList 
	 *  @param mapStatDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/marker/dongCenter/list.json")
	public String getDongCenterMarkerList(MapStatDto mapStatDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		
		model.addAttribute("dongCenterMarkerList", mapService.selectDongCenterMarkerList(mapStatDto));
		return "jsonView";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method getCrimeMarkerList 
	 *  @param applyDto
	 *  @param request
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/marker/crime/list.json")
	public String getCrimeMarkerList(ApplyCustomDto applyDto, HttpServletRequest request, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				applyDto.setAddr2(configDto.getItemValue());
			}
		}
		
		model.addAttribute("crimeMarkerList", applyService.selectCrimeMarkerList(applyDto));
		
		return "jsonView";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method getNearMarkerList 
	 *  @param latlngDto
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/marker/radius.json")
	public String getNearMarkerList(ItemLatlngDto latlngDto, Model model){
		
		model.addAttribute("nearMarkerList", mapService.selectNearMarkerList(latlngDto));
		
		return "jsonView";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method getCctvImage
	 *  @param latlngDto
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/map/marker/getCctvImage.do")
	public ModelAndView getCctvImage(ItemLatlngDto latlngDto, Model model){
		
		ModelAndView mv = new ModelAndView("imageView", "managecode", latlngDto.getManagecode());
		 
        return mv;
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 구에 대한 지도 경계선 정보를 Insert 한다.
	 *  TODO : coordinateValue를 json 스트링 형태로 받을 수 있도록 추가.
	 *  </pre>
	 * 	@Method coordinateInsert 
	 *  @param coordinateValues 네이버지도에서 확인된 위도,경도 정보. <br> ex> "127.026506,37.5358255|127.0401928,37.5357743"
	 *  @param locationsId ves_locations 테이블의 구에 대한 locations_id
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @return
	 *  @throws ParseException
	 **********************************************/
	@RequestMapping(value="/map/coordinate/insert.json")
	public String coordinateInsert(String coordinateValues, String locationsId, HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {

		int result = 0;
		//String coordinateValues = "127.026506,37.5358255|127.0401928,37.5357743|127.0460103,37.5342606|127.055127,37.5286675|127.056219,37.5283238|127.065388,37.5250928|127.0675011,37.5245837|127.067728,37.5193212|127.0698167,37.5027391|127.0769875,37.5020697|127.0842143,37.4996527|127.0947942,37.4966809|127.097801,37.495479|127.0974007,37.4951893|127.0997626,37.4945088|127.1035151,37.4927031|127.1070069,37.4902915|127.1129726,37.4817155|127.1242069,37.4665208|127.1251024,37.4674464|127.1244886,37.4666421|127.1218598,37.465137|127.1210936,37.4641239|127.1174753,37.4621988|127.1169897,37.4614827|127.1168965,37.4586387|127.1154965,37.4586961|127.1151485,37.45906|127.1139553,37.4595167|127.1132334,37.4603534|127.1127646,37.4605295|127.1133344,37.4610858|127.1118208,37.4616427|127.1066726,37.4624091|127.1043417,37.4621729|127.1047586,37.4611681|127.1040302,37.460609|127.1039421,37.4600492|127.1034433,37.45975|127.1026346,37.4598656|127.1013816,37.458971|127.1012782,37.4582398|127.1007663,37.457758|127.0997392,37.4573528|127.0990961,37.4562169|127.0957698,37.4565178|127.0952143,37.4563195|127.093774,37.4560759|127.0935408,37.4558866|127.0931432,37.4549043|127.0935404,37.4558884|127.0937735,37.4560777|127.0952138,37.4563213|127.0954396,37.4575585|127.0948961,37.4584803|127.09506,37.4597694|127.0956065,37.4602499|127.0956765,37.4610041|127.0946385,37.4609501|127.0931943,37.4612722|127.0920244,37.462274|127.0920278,37.464789|127.0916174,37.4656264|127.0904368,37.4670249|127.0894604,37.4675739|127.0890383,37.4682404|127.0884459,37.4680958|127.0881648,37.4683156|127.087532,37.4697622|127.0871854,37.4698841|127.0867119,37.4706607|127.085896,37.4711|127.0850276,37.4712|127.0841639,37.4729933|127.0845059,37.4751059|127.084882,37.4755122|127.0832324,37.4755266|127.0789907,37.474767|127.0785154,37.474961|127.0779346,37.4747361|127.076911,37.4751577|127.0753719,37.4734893|127.073845,37.4729353|127.0726839,37.4719578|127.0719183,37.4718834|127.069937,37.4709284|127.0685742,37.4710077|127.0678684,37.4704278|127.0664535,37.4701355|127.0650989,37.4693355|127.063813,37.4690014|127.0625996,37.4693407|127.0609188,37.4689179|127.0590867,37.4691947|127.0587085,37.4689054|127.0571972,37.4690083|127.056532,37.4684785|127.0560844,37.4684381|127.0550444,37.4688531|127.0542101,37.4681138|127.0532431,37.468159|127.0507555,37.4672959|127.0510347,37.469356|127.0504358,37.4697501|127.0486694,37.4700597|127.0508264,37.4716353|127.0450398,37.4773167|127.0441435,37.47929|127.0438893,37.4805605|127.0416089,37.4852392|127.0417507,37.485436|127.0340873,37.4843689|127.0205154,37.512799|127.0178224,37.5218119|127.0152244,37.5248485|127.0131716,37.5225918|127.0085782,37.525595|127.014034,37.5312063|127.0174396,37.5339928|127.0211731,37.53582|127.026506,37.5358255";
		
		StringTokenizer stn = new StringTokenizer(coordinateValues,"|");
		
		while(stn.hasMoreTokens()){
			String coordinate = stn.nextToken();
			
			String[] lat_lng = coordinate.split(",");
			
			Map<String, String> latlng = new HashMap<String, String>();
			
			latlng.put("lng", lat_lng[0]);
			latlng.put("lat", lat_lng[1]);
			latlng.put("locationsId", locationsId);
			
			result = coordinateDao.insertCoordinate(latlng);
		}
		
		model.addAttribute("result", result > 0 ? "SUCCESS" : "FAIL");

		return "jsonView";
	}
}
