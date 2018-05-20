package com.happyJ.realestate.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.common.util.FileUploadUtils;
import com.happyJ.realestate.common.util.StringUtil;
import com.happyJ.realestate.dao.ApplyDao;
import com.happyJ.realestate.dao.ChangeReqDao;
import com.happyJ.realestate.dao.FileDao;
import com.happyJ.realestate.dao.UserDao;
import com.happyJ.realestate.model.custom.ApplyCustomDto;
import com.happyJ.realestate.model.schema.ChangeReqContentsDto;
import com.happyJ.realestate.model.schema.ChangeReqDto;
import com.happyJ.realestate.model.schema.CodeDto;
import com.happyJ.realestate.model.schema.FileDto;
import com.happyJ.realestate.model.schema.UserDto;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Service
public class ChangeReqService {

	private static Logger logger = LoggerFactory.getLogger(ChangeReqService.class);

	@Autowired
	private ChangeReqDao changeReqDao;

	@Autowired
	private ApplyDao applyDao;

	@Autowired
	private ApplyService applyService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;

	@Autowired
	private CommonCodeService codeService;

	@Autowired
	private FileDao fileDao;

	@Value("#{config['attach.file.path']}")
	private String attachfilePath;

	/**
	 * 자신의 변경요청한 목록을 가져온다. 
	 * @param request
	 * @param changeReqDto
	 * @return
	 */
	public List<ChangeReqDto> selectChangeReqList(HttpServletRequest request, ChangeReqDto changeReqDto) {

		changeReqDto.setReqUserid(request.getSession().getAttribute("USER_ID").toString());

		List<ChangeReqDto> chageReqList = changeReqDao.selectChangeReqList(changeReqDto);

		if (!chageReqList.isEmpty()) {
			// 반려 목록 카운트
			int listCnt = changeReqDao.selectChangeReqListCounts(changeReqDto);

			// 페이징 처리
			chageReqList.get(0).setCurrentPage(changeReqDto.getCurrentPage());
			chageReqList.get(0).setRowSize(changeReqDto.getRowSize());
			chageReqList.get(0).setListCnt(listCnt);
			chageReqList.get(0).setTotalPage((int) Math.ceil((double) listCnt / changeReqDto.getRowSize()));
		}

		return chageReqList;
	}

	/**
	 * 모든 사용자의 변경요청한 목록을 가지고 온다.
	 * @param changeReqDto
	 * @return
	 */
	public List<ChangeReqDto> selectAdminChangeReqList(ChangeReqDto changeReqDto) {

		List<ChangeReqDto> chageReqList = changeReqDao.selectAdminChangeReqList(changeReqDto);

		if (!chageReqList.isEmpty()) {
			// 반려 목록 카운트
			int listCnt = changeReqDao.selectAdminChangeReqListCounts(changeReqDto);

			// 페이징 처리
			chageReqList.get(0).setCurrentPage(changeReqDto.getCurrentPage());
			chageReqList.get(0).setRowSize(changeReqDto.getRowSize());
			chageReqList.get(0).setListCnt(listCnt);
			chageReqList.get(0).setTotalPage((int) Math.ceil((double) listCnt / changeReqDto.getRowSize()));
		}

		return chageReqList;
	}

	/**
	 * 변경요청을 취소한다.
	 * @param changeReqDto
	 * @return
	 */
	public int cancel(HttpServletRequest request, ChangeReqDto changeReqDto) {

		String userId = request.getSession().getAttribute("USER_ID").toString();
		
		// 변경요청 파일 삭제
		if (!StringUtil.isEmpty(changeReqDto.getOfficialFileTempNm())) {
			FileUploadUtils.deleteFile(attachfilePath + userId, changeReqDto.getOfficialFileTempNm());
		}
		if (!StringUtil.isEmpty(changeReqDto.getPledgeFileTempNm())) {
			FileUploadUtils.deleteFile(attachfilePath + userId, changeReqDto.getPledgeFileTempNm());
		}

		return changeReqDao.deleteChangeReq(changeReqDto);
	}

	/**
	 * 변경요청에 대한 승인 또는 미승인을 수행한다.
	 * @param changeReqDto
	 * @return
	 * @throws Exception
	 */
	public String modifyChangeReqStatus(ChangeReqDto changeReqDto) throws Exception {

		List<CodeDto> statusList = codeService.selectCodeList("117", null, "N");
		List<CodeDto> typeDepth1List = codeService.selectCodeList("118", null, "N");
		List<CodeDto> typeDepth2List = codeService.selectCodeList("119", null, "N");

		if (typeDepth1List.get(0).getCodeVal().equals(changeReqDto.getTypeDepth1())) { // 신청정보수정

			ApplyCustomDto applyDto = new ApplyCustomDto();
			applyDto.setItemSerial(changeReqDto.getItemId());
			applyDto = applyDao.selectApplyInfo(applyDto);
			
			changeReqDto.setReqUserid(applyDto.getUserId());
			
			if (changeReqDto.getStatus().equals(statusList.get(1).getCodeKey())) { // 승인

				changeReqDto.setApprovalDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

				FileDto fileDto = new FileDto();
				fileDto.setItemSerial(applyDto.getItemSerial());
				fileDto.setItemType("0");
				List<FileDto> fileList = fileDao.selectFileList(fileDto);

				if (!fileList.isEmpty()) {
					String[] fileNo = new String[fileList.size()];
					int[] fileType = new int[fileList.size()];
					String[] fileNm = new String[fileList.size()];
					String[] fileTempNm = new String[fileList.size()];
					for (int j = 0; j < fileList.size(); j++) {
						fileNo[j] = fileList.get(j).getItemNo();
						fileType[j] = fileList.get(j).getFileType();
						fileNm[j] = fileList.get(j).getOrgFilename();
						fileTempNm[j] = fileList.get(j).getTempFilename();
					}

					applyDto.setAttachFileNo(fileNo);
					applyDto.setAttachFileType(fileType);
					applyDto.setAttachFileNm(fileNm);
					applyDto.setAttachFileTempNm(fileTempNm);
				}

				List<ChangeReqContentsDto> changeReqContentsDtoList = changeReqDao.selectChangeReqContents(changeReqDto);

				for (ChangeReqContentsDto changeReqContents : changeReqContentsDtoList) {
					JSONObject json;

					String typeDepth2 = changeReqContents.getTypeDepth2();

					if (typeDepth2.equals(typeDepth2List.get(0).getCodeVal())) {
						applyDto.setReqReason(changeReqContents.getRequestValue());
					} else if (typeDepth2.equals(typeDepth2List.get(1).getCodeVal())) {
						applyDto.setVeiDocNo(changeReqContents.getRequestValue());
					} else if (typeDepth2.equals(typeDepth2List.get(2).getCodeVal())) {
						// 기존 파일 삭제
						if (applyDto.getAttachFileTempNm() != null && applyDto.getAttachFileTempNm().length == 2 && !StringUtil.isEmpty(applyDto.getAttachFileTempNm()[0])) {
							FileUploadUtils.deleteFile(attachfilePath  + changeReqDto.getReqUserid(), applyDto.getAttachFileTempNm()[0]);
							fileDto.setItemNo(applyDto.getAttachFileNo()[0]);
						}if (applyDto.getAttachFileTempNm() != null && applyDto.getAttachFileTempNm().length == 1 && !StringUtil.isEmpty(applyDto.getAttachFileTempNm()[0])) {
							FileUploadUtils.deleteFile(attachfilePath + changeReqDto.getReqUserid(), applyDto.getAttachFileTempNm()[0]);
							fileDto.setItemNo(applyDto.getAttachFileNo()[0]);
						}else{
							logger.debug("Not Exist Official File !!!");
						}

						json = JSONObject.fromObject(JSONSerializer.toJSON(changeReqContents.getRequestValue().replaceAll("\\\\", "")));
						JSONObject officialFileJson = json.getJSONObject("officialFile");
						
						fileDto.setFileType(FileDto.FILE_TYPE_OFFICIAL);
						fileDto.setOrgFilename(officialFileJson.get("orgFilename").toString());
						fileDto.setTempFilename(officialFileJson.get("tempFilename").toString());

						//fileDao.updateFileInfo(fileDto);
						fileDao.insertFileInfo(fileDto);
					} else if (typeDepth2.equals(typeDepth2List.get(3).getCodeVal())) {
						// 기존 파일 삭제
						if (applyDto.getAttachFileTempNm() != null && applyDto.getAttachFileTempNm().length == 2 && !StringUtil.isEmpty(applyDto.getAttachFileTempNm()[1])) {
							FileUploadUtils.deleteFile(attachfilePath + changeReqDto.getReqUserid(), applyDto.getAttachFileTempNm()[1]);
							fileDto.setItemNo(applyDto.getAttachFileNo()[1]);
						}if (applyDto.getAttachFileTempNm() != null && applyDto.getAttachFileTempNm().length == 1 && !StringUtil.isEmpty(applyDto.getAttachFileTempNm()[0])) {
							FileUploadUtils.deleteFile(attachfilePath + changeReqDto.getReqUserid(), applyDto.getAttachFileTempNm()[0]);
							fileDto.setItemNo(applyDto.getAttachFileNo()[0]);
						}else{
							logger.debug("Not Exist Pledge File !!!");
						}

						json = JSONObject.fromObject(JSONSerializer.toJSON(changeReqContents.getRequestValue().replaceAll("\\\\", "")));
						JSONObject pledgeFileJson = json.getJSONObject("pledgeFile");

						fileDto.setFileType(FileDto.FILE_TYPE_PLEDGE);
						fileDto.setOrgFilename(pledgeFileJson.get("orgFilename").toString());
						fileDto.setTempFilename(pledgeFileJson.get("tempFilename").toString());

						//fileDao.updateFileInfo(fileDto);
						fileDao.insertFileInfo(fileDto);
					} else if (typeDepth2.equals(typeDepth2List.get(4).getCodeVal())) {
						String[] codes = changeReqContents.getRequestValue().split(">");

						applyDto.setReqReasonCode(codes[0]);
						if (codes.length > 1) {
							applyDto.setReqReasoncodeReason(codes[1]);
						}
					}

				}

				return applyService.updateApplyInfo(applyDto) > 0 && changeReqDao.updateChangeReq(changeReqDto) > 0 ? "SUCCESS" : "FAIL";
			} else if (changeReqDto.getStatus().equals(statusList.get(2).getCodeKey())) { // 미승인
				// 변경요청 파일 삭제
				if (!StringUtil.isEmpty(changeReqDto.getOfficialFileTempNm())) {
					FileUploadUtils.deleteFile(attachfilePath + changeReqDto.getReqUserid(), changeReqDto.getOfficialFileTempNm());
				}
				if (!StringUtil.isEmpty(changeReqDto.getPledgeFileTempNm())) {
					FileUploadUtils.deleteFile(attachfilePath + changeReqDto.getReqUserid(), changeReqDto.getPledgeFileTempNm());
				}

				return changeReqDao.updateChangeReq(changeReqDto) > 0 ? "SUCCESS" : "FAIL";
			}
		} else { // 개인정보수정
			if (changeReqDto.getStatus().equals(statusList.get(1).getCodeKey())) { // 승인

				UserDto userDto = new UserDto();
				userDto.setUserId(changeReqDto.getItemId());
				userDto = userDao.selectUserInfo(userDto);

				changeReqDto.setApprovalDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
				
				List<ChangeReqContentsDto> changeReqContentsDtoList = changeReqDao.selectChangeReqContents(changeReqDto);

				for (ChangeReqContentsDto changeReqContents : changeReqContentsDtoList) {
					String typeDepth2 = changeReqContents.getTypeDepth2();

					if (typeDepth2.equals(typeDepth2List.get(5).getCodeVal())) {
						//패스워드 암호화여부
						userDto.setChgPasswordFlag("true");
						userDto.setPassword(changeReqContents.getRequestValue());
					} else if (typeDepth2.equals(typeDepth2List.get(6).getCodeVal())) {
						userDto.setPosition(changeReqContents.getRequestValue());
					} else if (typeDepth2.equals(typeDepth2List.get(7).getCodeVal())) {
						userDto.setDepart(changeReqContents.getRequestValue());
					} else if (typeDepth2.equals(typeDepth2List.get(8).getCodeVal())) {
						userDto.setTeam(changeReqContents.getRequestValue());
					} else if (typeDepth2.equals(typeDepth2List.get(9).getCodeVal())) {
						userDto.setUserName(changeReqContents.getRequestValue());
					} else if (typeDepth2.equals(typeDepth2List.get(10).getCodeVal())) {
						userDto.setPhoneNum(changeReqContents.getRequestValue());
					}
				}
				
				// 패스워드 수정이 아니면 초기화
				if (userDto.getChgPasswordFlag() == null) {
					userDto.setPassword(null);
				}
				
				return userService.updateUserInfo(userDto) > 0 && changeReqDao.updateChangeReq(changeReqDto) > 0 ? "SUCCESS" : "FAIL";
			} else if (changeReqDto.getStatus().equals(statusList.get(2).getCodeKey())) { // 미승인
				return changeReqDao.updateChangeReq(changeReqDto) > 0 ? "SUCCESS" : "FAIL";
			}
		}

		return "FAIL";
	}

	/**
	 * 자신의 변경요청한 목록을 가져온다.(엑셀파일용)
	 * @param changeReqDto
	 * @return
	 */
	public List<ChangeReqDto> selectChangeReqListExcel(ChangeReqDto changeReqDto) {
		return changeReqDao.selectChangeReqListExcel(changeReqDto);
	}

	/**
	 * 모든 사용자의 변경요청한 목록을 가지고 온다.(엑셀파일용)
	 * @param changeReqDto
	 * @return
	 */
	public List<ChangeReqDto> selectAdminChangeReqListExcel(ChangeReqDto changeReqDto) {
		return changeReqDao.selectAdminChangeReqListExcel(changeReqDto);
	}

}
