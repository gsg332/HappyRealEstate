/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 10. 13. jincheol
*****************************************************************************/
package com.happyJ.realestate.common.excel;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.util.CellRangeAddress;

import com.happyJ.realestate.common.util.ExcelView;
import com.happyJ.realestate.model.custom.MapStatDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.common.excel
 *  @fileName : TypeAddr3StatListExcelView.java
 *  @author : jincheol
 *  @since 2016. 10. 13.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 10. 13.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 10. 13.   jincheol       create TypeAddr3StatListExcelView.java
 *  </pre>
 ******************************************************************************/
public class TypeAddr3StatListExcelView extends ExcelView{

	/* (non-Javadoc)
	 * @see com.happyJ.realestate.common.util.ExcelView#createData(java.util.HashMap, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void createData(Map<String,Object> model) {
		
		List<MapStatDto> excelList = (List<MapStatDto>) model.get("excelList");
		String excelTitle = (String) model.get("title");
		
		HSSFRow row = null;
		HSSFCell cell = null;
		HSSFCellStyle style = null;
		int rowNo = 0;
		// 칼럼 타이틀
		String[] colTitles = {"행정동", "범죄총계", "살인","강도","강간/추행","절도","폭행","교통사고","재물손괴","기타"};

		// 제목
		row = worksheet.createRow(rowNo); // row 생성
		cell = row.createCell(0);
		cell.setCellValue(excelTitle);
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
		if (excelList.isEmpty()){
			
			row = worksheet.createRow(rowNo); // row 생성

			cell = row.createCell(0);
			cell.setCellValue("검색 결과가 없습니다.");
			cell.setCellStyle(columnStyle("center", ""));
			
			worksheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 0, colTitles.length)); // cell 합치기
			rowNo++;
		} else {
			for (MapStatDto mapStatDto : excelList){
				
				style = columnStyle("center", "");
				row = worksheet.createRow(rowNo); // row 생성
				
				cell = row.createCell(0);
				cell.setCellValue(mapStatDto.getLocationDong());
				cell.setCellStyle(style);
				
				cell = row.createCell(1);
				cell.setCellValue(mapStatDto.getCrimeCnt()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(2);
				cell.setCellValue(mapStatDto.getMurderCnt()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(3);
				cell.setCellValue(mapStatDto.getRobberCnt()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(4);
				cell.setCellValue(mapStatDto.getRapeCnt()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(5);
				cell.setCellValue(mapStatDto.getTheftCnt()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(6);
				cell.setCellValue(mapStatDto.getViolenceCnt()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(7);
				cell.setCellValue(mapStatDto.getAccidentCnt()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(8);
				cell.setCellValue(mapStatDto.getDestroyCnt()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(9);
				cell.setCellValue(mapStatDto.getEtcCnt()+"건");
				cell.setCellStyle(style);
				
				rowNo++;
			}
		}
		
		super.createData(model);
	}
}