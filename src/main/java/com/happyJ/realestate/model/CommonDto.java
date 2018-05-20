/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 4. yongpal
*****************************************************************************/
package com.happyJ.realestate.model;

import java.io.Serializable;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model
 *  @fileName : CommonDto.java
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
 *     2016. 4. 4.        yongpal       create CommonDto.java
 *  </pre>
 ******************************************************************************/
public class CommonDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2462039079768181591L;
	
	// 페이징 처리
	/** 현재 페이지 */
	private int currentPage = 1;

	/** 전체 페이지 */
	private int totalPage = 1;

	/** 그리드 표시 갯수 */
	private int rowSize = 10;

	/** 페이지사이즈 */
	private int pageSize = 10;
	
	/** 리스트 총 Count */
	private int listCnt = 10;
	
	/** row number */
	private int rnum = 0;
	
	/** 시작  row */
	private int startRow = 0;
	
	

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the rowSize
	 */
	public int getRowSize() {
		return rowSize;
	}

	/**
	 * @param rowSize the rowSize to set
	 */
	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the listCnt
	 */
	public int getListCnt() {
		return listCnt;
	}

	/**
	 * @param listCnt the listCnt to set
	 */
	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}

	/**
	 * @return the rnum
	 */
	public int getRnum() {
		return rnum;
	}

	/**
	 * @param rnum the rnum to set
	 */
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	
}
