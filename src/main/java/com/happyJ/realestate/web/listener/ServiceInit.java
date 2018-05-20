package com.happyJ.realestate.web.listener;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.service.ConfigService;
import com.happyJ.realestate.service.FnConfigService;
import com.happyJ.realestate.web.common.Config;

public class ServiceInit extends HttpServlet {

	private static final long serialVersionUID = -5531336059883563878L;

	private static Logger logger = LoggerFactory.getLogger(ServiceInit.class);

	@Value("#{config['use.location.code']}")
	private String location;
	
	@Autowired
	private ConfigService configService;
	
	@Autowired
	private FnConfigService fnConfigService;
	
	
	public void init(ServletConfig config) throws ServletException { //init()은 서블릿 수행시 최초 1회만 동작함
		
		super.init(config);
		
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		
		ConfigDto configDto = new ConfigDto();
		configDto.setItemType(location);
		
		Config.configList = configService.selectConfigListAll(configDto);
		
		Config.fnConfigList = Config.getFnConfigList(fnConfigService);
		
		logger.debug("VES Config Setting Complete.");
	}

}
