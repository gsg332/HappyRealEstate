/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 28. yongpal
*****************************************************************************/
package com.happyJ.realestate.web.statistics;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happyJ.realestate.dao.ApplyStatDao;
import com.happyJ.realestate.model.custom.ApplyStatCustomDto;
import com.happyJ.realestate.service.ApplyStatService;
import com.happyJ.realestate.service.CommonCodeService;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.statistics
 *  @fileName : ApplyStatisticsController.java
 *  @author : yongpal
 *  @since 2016. 4. 28.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 28.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 28.        yongpal       create ApplyStatisticsController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class ApplyStatController {

	private static Logger logger = LoggerFactory.getLogger(ApplyStatController.class);
	
	@Autowired
	private ApplyStatService applyStatService;
	
	@Autowired
	private CommonCodeService codeService;
	
	
	@Autowired
	private ApplyStatDao applyStatDao;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method viewUserApplyStat 
	 *  @return
	 **********************************************/
	@RequestMapping(value="/stat/apply/userList.do")
	public String viewUserApplyStat(ApplyStatCustomDto applyStatCustomDto, HttpServletRequest request, HttpServletResponse response, Model model){
	
		applyStatCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyStatCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		// 범죄유형 코드 리스트 조회
		model.addAttribute("crimeTypeList", codeService.selectCodeList("101", null, null));
		
		// 기여율(해결사건) 조회
		model.addAttribute("solveList", applyStatService.userStatSolveList(applyStatCustomDto));
		
		// 전체 현황 조회
		model.addAttribute("allList", applyStatService.userStatAllList(applyStatCustomDto));
		
		// 미활용 사유 년도 조회
		String[] yearArr = applyStatService.userStatYears(applyStatCustomDto);
		model.addAttribute("yearList", yearArr);
		
		// 미활용 사유 조회
		applyStatCustomDto.setYears(yearArr);
		model.addAttribute("reasonList", applyStatService.userStatReasonList(applyStatCustomDto));	
		
		return "statistics/apply/userApplyStat";
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method searchUserApplyStat 
	 *  @return
	 **********************************************/	
	@RequestMapping(value="/stat/apply/userList.json")
	public String searchUserApplyStat(ApplyStatCustomDto applyStatCustomDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		applyStatCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyStatCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		// 범죄유형 코드 리스트 조회
		model.addAttribute("crimeTypeList", codeService.selectCodeList("101", null, null));
		
		// 기여율(해결사건) 조회
		model.addAttribute("solveList", applyStatService.userStatSolveList(applyStatCustomDto));
		
		// 전체 현황 조회
		model.addAttribute("allList", applyStatService.userStatAllList(applyStatCustomDto));
		
		// 미활용 사유 년도 조회
		String[] yearArr = applyStatService.userStatYears(applyStatCustomDto);
		model.addAttribute("yearList", yearArr);
		
		// 미활용 사유 조회
		applyStatCustomDto.setYears(yearArr);
		model.addAttribute("reasonList", applyStatService.userStatReasonList(applyStatCustomDto));
		
		model.addAttribute("stDate", applyStatCustomDto.getSearchStDate());
		model.addAttribute("edDate", applyStatCustomDto.getSearchEdDate());
		
		return "statistics/apply/userApplyStatTb";
	}
	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method searchDetailEtcReasonList 
	 *  @return
	 **********************************************/	
	@RequestMapping(value="/stat/apply/detailEtcReasonList.do")
	public String searchDetailEtcReasonList(ApplyStatCustomDto applyStatCustomDto, HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("applyYear", applyStatCustomDto.getApplyYear());
		return "statistics/apply/detailEtcReasonListPop";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method searchDetailEtcReasonListTb 
	 *  @return
	 **********************************************/	
	@RequestMapping(value="/stat/apply/detailEtcReasonListTb.json")
	public String searchDetailEtcReasonListTb(ApplyStatCustomDto applyStatCustomDto, HttpServletRequest request, HttpServletResponse response, Model model){
	
		List<ApplyStatCustomDto> detailEtcReasonList = applyStatService.selectDetailEtcReasonList(applyStatCustomDto);
		
		if (!detailEtcReasonList.isEmpty()){
			
			int listCnt = applyStatDao.selectDetailEtcReasonListCount(applyStatCustomDto);
			
			// 페이징 처리
			detailEtcReasonList.get(0).setCurrentPage(applyStatCustomDto.getCurrentPage());
			detailEtcReasonList.get(0).setRowSize(applyStatCustomDto.getRowSize());
			detailEtcReasonList.get(0).setListCnt(listCnt);
			detailEtcReasonList.get(0).setTotalPage((int)Math.ceil((double)listCnt / applyStatCustomDto.getRowSize()));
		}
		
		model.addAttribute("detailEtcReasonList", detailEtcReasonList);
		
		return "statistics/apply/detailEtcReasonListPopTb";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method viewAdminApplyStat 
	 *  @return
	 **********************************************/
	@RequestMapping(value="/stat/apply/adminList.do")
	public String viewAdminApplyStat(ApplyStatCustomDto applyStatCustomDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		// 범죄유형 코드 리스트 조회
		model.addAttribute("crimeTypeList", codeService.selectCodeList("101", null, null));
		
		model.addAttribute("positionList", applyStatService.selectPartList(applyStatCustomDto));
		
		model.addAttribute("itemMinDate", applyStatService.selectItemMinDate());
		
		return "statistics/apply/adminApplyStat";
	}
	
	@RequestMapping(value="/stat/apply/adminList.json")
	public String searchAdminApplyStat(ApplyStatCustomDto applyStatCustomDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		applyStatCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyStatCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		// 전체 현황 조회
		model.addAttribute("allList", applyStatService.userStatAllList(applyStatCustomDto));
		// 기여율(해결사건) 조회
		model.addAttribute("solveList", applyStatService.userStatSolveList(applyStatCustomDto));
		
		// 미활용 사유 년도 조회
		String[] yearArr = applyStatService.userStatYears(applyStatCustomDto);
		model.addAttribute("yearList", yearArr);		
		// 미활용 사유 조회
		applyStatCustomDto.setYears(yearArr);		
		model.addAttribute("reasonList", applyStatService.userStatReasonList(applyStatCustomDto));		

		model.addAttribute("stDate", applyStatCustomDto.getSearchStDate());
		model.addAttribute("edDate", applyStatCustomDto.getSearchEdDate());		
		
		return "statistics/apply/adminApplyStatTb";
	}	
	
	
	@RequestMapping(value="/stat/apply/departList.json")
	public String searchDepartList(ApplyStatCustomDto applyStatCustomDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		// 범죄유형 코드 리스트 조회
		model.addAttribute("departList", applyStatService.selectPartList(applyStatCustomDto));
		
		return "jsonView";
	}	
	
}
