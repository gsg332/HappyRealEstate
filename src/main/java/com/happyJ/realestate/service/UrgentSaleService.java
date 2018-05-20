package com.happyJ.realestate.service;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.dao.UrgentSaleDao;
import com.happyJ.realestate.dto.InterestDto;
import com.happyJ.realestate.dto.UrgentSaleDto;

@Service
public class UrgentSaleService {
	
	private static Logger logger = LoggerFactory.getLogger(UrgentSaleService.class);
	
	@Autowired
	private UrgentSaleDao urgentSaleDao;
	
	public List<InterestDto> selectUrgentSaleList(UrgentSaleDto urgentSaleDto) throws Exception {
		//서울 , 서울시, 서울특별시처럼 표현방식의 차이가 있는데 이런 경우 DB에 저장된  sido값이 5자가 넘어가면 앞의 두자리만 이용해도 될 것이므로 앞글자 두자리로 검색한다. 
		if(urgentSaleDto.getSido().length() >= 5){
			urgentSaleDto.setSido(urgentSaleDto.getSido().substring(0, 2));
		}
		return urgentSaleDao.selectUrgentSaleList(urgentSaleDto);
	}
	
	public int insertUrgentSale(UrgentSaleDto urgentSaleDto) throws ParseException {
		return urgentSaleDao.insertUrgentSale(urgentSaleDto);
	}
	
}
