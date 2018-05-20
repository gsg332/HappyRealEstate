/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 6. 3. yongpal
*****************************************************************************/
package com.happyJ.realestate.web.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happyJ.realestate.model.schema.ActionHistoryDto;
import com.happyJ.realestate.service.ActionHistoryService;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.system
 *  @fileName : ChangeHistoryController.java
 *  @author : yongpal
 *  @since 2016. 6. 3.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 6. 3.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 6. 3.        yongpal       create ChangeHistoryController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class ChangeHistoryController {

	private static Logger logger = LoggerFactory.getLogger(ChangeHistoryController.class);
	
	@Autowired
	private ActionHistoryService actionHistoryService;
	
	@RequestMapping(value="/system/change/list.do")
	public String viewActionHistoryList(HttpServletRequest request, HttpServletResponse response){
		
		return "system/change/historyList";
	}
	
	@RequestMapping(value="/system/change/list.json")
	public String searchActionHistoryList(ActionHistoryDto actionHistoryDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		model.addAttribute("actionHistoryList", actionHistoryService.selectActionHistoryList(actionHistoryDto));
		
		return "system/change/historyListTb";
	}
	
	@RequestMapping(value="/system/change/excel.do")
	public String exportActionHistoryListExcel(ActionHistoryDto actionHistoryDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		model.addAttribute("title", "사용 이력 리스트");
		model.addAttribute("excelList", actionHistoryService.selectActionHistoryListExcel(actionHistoryDto));
		
		return "actionHistoryListExcelView";
	}	
	
	
	@RequestMapping(value="/system/user/join.json")
	public String searchJoinList(ActionHistoryDto actionHistoryDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		model.addAttribute("joinList", actionHistoryService.joinHistoryList(actionHistoryDto));
		
		return "system/user/joinListTb";
	}
	
	@RequestMapping(value="/system/user/joinExcel.do")
	public String exportJoinListExcel(ActionHistoryDto actionHistoryDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		model.addAttribute("title", "가입 이력 리스트");
		model.addAttribute("excelList", actionHistoryService.joinHistoryListExcel(actionHistoryDto));
		
		return "joinListExcelView";
	}	
	
}
