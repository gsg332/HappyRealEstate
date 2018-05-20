/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 5. 21. yongpal
*****************************************************************************/
package com.happyJ.realestate.web.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.service.ConfigService;
import com.happyJ.realestate.web.common.Config;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.system
 *  @fileName : ConfigController.java
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
 *     2016. 5. 21.        yongpal       create ConfigController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class ConfigController {

	private static Logger logger = LoggerFactory.getLogger(ConfigController.class);
	
	@Value("#{config['use.location.code']}")
	private String location;
	
	@Autowired
	private ConfigService configService;
	
	@RequestMapping(value="/system/config/list.do")
	public String viewConfigList(Model model){
		
		return "system/config/configList";
	}
	
	@RequestMapping(value="/system/config/list.json")
	public String searchConfigList(ConfigDto configDto, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		
		configDto.setItemType(location);
		
		List<ConfigDto> configList = configService.selectConfigList(configDto);
		model.addAttribute("configList", configList);
		
		return "system/config/configListTb";
	}
	
	@RequestMapping(value="/system/config/modify.json")
	public String modifyConfigInfo(ConfigDto configDto, HttpServletRequest request, Model model){
		
		if (configService.updateConfigInfo(configDto) > 0){
			
			configDto.setItemType(location);
			
			Config.configList = configService.selectConfigListAll(configDto);
			
			model.addAttribute("result", "SUCCESS");
		} else {
			model.addAttribute("result", "FAIL");
		}
		
		return "jsonView";
	}
}
