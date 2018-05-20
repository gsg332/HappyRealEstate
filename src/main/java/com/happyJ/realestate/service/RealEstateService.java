package com.happyJ.realestate.service;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.dao.ApartDao;
import com.happyJ.realestate.dto.ApartDto;

@Service
public class RealEstateService {
	
	private static Logger logger = LoggerFactory.getLogger(RealEstateService.class);
	/*
	@Autowired
	private ApartDao apartDao;
	
	public int insertApart(ApartDto apartDto) throws ParseException {
		return apartDao.insertApart(apartDto);
	}*/
	
}
