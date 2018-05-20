package com.happyJ.realestate.web.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.happyJ.realestate.model.schema.ChangeReqDto;
import com.happyJ.realestate.service.ApplyStatService;
import com.happyJ.realestate.service.ChangeReqService;
import com.happyJ.realestate.service.CommonCodeService;

@Controller
public class ChangeReqController {

	private static Logger logger = LoggerFactory.getLogger(ChangeReqController.class);

	@Autowired
	private ChangeReqService changeReqService;

	@Autowired
	private CommonCodeService codeService;

	@Autowired
	private ApplyStatService applyStatService;

	/**
	 * 변경내역에 대한 view를 가지고 온다.
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeReq/list.do")
	public String viewChangeReqList(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value="searchStatus", required =false, defaultValue="") String searchStatus, @RequestParam(value="searchTypeDepth1", required =false, defaultValue="") String searchTypeDepth1) {
		model.addAttribute("statusList", codeService.selectCodeList("117", null, "N"));
		model.addAttribute("typeDepth1List", codeService.selectCodeList("118", null, "N"));
		
		model.addAttribute("pSearchStatus", searchStatus);
		model.addAttribute("pSearchTypeDepth1", searchTypeDepth1);
		
		return "changeReq/changeReqList";
	}

	/**
	 * 변경내역에 대한 목록을 가지고 온다.
	 * @param changeReqDto
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeReq/list.json")
	public String searchChangeReqList(ChangeReqDto changeReqDto, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		model.addAttribute("changeReqList", changeReqService.selectChangeReqList(request, changeReqDto));
		return "changeReq/changeReqListTb";
	}

	/**
	 * 변경요청을 취소한다.
	 * @param request
	 * @param response
	 * @param changeReqDto
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeReq/cancel.json")
	public String cancel(HttpServletRequest request, HttpServletResponse response, ChangeReqDto changeReqDto,
			Model model) {
		model.addAttribute("result", changeReqService.cancel(request, changeReqDto));
		return "jsonView";
	}

	/**
	 * 변경요청내역에 대한 view를 가지고 온다.
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/changeReq/list.do")
	public String adminViewChangeReqList(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value="searchStatus", required =false, defaultValue="") String searchStatus, @RequestParam(value="searchTypeDepth1", required =false, defaultValue="") String searchTypeDepth1) {
		model.addAttribute("statusList", codeService.selectCodeList("117", null, "N"));
		model.addAttribute("typeDepth1List", codeService.selectCodeList("118", null, "N"));
		model.addAttribute("unApprovalReasonList", codeService.selectCodeList("105", null, null));
		model.addAttribute("positionList", applyStatService.selectPartList(null));
		
		//파라메터 값 전달
		model.addAttribute("pSearchStatus", searchStatus);
		model.addAttribute("pSearchTypeDepth1", searchTypeDepth1);
		
		return "system/changeReq/changeReqList";
	}

	/**
	 * 변경요청내역에 대한 목록을 가지고 온다.
	 * @param changeReqDto
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/changeReq/list.json")
	public String adminSearchChnageReqList(ChangeReqDto changeReqDto, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		model.addAttribute("changeReqList", changeReqService.selectAdminChangeReqList(changeReqDto));
		return "system/changeReq/changeReqListTb";
	}

	/**
	 * 변경요청에 대한 승인 또는 미승인을 처리한다.
	 * @param request
	 * @param response
	 * @param changeReqDto
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/changeReq/modifyChangeReqStatus.json")
	public String modifyChangeReqStatus(HttpServletRequest request, HttpServletResponse response,
			ChangeReqDto changeReqDto, Model model) throws Exception {
		model.addAttribute("result", changeReqService.modifyChangeReqStatus(changeReqDto));
		return "jsonView";
	}

	/**
	 * 변경내역에 대한 목록을 엑셀파일로 내려준다.
	 * @param changeReqDto
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeReq/excel.do")
	public String exportChangeReqListExcel(ChangeReqDto changeReqDto, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		changeReqDto.setReqUserid(request.getSession().getAttribute("USER_ID").toString());

		model.addAttribute("title", "변경 이력 리스트");
		model.addAttribute("excelList", changeReqService.selectChangeReqListExcel(changeReqDto));

		return "changeReqListExcelView";
	}

	/**
	 * 변경요청내역에 대한 목록을 엑셀파일로 내려준다.
	 * @param changeReqDto
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/changeReq/excel.do")
	public String exportAdminChangeReqListExcel(ChangeReqDto changeReqDto, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		model.addAttribute("title", "변경요청 이력 리스트");
		model.addAttribute("excelList", changeReqService.selectAdminChangeReqListExcel(changeReqDto));

		return "adminChangeReqListExcelView";
	}
}
