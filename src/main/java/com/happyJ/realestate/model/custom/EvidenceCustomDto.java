/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 25. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.custom;

import org.springframework.web.multipart.MultipartFile;

import com.happyJ.realestate.model.schema.ItemEvidenceDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model
 *  @fileName : EvidenceCustomDto.java
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
 *     2016. 4. 25.        yongpal       create EvidenceCustomDto.java
 *  </pre>
 ******************************************************************************/
public class EvidenceCustomDto extends ItemEvidenceDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String level;
	private String permitNm;
	private String modLimit;
	private String playCount;
	
	private int evi_item_no;
	
	/* 첨부파일 수정 시 사용  */
	private MultipartFile officialFile;
	private String officialFileNo;
	private String officialFileNm;
	private String officialFileTempNm;
	
	private String searchPermit;
	private String searchWord;


	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the permitNm
	 */
	public String getPermitNm() {
		return permitNm;
	}

	/**
	 * @param permitNm the permitNm to set
	 */
	public void setPermitNm(String permitNm) {
		this.permitNm = permitNm;
	}

	/**
	 * @return the searchPermit
	 */
	public String getSearchPermit() {
		return searchPermit;
	}

	/**
	 * @param searchPermit the searchPermit to set
	 */
	public void setSearchPermit(String searchPermit) {
		this.searchPermit = searchPermit;
	}

	/**
	 * @return the searchWord
	 */
	public String getSearchWord() {
		return searchWord;
	}

	/**
	 * @param searchWord the searchWord to set
	 */
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	/**
	 * @return the officialFile
	 */
	public MultipartFile getOfficialFile() {
		return officialFile;
	}

	/**
	 * @param officialFile the officialFile to set
	 */
	public void setOfficialFile(MultipartFile officialFile) {
		this.officialFile = officialFile;
	}

	/**
	 * @return the officialFileNo
	 */
	public String getOfficialFileNo() {
		return officialFileNo;
	}

	/**
	 * @param officialFileNo the officialFileNo to set
	 */
	public void setOfficialFileNo(String officialFileNo) {
		this.officialFileNo = officialFileNo;
	}

	/**
	 * @return the officialFileNm
	 */
	public String getOfficialFileNm() {
		return officialFileNm;
	}

	/**
	 * @param officialFileNm the officialFileNm to set
	 */
	public void setOfficialFileNm(String officialFileNm) {
		this.officialFileNm = officialFileNm;
	}

	/**
	 * @return the officialFileTempNm
	 */
	public String getOfficialFileTempNm() {
		return officialFileTempNm;
	}

	/**
	 * @param officialFileTempNm the officialFileTempNm to set
	 */
	public void setOfficialFileTempNm(String officialFileTempNm) {
		this.officialFileTempNm = officialFileTempNm;
	}

	/**
	 * @return the evi_item_no
	 */
	public int getEvi_item_no() {
		return evi_item_no;
	}

	/**
	 * @param evi_item_no the evi_item_no to set
	 */
	public void setEvi_item_no(int evi_item_no) {
		this.evi_item_no = evi_item_no;
	}

	/**
	 * @return the modLimit
	 */
	public String getModLimit() {
		return modLimit;
	}

	/**
	 * @param modLimit the modLimit to set
	 */
	public void setModLimit(String modLimit) {
		this.modLimit = modLimit;
	}

	/**
	 * @return the playCount
	 */
	public String getPlayCount() {
		return playCount;
	}

	/**
	 * @param playCount the playCount to set
	 */
	public void setPlayCount(String playCount) {
		this.playCount = playCount;
	}

}
