/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 5. 21. yongpal
*****************************************************************************/
package com.happyJ.realestate.web.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happyJ.realestate.model.schema.CodeDto;
import com.happyJ.realestate.service.CommonCodeService;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.system
 *  @fileName : CodeController.java
 *  @author : yongpal
 *  @since 2016. 5. 21.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 5. 21.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 5. 21.        yongpal       create CodeController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class CodeController {

	private static Logger logger = LoggerFactory.getLogger(CodeController.class);
	
	@Autowired
	private CommonCodeService codeService;
	
	@RequestMapping(value="/system/code/list.do")
	public String viewCodeList(CodeDto codeDto, Model model){
		
		// 코드 그룹 리스트 조회
		model.addAttribute("codeGroupList", codeService.selectCodeGroupList(codeDto));		

		return "system/code/codeList";
	}
	
	@RequestMapping(value="/system/code/list.json")
	public String searchCodeList(CodeDto codeDto, Model model){
		
		// 코드 그룹 상세 리스트 조회
		model.addAttribute("searchGroupList", codeService.searchCodeGroupList(codeDto));		
		
		return "system/code/codeListTb";
	}
	
	@RequestMapping(value="/system/code/modify.json")
	public String modifyCodeInfo(CodeDto codeDto, Model model){
		
		if (codeService.updateCodeInfo(codeDto) > 0){
			model.addAttribute("result", "SUCCESS");
		} else {
			model.addAttribute("result", "FAIL");
		}
		
		return "jsonView";
	}
	
	@RequestMapping(value="/system/code/delete.json")
	public String deleteCodeInfo(CodeDto codeDto, Model model){
		
		if (codeService.deleteCodeInfo(codeDto) > 0){
			model.addAttribute("result", "SUCCESS");
		} else {
			model.addAttribute("result", "FAIL");
		}
		
		return "jsonView";
	}	
	
	
	@RequestMapping(value="/system/code/insert.json")
	public String insertCodeInfo(CodeDto codeDto, Model model){
		
		codeService.insertCodeInfo(codeDto);
		
		model.addAttribute("result", "SUCCESS");
		
		return "jsonView";		
	}		
}
