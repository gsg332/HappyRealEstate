package com.happyJ.realestate.common.util;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/*******************************************************************************
 *  @packageName : com.tis.xinc.web.common.util
 *  @fileName : ExcelView.java
 *  @content : 공통 excel view
 *             각 데이타 생성은 해당 class를 상속받아 createData 구현
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE            NAME         DESC
 *    -------------   ----------   ---------------------------------------
 *    2015. 11. 1.                 create
 *  </pre>
 ******************************************************************************/

public class ExcelView extends AbstractExcelView {
	
	// 엑셀 객체 선언
	protected HSSFWorkbook workbook = null;
	protected HSSFSheet worksheet = null;
	HSSFPalette palette = null;
	HSSFCellStyle style1 = null;
	HSSFCellStyle style2 = null;
	HSSFCellStyle style3 = null;
	HSSFFont font = null;
	
	@SuppressWarnings("unchecked")
	@Override
    protected void buildExcelDocument( Map< String,Object > model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		style1 = null;
		style2 = null;
		style3 = null;
		
		this.workbook = workbook;
		
		String title = model.get("title").toString();
		
		Calendar cal = Calendar.getInstance();
  		SimpleDateFormat date = new SimpleDateFormat("yyyymmddhhmmss");
  		date.format(cal.getTime()); 
  		
  		String fileName = "";
		// 파일명 및 시트명 생성
		fileName = URLEncoder.encode( title + "_" + date.format(cal.getTime()) + ".xls", "UTF-8" );
		worksheet = workbook.createSheet( title );
		
		// 색상 팔렛트 생성
		createPalette();
		
		// 엑셀 데이타 생성
		createData(model);
			
		response.setContentType( "Application/Msexcel" );
		response.setHeader( "Content-Disposition", "ATTachment; Filename=" + fileName + ";" );
		
    }
	
	protected void createPalette() {
		
		palette = workbook.getCustomPalette();
		
		palette.setColorAtIndex( ( short )63, ( byte )176, ( byte )224, ( byte )230 ); // #b0e0e6 ** powderblue
		palette.setColorAtIndex( ( short )62, ( byte )0, ( byte )191, ( byte )255 ); // #00bfff ** deepskyblue
		palette.setColorAtIndex( ( short )61, ( byte )210, ( byte )180, ( byte )140 ); // #d2b48c ** tan
		palette.setColorAtIndex( ( short )60, ( byte )255, ( byte )192, ( byte )203 ); // #ffc0cb ** pink
		palette.setColorAtIndex( ( short )59, ( byte )216, ( byte )191, ( byte )216 ); // #d8bfd8 ** thistle
		palette.setColorAtIndex( ( short )58, ( byte )255, ( byte )127, ( byte )80 ); // #ff7f50 ** coral
		palette.setColorAtIndex( ( short )57, ( byte )240, ( byte )230, ( byte )140 ); // #f0e68c ** khaki
		palette.setColorAtIndex( ( short )56, ( byte )255, ( byte )165, ( byte )0 ); // #ffa500 ** orange
		palette.setColorAtIndex( ( short )55, ( byte )152, ( byte )251, ( byte )152 ); // #98fb98 ** palegreen
		palette.setColorAtIndex( ( short )54, ( byte )173, ( byte )255, ( byte )47 ); // #adff2f ** greenyellow
		palette.setColorAtIndex( ( short )53, ( byte )201, ( byte )201, ( byte )201 ); // #c9c9c9 ** gray
		
	}
	
	/**
	 * 제목 스타일
	 * 
	 * @return 엑셀 스타일
	 */
	protected HSSFCellStyle subjectStyle() {
		
		if(style1 == null){
			style1 = workbook.createCellStyle();
			
			font = workbook.createFont();
			font.setBoldweight( ( short ) 700 );
			font.setFontHeight( (short) 250 );
			
			style1.setAlignment( HSSFCellStyle.ALIGN_CENTER );
			style1.setFont( font );
		}
				
		return style1;
		
	}
	
	/**
	 * 컬럼명 스타일
	 * 
	 * @return 엑셀 스타일
	 */
	protected HSSFCellStyle columnTileStyle() {
		
		if(style2 == null){
			style2 = workbook.createCellStyle();
			 
			style2.setFillForegroundColor( palette.getColor( 53 ).getIndex() );
			style2.setFillPattern( HSSFCellStyle.SOLID_FOREGROUND );
			style2.setAlignment( HSSFCellStyle.ALIGN_CENTER_SELECTION );
			style2.setBorderLeft( HSSFCellStyle.BORDER_THIN );
			style2.setBorderRight( HSSFCellStyle.BORDER_THIN );
			style2.setBorderBottom( HSSFCellStyle.BORDER_THIN );
			style2.setBorderTop( HSSFCellStyle.BORDER_THIN );
		}
				
		return style2;
		
	}
	
	/**
	 * 컬럼 스타일
	 * 
	 * @param align 텍스트 정렬
	 * @return 엑셀 스타일
	 */
	protected HSSFCellStyle columnStyle( String align, String color ) {
		
		if(style3 == null){
			style3 = workbook.createCellStyle();
			
			// 정렬
			if( "center".equals( align ) ) {
				style3.setAlignment( HSSFCellStyle.ALIGN_CENTER );
			} else if( "right".equals( align ) ) {
				style3.setAlignment( HSSFCellStyle.ALIGN_RIGHT );
			}
			
			// 색상
			if( "khaki".equals( color ) ) {
				style3.setFillForegroundColor( palette.getColor( 57 ).getIndex() );
				style3.setFillPattern( HSSFCellStyle.SOLID_FOREGROUND );
			} else if( "coral".equals( color ) ) {
				style3.setFillForegroundColor( palette.getColor( 56 ).getIndex() );
				style3.setFillPattern( HSSFCellStyle.SOLID_FOREGROUND );
			} else if( "pink".equals( color ) ) {
				style3.setFillForegroundColor( palette.getColor( 60 ).getIndex() );
				style3.setFillPattern( HSSFCellStyle.SOLID_FOREGROUND );
			}
			
			style3.setBorderLeft( HSSFCellStyle.BORDER_THIN );
			style3.setBorderRight( HSSFCellStyle.BORDER_THIN );
			style3.setBorderBottom( HSSFCellStyle.BORDER_THIN );
			style3.setBorderTop( HSSFCellStyle.BORDER_THIN );
		}
			
		return style3;
	}
	
	/**
	 * 셀의 자동 넓이 조정
	 * 
	 * @param columnLen 칼럼 수
	 * @param addWidth 자동 넓이가 작아서 좀 더해주어야 할 넓이( 시트에 맞게 가변 )
	 */
	protected void autoWidth( int columnLen, int addWidth ) {
		
		for( int i = 0 ; i < columnLen ; i++ ) {
			worksheet.autoSizeColumn( ( short ) i );
			worksheet.setColumnWidth( i, ( worksheet.getColumnWidth( i ) ) + addWidth );
		}
		
	}

	/**
	 * 엑셀 데이타 생성
	 * 
	 * @param params 검색조건
	 * @param list 데이타 리스트
	 */
	protected void createData(Map< String,Object > model) {
		// 각 엑셀에 맞는 내용 그리기
	}

}
