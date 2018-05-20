package com.happyJ.realestate.service;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.dao.ApartDao;
import com.happyJ.realestate.dto.ApartDto;

@Service
public class ApartService {
	
	private static Logger logger = LoggerFactory.getLogger(ApartService.class);
	
	@Autowired
	private ApartDao apartDao;
	
	public int insertApart(ApartDto apartDto) throws ParseException {
		return apartDao.insertApart(apartDto);
	}
	
	public List<ApartDto> selectApartList(ApartDto apartDto) throws ParseException {
		return apartDao.selectApartList(apartDto);
	}
	
}
