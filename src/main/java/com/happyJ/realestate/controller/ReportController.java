package com.happyJ.realestate.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happyJ.realestate.dto.InterestDto;
import com.happyJ.realestate.dto.RegionDto;
import com.happyJ.realestate.service.InterestService;
import com.happyJ.realestate.service.RegionService;

@Controller
public class ReportController {

	private static Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	RegionService regionService; 
	@Autowired
	InterestService interestService; 

	@RequestMapping(value="/report/main.do")
	public String main(HttpServletRequest request, Model model) throws Exception {
		
		model.addAttribute("regionList", regionService.selectRegionList(new RegionDto()));
		model.addAttribute("interestList", interestService.selectInterestList(new InterestDto()));
		
		return "report/main";
	}
	
}
