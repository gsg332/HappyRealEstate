package com.happyJ.realestate.web.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happyJ.realestate.model.schema.FnConfigDto;
import com.happyJ.realestate.service.FnConfigService;
import com.happyJ.realestate.web.common.Config;

@Controller
public class FnConfigController {

	private static Logger logger = LoggerFactory.getLogger(FnConfigController.class);

	@Autowired
	private FnConfigService fnConfigService;

	@RequestMapping(value = "/system/fnConfig/list.do")
	public String viewFnConfigList(Model model) {
		return "system/fnConfig/fnConfigList";
	}

	@RequestMapping(value = "/system/fnConfig/list.json")
	public String searchFnConfigList(FnConfigDto fnConfigDto, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		List<FnConfigDto> fnConfigList = fnConfigService.selectFnConfigList(fnConfigDto);
		model.addAttribute("fnConfigList", fnConfigList);

		return "system/fnConfig/fnConfigListTb";
	}

	@RequestMapping(value = "/system/fnConfig/insert.json")
	public String insertFnConfigInfo(FnConfigDto fnConfigDto, HttpServletRequest request, Model model) {

		fnConfigDto.setpItemIndex("".equals(fnConfigDto.getpItemIndex()) ? null : fnConfigDto.getpItemIndex());
		fnConfigDto.setItemVisible("1");

		if (fnConfigService.insertFnConfigInfo(fnConfigDto) > 0) {

			Config.fnConfigList = Config.getFnConfigList(fnConfigService);

			model.addAttribute("result", "SUCCESS");
		} else {
			model.addAttribute("result", "FAIL");
		}

		return "jsonView";
	}

	@RequestMapping(value = "/system/fnConfig/modify.json")
	public String modifyFnConfigInfo(FnConfigDto fnConfigDto, HttpServletRequest request, Model model) {

		if (fnConfigService.updateFnConfigInfo(fnConfigDto) > 0) {

			Config.fnConfigList = Config.getFnConfigList(fnConfigService);

			model.addAttribute("result", "SUCCESS");
		} else {
			model.addAttribute("result", "FAIL");
		}

		return "jsonView";
	}

	@RequestMapping(value = "/system/fnConfig/delete.json")
	public String deleteFnConfigInfo(FnConfigDto fnConfigDto, HttpServletRequest request, Model model) {

		if (fnConfigService.deleteFnConfigInfo(fnConfigDto) > 0) {

			Config.fnConfigList = Config.getFnConfigList(fnConfigService);

			model.addAttribute("result", "SUCCESS");
		} else {
			model.addAttribute("result", "FAIL");
		}

		return "jsonView";
	}

}
