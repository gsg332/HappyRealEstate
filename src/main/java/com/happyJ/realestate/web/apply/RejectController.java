/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 5. 19. yongpal
*****************************************************************************/
package com.happyJ.realestate.web.apply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happyJ.realestate.model.custom.ApplyCustomDto;
import com.happyJ.realestate.service.ApplyService;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.apply
 *  @fileName : RejectController.java
 *  @author : yongpal
 *  @since 2016. 5. 19.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 5. 19.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 5. 19.        yongpal       create RejectController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class RejectController {

	private static Logger logger = LoggerFactory.getLogger(RejectController.class);
	
	@Autowired
	private ApplyService applyService;
	
	@RequestMapping(value="/apply/reject/list.do")
	public String viewRejectList(HttpServletRequest request, HttpServletResponse response){
		
		return "apply/reject/rejectList";
	}
	
	@RequestMapping(value="/apply/reject/list.json")
	public String searchRejectList(ApplyCustomDto applyDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		model.addAttribute("rejectList", applyService.selectRejectList(applyDto));
		
		return "apply/reject/rejectListTb";
	}
	
	@RequestMapping(value="/apply/reject/excel.do")
	public String exportRejectListExcel(ApplyCustomDto applyDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		model.addAttribute("title", "영상 신청 반려 리스트");
		model.addAttribute("excelList", applyService.selectRejectListExcel(applyDto));
		
		return "rejectListExcelView";
	}
	
}
