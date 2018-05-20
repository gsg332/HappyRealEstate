/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.common.util.FileUploadUtils;
import com.happyJ.realestate.dao.EvidenceDao;
import com.happyJ.realestate.dao.FileDao;
import com.happyJ.realestate.model.custom.EvidenceCustomDto;
import com.happyJ.realestate.model.schema.FileDto;
import com.happyJ.realestate.model.schema.ItemEvidenceDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.service
 *  @fileName : EvidenceService.java
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
 *     2016. 4. 25.        yongpal       create EvidenceService.java
 *  </pre>
 ******************************************************************************/
@Service
public class EvidenceService {
	
	private static Logger logger = LoggerFactory.getLogger(EvidenceService.class);
	
	@Value("#{config['attach.file.path']}")
	private String attachfilePath;
	
	@Autowired
	private EvidenceDao evidenceDao;
	
	@Autowired
	private FileDao fileDao;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method getEvidenceList 
	 *  @param evidenceCustomDto
	 *  @return
	 **********************************************/
	public List<ItemEvidenceDto> getEvidenceList(EvidenceCustomDto evidenceCustomDto) {

		List<ItemEvidenceDto> evidenceList = evidenceDao.selectEvidenceList(evidenceCustomDto);
		
		if (!evidenceList.isEmpty()){

			// 신청 목록 카운트
			int listCnt = evidenceDao.selectEvidenceListCount(evidenceCustomDto);

			// 페이징 처리
			evidenceList.get(0).setCurrentPage(evidenceCustomDto.getCurrentPage());
			evidenceList.get(0).setRowSize(evidenceCustomDto.getRowSize());
			evidenceList.get(0).setListCnt(listCnt);
			evidenceList.get(0).setTotalPage((int)Math.ceil((double)listCnt / evidenceCustomDto.getRowSize()));
		}
		
		return evidenceList;	
	}
	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method getEvidenceListExcel 
	 *  @param evidenceCustomDto
	 *  @return
	 **********************************************/
	public List<EvidenceCustomDto> getEvidenceListExcel(EvidenceCustomDto evidenceCustomDto) {

		return evidenceDao.selectEvidenceListExcel(evidenceCustomDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method selectEvidenceInfo 
	 *  @param evidenceCustomDto
	 *  @return
	 **********************************************/
	public EvidenceCustomDto selectEvidenceInfo(EvidenceCustomDto evidenceCustomDto) {

		return evidenceDao.selectEvidenceInfo(evidenceCustomDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateEvidenceInfo 
	 *  @param evidenceDto
	 *  @return
	 **********************************************/
	public int updateEvidenceInfo(ItemEvidenceDto evidenceDto) {

		return evidenceDao.updateEvidenceInfo(evidenceDto);
	}


	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertEvidenceInfo 
	 *  @param evidenceDto
	 *  @return
	 * @throws Exception 
	 **********************************************/
	public int insertEvidenceInfo(EvidenceCustomDto evidenceDto) throws Exception {

		// 증거자료 제출 정보 insert
		evidenceDao.insertEvidenceInfo(evidenceDto);
		
		logger.info("evidenceCustomDto.getEvi_item_no() ====> "+evidenceDto.getEvi_item_no());
		
		// 첨부파일 저장
		FileDto fileDto = new FileDto();
		fileDto.setItemSerial(evidenceDto.getItemSerial());
		fileDto.setItemType("1");
		fileDto.setEviItemNo(Integer.toString(evidenceDto.getEvi_item_no()));
		
		HashMap<String, String> fileInfo;
		
		if (!evidenceDto.getOfficialFile().isEmpty()){
			
			// 저장소에 파일 저장
			fileInfo = FileUploadUtils.uploadFile(evidenceDto.getOfficialFile(), attachfilePath+evidenceDto.getUserId());

			fileDto.setUserid(evidenceDto.getUserId());
			fileDto.setFileType(FileDto.FILE_TYPE_OFFICIAL);
			fileDto.setOrgFilename(fileInfo.get("originalFileName"));
			fileDto.setTempFilename(fileInfo.get("uploadFileName"));
			// ves_file insert
			fileDao.insertFileInfo(fileDto);
		}
		
		return evidenceDto.getEvi_item_no();
	}

}
