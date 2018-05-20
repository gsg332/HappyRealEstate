/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 4. yongpal
*****************************************************************************/
package com.happyJ.realestate.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import com.happyJ.realestate.model.schema.UserDto;
import com.happyJ.realestate.service.ConfigService;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.common
 *  @fileName : LoginSuccessHandler.java
 *  @author : yongpal
 *  @since 2016. 4. 4.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 4.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 4.        yongpal       create LoginSuccessHandler.java
 *  </pre>
 ******************************************************************************/
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);
	
	@Value("#{config['use.location.code']}")
	private String location;
	
	@Value("#{config['function.setting.admin']}")
	private String fnSettingAdmin;
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	private SecurityUserService secUserService;
	
	private String defaultUrl = "/main.do";
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private ConfigService configService;
	
	public void setDefaultUrl(String defaultUrl){
		this.defaultUrl = defaultUrl;
	}
	
	public String getDefaultUrl (){
		return this.defaultUrl;
	}
	
	public void setSecurityUserService(SecurityUserService securityUserService){
		this.secUserService = securityUserService;
	}
	
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		// 로그인 성공
		// 1. 세션 초기화
		HttpSession session = request.getSession(false);
		if (session == null){
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		
		// 2. 접속자 IP 체크
		//if (secUserService.checkAllowIp(request.getRemoteAddr())){
			// 3. 사용자 정보 조회 및 세션 생성
			UserDto authUserDto = new UserDto();
			authUserDto.setUserId(authentication.getName());
			authUserDto = secUserService.selectUserInfo(authUserDto);
			
			if(fnSettingAdmin.equals(authUserDto.getUserId())){ //해당 관리자만 기능설정 페이지에 접근할 수 있는 권한을 부여한다.
				List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
	            gas.add(new SimpleGrantedAuthority("INCON_ADMIN"));
	            gas.add(new SimpleGrantedAuthority(authUserDto.getLevel()));
	                       
				SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), gas));
			}
			
			// 사용자 정보 session 등록
			secUserService.createUserSession(session, authUserDto);
			// 로그인 사용자의 메뉴 sesstion 등록
			secUserService.createUserMenuSession(session, authUserDto);
			//디스크 용량정보 session 등록
			secUserService.createCapacitySession(session);
			
			// 4. 필요 시 로그인 History 추가
			
			// 사용자의 기존 요청 URL 이 .json 이라면 main.neo 혹은 설정된 defaultUrl 
			/*
			SavedRequest savedRequest = requestCache.getRequest(request, response);
			if(savedRequest == null){
				redirectStrategy.sendRedirect(request, response, defaultUrl);
			}else{
				String redirectUrl = savedRequest.getRedirectUrl();
				logger.info("redirectUrl : {}", redirectUrl);
				// 
				if(redirectUrl.contains(".json") || redirectUrl.contains("Main")){
					logger.info("json or main command");
					redirectStrategy.sendRedirect(request, response, defaultUrl);
				}else{
					super.onAuthenticationSuccess(request, response, authentication);
				}
			}
			*/
			redirectStrategy.sendRedirect(request, response, defaultUrl);
		//} else {
			//redirectStrategy.sendRedirect(request, response, "/login.do?denied=true");
		//}
		
	}

}
