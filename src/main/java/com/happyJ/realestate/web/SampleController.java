/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 8. yongpal
*****************************************************************************/
package com.happyJ.realestate.web;

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
import com.happyJ.realestate.service.CommonCodeService;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web
 *  @fileName : SampleController.java
 *  @author : yongpal
 *  @since 2016. 4. 8.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 8.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 8.        yongpal       create SampleController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class SampleController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ApplyService applyService;
	@Autowired
	private CommonCodeService codeService;

	@RequestMapping(value="/sample.do")
	public String getSample(HttpServletRequest request, HttpServletResponse response, Model model){
		
		ApplyCustomDto applyCustomDto = new ApplyCustomDto();
		applyCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		applyCustomDto.setCurrentPage(1);
		applyCustomDto.setRowSize(10);
		applyCustomDto.setTotalPage(10);
		
		logger.info("level : "+applyCustomDto.getLevel());
		// 상태 코드 리스트 조회
		model.addAttribute("statusList", codeService.selectCodeList("109", null, "Y"));
		// 범죄유형 코드 리스트 조회
		model.addAttribute("crimeTypeList", codeService.selectCodeList("101", null, null));
		// 주요 현황 카운트 조회
		model.addAttribute("totalCounts", applyService.selectTotalCounts(applyCustomDto));
		
		//model.addAttribute("applyList", applyService.selectApplyList(applyCustomDto));
		
		return "sample";
	}
}
