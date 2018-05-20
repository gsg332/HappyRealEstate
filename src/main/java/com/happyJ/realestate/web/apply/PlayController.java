/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 5. 31. yongpal
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

import com.happyJ.realestate.model.schema.LogDto;
import com.happyJ.realestate.service.LogService;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.apply
 *  @fileName : PlayController.java
 *  @author : yongpal
 *  @since 2016. 5. 31.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 5. 31.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 5. 31.        yongpal       create PlayController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class PlayController {

	private static Logger logger = LoggerFactory.getLogger(ApplyController.class);
	
	@Autowired
	private LogService logService;
	
	@RequestMapping(value="/apply/play/list.json")
	public String searchPlayList(LogDto logDto, HttpServletRequest request,
			HttpServletResponse response, Model model){
		
		// 재생 기록 조회
		model.addAttribute("playList", logService.selectLogList(logDto));
				
		return "apply/apply/playListTb";
	}
	
	@RequestMapping(value="/apply/play/excel.do")
	public String exportPlayListExcel(LogDto logDto, HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {
		
		model.addAttribute("title", "영상 재생 리스트");
		model.addAttribute("excelList", logService.selectLogListExcel(logDto));
		
		return "playListExcelView";
	}
}
