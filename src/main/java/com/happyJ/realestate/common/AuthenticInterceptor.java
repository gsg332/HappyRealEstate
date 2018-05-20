package com.happyJ.realestate.common;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.happyJ.realestate.common.message.SmsMessageEnum;
import com.happyJ.realestate.dao.ActionHistoryDao;
import com.happyJ.realestate.model.schema.ActionHistoryDto;
import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.model.schema.MenuDto;
import com.happyJ.realestate.service.SmsSendService;
import com.happyJ.realestate.web.common.Config;

/*******************************************************************************
 *  @packageName : com.happyJ.realestate.common
 *  @fileName : AuthenticInterceptor.java
 *  @content : 로그인 및 메뉴권한 체크 Interceptor
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE            NAME         DESC
 *    -------------   ----------   ---------------------------------------
 *    2015. 11. 1.                 create
 *  </pre>
 ******************************************************************************/

@Service
public class AuthenticInterceptor extends HandlerInterceptorAdapter {
	
	static Logger logger = LoggerFactory.getLogger( AuthenticInterceptor.class );

	@Autowired
	private ActionHistoryDao actionHistoryDao;
	
	@Autowired
	private SmsSendService smsService;
	
	/**
	 * 컨트롤러가 실행되기 전에 실행 false 리턴일 경우 Dispatcher Servlet은 Handler가 직접 요청을 처리 뷰를
	 * 직접 다룬다고 생각하고, 다른 인터셉터나 핸들러를 중단 시킨다. (해당 메뉴 URL 의 권한과 접근한 사용자의 권한을 체크한다.)
	 */
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
		
		// request information print
		printRequestLog(request);
		
		// 메뉴 이동 시 상위 메뉴 ID를 세션에 저장
		HttpSession session = request.getSession();
		if (request.getParameter("parentMenuId") != null){
			session.setAttribute("P_MENU_ID", request.getParameter("parentMenuId"));
		} else {
			session.setAttribute("P_MENU_ID", "");
		}
		
		// 로그아웃 시 History 저장
		if (request.getServletPath().startsWith("/login") && request.getParameter("logout") != null){
			ActionHistoryDto actionHistoryDto = new ActionHistoryDto();
			actionHistoryDto.setAccessIp(request.getRemoteAddr());
			actionHistoryDto.setMenu("로그아웃");
			
			request.getSession().invalidate();
		}else{
			//환경설정의 세션타임 후 세션만료.
			Long lastLoginTime = (Long) session.getAttribute("LAST_LOGIN_TIME");
			if(lastLoginTime != null){
				for(ConfigDto configDto : Config.configList){
					if("SESSION_TIMEOUT".equals(configDto.getItemName())){
						String sessionTimeout = configDto.getItemValue();
						if(StringUtils.isNotEmpty(sessionTimeout) && ((lastLoginTime+(Long.parseLong(sessionTimeout)*60*1000)) < System.currentTimeMillis())){
							session.invalidate();
							//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
						    //if (auth != null){    
						        //new SecurityContextLogoutHandler().logout(request, response, auth);
						        
						        String url = request.getContextPath() + "/login.do?timeover=true";
					            response.sendRedirect(url);
					            return false;
						    //}
						}
						break;
					}
				}
			}
		}
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {
		
		ActionHistoryDto actionHistoryDto = new ActionHistoryDto();
		actionHistoryDto.setAccessIp(request.getRemoteAddr());
		actionHistoryDto.setUserId((String) request.getSession().getAttribute("USER_ID"));
		actionHistoryDto.setResult("성공");
		actionHistoryDto.setDescription("");

		if(request.getServletPath().startsWith("/main")){

			actionHistoryDto.setMenu("로그인");
			// 로그인 시 History 저장
			actionHistoryDao.insertActionHistoryInfo(actionHistoryDto);
			
		} else if (request.getServletPath().startsWith("/user/regist.json")){

			actionHistoryDto.setUserId(request.getParameter("userId"));
			actionHistoryDto.setMemo(request.getParameter("userId"));
			actionHistoryDto.setMenu("사용자관리");
			actionHistoryDto.setAction("등록");
			// 사용자 등록 시 History 저장
			actionHistoryDao.insertActionHistoryInfo(actionHistoryDto);
			
			// 사용자가 직접 가입했을 경우 관리자에게 SMS 전송
			if (request.getSession().getAttribute("USER_LEVEL") == null){
				
				String callback = null;
				for(ConfigDto configDto : Config.configList){
					if("SMS_CALLBACK".equals(configDto.getItemName())){
						callback = configDto.getItemValue();
					}
				}					
				smsService.processSendSms(SmsMessageEnum.NEW_JOIN, request.getParameter("userId"), "",
						request.getParameter("userId"), 1, callback);
			}
			
		} else if (request.getServletPath().startsWith("/user/modify.json")){
			
			actionHistoryDto.setMenu("사용자관리");
			actionHistoryDto.setUserId(request.getParameter("userId"));
			actionHistoryDto.setMemo((String) request.getAttribute("USER_ID"));
			
			if (request.getParameter("expired") != null){
				if ("Y".equals(request.getParameter("expired"))){
					actionHistoryDto.setAction("탈퇴/반려");
					
					// 반려일 경우 만 SMS 전송
					if (request.getParameter("phoneNum") != null){
						
						String callback = null;
						for(ConfigDto configDto : Config.configList){
							if("SMS_CALLBACK".equals(configDto.getItemName())){
								callback = configDto.getItemValue();
							}
						}
						smsService.processSendSms(SmsMessageEnum.REJ_JOIN, request.getParameter("userId"),
								request.getParameter("phoneNum"), request.getParameter("userId"), 0, callback);
					}
				} else {
					actionHistoryDto.setAction("승인");
					
					String callback = null;
					for(ConfigDto configDto : Config.configList){
						if("SMS_CALLBACK".equals(configDto.getItemName())){
							callback = configDto.getItemValue();
						}
					}					
					smsService.processSendSms(SmsMessageEnum.APP_JOIN, request.getParameter("userId"),
							request.getParameter("phoneNum"), request.getParameter("userId"), 0, callback);
				}
			} else {
				actionHistoryDto.setAction("수정");
			}
			// 사용자 승인/반려/탈퇴/수정 시 History 저장
			actionHistoryDao.insertActionHistoryInfo(actionHistoryDto);
			
		} else {
			
			List<MenuDto> menuList = (List<MenuDto>) request.getSession().getAttribute("FIRST_MENU_LIST");
			if (menuList != null){
				for(MenuDto menuDto : menuList){
					if (menuDto.getMenuUrl().equals(request.getServletPath())){
						actionHistoryDto.setMenu(menuDto.getMenuNm());
					}
				}
			}
			
			menuList = (List<MenuDto>) request.getSession().getAttribute("SECOND_MENU_LIST");
			if (menuList != null){
				for(MenuDto menuDto : menuList){
					if (menuDto.getMenuUrl().equals(request.getServletPath())){
						actionHistoryDto.setMenu(menuDto.getMenuNm());
					}
				}
			}
			
			if (actionHistoryDto.getMenu() != null){

				actionHistoryDto.setAction("접근");
				// 메뉴 이동 시 History 저장
				actionHistoryDao.insertActionHistoryInfo(actionHistoryDto);
				
			}
		}
		
		super.postHandle( request, response, handler, modelAndView );
		
	}

	@Override
	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) throws Exception {
		
		super.afterCompletion( request, response, handler, ex );
		
	}
	
	/**
	 * 모든 요청에 대한 기본 log 출력
	 * 
	 * @param request
	 */
	private void printRequestLog( HttpServletRequest request ) {
		
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("[REQ]");
			sb.append("_IP_[" + request.getRemoteAddr() + "]");
			sb.append("_REQURL_[" + request.getRequestURI() + "]");
			sb.append("_PARAM_[");

			// parameter
			Enumeration<?> eNames = request.getParameterNames();
			while (eNames.hasMoreElements()) {
				String name = (String) eNames.nextElement();
				String[] values = request.getParameterValues(name);
				String paramIngo = "[" + name + " : ";
				for (int x = 0; x < values.length; x++) {
					if (x == 0) {
						paramIngo += values[x];
					} else {
						paramIngo += ", " + values[x];
					}
				}

				if (!StringUtils.isEmpty((name))) {
					if (name.equals("passWd")) {
						paramIngo = "[password : xxxx ]";
					} else {
						paramIngo += "]";
						
					}
				}

				if (eNames.hasMoreElements()) {
					sb.append(paramIngo + ",");
				} else {
					sb.append(paramIngo);
				}
			}
			sb.append("]");
			logger.info(sb.toString());
		} catch (Exception e) {
			logger.error("{}", e);
		}
		
	}
}
