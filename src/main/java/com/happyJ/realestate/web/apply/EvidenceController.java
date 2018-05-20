/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.web.apply;

import java.text.ParseException;

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

import com.happyJ.realestate.model.custom.EvidenceCustomDto;
import com.happyJ.realestate.model.schema.ItemEvidenceDto;
import com.happyJ.realestate.service.CommonCodeService;
import com.happyJ.realestate.service.EvidenceService;

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
public class EvidenceController {

	private static Logger logger = LoggerFactory.getLogger(EvidenceController.class);
	
	@Autowired
	private EvidenceService evidenceService;
	
	@Autowired
	private CommonCodeService codeService;
	
	@RequestMapping(value="/apply/evidence/list.do")
	public String viewEvidenceList(EvidenceCustomDto evidenceCustomDto, HttpServletRequest request, HttpServletResponse response, Model model){

		evidenceCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		evidenceCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		// 증거자료 제출 상태 코드 리스트 조회
		model.addAttribute("permitList", codeService.selectCodeList("110", null, "Y"));
		
		return "apply/evidence/evidenceList";
	}
	
	@RequestMapping(value="/apply/evidence/list.json")
	public String searchEvidenceList(EvidenceCustomDto evidenceCustomDto, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		evidenceCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		evidenceCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		// 증거자료 제출내역 리스트 조회
		model.addAttribute("evidenceList", evidenceService.getEvidenceList(evidenceCustomDto));
		
		return "apply/evidence/evidenceListTb";
	}
	
	@RequestMapping(value="/apply/evidence/excel.do")
	public String exportEvidenceListExcel(EvidenceCustomDto evidenceCustomDto, HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {
		
		evidenceCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		evidenceCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		model.addAttribute("title", "증거자료 제출 리스트");
		model.addAttribute("excelList", evidenceService.getEvidenceListExcel(evidenceCustomDto));
		
		return "evidenceListExcelView";
	}
	
	@RequestMapping(value="/apply/evidence/modify.json")
	public String updateEvidenceInfo(ItemEvidenceDto evidenceDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		if (evidenceService.updateEvidenceInfo(evidenceDto) > 0){
			model.addAttribute("result", "SUCCESS");
		} else {
			model.addAttribute("result", "FAIL");
		}
		
		return "jsonView";
	}
	
	@RequestMapping(value="/apply/evidence/regPop.do", method=RequestMethod.GET)
	public String popEvidenceReg(@RequestParam String itemSerial, HttpServletRequest request, HttpServletResponse response, Model model){
		
		model.addAttribute("itemSerial", itemSerial);
		model.addAttribute("periodList", codeService.selectCodeList("107", null, null));
		
		return "apply/evidence/evidenceRegPop";
	}
	
	@RequestMapping(value="/apply/evidence/regist.json")
	public String registEvidenceInfo(EvidenceCustomDto evidenceCustomDto, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		
		evidenceCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		
		if (evidenceService.insertEvidenceInfo(evidenceCustomDto) > 0){
			model.addAttribute("result", "SUCCESS");
		} else {
			model.addAttribute("result", "FAIL");
		}
		
		return "jsonView";
	}
	
}
