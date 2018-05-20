package com.happyJ.realestate.common.excel;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happyJ.realestate.common.util.ExcelView;
import com.happyJ.realestate.model.schema.ChangeReqDto;
import com.happyJ.realestate.model.schema.CodeDto;
import com.happyJ.realestate.service.CommonCodeService;

/**
 * 변경내역 목록을 엑셀파일로 돌려준다.
 * @author ydak
 *
 */
@Component
public class ChangeReqListExcelView extends ExcelView{
	
	@Autowired
	private CommonCodeService codeService;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void createData(Map<String,Object> model) {
		
		List<ChangeReqDto> changeReqList = (List<ChangeReqDto>) model.get("excelList");

		HSSFRow row = null;
		HSSFCell cell = null;
		HSSFCellStyle style = null;
		int rowNo = 0;
		// 칼럼 타이틀
		String[] colTitles = {"번호", "변경요청일", "승인완료일", "변경메뉴", "상세내용", "미승인 사유", "상태"};
		
		// 제목
		row = worksheet.createRow(rowNo); // row 생성
		cell = row.createCell(0);
		cell.setCellValue("변경 내역 리스트");
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
		if (changeReqList.isEmpty()){
			
			row = worksheet.createRow(rowNo); // row 생성

			cell = row.createCell(0);
			cell.setCellValue("검색 결과가 없습니다.");
			cell.setCellStyle(columnStyle("center", ""));
			
			worksheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 0, colTitles.length)); // cell 합치기
			rowNo++;
		} else {
			for (ChangeReqDto changeReq : changeReqList){
				
				style = columnStyle("center", "");
				row = worksheet.createRow(rowNo); // row 생성
				
				cell = row.createCell(0);
				cell.setCellValue(changeReq.getNo());
				cell.setCellStyle(style);
				
				cell = row.createCell(1);
				cell.setCellValue(changeReq.getReqDate());
				cell.setCellStyle(style);
				
				cell = row.createCell(2);
				cell.setCellValue(changeReq.getApprovalDate());
				cell.setCellStyle(style);
				
				cell = row.createCell(3);
				cell.setCellValue(changeReq.getTypeDepth1());
				cell.setCellStyle(style);
				
				cell = row.createCell(4);
				cell.setCellValue(changeReq.getTypeDepth2());
				cell.setCellStyle(style);
				
				cell = row.createCell(5);
				cell.setCellValue(changeReq.getUnapprovalReason());
				cell.setCellStyle(style);
				
				cell = row.createCell(6);
				List<CodeDto> statusList = codeService.selectCodeList("117", changeReq.getStatus(), "N");
				cell.setCellValue(statusList.get(0).getCodeVal());
				cell.setCellStyle(style);
				
				rowNo++;
			}
		}
		
		super.createData(model);
	}

}
