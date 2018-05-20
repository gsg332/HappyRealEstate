/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 1. yongpal
*****************************************************************************/
package com.happyJ.realestate.web.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happyJ.realestate.dao.ApplyDao;
import com.happyJ.realestate.model.custom.ApplyCustomDto;
import com.happyJ.realestate.model.custom.ApplyStatCustomDto;
import com.happyJ.realestate.model.custom.MapStatDto;
import com.happyJ.realestate.model.schema.ChangeReqDto;
import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.service.ApplyService;
import com.happyJ.realestate.service.ApplyStatService;
import com.happyJ.realestate.service.ChangeReqService;
import com.happyJ.realestate.service.MapService;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.common
 *  @fileName : MainController.java
 *  @author : yongpal
 *  @since 2016. 4. 1.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 1.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 1.        yongpal       create MainController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class MainController {
	
	private static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Value("#{config['use.location.code']}")
	private String location;

	@Autowired
	private ApplyStatService applyStatService;
	
	@Autowired
	private ApplyService applyService;	
	
	@Autowired
	private MapService mapService;
	
	@Autowired
	private ChangeReqService changeReqService;
	
	@Autowired
	private ApplyDao applyDao;
	
	@RequestMapping(value="/login.do")
	public String login(HttpServletRequest request){
		return "login";
	}

	@RequestMapping(value="/main.do")
	public String main(HttpServletRequest request, HttpServletResponse response){
		
		if (Integer.parseInt( request.getSession().getAttribute("USER_LEVEL").toString()) < 8){
			
			request.getSession().setAttribute("USER_HOME", "/userMain.do");
			
			return "redirect:/userMain.do";
		
		} else {
			
			request.getSession().setAttribute("USER_HOME", "/adminMain.do");
			
			return "redirect:/adminMain.do";
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/userMain.do")
	public String viewUserMain(HttpServletRequest request, HttpServletResponse response, Model model, MapStatDto mapStatDto){

		ApplyStatCustomDto applyStatCustomDto = new ApplyStatCustomDto();
		applyStatCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyStatCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		// 신청 현황 조회
		applyStatCustomDto.setUserGb("user");
		model.addAttribute("applyCounts", applyStatService.userMainTotalCounts(applyStatCustomDto));
		// 신청현황 월별 그래프
		model.addAttribute("applyDateCounts", applyStatService.userMainDateGraphCounts(applyStatCustomDto));

		// 만료 예정 영상 조회
		model.addAttribute("limitDateCounts", applyStatService.userMainLimitDateCounts(applyStatCustomDto));
		
		/*
		ChangeReqDto changeReqDto = new ChangeReqDto();
		changeReqDto.setSearchTypeDepth1("영상정보신청");
		changeReqDto.setSearchStatus("1000"); //대기
		
		// 변경요청(대기상태) 조회
		model.addAttribute("waitChangeReqList", changeReqService.selectChangeReqList(request, changeReqDto));
		*/
		
		ApplyCustomDto applyDto = new ApplyCustomDto();
		applyDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		for(ConfigDto configDto : Config.configList){
			if("veiLimitDatetime".equals(configDto.getItemName())){
				//영상재생만료일자(기본:14일)
				applyDto.setVeiLimitDays(configDto.getItemValue());
			}
		}
		
		// 활용결과 미등록 조회
		//ApplyStatCustomDto resultCounts = applyStatService.userMainResultCounts(applyStatCustomDto);
		ApplyStatCustomDto resultCounts = new ApplyStatCustomDto();
		String notResultCnt = String.valueOf(applyDao.selectResultListCounts(applyDto));
		resultCounts.setNotResultCnt(notResultCnt.length()==1 ? "0" + notResultCnt : notResultCnt);
		model.addAttribute("resultCounts", resultCounts);
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		// CCTV 운영현황 조회 selectCctvMarkerTypeList
		model.addAttribute("cctvMarkerTypeList", mapService.selectCctvMarkerTypeList(mapStatDto));
		
		// 활용결과 미등록 리스트
		model.addAttribute("resultList", applyService.selectResultList(applyDto));
		// 만료예정 영상 리스트
		model.addAttribute("limitDateList", applyStatService.userMainLimitDateList(applyStatCustomDto));
		
		//request.getSession().setAttribute("NOT_RESULT_CNT", resultCounts.getNotResultCnt());
		
		return "main/userMain";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/adminMain.do")
	public String viewAdminMain(HttpServletRequest request, HttpServletResponse response, Model model, MapStatDto mapStatDto){
		
		ApplyStatCustomDto applyStatCustomDto = new ApplyStatCustomDto();
		applyStatCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyStatCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		//업무처리 현황
		model.addAttribute("BusinessCounts",applyStatService.applyBusinessList(applyStatCustomDto));
		// 신청 현황 조회
		model.addAttribute("applyCounts", applyStatService.userMainTotalCounts(applyStatCustomDto));
		// 미활용 현황 조회
		model.addAttribute("reasonList", applyStatService.userStatReasonList(applyStatCustomDto));
		// 전체 현황 조회
		model.addAttribute("allList", applyStatService.userStatAllList(applyStatCustomDto));
		// 미활용 현황 
		model.addAttribute("retUseResList", applyStatService.itemUseResultDiff(applyStatCustomDto));
		
		for(ConfigDto configDto : Config.configList){
			if("LOCATION_GU".equals(configDto.getItemName())){
				mapStatDto.setLocationGu(configDto.getItemValue());
			}
		}
		// CCTV 운영현황 조회 selectCctvMarkerTypeList
		model.addAttribute("cctvMarkerTypeList", mapService.selectCctvMarkerTypeList(mapStatDto));
		// 유형별 범죄현황 조회
		model.addAttribute("typeStatInfo", mapService.selectTypeAddr2StatList(mapStatDto));
		
		// 승인 소요시간 조회
		model.addAttribute("resultApprTimeList", applyStatService.resultStatApprTimeList(applyStatCustomDto));
		// 재생횟수 조회
		model.addAttribute("resultPlayList", applyStatService.resultStatPlayList(applyStatCustomDto));
		// 파일크기 조회
		model.addAttribute("resultVolumeList", applyStatService.resultStatVolumeList(applyStatCustomDto));		
		// 범죄 발생시간 조회
		model.addAttribute("resultTimeList", applyStatService.resultStatTimeList(applyStatCustomDto));
		
		return "main/adminMain";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 노티 팝업 1
	 *  </pre>
	 * 	@Method notify 
	 *  @param mapStatDto
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/notifyPopup/notify.do")
	public String notify(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "notifyPopup/notify";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 노티팝업2
	 *  </pre>
	 * 	@Method notify2 
	 *  @param mapStatDto
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/notifyPopup/notify2.do")
	public String notify2(HttpServletRequest request, HttpServletResponse response, Model model) {

		return "notifyPopup/notify2";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : backviewer popup
	 *  </pre>
	 * 	@Method bkViewnotify 
	 *  @param mapStatDto
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/notifyPopup/bkViewnotify.do")
	public String bkViewnotify(HttpServletRequest request, HttpServletResponse response, Model model) {

		return "notifyPopup/bkViewnotify";
	}
}
