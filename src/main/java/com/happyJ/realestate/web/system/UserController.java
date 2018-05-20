/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 5. 20. yongpal
*****************************************************************************/
package com.happyJ.realestate.web.system;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;

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

import com.happyJ.realestate.dao.UserDao;
import com.happyJ.realestate.model.schema.ChangeReqDto;
import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.model.schema.FileDto;
import com.happyJ.realestate.model.schema.UserDto;
import com.happyJ.realestate.service.ChangeReqService;
import com.happyJ.realestate.service.ConfigService;
import com.happyJ.realestate.service.UserService;
import com.happyJ.realestate.web.common.Config;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.web.system
 *  @fileName : UserController.java
 *  @author : yongpal
 *  @since 2016. 5. 20.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 5. 20.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 5. 20.        yongpal       create UserController.java
 *  </pre>
 ******************************************************************************/
@Controller
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Value("#{config['user.file.pledge.path']}")
	private String userfilePledgePath;
	
	@Value("#{config['use.location.code']}")
	private String location;
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private UserDao userDao;
	
	@Autowired
	private ConfigService configService;
	
	@Autowired
	private ChangeReqService changeReqService;
	
	@RequestMapping(value="/user/modify.do")
	public String viewModifyUser(UserDto userDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		userDto.setUserId(request.getSession().getAttribute("USER_ID").toString());	
		
		userDto = userService.selectUserInfo(userDto);
		request.getSession().setAttribute("USER_PASSWD_LIMIT", userDto.getPasswordLimitDate());	// 세션의 비밀번호 만료일 갱신
		
		model.addAttribute("userInfo", userService.selectUserInfo(userDto));
		
		return "user/userModify";
	}
	
	@RequestMapping(value="/user/history.do")
	public String viewUserHistory(HttpServletRequest request, HttpServletResponse response, Model model){
		
		return "user/userHistory";
	}
	
	@RequestMapping(value="/user/history/list.json")
	public String searchUserHistoryList(HttpServletRequest request, HttpServletResponse response, Model model){
		
		return "user/userHistoryTb";
	}
	
	@RequestMapping(value="/user/regist.do")
	public String viewRegistUser(HttpServletRequest request, HttpServletResponse response, Model model){
	
		ConfigDto configDto = new ConfigDto();
		configDto.setItemType(location);
		
		for(ConfigDto configDtoItem : configService.selectConfigListAll(configDto)){
			if("PLEDGE_FILE_REG_TYPE".equals(configDtoItem.getItemName())){
				//보안서약서등록유형(0:등록안함,1:가입시,2:영상정보신청시
				model.addAttribute("pledgeFileRegType", configDtoItem.getItemValue());
			}
		}
		
		return "user/userRegist";
	}
	
	@RequestMapping(value="/user/find.do")
	public String viewFindUserInfo(HttpServletRequest request, HttpServletResponse response, Model model){
		
		return "user/userFind";
	}
	
	@RequestMapping(value="/system/user/list.do")
	public String viewUserList(Model model){
		
		return "system/user/userList";
	}
	
	@RequestMapping(value="/system/user/list.json")
	public String searchUserList(UserDto userDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		for(ConfigDto configDto : Config.configList){
			if("PLEDGE_FILE_REG_TYPE".equals(configDto.getItemName())){
				//보안서약서등록유형(0:등록안함,1:가입시,2:영상정보신청시
				model.addAttribute("pledgeFileRegType", configDto.getItemValue());
			}
		}
		
		model.addAttribute("userList", userService.selectUserList(request, userDto));
		
		return "system/user/userListTb";
	}
	
	@RequestMapping(value="/user/modify.json")
	public String updateUserInfo(UserDto userDto, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		
		if (Integer.parseInt(request.getSession().getAttribute("USER_LEVEL").toString()) >= 8) { //괸라자일 경우
			if(userDao.updateUserInfo(userDto) > 0){
				model.addAttribute("result", "SUCCESS");
			}else{
				model.addAttribute("result", "FAIL");
			}
		}else{
			Long no = userService.userInfoChangeReq(request, userDto);
			if (no > 0){
				if("Y".equals(Config.getFnConfig("USER_MODIFY_APPROVE_ADMIN","Value"))){
				}else{
					ChangeReqDto changeReqDto = new ChangeReqDto();
					changeReqDto.setNo(no);
					changeReqDto.setItemId(userDto.getUserId());
					changeReqDto.setTypeDepth1("개인정보수정");
					changeReqDto.setStatus("1001"); //승인
					changeReqService.modifyChangeReqStatus(changeReqDto);
				}
				model.addAttribute("result", "SUCCESS");
			} else {  
				model.addAttribute("result", "FAIL");
			}
		}

		return "jsonView";
	}	
	
	@RequestMapping(value="/user/regist.json")
	public String registUserInfo(UserDto userDto, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		
		userService.insertUserInfo(request, userDto);
		
		model.addAttribute("result", "SUCCESS");
		
		return "jsonView";		
	}	
	
	@RequestMapping(value="/check/idDupli.json")
	public String idDuplChkUser(UserDto userDto, HttpServletRequest request, HttpServletResponse response, Model model){

		if (userService.idDuplChkUser(userDto) > 0){
			model.addAttribute("result", "used");
		} else {
			model.addAttribute("result", "free");
		}
		
		return "jsonView";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 아이디 찾기
	 *  </pre>
	 * 	@Method findIdUser 
	 *  @param userDto
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/sms/auth/findIdUser.json")
	public String findIdUser(UserDto userDto, HttpServletRequest request, HttpServletResponse response, Model model){

		if (userService.selectFindInfo(userDto) == null){
			model.addAttribute("result", "false");
		} else {
			model.addAttribute("result", "success");
			model.addAttribute("userId", userService.selectFindInfo(userDto).getUserId());
		}
		
		return "user/userFindId";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method findPwUser 
	 *  @param userDto
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/sms/auth/findPwUser.json")
	public String findPwUser(UserDto userDto, HttpServletRequest request, HttpServletResponse response, Model model){

		model.addAttribute("userId", request.getParameter("userId").toString());
		
		return "user/userFindPwAuth";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 유저 pw 수정 auth
	 *  </pre>
	 * 	@Method findPwAuthUser 
	 *  @param userDto
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @return
	 **********************************************/
	@RequestMapping(value="/sms/auth/findPwAuthUser.json")
	public String findPwAuthUser(UserDto userDto, HttpServletRequest request, HttpServletResponse response, Model model){
		
		userDto.setUserId(request.getParameter("userId").toString());
		
		if (userService.selectFindInfo(userDto) == null){
			model.addAttribute("result", "false");
		} else {
			model.addAttribute("result", "success");
			model.addAttribute("userId", userService.selectFindInfo(userDto).getUserId());
		}
		
		return "user/userFindPwReset";
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 유저 pw 업데이트
	 *  </pre>
	 * 	@Method findPwReplaceUser 
	 *  @param userDto
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @return
	 * @throws Exception 
	 **********************************************/
	@RequestMapping(value="/sms/auth/findPwReplaceUser.json")
	public String findPwReplaceUser(UserDto userDto, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		
		// 기존 데이터 메모리 상주
		UserDto dto = new UserDto();
		dto.setUserId(request.getParameter("userId").toString());
		dto = userService.selectUserInfo(dto);

		// 변경전 테이블 인서트
		if (userService.userInfoChangeReq(request, userDto) > 0){
			model.addAttribute("result", "SUCCESS");
		} else {
			model.addAttribute("result", "FAIL");
		}
		return "jsonView";
	}
	
	@RequestMapping(value="/user/fileDown.do", method=RequestMethod.GET)
	public void downloadAttachFile(@RequestParam String itemNo, HttpServletResponse response) throws IOException{
		
		FileDto fileDto = userService.selectFileList(itemNo);

		File downFile = new File(userfilePledgePath+File.separator+fileDto.getUserid()+File.separator+fileDto.getTempFilename());
		
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
}
