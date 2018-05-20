/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 5. 21. yongpal
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

import com.happyJ.realestate.model.schema.IpDto;
import com.happyJ.realestate.service.IpService;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.system
 *  @fileName : IpController.java
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
 *     2016. 5. 21.        yongpal       create IpController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class IpController {

	private static Logger logger = LoggerFactory.getLogger(IpController.class);
	
	@Autowired
	private IpService ipService;
	
	@RequestMapping(value="/system/ip/list.do")
	public String viewIpList(Model model){

		return "system/ip/ipList";
	}
	
	@RequestMapping(value="/system/ip/list.json")
	public String searchIpList(IpDto ipDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		ipDto.setWriteUser(request.getSession().getAttribute("USER_ID").toString());
		
		model.addAttribute("ipList", ipService.selectIpList(ipDto));
		
		return "system/ip/ipListTb";
	}	
	
	@RequestMapping(value="/system/ip/modify.json")
	public String modifyIpInfo(IpDto ipDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		ipDto.setWriteUser(request.getSession().getAttribute("USER_ID").toString());
		
		if (ipService.updateIpInfo(ipDto) > 0){
			model.addAttribute("result", "SUCCESS");
		} else {
			model.addAttribute("result", "FAIL");
		}
		
		return "jsonView";
	}
	
	@RequestMapping(value="/system/ip/delete.json")
	public String deleteIpInfo(IpDto ipDto, Model model){
		
		if (ipService.deleteIpInfo(ipDto) > 0){
			model.addAttribute("result", "SUCCESS");
		} else {
			model.addAttribute("result", "FAIL");
		}
		
		return "jsonView";
	}	
	
	
	@RequestMapping(value="/system/ip/insert.json")
	public String insertIpInfo(IpDto ipDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		if (request.getSession().getAttribute("USER_ID") != null){
			ipDto.setWriteUser(request.getSession().getAttribute("USER_ID").toString());
		} else {
			ipDto.setWriteUser("anonymous");
		}
		
		
		ipService.insertIpInfo(ipDto);
		
		model.addAttribute("result", "SUCCESS");
		
		return "jsonView";		
	}			
}
