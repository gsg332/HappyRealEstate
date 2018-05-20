package com.happyJ.realestate.service;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.dao.RegionDao;
import com.happyJ.realestate.dto.RegionDto;

@Service
public class RegionService {
	
	private static Logger logger = LoggerFactory.getLogger(RegionService.class);
	
	@Autowired
	private RegionDao regionDao;
	
	public int insertRegion(RegionDto regionDto) throws ParseException {
		return regionDao.insertRegion(regionDto);
	}
	
	public int deleteRegion(RegionDto regionDto) throws ParseException {
		return regionDao.deleteRegion(regionDto);
	}
	
	public List<RegionDto> selectRegionList(RegionDto regionDto) throws ParseException {
		return regionDao.selectRegionList(regionDto);
	}
	
}
