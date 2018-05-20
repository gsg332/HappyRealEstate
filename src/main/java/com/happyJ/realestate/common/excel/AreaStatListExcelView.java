/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 10. 12. jincheol
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
 *  @fileName : AreaStatListExcelView.java
 *  @author : jincheol
 *  @since 2016. 10. 12.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 10. 12.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 10. 12.   jincheol       create AreaStatListExcelView.java
 *  </pre>
 ******************************************************************************/
public class AreaStatListExcelView extends ExcelView{

	/* (non-Javadoc)
	 * @see com.happyJ.realestate.common.util.ExcelView#createData(java.util.HashMap, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void createData(Map<String,Object> model) {
		
		List<MapStatDto> AreaStatList = (List<MapStatDto>) model.get("excelList");
		String excelTitle = (String) model.get("title");
		
		HSSFRow row = null;
		HSSFCell cell = null;
		HSSFCellStyle style = null;
		int rowNo = 0;
		// 칼럼 타이틀
		String[] colTitles = {"행정동", "CCTV", "총 결과등록 수","결과등록 사건해결 완료 수","주간(07h ~ 18h)","저녁(18h ~ 22h)","심야(22h ~ 03h)","새벽(03h ~ 07h)","해결율"};
		
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
		if (AreaStatList.isEmpty()){
			
			row = worksheet.createRow(rowNo); // row 생성

			cell = row.createCell(0);
			cell.setCellValue("검색 결과가 없습니다.");
			cell.setCellStyle(columnStyle("center", ""));
			
			worksheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 0, colTitles.length)); // cell 합치기
			rowNo++;
		} else {
			for (MapStatDto mapStatDto : AreaStatList){
				
				style = columnStyle("center", "");
				row = worksheet.createRow(rowNo); // row 생성
				
				cell = row.createCell(0);
				cell.setCellValue(mapStatDto.getLocationDong());
				cell.setCellStyle(style);
				
				cell = row.createCell(1);
				cell.setCellValue(mapStatDto.getCctvCnt());
				cell.setCellStyle(style);
				
				cell = row.createCell(2);
				cell.setCellValue(mapStatDto.getCrimeCnt());
				cell.setCellStyle(style);
				
				cell = row.createCell(3);
				cell.setCellValue(mapStatDto.getResultTotal());
				cell.setCellStyle(style);
				
				cell = row.createCell(4);
				cell.setCellValue(mapStatDto.getNoon());
				cell.setCellStyle(style);
				
				cell = row.createCell(5);
				cell.setCellValue(mapStatDto.getEvening());
				cell.setCellStyle(style);
				
				cell = row.createCell(6);
				cell.setCellValue(mapStatDto.getNight());
				cell.setCellStyle(style);
				
				cell = row.createCell(7);
				cell.setCellValue(mapStatDto.getDawn());
				cell.setCellStyle(style);
				
				cell = row.createCell(8);
				cell.setCellValue(mapStatDto.getRate()+"%");
				cell.setCellStyle(style);
				
				rowNo++;
			}
		}
		
		super.createData(model);
	}
}
