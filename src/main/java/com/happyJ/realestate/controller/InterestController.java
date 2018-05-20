package com.happyJ.realestate.controller;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happyJ.realestate.dto.InterestDto;
import com.happyJ.realestate.service.InterestService;

@Controller
public class InterestController {

	private static Logger logger = LoggerFactory.getLogger(InterestController.class);
	
	@Autowired
	private InterestService interestService;
	
	@RequestMapping(value="/interest/list.json")
	public String selectInterestList(InterestDto interestDto, HttpServletRequest request, Model model) throws Exception {
		
		model.addAttribute("interestList", interestService.selectInterestList(interestDto));
		
		return "jsonView";
	}
	
	@RequestMapping(value="/interest/add.json")
	public String addInteres(InterestDto interestDto, HttpServletRequest request, Model model) throws Exception {
		
		model.addAttribute("result", interestService.addInterest(interestDto));
		
		return "jsonView";
	}
	
	@RequestMapping(value="/interest/delete.json")
	public String deleteInterest(InterestDto interestDto, HttpServletRequest request, Model model) throws Exception {
		
		/*if(!"APT".equals(interestDto.getType())){
			int subInterestCnt = interestService.existSubInterestCheck(interestDto);
			if(subInterestCnt > 0){
				model.addAttribute("subInterestCnt", subInterestCnt);
				return "jsonView";
			}
		}*/
		
		model.addAttribute("result", interestService.deleteInterest(interestDto));
		
		return "jsonView";
	}
	
}
