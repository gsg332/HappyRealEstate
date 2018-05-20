/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.common.util.FileUploadUtils;
import com.happyJ.realestate.dao.ApplyDao;
import com.happyJ.realestate.dao.ChangeReqDao;
import com.happyJ.realestate.dao.FileDao;
import com.happyJ.realestate.dao.PopupHistoryDao;
import com.happyJ.realestate.model.custom.ApplyCustomDto;
import com.happyJ.realestate.model.protocol.body.ItemList;
import com.happyJ.realestate.model.schema.ChangeReqContentsDto;
import com.happyJ.realestate.model.schema.ChangeReqDto;
import com.happyJ.realestate.model.schema.CodeDto;
import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.model.schema.FileDto;
import com.happyJ.realestate.model.schema.PopupHIstoryDto;
import com.happyJ.realestate.web.common.Config;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.service
 *  @fileName : ApplyService.java
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
 *     2016. 4. 11.        yongpal       create ApplyService.java
 *  </pre>
 ******************************************************************************/
@Service
public class ApplyService {
	
	private static Logger logger = LoggerFactory.getLogger(ApplyService.class);
	
	@Value("#{config['attach.file.path']}")
	private String attachfilePath;
	
	@Value("#{message['pop.history.apply']}")
	private String popApplyStr;
	
	@Autowired
	private ApplyDao applyDao;
	
	@Autowired
	private ChangeReqDao changeReqDao;
	
	@Autowired
	private PopupHistoryDao popDao;
	
	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private CommonCodeService codeService;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 신청 리스트 조회
	 *  </pre>
	 * 	@Method selectApplyList 
	 *  @param applyDto
	 * @return 
	 * @throws ParseException 
	 **********************************************/
	public List<ApplyCustomDto> selectApplyList(ApplyCustomDto applyDto) throws ParseException {
		
		// 신청 목록 조회
		applyDto.setStartRow(10 * (applyDto.getCurrentPage()-1));
		List<ApplyCustomDto> applyList = applyDao.selectApplyList(applyDto);

		if (!applyList.isEmpty()){

			// 신청 목록 카운트
			int listCnt = applyDao.selectApplyListCount(applyDto);

			// 페이징 처리
			applyList.get(0).setCurrentPage(applyDto.getCurrentPage());
			applyList.get(0).setRowSize(applyDto.getRowSize());
			applyList.get(0).setListCnt(listCnt);
			applyList.get(0).setTotalPage((int)Math.ceil((double)listCnt / applyDto.getRowSize()));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar stCal = Calendar.getInstance();
			Calendar edCal = Calendar.getInstance();
			stCal.setTime(new Date());
			
			FileDto fileDto = new FileDto();
			for(int i = 0; i < applyList.size(); i++){
				
				// 신청 시 업로드한 파일 리스트 조회
				fileDto.setItemSerial(applyList.get(i).getItemSerial());
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
					applyList.get(i).setAttachFileNo(fileNo);
					applyDto.setAttachFileType(fileType);
					applyList.get(i).setAttachFileNm(fileNm);
					applyList.get(i).setAttachFileTempNm(fileTempNm);
				}
				
				edCal.setTime(sdf.parse(applyList.get(i).getVeiLimitDatetime()));
				// 현재 날짜와 재생 만료일의 차이 계산
				int limitDays = (int)((edCal.getTimeInMillis() - stCal.getTimeInMillis()) / (24*60*60*1000));
				// 연장/재신청  활성화 여부 처리
				if (limitDays >= 0 && "0".equals(applyList.get(i).getExtentionApply())){
					// 연장신청
					applyList.get(i).setExtendFlag(true);
				} else {
					if ("0".equals(applyList.get(i).getExtentionApply())){
						// 재신청
						applyList.get(i).setExtendFlag(false);
					}
				}
			}
		}
		return applyList;
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyDto 
	 * 	@Method selectTotalCounts 
	 **********************************************/
	public ApplyCustomDto selectTotalCounts(ApplyCustomDto applyDto) {

		return applyDao.selectTotalCounts(applyDto);
	}

	static private ApplyCustomDto beforeChangeApplyCustomDto;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectApplyInfo 
	 *  @param applyDto
	 *  @return
	 **********************************************/
	public ApplyCustomDto selectApplyInfo(ApplyCustomDto applyDto) {
		
		applyDto = applyDao.selectApplyInfo(applyDto);
		
		beforeChangeApplyCustomDto = applyDto;
		
		if (applyDto != null) {
			// 신청 시 업로드한 파일 리스트 조회
			FileDto fileDto = new FileDto();
			fileDto.setItemSerial(applyDto.getItemSerial());
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
				applyDto.setAttachFileNo(fileNo);
				applyDto.setAttachFileType(fileType);
				applyDto.setAttachFileNm(fileNm);
				applyDto.setAttachFileTempNm(fileTempNm);
			}
		}
		
		return applyDto;
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectApplyListExcel 
	 *  @param applyDto
	 *  @return
	 **********************************************/
	public List<ApplyCustomDto> selectApplyListExcel(ApplyCustomDto applyDto) {

		return applyDao.selectApplyListExcel(applyDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method registApply 
	 *  @param applyDto
	 * @throws Exception 
	 **********************************************/
	public int registApplyInfo(HttpServletRequest request, ApplyCustomDto applyDto) throws Exception {
		
		applyDto.setReqPosition(applyDto.getAddr1() + " " + applyDto.getAddr2() + " "
				+ applyDto.getAddr3() + " " + applyDto.getAddr4());
		// 암호화 키 생성
		applyDto.setVeiCryptKey(UUID.randomUUID().toString().replace("-", ""));
		
		// 신청 정보 insert
		applyDao.insertApplyInfo(applyDto);

		PopupHIstoryDto popDto = new PopupHIstoryDto();
		popDto.setItemSerial(Integer.toString(applyDto.getItem_serial()));
		popDto.setSubject("신청접수");
		popDto.setContents(popApplyStr);
		if (applyDto.getVeiReqType() == "0"){
			popDto.setPopupType("0");	// 반출
		} else {
			popDto.setPopupType("3");	// 열람
		}
		// popup history insert
		popDao.insertPopupHistory(popDto);
		
		FileDto fileDto = new FileDto();
		fileDto.setItemSerial(Integer.toString(applyDto.getItem_serial()));
		fileDto.setItemType("0");
		
		HashMap<String, String> fileInfo;
		
		for(ConfigDto configDto : Config.configList){
			if("OFFICIAL_FILE_REG_TYPE".equals(configDto.getItemName())){
				if ("1".equals(configDto.getItemValue()) && !applyDto.getOfficialFile().isEmpty()){
					// 저장소에 파일 저장
					fileInfo = FileUploadUtils.uploadFile(applyDto.getOfficialFile(), attachfilePath+applyDto.getUserId());

					fileDto.setUserid(applyDto.getUserId());
					fileDto.setFileType(FileDto.FILE_TYPE_OFFICIAL);
					fileDto.setOrgFilename(fileInfo.get("originalFileName"));
					fileDto.setTempFilename(fileInfo.get("uploadFileName"));
					// ves_file insert
					fileDao.insertFileInfo(fileDto);
				}
			}
			
			if("PLEDGE_FILE_REG_TYPE".equals(configDto.getItemName())){
				if ("1".equals(configDto.getItemValue()) && !applyDto.getPledgeFile().isEmpty()){
					// 저장소에 파일 저장
					fileInfo = FileUploadUtils.uploadFile(applyDto.getPledgeFile(), attachfilePath+applyDto.getUserId());
	
					fileDto.setUserid(applyDto.getUserId());
					fileDto.setFileType(FileDto.FILE_TYPE_PLEDGE);
					fileDto.setOrgFilename(fileInfo.get("originalFileName"));
					fileDto.setTempFilename(fileInfo.get("uploadFileName"));
					// ves_file insert
					fileDao.insertFileInfo(fileDto);
				}
			}
		}
		
		return applyDto.getItem_serial();
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectRejectList 
	 *  @param applyDto
	 *  @return
	 **********************************************/
	public List<ApplyCustomDto> selectRejectList(ApplyCustomDto applyDto) {
		
		List<ApplyCustomDto> rejectList = applyDao.selectRejectList(applyDto);
		
		if (!rejectList.isEmpty()){
			// 반려 목록 카운트
			int listCnt = applyDao.selectRejectListCounts(applyDto);

			// 페이징 처리
			rejectList.get(0).setCurrentPage(applyDto.getCurrentPage());
			rejectList.get(0).setRowSize(applyDto.getRowSize());
			rejectList.get(0).setListCnt(listCnt);
			rejectList.get(0).setTotalPage((int)Math.ceil((double)listCnt / applyDto.getRowSize()));
		}
		
		return rejectList;
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectRejectListExcel 
	 *  @param applyDto
	 *  @return List<ApplyCustomDto>
	 **********************************************/
	public List<ApplyCustomDto> selectRejectListExcel(ApplyCustomDto applyDto) {

		return applyDao.selectRejectListExcel(applyDto);
	}

	/**
	 * 영상신청정보에 대하여 변경요청을 한다. 
	 * @param request
	 * @param applyDto
	 * @return
	 * @throws Exception
	 */
	public int applyInfoChangeReq(HttpServletRequest request, ApplyCustomDto applyDto) throws Exception {

		List<CodeDto> statusList = codeService.selectCodeList("117", null, "N");
		
		ChangeReqDto changeReqDto = new ChangeReqDto();
		changeReqDto.setItemId(applyDto.getItemSerial());
		changeReqDto.setReqIp(request.getRemoteAddr());
		changeReqDto.setReqUserid(request.getSession().getAttribute("USER_ID").toString());
		changeReqDto.setReqIp(request.getRemoteAddr());
		changeReqDto.setStatus(statusList.get(0).getCodeKey());
		
		changeReqDao.insertApplyInfoChangeReq(changeReqDto);
		
		List<CodeDto> typeDepth1List = codeService.selectCodeList("118", null, "N");
		List<CodeDto> typeDepth2List = codeService.selectCodeList("119", null, "N");
		
		ChangeReqContentsDto changeReqContentsDto = new ChangeReqContentsDto();
		changeReqContentsDto.setNo(changeReqDto.getNo());
		changeReqContentsDto.setTypeDepth1(typeDepth1List.get(0).getCodeVal());
		
		int result = 0;
		
		if(!beforeChangeApplyCustomDto.getReqReason().equals(applyDto.getReqReason())){
			changeReqContentsDto.setRequestValue(applyDto.getReqReason());
			changeReqContentsDto.setTypeDepth2(typeDepth2List.get(0).getCodeVal());
			result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
		}
		if(!beforeChangeApplyCustomDto.getVeiDocNo().equals(applyDto.getVeiDocNo())){
			changeReqContentsDto.setRequestValue(applyDto.getVeiDocNo());
			changeReqContentsDto.setTypeDepth2(typeDepth2List.get(1).getCodeVal());
			result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
		}
		if(applyDto.getOfficialFile() != null){
			HashMap<String, String> fileInfo = FileUploadUtils.uploadFile(applyDto.getOfficialFile(), attachfilePath+applyDto.getUserId());

			changeReqContentsDto.setRequestValue("{\\'officialFile\\':{\\'itemSerial\\':\\'" +applyDto.getItemSerial() + "\\',\\'itemType\\':0" + ",\\'itemNo\\':\\'" + applyDto.getOfficialFileNo() + "\\',\\'orgFilename\\':\\'" + fileInfo.get("originalFileName") + "\\',\\'tempFilename\\':\\'" + fileInfo.get("uploadFileName") + "\\'}}");
			changeReqContentsDto.setTypeDepth2(typeDepth2List.get(2).getCodeVal());
			result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
		}
		if (applyDto.getPledgeFile() != null){
			HashMap<String, String> fileInfo = FileUploadUtils.uploadFile(applyDto.getPledgeFile(), attachfilePath+applyDto.getUserId());

			changeReqContentsDto.setRequestValue("{\\'pledgeFile\\':{\\'itemSerial\\':\\'" +applyDto.getItemSerial() + "\\',\\'itemType\\':0" + ",itemNo:\\'" + applyDto.getPledgeFileNo() + "\\',\\'orgFilename\\':\\'" + fileInfo.get("originalFileName") + "\\',\\'tempFilename\\':\\'" + fileInfo.get("uploadFileName") + "\\'}}");
			changeReqContentsDto.setTypeDepth2(typeDepth2List.get(3).getCodeVal());
			result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
		}
		if(!beforeChangeApplyCustomDto.getReqReasonCode().equals(applyDto.getReqReasonCode()) || !beforeChangeApplyCustomDto.getReqReasoncodeReason().equals(applyDto.getReqReasoncodeReason())){
			
			String requestValue;
			
			if(applyDto.getReqReasonCode().equals("99999")){
				requestValue = applyDto.getReqReasonCode() + ">" + applyDto.getReqReasoncodeReason();
			}else{
				requestValue = applyDto.getReqReasonCode();
			}
			
			changeReqContentsDto.setRequestValue(requestValue);
			changeReqContentsDto.setTypeDepth2(typeDepth2List.get(4).getCodeVal());
			result = changeReqDao.insertChangeReqContents(changeReqContentsDto);
		}
		if(result == 0){
			changeReqDao.deleteChangeReq(changeReqDto);
		}
		
		return result;
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateApplyInfo 
	 *  @param applyCustomDto
	 *  @return
	 * @throws Exception 
	 **********************************************/
	public int updateApplyInfo(ApplyCustomDto applyDto) throws Exception {

		ApplyCustomDto applyCustomDto = applyDao.selectApplyInfo(applyDto);
		
		FileDto fileDto = new FileDto();
		fileDto.setItemSerial(applyDto.getItemSerial());
		fileDto.setItemType("0");
		
		applyDto.setUserId(applyCustomDto.getUserId());
		
		if (applyDto.getOfficialFile() != null){
			
			// 기존 파일 삭제
			FileUploadUtils.deleteFile(attachfilePath+applyDto.getUserId(), applyDto.getOfficialFileTempNm());
			
			// 저장소에 파일 저장
			HashMap<String, String> fileInfo = FileUploadUtils.uploadFile(applyDto.getOfficialFile(), attachfilePath+applyDto.getUserId());

			fileDto.setUserid(applyDto.getUserId());
			fileDto.setItemNo(applyDto.getOfficialFileNo());
			fileDto.setFileType(fileDto.FILE_TYPE_OFFICIAL);
			fileDto.setOrgFilename(fileInfo.get("originalFileName"));
			fileDto.setTempFilename(fileInfo.get("uploadFileName"));
			
			fileDao.insertFileInfo(fileDto);
		}
		
		if (applyDto.getPledgeFile() != null){
			
			// 기존 파일 삭제
			FileUploadUtils.deleteFile(attachfilePath+applyDto.getUserId(), applyDto.getPledgeFileTempNm());
			
			// 저장소에 파일 저장
			HashMap<String, String> fileInfo = FileUploadUtils.uploadFile(applyDto.getPledgeFile(), attachfilePath+applyDto.getUserId());

			fileDto.setUserid(applyDto.getUserId());
			fileDto.setItemNo(applyDto.getPledgeFileNo());
			fileDto.setFileType(fileDto.FILE_TYPE_PLEDGE);
			fileDto.setOrgFilename(fileInfo.get("originalFileName"));
			fileDto.setTempFilename(fileInfo.get("uploadFileName"));

			fileDao.insertFileInfo(fileDto);
		}
		
		return applyDao.updateApplyInfo(applyDto);
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
	
	public List<ApplyCustomDto> selectCrimeMarkerList(ApplyCustomDto applyDto){
		
		return applyDao.selectMarkerList(applyDto);
	}
		
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method joinHistoryList 
	 *  @param actionHistoryDto
	 *  @return
	 **********************************************/
	public List<ApplyCustomDto> selectResultList(ApplyCustomDto applyDto) {
		
		List<ApplyCustomDto> resultList = applyDao.selectResultList(applyDto);
		
		if (!resultList.isEmpty()){
			// 반려 목록 카운트
			int listCnt = applyDao.selectResultListCounts(applyDto);

			// 페이징 처리
			resultList.get(0).setCurrentPage(applyDto.getCurrentPage());
			resultList.get(0).setRowSize(applyDto.getRowSize());
			resultList.get(0).setListCnt(listCnt);
			resultList.get(0).setTotalPage((int)Math.ceil((double)listCnt / applyDto.getRowSize()));
		}
		
		return resultList;
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method joinHistoryListExcel 
	 *  @param actionHistoryDto
	 *  @return List<ActionHistoryDto>
	 **********************************************/
	public List<ApplyCustomDto> selectResultListExcel(ApplyCustomDto applyDto) {

		return applyDao.selectResultListExcel(applyDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectApplyListForVMS 
	 *  @param itemDto
	 *  @return
	 **********************************************/
	public List<ItemList> selectApplyListForAPI(ApplyCustomDto applyDto) {
		
		return applyDao.selectApplyListForAPI(applyDto);
	}	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectUserIdForFile 
	 *  @param itemDto
	 *  @return
	 **********************************************/
	public ApplyCustomDto selectUserIdForFile(String itemNo) {
		
		return applyDao.selectUserIdForFile(itemNo);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectDownloadLimitCount 
	 *  @param itemNo
	 *  @return
	 **********************************************/
	public Integer selectDownloadLimitCount(ApplyCustomDto applyDto) {
		
		return applyDao.selectDownloadLimitCount(applyDto);
	}
	
}
