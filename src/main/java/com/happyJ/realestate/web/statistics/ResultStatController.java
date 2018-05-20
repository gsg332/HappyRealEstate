/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 5. 20. yongpal
*****************************************************************************/
package com.happyJ.realestate.web.statistics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happyJ.realestate.model.custom.ApplyStatCustomDto;
import com.happyJ.realestate.service.ApplyStatService;
import com.happyJ.realestate.service.CommonCodeService;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.statistics
 *  @fileName : ResultStatController.java
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
 *     2016. 5. 20.        yongpal       create ResultStatController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class ResultStatController {

	private static Logger logger = LoggerFactory.getLogger(ResultStatController.class);
	
	@Autowired
	private ApplyStatService applyStatService;
	
	@Autowired
	private CommonCodeService codeService;	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method viewResultStat 
	 *  @return
	 **********************************************/	
	@RequestMapping(value="/stat/result/list.do")
	public String viewResultStat(ApplyStatCustomDto applyStatCustomDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		// 범죄유형 코드 리스트 조회
		model.addAttribute("crimeTypeList", codeService.selectCodeList("101", null, null));
		
		model.addAttribute("positionList", applyStatService.selectPartList(applyStatCustomDto));
		
		model.addAttribute("itemMinDate", applyStatService.selectItemMinDate());
		return "statistics/result/resultStat";
	}

	@RequestMapping(value="/stat/result/resultList.json")
	public String searchResultStat(ApplyStatCustomDto applyStatCustomDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		applyStatCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyStatCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		// 종합 현황 조회
		model.addAttribute("resultApplyList", applyStatService.resultStatApplyList(applyStatCustomDto));

		// 해당 년도 조회
		String[] yearArr = applyStatService.userStatYears(applyStatCustomDto);
		model.addAttribute("yearList", yearArr);
		// 소속/부서/개인별 현황 조회
		applyStatCustomDto.setYears(yearArr);		
		model.addAttribute("resultPositionList", applyStatService.resultStatPositionList(applyStatCustomDto));
		model.addAttribute("resultDepartList", applyStatService.resultStatDepartList(applyStatCustomDto));
		model.addAttribute("resultUserList", applyStatService.resultStatUserList(applyStatCustomDto));
		// 범죄유형별 현황 조회
		model.addAttribute("resultCrimeList", applyStatService.resultStatCrimeList(applyStatCustomDto));
		// 승인 소요시간 조회
		model.addAttribute("resultApprTimeList", applyStatService.resultStatApprTimeList(applyStatCustomDto));
		// 재생횟수 조회
		model.addAttribute("resultPlayList", applyStatService.resultStatPlayList(applyStatCustomDto));
		// 파일크기 조회
		model.addAttribute("resultVolumeList", applyStatService.resultStatVolumeList(applyStatCustomDto));
		// 범죄 발생시간 조회
		model.addAttribute("resultTimeList", applyStatService.resultStatTimeList(applyStatCustomDto));
		model.addAttribute("stDate", applyStatCustomDto.getSearchStDate());
		model.addAttribute("edDate", applyStatCustomDto.getSearchEdDate());		

		return "statistics/result/resultStatTb";
	}	
}
