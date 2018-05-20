/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.common.util.FileUploadUtils;
import com.happyJ.realestate.common.util.MySqlPasswordEncoder;
import com.happyJ.realestate.common.util.StringUtil;
import com.happyJ.realestate.dao.ChangeReqDao;
import com.happyJ.realestate.dao.FileDao;
import com.happyJ.realestate.dao.UserDao;
import com.happyJ.realestate.model.protocol.body.LoginBody;
import com.happyJ.realestate.model.protocol.body.RealPriceBody;
import com.happyJ.realestate.model.schema.ChangeReqContentsDto;
import com.happyJ.realestate.model.schema.ChangeReqDto;
import com.happyJ.realestate.model.schema.CodeDto;
import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.model.schema.FileDto;
import com.happyJ.realestate.model.schema.RealPriceDto;
import com.happyJ.realestate.model.schema.UserDto;
import com.happyJ.realestate.web.common.Config;

/*****************************************************************************
 * 
 * @packageName : com.happyJ.realestate.service
 * @fileName : UserService.java
 * @author : yongpal
 * @since 2016. 4. 25.
 * @version 1.0
 * @see :
 * @revision : 2016. 4. 25.
 * 
 *           <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 25.        yongpal       create UserService.java
 *           </pre>
 ******************************************************************************/
@Service
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Value("#{config['user.file.pledge.path']}")
	private String userfilePledgePath;
	
	@Value("#{config['use.location.code']}")
	private String location;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ChangeReqDao changeReqDao;
	
	@Autowired
	private ConfigService configService;

	@Autowired
	private CommonCodeService codeService;

	@Autowired
	private MySqlPasswordEncoder mySqlPasswordEncoder;

	@Autowired
	private FileDao fileDao;

	/**********************************************
	 * <pre>
	 *  개요 :
	 * </pre>
	 * 
	 * @Method insertUserInfo
	 * @param userDto
	 * @return
	 **********************************************/
	public void insertUserInfo(HttpServletRequest request, UserDto userDto) throws Exception{
		
		userDao.insertUserInfo(userDto);

		HashMap<String, String> fileInfo;

		FileDto fileDto = new FileDto();
		fileDto.setUserid(userDto.getUserId());
		fileDto.setItemType("0");

		ConfigDto configDto = new ConfigDto();
		configDto.setItemType(location);
		
		for (ConfigDto configDtoItem : configService.selectConfigListAll(configDto)) {
			if ("PLEDGE_FILE_REG_TYPE".equals(configDtoItem.getItemName())) {
				if ("2".equals(configDtoItem.getItemValue()) && !userDto.getPledgeFile().isEmpty()) {
					// 저장소에 파일 저장
					fileInfo = FileUploadUtils.uploadFile(userDto.getPledgeFile(), userfilePledgePath + File.separator + userDto.getUserId());

					fileDto.setFileType(FileDto.FILE_TYPE_PLEDGE);
					fileDto.setOrgFilename(fileInfo.get("originalFileName"));
					fileDto.setTempFilename(fileInfo.get("uploadFileName"));
					// ves_file insert
					fileDao.insertFileInfo(fileDto);
				}
			}
		}
	}

	/**********************************************
	 * <pre>
	 *  개요 :
	 * </pre>
	 * 
	 * @Method selectUserInfo
	 * @param userDto
	 * @return
	 **********************************************/
	public UserDto selectUserInfo(UserDto userDto) {
		return userDao.selectUserInfo(userDto);
	}

	/**********************************************
	 * <pre>
	 *  개요 : 유져 ID/PW 찾기
	 * </pre>
	 * 
	 * @Method selectFindInfo
	 * @param userDto
	 * @return
	 **********************************************/
	public UserDto selectFindInfo(UserDto userDto) {
		return userDao.selectFindInfo(userDto);
	}

	/**********************************************
	 * <pre>
	 *  개요 :
	 * </pre>
	 * 
	 * @Method updateUserInfo
	 * @param userDto
	 * @return
	 **********************************************/
	public int updateUserInfo(UserDto userDto) {
		return userDao.updateUserInfo(userDto);
	}

	/**********************************************
	 * <pre>
	 *  개요 :
	 * </pre>
	 * 
	 * @Method idDuplChkUser
	 * @param userDto
	 * @return
	 **********************************************/
	public int idDuplChkUser(UserDto userDto) {
		return userDao.idDuplChkUser(userDto);
	}

	/**********************************************
	 * <pre>
	 *  개요 :
	 * </pre>
	 * 
	 * @Method selectUsersInfo
	 * @param userDto
	 * @return
	 **********************************************/
	public List<UserDto> selectUserList(HttpServletRequest request, UserDto userDto) {

		List<UserDto> userList = userDao.selectUserList(userDto);

		if (!userList.isEmpty()) {
			// 반려 목록 카운트
			int listCnt = userDao.selectUsersCounts(userDto);

			// 페이징 처리
			userList.get(0).setCurrentPage(userDto.getCurrentPage());
			userList.get(0).setRowSize(userDto.getRowSize());
			userList.get(0).setListCnt(listCnt);
			userList.get(0).setTotalPage((int) Math.ceil((double) listCnt / userDto.getRowSize()));
			
			for(ConfigDto configDto : Config.configList){
				
				if("PLEDGE_FILE_REG_TYPE".equals(configDto.getItemName())){
					if ("2".equals(configDto.getItemValue())){
						FileDto fileDto = new FileDto();
						for(int i = 0; i < userList.size(); i++){
							
							fileDto.setUserid(userList.get(i).getUserId());
							List<FileDto> fileList = fileDao.selectFileList(fileDto);
							
							if (!fileList.isEmpty()){
								String[] fileNo = new String[fileList.size()];
								int[] fileType = new int[fileList.size()];
								String[] fileNm = new String[fileList.size()];
								String[] fileTempNm = new String[fileList.size()];
								for(int j = 0; j < fileList.size(); j++){
									fileNo[j] = fileList.get(j).getItemNo();
									fileType[j] = fileList.get(j).getFileType();
									fileNm[j] = fileList.get(j).getOrgFilename();
									fileTempNm[j] = fileList.get(j).getTempFilename();
								}
								userList.get(i).setAttachFileNo(fileNo);
								userList.get(i).setAttachFileType(fileType);
								userList.get(i).setAttachFileNm(fileNm);
								userList.get(i).setAttachFileTempNm(fileTempNm);
							}
						}
					}
				}
			}
		}

		return userList;
	}

	/**********************************************
	 * <pre>
	 *  개요 :
	 * </pre>
	 * 
	 * @Method selectUserInfoForAPI
	 * @param userDto
	 * @return
	 **********************************************/
	public LoginBody selectUserInfoForAPI(UserDto userDto) {

		return userDao.selectUserInfoForAPI(userDto);
	}


	
	
	
	/**********************************************
	 * <pre>
	 *  개요 :
	 * </pre>
	 * 
	 * @Method selectUserInfoForAPI
	 * @param userDto
	 * @return
	 **********************************************/
	public int insertNaverRealPriceForAPI(RealPriceDto realPriceDto) {

		return userDao.insertNaverRealPriceForAPI(realPriceDto);
	}
	
	
	
	
	
	
	/**********************************************
	 * <pre>
	 *  개요 :
	 * </pre>
	 * 
	 * @Method userInfoChangeReq
	 * @param request
	 * @param userDto
	 * @return
	 * @throws Exception
	 **********************************************/
	public long userInfoChangeReq(HttpServletRequest request, UserDto userDto) throws Exception {

		long result = 0;
		long no = 0 ;

		UserDto beforeChangeUserDto = userDao.selectUserInfo(userDto);
		
		List<CodeDto> statusList = codeService.selectCodeList("117", null, "N");

		ChangeReqDto changeReqDto = new ChangeReqDto();
		changeReqDto.setItemId(userDto.getUserId());
		changeReqDto.setReqIp(request.getRemoteAddr());
		if (request.getSession().getAttribute("USER_ID") == null) {
			changeReqDto.setReqUserid(request.getParameter("userId").toString());
		} else {
			changeReqDto.setReqUserid(request.getSession().getAttribute("USER_ID").toString());
		}
		changeReqDto.setReqIp(request.getRemoteAddr());
		changeReqDto.setStatus(statusList.get(0).getCodeKey());

		changeReqDao.insertApplyInfoChangeReq(changeReqDto);
		
		no = changeReqDto.getNo();

		List<CodeDto> typeDepth1List = codeService.selectCodeList("118", null, "N");
		List<CodeDto> typeDepth2List = codeService.selectCodeList("119", null, "N");

		ChangeReqContentsDto changeReqContentsDto = new ChangeReqContentsDto();
		changeReqContentsDto.setNo(no);
		changeReqContentsDto.setTypeDepth1(typeDepth1List.get(1).getCodeVal());

		if (!StringUtil.isEmpty(userDto.getPassword())) {
			changeReqContentsDto.setRequestValue(mySqlPasswordEncoder.encode(userDto.getPassword()));
			changeReqContentsDto.setTypeDepth2(typeDepth2List.get(5).getCodeVal());
			result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
		}
		
		//Position
		if (beforeChangeUserDto.getPosition() != null) {
			if (!beforeChangeUserDto.getPosition().equals(userDto.getPosition())) {
				changeReqContentsDto.setRequestValue(userDto.getPosition());
				changeReqContentsDto.setTypeDepth2(typeDepth2List.get(6).getCodeVal());
				result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
			}
		} else {
			if (userDto.getPosition() != null) {
				changeReqContentsDto.setRequestValue(userDto.getPosition());
				changeReqContentsDto.setTypeDepth2(typeDepth2List.get(6).getCodeVal());
				result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
			}
		}
		
		//depart
		if (beforeChangeUserDto.getDepart() != null) {
			if (!beforeChangeUserDto.getDepart().equals(userDto.getDepart())) {
				changeReqContentsDto.setRequestValue(userDto.getDepart());
				changeReqContentsDto.setTypeDepth2(typeDepth2List.get(7).getCodeVal());
				result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
			}
		} else {
			if (userDto.getDepart() != null) {
				changeReqContentsDto.setRequestValue(userDto.getDepart());
				changeReqContentsDto.setTypeDepth2(typeDepth2List.get(7).getCodeVal());
				result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
			}
		}
			
		// Team
		if (beforeChangeUserDto.getTeam() != null) {
			if (!beforeChangeUserDto.getTeam().equals(userDto.getTeam())) {
				changeReqContentsDto.setRequestValue(userDto.getTeam());
				changeReqContentsDto.setTypeDepth2(typeDepth2List.get(8).getCodeVal());
				result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
			}
		} else {
			if (userDto.getTeam() != null) {
				changeReqContentsDto.setRequestValue(userDto.getTeam());
				changeReqContentsDto.setTypeDepth2(typeDepth2List.get(8).getCodeVal());
				result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
			}
		}
		//userName
		if (beforeChangeUserDto.getUserName() != null) {
			if (!beforeChangeUserDto.getUserName().equals(userDto.getUserName())) {
				changeReqContentsDto.setRequestValue(userDto.getUserName());
				changeReqContentsDto.setTypeDepth2(typeDepth2List.get(9).getCodeVal());
				result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
			}
		} else {
			if (userDto.getUserName() != null) {
				changeReqContentsDto.setRequestValue(userDto.getUserName());
				changeReqContentsDto.setTypeDepth2(typeDepth2List.get(9).getCodeVal());
				result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
			}
		}
		
		//userPhone
		if (beforeChangeUserDto.getPhoneNum() != null) {
			if (!beforeChangeUserDto.getPhoneNum().equals(userDto.getPhoneNum())) {
				changeReqContentsDto.setRequestValue(userDto.getPhoneNum());
				changeReqContentsDto.setTypeDepth2(typeDepth2List.get(10).getCodeVal());
				result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
			}
		} else {
			if (userDto.getPhoneNum() != null) {
				changeReqContentsDto.setRequestValue(userDto.getPhoneNum());
				changeReqContentsDto.setTypeDepth2(typeDepth2List.get(10).getCodeVal());
				result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
			}
		}

		if (result == 0) {
			changeReqDao.deleteChangeReq(changeReqDto);
		}
		
		return no;
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectFileList 
	 *  @param itemNo
	 *  @return
	 **********************************************/
	public FileDto selectFileList(String itemNo) {

		FileDto fileDto = new FileDto();
		fileDto.setItemNo(itemNo);
		
		return fileDao.selectFileList(fileDto).get(0);
	}
}
