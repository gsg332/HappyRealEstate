/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.web.apply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.happyJ.realestate.model.custom.ApplyCustomDto;
import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.model.schema.ItemResultDto;
import com.happyJ.realestate.service.ApplyService;
import com.happyJ.realestate.service.ApplyStatService;
import com.happyJ.realestate.service.CommonCodeService;
import com.happyJ.realestate.service.ResultService;
import com.happyJ.realestate.web.common.Config;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.aplly
 *  @fileName : EvidenceController.java
 *  @author : yongpal
 *  @since 2016. 4. 25.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 25.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 25.        yongpal       create DocSubmitController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class ResultController {

	private static Logger logger = LoggerFactory.getLogger(ResultController.class);
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private CommonCodeService codeService;
	
	@Autowired 
	private ApplyService applyService;
	
	@Autowired
	private ApplyStatService applyStatService;
	
	@RequestMapping(value="/apply/result/list.do")
	public String viewResultList(ApplyCustomDto applyDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		applyDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		// 사건 결과 코드 리스트 조회
		model.addAttribute("resultCdList", codeService.selectCodeList("111", null, "Y"));
		// 범죄유형 코드 리스트 조회
		model.addAttribute("crimeTypeList", codeService.selectCodeList("101", null, null));
		// 결과미등록내역 구분 코드 리스트 조회
		model.addAttribute("typeList", codeService.selectCodeList("122", null, "Y"));
		
		model.addAttribute("positionList", applyStatService.selectPartList(null));
		
		return "apply/result/resultList";
	}
	
	@RequestMapping(value="/apply/result/list.json")
	public String searchResultList(ApplyCustomDto applyDto, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		applyDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		for(ConfigDto configDto : Config.configList){
			if("veiLimitDatetime".equals(configDto.getItemName())){
				//영상재생만료일자(기본:14일)
				applyDto.setVeiLimitDays(configDto.getItemValue());
			}
		}
		
		List<ApplyCustomDto> resultList = applyService.selectResultList(applyDto);
		
		model.addAttribute("resultList", resultList);
		
		if (Integer.parseInt(applyDto.getLevel()) <= 7 && resultList.size() > 0){
			request.getSession().setAttribute("NOT_RESULT_CNT", resultList.get(0).getListCnt());
		}
		
		return "apply/result/resultListTb";
	}
	
	@RequestMapping(value="/apply/result/excel.do")
	public String searchResultListExcel(ApplyCustomDto applyDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		applyDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		for(ConfigDto configDto : Config.configList){
			if("veiLimitDatetime".equals(configDto.getItemName())){
				//영상재생만료일자(기본:14일)
				applyDto.setVeiLimitDays(configDto.getItemValue());
			}
		}
		
		model.addAttribute("title", "활용결과 미등록 리스트");
		model.addAttribute("excelList", applyService.selectResultListExcel(applyDto));
		
		return "resultListExcelView";
	}	
	
	@RequestMapping(value="/apply/result/regPop.do")
	public String popResultReg(@RequestParam String itemSerial, Model model){
		
		// 사건 결과 코드 조회
		model.addAttribute("itemResultCode", codeService.selectCodeList("111", null, "Y"));
		// CCTV 활용 여부 코드 조회
		model.addAttribute("cctvCode", codeService.selectCodeList("112", null, "Y"));
		// CCTV 미사용 사유  코드 조회
		model.addAttribute("resultCode", codeService.selectCodeList("104", null, null));
		
		ApplyCustomDto applyDto = new ApplyCustomDto();
		applyDto.setItemSerial(itemSerial);
		// 신청 정보 조회
		model.addAttribute("applyInfo", applyService.selectApplyInfo(applyDto));
		
		return "apply/result/resultRegPop";
	}
	
	@RequestMapping(value="/apply/result/viewPop.do")
	public String popResultView(@RequestParam String itemSerial, Model model){
		
		// 사건 결과 코드 조회
		model.addAttribute("itemResultCode", codeService.selectCodeList("111", null, "Y"));
		// CCTV 활용 여부 코드 조회
		model.addAttribute("cctvCode", codeService.selectCodeList("112", null, "Y"));
		// CCTV 미사용 사유  코드 조회
		model.addAttribute("resultCode", codeService.selectCodeList("104", null, null));
		
		ItemResultDto resultDto = new ItemResultDto();
		resultDto.setItemSerial(itemSerial);
		// 활용결과 정보 조회
		model.addAttribute("resultInfo", resultService.selectResultInfo(resultDto));
		
		ApplyCustomDto applyDto = new ApplyCustomDto();
		applyDto.setItemSerial(itemSerial);
		// 신청 정보 조회
		model.addAttribute("applyInfo", applyService.selectApplyInfo(applyDto));
		
		return "apply/result/resultViewPop";
	}
	
	@RequestMapping(value="/apply/result/regist.json")
	public String registResultInfo(ItemResultDto resultDto, Model model) throws Exception{
		
		ApplyCustomDto applyCustomDto = new ApplyCustomDto();
		applyCustomDto.setItemSerial(resultDto.getItemSerial());

		// 활용결과 등록완료
		applyCustomDto.setVeiApplyStatus("4");
		
		applyService.updateApplyInfo(applyCustomDto);
		
		resultService.insertResultInfo(resultDto);
		
		model.addAttribute("result", "SUCCESS");
		
		return "jsonView";
	}
	
	@RequestMapping(value="/apply/result/modify.json")
	public String modifyResultInfo(ItemResultDto resultDto, Model model) throws Exception{
		
		ApplyCustomDto applyCustomDto = new ApplyCustomDto();
		applyCustomDto.setItemSerial(resultDto.getItemSerial());

		// 활용결과 등록완료
		applyCustomDto.setVeiApplyStatus("4");

		applyService.updateApplyInfo(applyCustomDto);
		
		resultService.updateResultInfo(resultDto);

		model.addAttribute("result", "SUCCESS");
		
		return "jsonView";
	}
	
	@RequestMapping(value="/apply/result/registInvestigating.json", method=RequestMethod.POST)
	public String modifyApplyInfo(ItemResultDto resultDto, HttpServletRequest request, Model model) throws Exception {
			
		ApplyCustomDto applyCustomDto = new ApplyCustomDto();
		applyCustomDto.setItemSerial(resultDto.getItemSerial());

		// 활용결과 등록완료
		applyCustomDto.setVeiApplyStatus("1");
		applyCustomDto.setInvestigatingDate("Y");
		
		applyService.updateApplyInfo(applyCustomDto);
		
		resultDto.setItemResult("2");
		
		resultService.insertResultInfo(resultDto);
		
		model.addAttribute("result", "SUCCESS");
		
		return "jsonView";
	}
	
}
