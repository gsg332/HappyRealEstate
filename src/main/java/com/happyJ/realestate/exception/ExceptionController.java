package com.happyJ.realestate.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {

	@RequestMapping(value="/error404.do")
	public String viewError404(){
		return "error/error404";
	}
	
	@RequestMapping(value="/error500.do")
	public String viewError500(){
		return "error/error500";
	}

}
