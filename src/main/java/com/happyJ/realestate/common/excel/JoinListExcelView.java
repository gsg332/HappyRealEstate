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
import com.happyJ.realestate.model.schema.ActionHistoryDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.common.excel
 *  @fileName : JoinListExcelView.java
 *  @author : yongpal
 *  @since 2016. 5. 19.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 5. 19.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 5. 19.        yongpal       create JoinListExcelView.java
 *  </pre>
 ******************************************************************************/
public class JoinListExcelView extends ExcelView{
	
	/* (non-Javadoc)
	 * @see com.happyJ.realestate.common.util.ExcelView#createData(java.util.HashMap, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void createData(Map<String,Object> model) {
		
		List<ActionHistoryDto> joinHistoryList = (List<ActionHistoryDto>) model.get("excelList");

		HSSFRow row = null;
		HSSFCell cell = null;
		HSSFCellStyle style = null;
		int rowNo = 0;
		// 칼럼 타이틀
		String[] colTitles = {"번호", "아이디", "IP", "메뉴", "사용내역", "상세내용", "결과", "사용일시"};
	
		// 제목
		row = worksheet.createRow(rowNo); // row 생성
		cell = row.createCell(0);
		cell.setCellValue("가입 이력 리스트");
		cell.setCellStyle(subjectStyle());
		worksheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 0, (colTitles.length -1))); // cell 합치기
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
		if (joinHistoryList.isEmpty()){
			
			row = worksheet.createRow(rowNo); // row 생성

			cell = row.createCell(0);
			cell.setCellValue("검색 결과가 없습니다.");
			cell.setCellStyle(columnStyle("center", ""));
			
			worksheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 0, colTitles.length)); // cell 합치기
			rowNo++;
		} else {
			for (ActionHistoryDto actionHistory : joinHistoryList){
				
				style = columnStyle("center", "");
				row = worksheet.createRow(rowNo); // row 생성
				
				cell = row.createCell(0);
				cell.setCellValue(rowNo-1);
				cell.setCellStyle(style);
				
				cell = row.createCell(1);
				cell.setCellValue(actionHistory.getUserId());
				cell.setCellStyle(style);
				
				cell = row.createCell(2);
				cell.setCellValue(actionHistory.getAccessIp());
				cell.setCellStyle(style);
				
				cell = row.createCell(3);
				cell.setCellValue(actionHistory.getMenu());
				cell.setCellStyle(style);
				
				cell = row.createCell(4);
				cell.setCellValue(actionHistory.getAction());
				cell.setCellStyle(style);
				
				cell = row.createCell(5);
				cell.setCellValue(actionHistory.getMemo());
				cell.setCellStyle(style);
				
				cell = row.createCell(6);
				cell.setCellValue(actionHistory.getResult());
				cell.setCellStyle(style);

				cell = row.createCell(7);
				cell.setCellValue(actionHistory.getActionTime());
				cell.setCellStyle(style);
				
				rowNo++;
			}
		}
		
		super.createData(model);
	}

}
