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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happyJ.realestate.dao.ApplyDao;
import com.happyJ.realestate.dto.ApartDto;
import com.happyJ.realestate.service.ApartService;
import com.happyJ.realestate.service.RealEstateService;

/*****************************************************************************
 * 
 * @packageName : com.happyJ.realestate.web.aplly
 * @fileName : ApplyController.java
 * @author : yongpal
 * @since 2016. 4. 11.
 * @version 1.0
 * @see :
 * @revision : 2016. 4. 11.
 * 
 *           <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 11.        yongpal       create ApplyController.java
 *           </pre>
 ******************************************************************************/
@Controller
public class RealEstateController {

	private static Logger logger = LoggerFactory.getLogger(RealEstateController.class);

	@Autowired
	private ApartService apartService;
	

	@RequestMapping(value = "/realEstate/apartList.json")
	public String selectApartList(ApartDto apartDto, HttpServletRequest request, Model model)
			throws Exception {

		model.addAttribute("apartList", apartService.selectApartList(apartDto));

		return "jsonView";
	}

}
