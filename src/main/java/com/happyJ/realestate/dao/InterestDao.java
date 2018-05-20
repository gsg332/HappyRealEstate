package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.dto.InterestDto;

@Repository
public interface InterestDao {

	List<InterestDto> selectInterestList(InterestDto interestDto) throws DataAccessException;
	
	int addInterest(InterestDto interestDto) throws DataAccessException;
	
	int deleteInterest(InterestDto interestDto) throws DataAccessException;
	
	int existSubInterestCheck(InterestDto interestDto) throws DataAccessException;
	
	List<InterestDto> selectGroupInterestList(InterestDto interestDto) throws DataAccessException;

}
