/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 5. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.common.excel;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.util.CellRangeAddress;

import com.happyJ.realestate.common.util.ExcelView;
import com.happyJ.realestate.model.custom.EvidenceCustomDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.common
 *  @fileName : EvidenceListExcelView.java
 *  @author : yongpal
 *  @since 2016. 5. 11.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 5. 11.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 5. 11.        yongpal       create EvidenceListExcelView.java
 *  </pre>
 ******************************************************************************/
public class EvidenceListExcelView extends ExcelView{
	
	/* (non-Javadoc)
	 * @see com.happyJ.realestate.common.util.ExcelView#createData(java.util.HashMap, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void createData(Map<String,Object> model) {
		
		List<EvidenceCustomDto> evidenceList = (List<EvidenceCustomDto>) model.get("excelList");

		HSSFRow row = null;
		HSSFCell cell = null;
		HSSFCellStyle style = null;
		int rowNo = 0;
		// 칼럼 타이틀
		String[] colTitles = {"신청번호", "신청자ID", "발급아이디", "제출일시", "재생만료일", "상태"};
		
		// 제목
		row = worksheet.createRow(rowNo); // row 생성
		cell = row.createCell(0);
		cell.setCellValue("증거자료 제출 리스트");
		cell.setCellStyle(subjectStyle());
		worksheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 0, colTitles.length)); // cell 합치기
		rowNo++;
		
		style = columnTileStyle();

		// 컬럼 타이틀 처리
		row = worksheet.createRow(rowNo); // row 생성
		for (int i = 0; i < colTitles.length; i++){
			cell = row.createCell(i);
			cell.setCellValue(colTitles[i]);
			cell.setCellStyle(style);
		}
		rowNo++;
		
		// 데이터
		if (evidenceList.isEmpty()){
			
			row = worksheet.createRow(rowNo); // row 생성

			cell = row.createCell(0);
			cell.setCellValue("검색 결과가 없습니다.");
			cell.setCellStyle(columnStyle("center", ""));
			
			worksheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 0, colTitles.length)); // cell 합치기
			rowNo++;
		} else {
			for (EvidenceCustomDto extend : evidenceList){
				
				style = columnStyle("center", "");
				row = worksheet.createRow(rowNo); // row 생성
				
				cell = row.createCell(0);
				cell.setCellValue(extend.getItemSerial());
				cell.setCellStyle(style);
				
				cell = row.createCell(1);
				cell.setCellValue(extend.getEviReqUserid());
				cell.setCellStyle(style);
				
				cell = row.createCell(2);
				cell.setCellValue(extend.getEviUserid());
				cell.setCellStyle(style);				
				
				cell = row.createCell(3);
				cell.setCellValue(extend.getEviItemDate());
				cell.setCellStyle(style);
				
				cell = row.createCell(4);
				cell.setCellValue(extend.getEviLimitDate());
				cell.setCellStyle(style);
				
				cell = row.createCell(5);
				cell.setCellValue(extend.getPermitNm());
				cell.setCellStyle(style);
				
				rowNo++;
			}
		}
		
		super.createData(model);
	}

}
