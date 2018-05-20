/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happyJ.realestate.dao.ApplyDao;
import com.happyJ.realestate.model.custom.ApplyCustomDto;
import com.happyJ.realestate.service.ApplyService;
import com.happyJ.realestate.service.ApplyStatService;
import com.happyJ.realestate.service.CommonCodeService;
import com.happyJ.realestate.service.SmsSendService;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.aplly
 *  @fileName : ApplyController.java
 *  @author : yongpal
 *  @since 2016. 4. 11.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 11.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 11.        yongpal       create ApplyController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class ApartController {

	private static Logger logger = LoggerFactory.getLogger(ApartController.class);
	
	@Value("#{config['attach.file.path']}")
	private String attachFilePath;
	
	@Value("#{config['video.file.path']}")
	private String videoFilePath;
	
	@Value("#{config['activeX.useSSL']}")
	private String activeXUseSSL;
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private ApplyStatService applyStatService;
	
	@Autowired
	private CommonCodeService codeService;
	
	@Autowired
	private SmsSendService smsSendService;
	
	@Autowired 
	private ApplyDao applyDao;
/*	
	@RequestMapping(value="/crawler/naverRealPriceList.json")
	public String searchApplyList(ApplyCustomDto applyCustomDto, HttpServletRequest request, Model model) throws Exception {
		
		//applyCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		//applyCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		
		
		
		// 검색 조건,권한에 맞는 신청 리스트 및 상태,관리 항목 조회
		model.addAttribute("applyList", applyService.selectApplyList(applyCustomDto));
		
		return "jsonView";
	}*/
	
}
