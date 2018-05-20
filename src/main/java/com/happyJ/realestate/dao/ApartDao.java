package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.dto.ApartDto;
import com.happyJ.realestate.dto.RealEstateDto;

@Repository
public interface ApartDao {

	int insertApart(ApartDto apartDto) throws DataAccessException;
	
	List<ApartDto> selectApartList(ApartDto apartDto) throws DataAccessException;

}
