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
import com.happyJ.realestate.web.common.Config;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.common.excel
 *  @fileName : CctvInstAddr3StatInfoExcelView.java
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
 *     2016. 10. 13.   jincheol       create CctvInstAddr3StatInfoExcelView.java
 *  </pre>
 ******************************************************************************/
public class CctvInstAddr3StatInfoExcelView extends ExcelView{

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
					cell.setCellValue(mapStatDto.getCctvCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(2);
					cell.setCellValue(mapStatDto.getPreventionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(3);
					cell.setCellValue(mapStatDto.getParkingCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(4);
					cell.setCellValue(mapStatDto.getTrackCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(5);
					cell.setCellValue(mapStatDto.getGarbageCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(6);
					cell.setCellValue(mapStatDto.getMultiCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(7);
					cell.setCellValue(mapStatDto.getFireCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(8);
					cell.setCellValue(mapStatDto.getmFireCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(9);
					cell.setCellValue(mapStatDto.getStreetCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(10);
					cell.setCellValue(mapStatDto.getParkCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(11);
					cell.setCellValue(mapStatDto.getChildCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(12);
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
					cell.setCellValue(mapStatDto.getPreventionResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(3);
					cell.setCellValue(mapStatDto.getParkingResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(4);
					cell.setCellValue(mapStatDto.getTrackResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(5);
					cell.setCellValue(mapStatDto.getGarbageResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(6);
					cell.setCellValue(mapStatDto.getMultiResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(7);
					cell.setCellValue(mapStatDto.getFireResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(8);
					cell.setCellValue(mapStatDto.getmFireResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(9);
					cell.setCellValue(mapStatDto.getStreetResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(10);
					cell.setCellValue(mapStatDto.getParkResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(11);
					cell.setCellValue(mapStatDto.getChildResolutionCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(12);
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
					cell.setCellValue(mapStatDto.getPreventionResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(3);
					cell.setCellValue(mapStatDto.getParkingResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(4);
					cell.setCellValue(mapStatDto.getTrackResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(5);
					cell.setCellValue(mapStatDto.getGarbageResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(6);
					cell.setCellValue(mapStatDto.getMultiResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(7);
					cell.setCellValue(mapStatDto.getFireResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(8);
					cell.setCellValue(mapStatDto.getmFireResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(9);
					cell.setCellValue(mapStatDto.getStreetResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(10);
					cell.setCellValue(mapStatDto.getParkResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(11);
					cell.setCellValue(mapStatDto.getChildResolutionPct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(12);
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
					cell.setCellValue(mapStatDto.getPreventionUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(3);
					cell.setCellValue(mapStatDto.getParkingUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(4);
					cell.setCellValue(mapStatDto.getTrackUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(5);
					cell.setCellValue(mapStatDto.getGarbageUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(6);
					cell.setCellValue(mapStatDto.getMultiUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(7);
					cell.setCellValue(mapStatDto.getFireUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(8);
					cell.setCellValue(mapStatDto.getmFireUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(9);
					cell.setCellValue(mapStatDto.getStreetUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(10);
					cell.setCellValue(mapStatDto.getParkUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(11);
					cell.setCellValue(mapStatDto.getChildUseCnt()+"건");
					cell.setCellStyle(style);
					
					cell = row.createCell(12);
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
					cell.setCellValue(mapStatDto.getPreventionUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(3);
					cell.setCellValue(mapStatDto.getParkingUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(4);
					cell.setCellValue(mapStatDto.getTrackUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(5);
					cell.setCellValue(mapStatDto.getGarbageUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(6);
					cell.setCellValue(mapStatDto.getMultiUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(7);
					cell.setCellValue(mapStatDto.getFireUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(8);
					cell.setCellValue(mapStatDto.getmFireUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(9);
					cell.setCellValue(mapStatDto.getStreetUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(10);
					cell.setCellValue(mapStatDto.getParkUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(11);
					cell.setCellValue(mapStatDto.getChildUsePct()+"%");
					cell.setCellStyle(style);
					
					cell = row.createCell(12);
					cell.setCellValue(mapStatDto.getEtcUsePct()+"%");
					cell.setCellStyle(style);
				}

				rowNo++;
			}
		}
		
		super.createData(model);
	}

}
