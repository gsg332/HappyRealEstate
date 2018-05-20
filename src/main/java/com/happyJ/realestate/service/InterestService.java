package com.happyJ.realestate.service;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.dao.InterestDao;
import com.happyJ.realestate.dto.InterestDto;

@Service
public class InterestService {
	
	private static Logger logger = LoggerFactory.getLogger(InterestService.class);
	
	@Autowired
	private InterestDao interestDao;
	
	public List<InterestDto> selectInterestList(InterestDto interestDto) throws Exception {
		interestDto.setuMemId(1L); // TODO : 추후 세션에서 고유id값을 얻어와 세팅한다.
		return interestDao.selectInterestList(interestDto);
	}
	
	public int addInterest(InterestDto interestDto) throws ParseException {
		
		String uRegionId = String.valueOf(interestDto.getuRegionId());
		
		if(uRegionId.length() == 10 && "00".equals(uRegionId.substring(8))){
			interestDto.setuRegionId(Long.parseLong(uRegionId.substring(0,8)));
		} 
		
		interestDto.setuMemId(1L); // TODO : 추후 세션에서 고유id값을 얻어와 세팅한다.
		
		Integer level = null; 
		if("APT".equals(interestDto.getType())){ // 부동산인 경우
			level = 5; // 동과 리 상관 없이 부동산이면 무조건 레벨 5로 지정.
		}else if("RI".equals(interestDto.getType())){ 
			level = 4;
		}else if("EMD".equals(interestDto.getType())){ 
			level = 3;
		}else if("SGG".equals(interestDto.getType())){ 
			level = 2;
		}else if("SIDO".equals(interestDto.getType())){ 
			level = 1;
		}
		interestDto.setLevel(level);
		
		return interestDao.addInterest(interestDto);
	}
	
	public int deleteInterest(InterestDto interestDto) throws ParseException {
		interestDto.setuMemId(1L); // TODO : 추후 세션에서 고유id값을 얻어와 세팅한다.
		return interestDao.deleteInterest(interestDto);
	}

	public int existSubInterestCheck(InterestDto interestDto) throws ParseException {
		
		interestDto.setuMemId(1L); // TODO : 추후 세션에서 고유id값을 얻어와 세팅한다.
		
		return interestDao.existSubInterestCheck(interestDto);
	}
	
	public List<InterestDto> selectGroupInterestList(InterestDto interestDto) throws Exception {
		interestDto.setuMemId(1L); // TODO : 추후 세션에서 고유id값을 얻어와 세팅한다.
		return interestDao.selectGroupInterestList(interestDto);
	}
	
	
}
