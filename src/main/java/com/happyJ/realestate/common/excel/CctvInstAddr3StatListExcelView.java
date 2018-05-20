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
import com.happyJ.realestate.web.common.Config;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.common.excel
 *  @fileName : CctvInstAddr3StatListExcelView.java
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
 *     2016. 10. 13.   jincheol       create CctvInstAddr3StatListExcelView.java
 *  </pre>
 ******************************************************************************/
public class CctvInstAddr3StatListExcelView extends ExcelView{

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
		
		String cctvUseChanged;
		if("Y".equals(Config.getFnConfig("CCTV_USE_CHANGE","Value"))){
			cctvUseChanged = Config.getFnConfig("CCTV_USE_CHANGE","SubConfigList","CHANGED_USE","Value"); 
		}else{
			cctvUseChanged = "다목적";
		}
		
		// 칼럼 타이틀
		String[] colTitles = {"구분", "총계", "방범", "주정차", "추적/감지", "쓰레기무단투기", "어린이보호", "화재감시", "산불감시", "도로방범", "공원방범", cctvUseChanged, "기타"};

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
				cell.setCellValue(mapStatDto.getCctvCnt()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(2);
				cell.setCellValue(mapStatDto.getCctvType0()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(3);
				cell.setCellValue(mapStatDto.getCctvType1()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(4);
				cell.setCellValue(mapStatDto.getCctvType2()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(5);
				cell.setCellValue(mapStatDto.getCctvType3()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(6);
				cell.setCellValue(mapStatDto.getCctvType4()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(7);
				cell.setCellValue(mapStatDto.getCctvType5()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(8);
				cell.setCellValue(mapStatDto.getCctvType6()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(9);
				cell.setCellValue(mapStatDto.getCctvType7()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(10);
				cell.setCellValue(mapStatDto.getCctvType8()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(11);
				cell.setCellValue(mapStatDto.getCctvType9()+"건");
				cell.setCellStyle(style);
				
				cell = row.createCell(12);
				cell.setCellValue(mapStatDto.getCctvType10()+"건");
				cell.setCellStyle(style);
				
				rowNo++;
			}
		}
		
		super.createData(model);
	}

}