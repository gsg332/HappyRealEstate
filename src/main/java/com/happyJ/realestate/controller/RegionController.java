package com.happyJ.realestate.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happyJ.realestate.dto.RegionDto;
import com.happyJ.realestate.service.RegionService;

@Controller
public class RegionController {

	private static Logger logger = LoggerFactory.getLogger(RegionController.class);

	@Autowired
	private RegionService regionService;

	@RequestMapping(value = "/region/list.json")
	public String selectRegionList(RegionDto regionDto, HttpServletRequest request, Model model) throws Exception {

		model.addAttribute("regionList", regionService.selectRegionList(regionDto));

		return "jsonView";
	}
}
