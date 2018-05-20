/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 10. 13. jincheol
*****************************************************************************/
package com.happyJ.realestate.common.excel;

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
 *  @fileName : TypeAddr3StatInfoExcel.java
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
 *     2016. 10. 13.   jincheol       create TypeAddr3StatInfoExcel.java
 *  </pre>
 ******************************************************************************/
public class TypeAddr3StatInfoExcelView extends ExcelView{

	/* (non-Javadoc)
	 * @see com.happyJ.realestate.common.util.ExcelView#createData(java.util.HashMap, java.util.List)
	 */
	@Override
	protected void createData(Map<String,Object> model) {
		
		MapStatDto mapStatDto = (MapStatDto) model.get("excelList");
		String excelTitle = (String) model.get("title");
		
		HSSFRow row = null;
		HSSFCell cell = null;
		HSSFCellStyle style = null;
		int rowNo = 0;
		// 칼럼 타이틀
		String[] colTitles = {"구분","범죄총계","살인","강도","강간/추행","절도","폭행","교통사고","재물손괴","기타"};

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
		if (mapStatDto == null){
			
			row = worksheet.createRow(rowNo); // row 생성

			cell = row.createCell(0);
			cell.setCellValue("검색 결과가 없습니다.");
			cell.setCellStyle(columnStyle("center", ""));
			
			worksheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 0, colTitles.length)); // cell 합치기
			rowNo++;
		} else {
			for (int i=0; i<5; i++){
				
				style = columnStyle("center", "");
				row = worksheet.createRow(rowNo); // row 생성
				//구 명
				if (i == 0) {
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
					
				} else if (i == 1) {
					//해결수
					cell = row.createCell(0);
					cell.setCellValue("해결수");
					cell.setCellStyle(style);
					
					cell = row.createCell(1);
					cell.setCellValue(mapStatDto.getResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(2);
					cell.setCellValue(mapStatDto.getMurderResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(3);
					cell.setCellValue(mapStatDto.getRobberResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(4);
					cell.setCellValue(mapStatDto.getRapeResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(5);
					cell.setCellValue(mapStatDto.getTheftResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(6);
					cell.setCellValue(mapStatDto.getViolenceResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(7);
					cell.setCellValue(mapStatDto.getAccidentResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(8);
					cell.setCellValue(mapStatDto.getDestroyResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(9);
					cell.setCellValue(mapStatDto.getEtcResolutionCnt()+"건");
					cell.setCellStyle(style);
					
				} if (i == 2) {
					//해결율
					cell = row.createCell(0);
					cell.setCellValue("해결율");
					cell.setCellStyle(style);
					
					cell = row.createCell(1);
					cell.setCellValue(mapStatDto.getResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(2);
					cell.setCellValue(mapStatDto.getMurderResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(3);
					cell.setCellValue(mapStatDto.getRobberResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(4);
					cell.setCellValue(mapStatDto.getRapeResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(5);
					cell.setCellValue(mapStatDto.getTheftResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(6);
					cell.setCellValue(mapStatDto.getViolenceResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(7);
					cell.setCellValue(mapStatDto.getAccidentResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(8);
					cell.setCellValue(mapStatDto.getDestroyResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(9);
					cell.setCellValue(mapStatDto.getEtcResolutionPct()+"%");
					cell.setCellStyle(style);
				} if (i == 3) {
					//활용수
					cell = row.createCell(0);
					cell.setCellValue("활용수");
					cell.setCellStyle(style);
					
					cell = row.createCell(1);
					cell.setCellValue(mapStatDto.getUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(2);
					cell.setCellValue(mapStatDto.getMurderUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(3);
					cell.setCellValue(mapStatDto.getRobberUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(4);
					cell.setCellValue(mapStatDto.getRapeUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(5);
					cell.setCellValue(mapStatDto.getTheftUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(6);
					cell.setCellValue(mapStatDto.getViolenceUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(7);
					cell.setCellValue(mapStatDto.getAccidentUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(8);
					cell.setCellValue(mapStatDto.getDestroyUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(9);
					cell.setCellValue(mapStatDto.getEtcUseCnt()+"건");
					cell.setCellStyle(style);
				} if (i == 4) {
					//기여율
					cell = row.createCell(0);
					cell.setCellValue("기여율");
					cell.setCellStyle(style);
					
					cell = row.createCell(1);
					cell.setCellValue(mapStatDto.getUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(2);
					cell.setCellValue(mapStatDto.getMurderUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(3);
					cell.setCellValue(mapStatDto.getRobberUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(4);
					cell.setCellValue(mapStatDto.getRapeUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(5);
					cell.setCellValue(mapStatDto.getTheftUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(6);
					cell.setCellValue(mapStatDto.getViolenceUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(7);
					cell.setCellValue(mapStatDto.getAccidentUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(8);
					cell.setCellValue(mapStatDto.getDestroyUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(9);
					cell.setCellValue(mapStatDto.getEtcUsePct()+"%");
					cell.setCellStyle(style);
				}

				rowNo++;
			}
		}
		
		super.createData(model);
	}

}
