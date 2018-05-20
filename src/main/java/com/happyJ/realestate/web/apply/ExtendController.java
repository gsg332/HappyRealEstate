/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.web.apply;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.happyJ.realestate.common.message.SmsMessageEnum;
import com.happyJ.realestate.common.util.StringUtil;
import com.happyJ.realestate.model.custom.ApplyCustomDto;
import com.happyJ.realestate.model.custom.ExtendCustomDto;
import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.model.schema.ItemExtendDto;
import com.happyJ.realestate.service.ApplyService;
import com.happyJ.realestate.service.CommonCodeService;
import com.happyJ.realestate.service.ExtendService;
import com.happyJ.realestate.service.SmsSendService;
import com.happyJ.realestate.web.common.Config;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.aplly
 *  @fileName : ExtendController.java
 *  @author : yongpal
 *  @since 2016. 4. 25.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 25.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 25.        yongpal       create ExtendController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class ExtendController {

	private static Logger logger = LoggerFactory.getLogger(ExtendController.class);
	
	@Autowired
	private ExtendService extendService;
	
	@Autowired
	private CommonCodeService codeService;
	
	@Autowired
	private SmsSendService smsSendService;
	
	@Autowired
	private ApplyService applyService;
	
	@RequestMapping(value="/apply/extend/list.do")
	public String viewExtendList(ExtendCustomDto extendCustomDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		extendCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		extendCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		// 증거자료 제출 상태 코드 리스트 조회
		model.addAttribute("permitList", codeService.selectCodeList("110", null, "Y"));		
		
		return "apply/extend/extendList";
	}		
	
	@RequestMapping(value="/apply/extend/list.json")
	public String searchExtendList(ExtendCustomDto extendCustomDto, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		extendCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		extendCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		// 연장신청내역 리스트 조회
		model.addAttribute("extendList", extendService.getExtendList(extendCustomDto));
		
		return "apply/extend/extendListTb";
	}
	
	
	@RequestMapping(value="/apply/extend/excel.do")
	public String exportExtendListExcel(ExtendCustomDto extendCustomDto, HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {
		
		extendCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		extendCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		model.addAttribute("title", "연장 신청 리스트");
		model.addAttribute("excelList", extendService.getExtendListExcel(extendCustomDto));
		
		return "extendListExcelView";
	}	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/apply/extend/regist.json")
	public String registExtendInfo(@RequestParam String itemSerial, ItemExtendDto extendDto, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		
		if (extendService.insertExtendInfo(extendDto) > 0){
			ApplyCustomDto applyCustomDto = new ApplyCustomDto();
			applyCustomDto.setItemSerial(itemSerial);
			// 연장신청
			applyCustomDto.setVeiApplyStatus("2");
			applyService.updateApplyInfo(applyCustomDto);
			model.addAttribute("result", "SUCCESS");
		} else {
			model.addAttribute("result", "FAIL");
		}
		
		String callback = null;
		for(ConfigDto configDto : Config.configList){
			if("SMS_CALLBACK".equals(configDto.getItemName())){
				callback = configDto.getItemValue();
			}
		}
		// 영상 신청 SMS 전송 to 관리자 
		model.addAttribute("result", StringUtil
				.successYn(smsSendService.processSendSms(SmsMessageEnum.EXT_VIDEO, "", "", itemSerial, 1, callback)));		
		
		return "jsonView";
	}
	
	@RequestMapping(value="/apply/extend/modify.json")
	public String updateExtendInfo(ExtendCustomDto extendCustomDto, HttpServletRequest request, HttpServletResponse response, Model model){
		// 승인 반려 veiStatus 상태값 변경은 ExtendService 에서 한다.
		if (extendService.updateExtendInfo(extendCustomDto) > 0){
			model.addAttribute("result", "SUCCESS");
		} else {
			model.addAttribute("result", "FAIL");
		}
		
		return "jsonView";
	}	
	
}
