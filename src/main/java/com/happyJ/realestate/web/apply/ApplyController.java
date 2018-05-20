/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.web.apply;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.happyJ.realestate.common.message.SmsMessageEnum;
import com.happyJ.realestate.common.util.StringUtil;
import com.happyJ.realestate.dao.ApplyDao;
import com.happyJ.realestate.model.custom.ApplyCustomDto;
import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.model.schema.FileDto;
import com.happyJ.realestate.service.ApplyService;
import com.happyJ.realestate.service.ApplyStatService;
import com.happyJ.realestate.service.CommonCodeService;
import com.happyJ.realestate.service.ResultService;
import com.happyJ.realestate.service.SmsSendService;
import com.happyJ.realestate.web.common.Config;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.aplly
 *  @fileName : ApplyController.java
 *  @author : yongpal
 *  @since 2016. 4. 11.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 11.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 11.        yongpal       create ApplyController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class ApplyController {

	private static Logger logger = LoggerFactory.getLogger(ApplyController.class);
	
	@Value("#{config['attach.file.path']}")
	private String attachFilePath;
	
	@Value("#{config['video.file.path']}")
	private String videoFilePath;
	
	@Value("#{config['activeX.useSSL']}")
	private String activeXUseSSL;
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private ApplyStatService applyStatService;
	
	@Autowired
	private CommonCodeService codeService;
	
	@Autowired
	private SmsSendService smsSendService;
	
	@Autowired 
	private ApplyDao applyDao;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method viewApplyList 
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/apply/apply/list.do")
	public String viewApplyList(ApplyCustomDto applyCustomDto, HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value="searchStatus", required =false, defaultValue="") String searchStatus,
			@RequestParam(value="searchApplyStatus", required =false, defaultValue="" ) String searchApplyStatus){
		
		applyCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		// 상태 코드 리스트 조회
		model.addAttribute("statusList", codeService.selectCodeList("109", null, "Y"));
		// 처리 완료 상태 코드 리스트 조회
		model.addAttribute("applyStatusList", codeService.selectCodeList("120", null, "Y"));
		// 범죄유형 코드 리스트 조회
		model.addAttribute("crimeTypeList", codeService.selectCodeList("101", null, null));
		// 반려 사유 코드 리스트 조회
		model.addAttribute("rejReasonList", codeService.selectCodeList("105", null, null));
		// 주요 현황 카운트 조회
		model.addAttribute("totalCounts", applyService.selectTotalCounts(applyCustomDto));
		
		model.addAttribute("positionList", applyStatService.selectPartList(null));
		//파라메터 값 전달
		model.addAttribute("pSearchStatus", searchStatus);
		model.addAttribute("pSearchApplyStatus", searchApplyStatus);
		
		return "apply/apply/applyList";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method searchApplyList 
	 *  @param applyCustomDto
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @return
	 * @throws ParseException 
	 **********************************************/
	@RequestMapping(value="/apply/apply/list.json")
	public String searchApplyList(ApplyCustomDto applyCustomDto, HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {
		
		applyCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		// 검색 조건,권한에 맞는 신청 리스트 및 상태,관리 항목 조회
		model.addAttribute("applyList", applyService.selectApplyList(applyCustomDto));
		
		return "apply/apply/applyListTb";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method viewApplyRegist 
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/apply/apply/regist.do")
	public String viewApplyRegist(ApplyCustomDto applyDto, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		applyDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		for(ConfigDto configDto : Config.configList){
			if("veiLimitDatetime".equals(configDto.getItemName())){
				//영상재생만료일자(기본:14일)
				applyDto.setVeiLimitDays(configDto.getItemValue());
			}
		}

		int notExistResultCnt = applyDao.selectResultListCounts(applyDto);
		
		if(notExistResultCnt > 0){
			model.addAttribute("notExistResultCnt", notExistResultCnt);
		}else{
			for(ConfigDto configDto : Config.configList){
				if("OFFICIAL_FILE_REG_TYPE".equals(configDto.getItemName())){
					//공문파일등록여부(0:등록안함,1:신청시등록)
					model.addAttribute("officialFileRegType", configDto.getItemValue());
				}
				if("PLEDGE_FILE_REG_TYPE".equals(configDto.getItemName())){
					//보안서약서등록유형(0:등록안함,1:가입시,2:영상정보신청시
					model.addAttribute("pledgeFileRegType", configDto.getItemValue());
				}
			}
			
			// 재신청 여부 확인
			if (applyDto.getItemSerial() != null){	// 재신청
				// 신청 정보 조회
				model.addAttribute("applyInfo", applyService.selectApplyInfo(applyDto));
			}
			
			// 범죄유형 코드 리스트 조회
			model.addAttribute("crimeTypeList", codeService.selectCodeList("101", null, null));
			// 기타 범죄유형 코드 리스트 조회
			model.addAttribute("crimeEtcTypeList", codeService.selectCodeList("108", null, null));
		}
		
		return "apply/apply/applyRegist";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method registApply 
	 *  @param applyCustomDto
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @return
	 *  @throws Exception
	 **********************************************/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/apply/apply/regist.json", method=RequestMethod.POST)
	public String registApply(ApplyCustomDto applyCustomDto,  
			HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		// Set Session Value
		applyCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyCustomDto.setUserName(request.getSession().getAttribute("USER_NAME").toString());
		applyCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		if (request.getSession().getAttribute("USER_LEVEL").toString() == "7"){
			applyCustomDto.setVeiReqType("1");
		} else {
			applyCustomDto.setVeiReqType("0");
		}
		
		int newItemSerial = applyService.registApplyInfo(request, applyCustomDto);
		String callback = null;
		for(ConfigDto configDto : Config.configList){
			if("SMS_CALLBACK".equals(configDto.getItemName())){
				callback = configDto.getItemValue();
			}
		}

		// 영상 신청 SMS 전송 to 관리자 
		model.addAttribute("result", StringUtil
				.successYn(smsSendService.processSendSms(SmsMessageEnum.REQ_VIDEO, "", "", Integer.toString(newItemSerial), 1, callback)));
		
		return "jsonView";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method popProvideEvidence 
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/apply/apply/providePop.do")
	public String popProvideEvidence(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		// 제공 근거 코드
		model.addAttribute("provideList", codeService.selectCodeList("114", null, "Y"));
		
		return "apply/apply/providePop";
	}
	
	@RequestMapping(value="/apply/apply/detail.do")
	public String searchApplyInfo(ApplyCustomDto applyCustomDto, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		for(ConfigDto configDto : Config.configList){
			if("OFFICIAL_FILE_REG_TYPE".equals(configDto.getItemName())){
				//공문파일등록여부(0:등록안함,1:신청시등록)
				model.addAttribute("officialFileRegType", configDto.getItemValue());
			}
			if("PLEDGE_FILE_REG_TYPE".equals(configDto.getItemName())){
				//보안서약서등록유형(0:등록안함,1:가입시,2:영상정보신청시
				model.addAttribute("pledgeFileRegType", configDto.getItemValue());
			}
		}
		
		// 제공 근거 코드
		model.addAttribute("provideList", codeService.selectCodeList("114", null, "Y"));
		// 범죄유형 코드 리스트 조회
		model.addAttribute("crimeTypeList", codeService.selectCodeList("101", null, null));
		// 기타 범죄유형 코드 리스트 조회
		model.addAttribute("crimeEtcTypeList", codeService.selectCodeList("108", null, null));
		// 신청 정보 조회
		model.addAttribute("applyInfo", applyService.selectApplyInfo(applyCustomDto));
		
		return "apply/apply/applyDetail";
	}
	
	/**
	 * 영상신청정보를 수정한다.
	 * @param applyDto
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/apply/apply/requestChange.json", method=RequestMethod.POST)
	public String requestApplyInfoChange(ApplyCustomDto applyDto, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		
		//date 포맷 셋팅
		applyDto.setVeiDnLimitDate(request.getParameter("veiDnLimitDate")+" 23:59:59");
		applyDto.setVeiLimitDatetime(request.getParameter("veiLimitDatetime")+" 23:59:59");

		int result = 0;
		
        if( Integer.parseInt(request.getSession().getAttribute("USER_LEVEL").toString()) <= 6 ){ // 일반 사용자
        	applyDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
        	result = applyService.applyInfoChangeReq(request, applyDto);
        }else{ // 관리자
        	result = applyService.updateApplyInfo(applyDto); 
        }
        
        if (result > 0){ //곤리자
			model.addAttribute("result", "SUCCESS");
		} else {
			model.addAttribute("result", "FAIL");
		}
		
		return "jsonView";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/apply/apply/modify.json", method=RequestMethod.POST)
	public String modifyApplyInfo(ApplyCustomDto applyDto, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		
		if (applyService.updateApplyInfo(applyDto) > 0){
			
			// FIXME 반려 시 SMS 발송 개발해!!!!!!! veiStatus = 3 
			if ("3".equals(applyDto.getVeiStatus())){
				String callback = null;
				for(ConfigDto configDto : Config.configList){
					if("SMS_CALLBACK".equals(configDto.getItemName())){
						callback = configDto.getItemValue();
					}
				}
				smsSendService.processSendSms(SmsMessageEnum.REJ_VIDEO, applyDto.getUserId(), applyDto.getPhoneNum(),
						applyDto.getItemSerial(), 0, callback);
			}
			
			model.addAttribute("result", "SUCCESS");
		} else {
			model.addAttribute("result", "FAIL");
		}
		
		return "jsonView";
	}
	
	@RequestMapping(value="/apply/apply/excel.do")
	public String exportApplyListExcel(ApplyCustomDto applyCustomDto, HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {
		
		applyCustomDto.setUserId(request.getSession().getAttribute("USER_ID").toString());
		applyCustomDto.setLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		
		model.addAttribute("title", "영상 신청 리스트");
		model.addAttribute("excelList", applyService.selectApplyListExcel(applyCustomDto));
		
		return "applyListExcelView";
	}
	
	@RequestMapping(value="/apply/apply/fileDown.do", method=RequestMethod.GET)
	public void downloadAttachFile(@RequestParam String itemNo, HttpServletResponse response) throws IOException{
		
		FileDto fileDto = applyService.selectFileList(itemNo);
		ApplyCustomDto applyDto = applyService.selectUserIdForFile(itemNo); //ves1.0에 대한 처리. 기존 ves1.0에는 file 테이블에 userid가 존재하지 않음.

		File downFile = new File(attachFilePath+applyDto.getUserId()+"/"+fileDto.getTempFilename());
		
		if (downFile.exists()){
			
			response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
//			response.setHeader("Content-Disposition", String.format("attachment; filename="+fileDto.getOrgFilename()));
			response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileDto.getOrgFilename(),"UTF-8"));
			response.setContentLength((int) downFile.length());
			
			InputStream ips = new BufferedInputStream(new FileInputStream(downFile));
			
			FileCopyUtils.copy(ips, response.getOutputStream());
			
		} else {
			// file does not exits
			String errorStr = "The File Does Not Exist!!";
			OutputStream ops = response.getOutputStream();
			
			ops.write(errorStr.getBytes(Charset.forName("UTF-8")));
			ops.close();
			
			return;
		}
	}
	
	@RequestMapping(value="/apply/apply/videoDown.do")
	public void downloadVideoFile(@RequestParam String itemSerial, HttpServletResponse response) throws Exception{
		File videoFileDir = new File(videoFilePath+itemSerial);
		
		if (videoFileDir.exists()){
			
			String[] fileNames = videoFileDir.list();
			for (String fileName : fileNames){
				
				ApplyCustomDto applyCustomDto = new ApplyCustomDto();
				applyCustomDto.setItemSerial(itemSerial);
				applyCustomDto.setVeiDnLimitCountMinus(1);
				
				applyService.updateApplyInfo(applyCustomDto);
				
				File videoFile = new File(videoFilePath+itemSerial+"/"+fileName);
				
				Cookie cookie = new Cookie("fileDownload", "true");
				cookie.setPath("/");
				
				response.addCookie(cookie);
				response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
				response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileName,"UTF-8"));
				response.setContentLength((int) videoFile.length());
				
				InputStream ips = new BufferedInputStream(new FileInputStream(videoFile));
				
				FileCopyUtils.copy(ips, response.getOutputStream());
				
			}
		} else {
			// file does not exits
			String errorStr = "The File Does Not Exist!!";
			OutputStream ops = response.getOutputStream();
			
			ops.write(errorStr.getBytes(Charset.forName("UTF-8")));
			ops.close();
			
			return;
		}
		
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @Method downloadVideoFile_ActiveX 
	 * @param itemSerial
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 **********************************************/

	@RequestMapping(value="/apply/apply/videoPop.do")
	public String downloadVideoFile_ActiveX(@RequestParam String itemSerial, HttpServletResponse response, Model model) throws Exception {
		File videoFileDir = new File(videoFilePath+itemSerial);
		ArrayList<String> file_list = new ArrayList<String>();
		if (videoFileDir.exists()){
			String[] fileNames = videoFileDir.list();
			for (String fileName : fileNames){
				String fileName1 = new String(fileName.getBytes(Charset.forName("UTF-8")));
				file_list.add(fileName1);
			}
			model.addAttribute("file_list", file_list);
			model.addAttribute("videoFilePath", videoFilePath);
			model.addAttribute("activeXUseSSL", activeXUseSSL);
			model.addAttribute("itemSerial", itemSerial);
			
		} else {
			// file does not exits
			String errorStr = "The File Does Not Exist!!";
			OutputStream ops = response.getOutputStream();
			
			ops.write(errorStr.getBytes(Charset.forName("UTF-8")));
			ops.close();
		}
		return "apply/apply/videoDownPop";
	}
	
	
	@RequestMapping(value="/apply/apply/dlLimitCount.json")
	public String downloadLimitCount(ApplyCustomDto applyCustomDto, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		
		model.addAttribute("downloadLimitCount", applyService.selectDownloadLimitCount(applyCustomDto));
		
		return "jsonView";
	}
}
