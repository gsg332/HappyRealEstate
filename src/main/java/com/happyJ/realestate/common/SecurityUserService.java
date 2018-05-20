/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 2. yongpal
*****************************************************************************/
package com.happyJ.realestate.common;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.dao.IpDao;
import com.happyJ.realestate.dao.MenuDao;
import com.happyJ.realestate.dao.UserDao;
import com.happyJ.realestate.model.schema.IpDto;
import com.happyJ.realestate.model.schema.MenuDto;
import com.happyJ.realestate.model.schema.UserDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.common
 *  @fileName : SecurityUserService.java
 *  @author : yongpal
 *  @since 2016. 4. 2.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 2.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 2.        yongpal       create SecurityUserService.java
 *  </pre>
 ******************************************************************************/
@Service("SecurityUserService")
public class SecurityUserService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("#{config['use.location.code']}")
	private String location;
	
	@Value("#{config['attach.file.path']}")
	private String filePath;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private IpDao ipDao;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectUserInfo 
	 *  @param authUserDto
	 *  @return
	 **********************************************/
	public UserDto selectUserInfo(UserDto authUserDto) {
		return userDao.selectUserInfo(authUserDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method createUserSession 
	 *  @param session
	 *  @param authUserDto
	 **********************************************/
	public void createUserSession(HttpSession session, UserDto authUserDto) {

		session.setAttribute("USER_ID", authUserDto.getUserId());
		session.setAttribute("USER_NAME", authUserDto.getUserName());
		session.setAttribute("USER_LEVEL", authUserDto.getLevel());
		session.setAttribute("USER_POSITION", authUserDto.getPosition());
		session.setAttribute("USER_DEPART", authUserDto.getDepart());
		session.setAttribute("USER_TEAM", authUserDto.getTeam());
		session.setAttribute("USER_PHONE", authUserDto.getPhoneNum());
		session.setAttribute("LAST_LOGIN_TIME", System.currentTimeMillis());
		
		if ("1".equals(authUserDto.getUseLimitDate())){
			session.setAttribute("USER_PASSWD_LIMIT", authUserDto.getPasswordLimitDate());
		}
		
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method createUserMenuSession 
	 *  @param session
	 *  @param authUserDto
	 **********************************************/
	public void createUserMenuSession(HttpSession session, UserDto authUserDto) {

		try {
			MenuDto menuDto = new MenuDto();
			menuDto.setMenuAuth(authUserDto.getLevel().toString());
			
			menuDto.setMenuDepth("1");
			List<MenuDto> menuList = menuDao.selectMenuList(menuDto);
			session.setAttribute("FIRST_MENU_LIST", menuList);
			
			menuDto.setMenuDepth("2");
			List<MenuDto> subMenuList = menuDao.selectMenuList(menuDto);
			session.setAttribute("SECOND_MENU_LIST", subMenuList);
			
		} catch (Exception e) {
			logger.error("User Menu List Session Error!! ----- ");
			
			e.printStackTrace();
			session.setAttribute("FIRST_MENU_LIST", null);
			session.setAttribute("SECOND_MENU_LIST", null);
			
		}
		
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 서버 용량 정보를 세션에 저장한다.
	 *  </pre>
	 * 	@Method createCapacitySession 
	 *  @param session
	 **********************************************/
	public void createCapacitySession(HttpSession session) {
		try {
			//File[] roots = File.listRoots();
			
			File dirFile = new File(filePath);
			/*
			System.out.println(filePath + " 드라이브 TotalSpace : " + dirFile.getTotalSpace());
			System.out.println(filePath + " 드라이브 FreeSpace : " + dirFile.getFreeSpace());
			System.out.println(filePath + " 드라이브 UsableSpace : " + dirFile.getUsableSpace());

			for (int i = 0; i < roots.length; i++) {
		        System.out.println(roots[i]);
		        System.out.println("현재드라이브 = " + roots[i].getAbsolutePath());
		        System.out.println("총 용량 = " + roots[i].getTotalSpace());        
		        System.out.println("남은 공간 = " + roots[i].getFreeSpace());
		        System.out.println("사용 공간 = " + roots[i].getUsableSpace());
		    }
			*/			
		    //리눅스에 설치하기 때문에 index가 0인 디스크의 용량을 세팅
		    //session.setAttribute("USED_CAPACITY", roots[0].getTotalSpace() - roots[0].getFreeSpace());
			//session.setAttribute("TOTAL_CAPACITY", roots[0].getTotalSpace());
			session.setAttribute("USED_CAPACITY", dirFile.getTotalSpace() - dirFile.getFreeSpace());
			session.setAttribute("TOTAL_CAPACITY", dirFile.getTotalSpace());
		} catch (Exception e) {
			logger.error("Capacity Session Error!! ----- ");
			e.printStackTrace();
			session.setAttribute("USED_CAPACITY", null);
			session.setAttribute("TOTAL_CAPACITY", null);
		}
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method checkAllowIp 
	 *  @param remoteAddr
	 * @throws UnknownHostException 
	 **********************************************/
	public boolean checkAllowIp(String remoteAddr) throws UnknownHostException {

		// 허용 IP 리스트 조회
		List<IpDto> ipList = ipDao.selectIpListAll();
		
		if (ipList.size() > 0){
			int intRemoteAddr = ByteBuffer.wrap(InetAddress.getByName(remoteAddr).getAddress()).getInt();
			int intStartIp, intEndIp;
			
			for(IpDto ipDto : ipList){
				intStartIp = ByteBuffer.wrap(InetAddress.getByName(ipDto.getStartIp()).getAddress()).getInt();
				if ("".equals(ipDto.getEndIp()) || ipDto.getEndIp() == null){
					logger.debug(" =====> endIp is Null.");
					if (intRemoteAddr == intStartIp){
						logger.debug("!!!!!! Allow IP Accept !!!!!!");
						return true;
					}
				} else {
					logger.debug(" ====> endIp is "+ipDto.getEndIp());
					intEndIp = ByteBuffer.wrap(InetAddress.getByName(ipDto.getEndIp()).getAddress()).getInt();
					if (intRemoteAddr >= intStartIp && intRemoteAddr <= intEndIp){
						logger.debug("!!!!!! Allow IP Accept !!!!!!");
						return true;
					}
				}
			}
		} else {
			logger.debug("***** Allow IP List is Empty. Allow All Connection. *****");
			return true;
		}
		logger.debug("!!!!!! Permission Denied IP Accept !!!!!!");
		return false;
	}
}
