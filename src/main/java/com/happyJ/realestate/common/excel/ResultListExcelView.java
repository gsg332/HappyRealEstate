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
import com.happyJ.realestate.model.custom.ApplyCustomDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.common
 *  @fileName : ResultListExcelView.java
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
 *     2016. 5. 11.        yongpal       create ResultListExcelView.java
 *  </pre>
 ******************************************************************************/
public class ResultListExcelView extends ExcelView{
	
	/* (non-Javadoc)
	 * @see com.happyJ.realestate.common.util.ExcelView#createData(java.util.HashMap, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void createData(Map<String,Object> model) {
		
		List<ApplyCustomDto> applyList = (List<ApplyCustomDto>) model.get("excelList");
		
		HSSFRow row = null;
		HSSFCell cell = null;
		HSSFCellStyle style = null;
		int rowNo = 0;
		// 칼럼 타이틀
		String[] colTitles = {"신청번호", "아이디", "이름", "범죄유형", "CCTV관리번호", "신청일", "재생 만료일", "경과일", "구분"};
		
		// 제목
		row = worksheet.createRow(rowNo); // row 생성
		cell = row.createCell(0);
		cell.setCellValue("활용결과 미등록 리스트");
		cell.setCellStyle(subjectStyle());
		worksheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 0, colTitles.length -1)); // cell 합치기
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
		if (applyList.isEmpty()){
			
			row = worksheet.createRow(rowNo); // row 생성

			cell = row.createCell(0);
			cell.setCellValue("검색 결과가 없습니다.");
			cell.setCellStyle(columnStyle("center", ""));
			
			worksheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 0, colTitles.length)); // cell 합치기
			rowNo++;
		} else {
			for (ApplyCustomDto apply : applyList){
				
				style = columnStyle("center", "");
				row = worksheet.createRow(rowNo); // row 생성
				
				cell = row.createCell(0);
				cell.setCellValue(apply.getItemSerial());
				cell.setCellStyle(style);
				
				cell = row.createCell(1);
				cell.setCellValue(apply.getReqUserId());
				cell.setCellStyle(style);
				
				cell = row.createCell(2);
				cell.setCellValue(apply.getReqUsername());
				cell.setCellStyle(style);				
//				
//				cell = row.createCell(3);
//				cell.setCellValue(apply.getReqPhone());
//				cell.setCellStyle(style);
				
				cell = row.createCell(3);
				cell.setCellValue(apply.getCodeVal());
				cell.setCellStyle(style);
				
				cell = row.createCell(4);
				cell.setCellValue(apply.getVideoId());
				cell.setCellStyle(style);
				
				cell = row.createCell(5);
				cell.setCellValue(apply.getReqDate());
				cell.setCellStyle(style);
				
				cell = row.createCell(6);
				cell.setCellValue(apply.getVeiLimitDatetime());
				cell.setCellStyle(style);
				
				cell = row.createCell(7);
				cell.setCellValue(apply.getPastDate());
				cell.setCellStyle(style);
				
				cell = row.createCell(8);
				cell.setCellValue(apply.getUnregistedType());
				cell.setCellStyle(style);
				
				rowNo++;
			}
		}
		
		super.createData(model);
	}

}
