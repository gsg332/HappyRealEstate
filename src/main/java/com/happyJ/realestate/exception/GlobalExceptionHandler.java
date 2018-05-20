package com.happyJ.realestate.exception;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public @ResponseBody ModelAndView handleError(HttpServletRequest req, HttpServletResponse res, Exception e)
			throws Exception {

		e.printStackTrace();
		
		res.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = res.getWriter();

		res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		boolean isAjax = req.getHeader("X-Requested-With") != null ? true : false;

		if (isAjax) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("name", e.getClass().getName());
			result.put("SimpleName", e.getClass().getSimpleName());
			result.put("message", e.getMessage());
			result.put("url", req.getRequestURL().append('?').append(req.getQueryString()));
			out.print(mapper.writeValueAsString(result));
			out.flush();
			out.close();
			return null;
		} else {
			ModelAndView view = new ModelAndView();
			view.addObject("name", e.getClass().getName());
			view.addObject("SimpleName", e.getClass().getSimpleName());
			view.addObject("message", e.getMessage());
			view.addObject("url", req.getRequestURL().append('?').append(req.getQueryString()));
			view.setViewName("forward:/error500.do");
			return view;
		}
	}

}
