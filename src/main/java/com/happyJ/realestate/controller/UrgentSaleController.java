package com.happyJ.realestate.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happyJ.realestate.dto.UrgentSaleDto;
import com.happyJ.realestate.service.UrgentSaleService;
@Controller
public class UrgentSaleController {

	private static Logger logger = LoggerFactory.getLogger(UrgentSaleController.class);

	@Autowired
	private UrgentSaleService urgentSaleService;
	

	@RequestMapping(value = "/urgentSale/list.json")
	public String selectUrgentSaleList(UrgentSaleDto urgentSaleDto, HttpServletRequest request, Model model)
			throws Exception {

		model.addAttribute("urgentSaleList", urgentSaleService.selectUrgentSaleList(urgentSaleDto));

		return "report/urgentSale";
	}

}
