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
 *  @fileName : ApplyListExcelView.java
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
 *     2016. 5. 11.        yongpal       create ApplyListExcelView.java
 *  </pre>
 ******************************************************************************/
public class ApplyListExcelView extends ExcelView{
	
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
		String[] colTitles = {"신청번호", "아이디", "신청일", "소속", "과", "계", "이름", "연락처", "제공근거", 
				"공문번호", "범죄유형", "사건장소", "CCTV관리번호", "영상시작일시", "영상종료일시", "다운 가능횟수", "다운로드 만료일"
				, "재생 허용횟수", "재생 만료일", "상태", "결과입력", "사건 결과", "CCTV활용", "미사용 사유"};
		
		// 제목
		row = worksheet.createRow(rowNo); // row 생성
		cell = row.createCell(0);
		cell.setCellValue("영상 신청 리스트");
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
				cell.setCellValue(apply.getUserId());
				cell.setCellStyle(style);
				
				cell = row.createCell(2);
				cell.setCellValue(apply.getReqDate());
				cell.setCellStyle(style);
				
				cell = row.createCell(3);
				cell.setCellValue(apply.getPosition());
				cell.setCellStyle(style);
				
				cell = row.createCell(4);
				cell.setCellValue(apply.getDepart());
				cell.setCellStyle(style);
				
				cell = row.createCell(5);
				cell.setCellValue(apply.getTeam());
				cell.setCellStyle(style);
				
				cell = row.createCell(6);
				cell.setCellValue(apply.getUserName());
				cell.setCellStyle(style);
				
				cell = row.createCell(7);
				cell.setCellValue(apply.getPhoneNum());
				cell.setCellStyle(style);
				
				cell = row.createCell(8);
				cell.setCellValue(apply.getReqReason());
				cell.setCellStyle(style);
				
				cell = row.createCell(9);
				cell.setCellValue(apply.getVeiDocNo());
				cell.setCellStyle(style);
				
				cell = row.createCell(10);
				cell.setCellValue(apply.getCodeVal());
				cell.setCellStyle(style);
				
				cell = row.createCell(11);
				cell.setCellValue(apply.getReqPosition());
				cell.setCellStyle(style);
				
				cell = row.createCell(12);
				cell.setCellValue(apply.getVideoId());
				cell.setCellStyle(style);
				
				cell = row.createCell(13);
				cell.setCellValue(apply.getVideoStart());
				cell.setCellStyle(style);
				
				cell = row.createCell(14);
				cell.setCellValue(apply.getVideoEnd());
				cell.setCellStyle(style);
				
				cell = row.createCell(15);
				cell.setCellValue(apply.getVeiDnLimitCount());
				cell.setCellStyle(style);
				
				cell = row.createCell(16);
				cell.setCellValue(apply.getVeiDnLimitDate());
				cell.setCellStyle(style);
				
				cell = row.createCell(17);
				cell.setCellValue(apply.getVeiLimitCount());
				cell.setCellStyle(style);
				
				cell = row.createCell(18);
				cell.setCellValue(apply.getVeiLimitDatetime());
				cell.setCellStyle(style);
				
				cell = row.createCell(19);
				cell.setCellValue(apply.getStatusNm());
				cell.setCellStyle(style);
				
//				CodeDto code = new CodeDto();
				
				if (apply.getItemResult() != null){
					
					cell = row.createCell(20);
					cell.setCellValue("입력");
					cell.setCellStyle(style);
					
					cell = row.createCell(21);
					cell.setCellValue(apply.getItemResult());
					cell.setCellStyle(style);
					
					cell = row.createCell(22);
					cell.setCellValue(apply.getItemUse());
					cell.setCellStyle(style);
					
					cell = row.createCell(23);
					cell.setCellValue(apply.getResultCode());
					cell.setCellStyle(style);
				} 

				else {
					cell = row.createCell(20);
					cell.setCellValue("미입력");
					cell.setCellStyle(style);
					
					cell = row.createCell(21);
					cell.setCellValue("");
					cell.setCellStyle(style);
					
					cell = row.createCell(22);
					cell.setCellValue("");
					cell.setCellStyle(style);
					
					cell = row.createCell(23);
					cell.setCellValue("");
					cell.setCellStyle(style);
				}
						
				rowNo++;
			}
		}
		
		super.createData(model);
	}

}
