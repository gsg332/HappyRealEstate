/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.dao.ApplyDao;
import com.happyJ.realestate.dao.ExtendDao;
import com.happyJ.realestate.model.custom.ApplyCustomDto;
import com.happyJ.realestate.model.custom.ExtendCustomDto;
import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.model.schema.ItemExtendDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.service
 *  @fileName : ExtendService.java
 *  @author : yongpal
 *  @since 2016. 4. 25.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 25.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 25.        yongpal       create ExtendService.java
 *  </pre>
 ******************************************************************************/
@Service
public class ExtendService {
	
	@Value("#{config['use.location.code']}")
	private String location;
	
	@Autowired
	private ExtendDao extendDao;
	
	@Autowired
	private ApplyDao applyDao;
	
	@Autowired
	private ConfigService configService;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method getExtendList 
	 *  @param extendCustomDto
	 *  @return
	 **********************************************/
	public List<ItemExtendDto> getExtendList(ExtendCustomDto extendCustomDto) {
		// TODO Auto-generated method stub

		List<ItemExtendDto> extendList = extendDao.selectExtendList(extendCustomDto);
		
		if (!extendList.isEmpty()){

			// 신청 목록 카운트
			int listCnt = extendDao.selectExtendListCount(extendCustomDto);

			// 페이징 처리
			extendList.get(0).setCurrentPage(extendCustomDto.getCurrentPage());
			extendList.get(0).setRowSize(extendCustomDto.getRowSize());
			extendList.get(0).setListCnt(listCnt);
			extendList.get(0).setTotalPage((int)Math.ceil((double)listCnt / extendCustomDto.getRowSize()));
		}
		
		return extendList;				
//		return extendDao.selectExtendList(extendCustomDto);
	}
	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method getExtendListExcel 
	 *  @param extendCustomDto
	 *  @return
	 **********************************************/
	public List<ExtendCustomDto> getExtendListExcel(ExtendCustomDto extendCustomDto) {
		// TODO Auto-generated method stub
		return extendDao.selectExtendListExcel(extendCustomDto);
	}


	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertExtendInfo 
	 *  @param extendDto
	 *  @return
	 **********************************************/
	public int insertExtendInfo(ItemExtendDto extendDto) {
		
		ApplyCustomDto applyDto = new ApplyCustomDto();
		applyDto.setItemSerial(extendDto.getItemSerial());
		applyDto.setExtentionApply("1");
		
		int result = applyDao.updateApplyInfo(applyDto);
		
		if (result > 0){
			extendDao.insertExtendInfo(extendDto);
		}
		
		return result;
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method updateExtendInfo 
	 *  @param extendDto
	 *  @return
	 **********************************************/
	public int updateExtendInfo(ExtendCustomDto extendDto) {
		
		int result = 0;
		
		if (extendDao.updateExtendInfo(extendDto) > 0){
			ApplyCustomDto applyDto = new ApplyCustomDto();
			
			applyDto.setItemSerial(extendDto.getItemSerial());
			applyDto.setExtentionApply("0");
			
			if ("1".equals(extendDto.getExtStatus())){
				
				ConfigDto confDto = new ConfigDto();
				confDto.setItemType(location);
				confDto.setItemName("veiLimitDatetime");
				confDto = configService.selectConfigInfo(confDto);
				 
				int limitDay = 14;
				if (confDto != null) {
					limitDay = Integer.parseInt(confDto.getItemValue());
				}
				
				Date today = new Date();
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = new GregorianCalendar(Locale.KOREA);
				cal.setTime(today);
				cal.add(Calendar.DAY_OF_YEAR, limitDay);	// 현재 날짜 + limitDate
				
				applyDto.setVeiLimitDatetime(sdf2.format(cal.getTime())+" 23:59:59");
				applyDto.setVeiApplyStatus("3");	//연장신청 승인 VeiApplyStatus
			} else {
				applyDto.setVeiApplyStatus("5");	//연장신청 미승인 VeiApplyStatus
			}
			
			result = applyDao.updateApplyInfo(applyDto);
		}

		return result;
	}	
	
}
