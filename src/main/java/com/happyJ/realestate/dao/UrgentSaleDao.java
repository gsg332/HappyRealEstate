package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.dto.InterestDto;
import com.happyJ.realestate.dto.UrgentSaleDto;

@Repository
public interface UrgentSaleDao {

	List<InterestDto> selectUrgentSaleList(UrgentSaleDto urgentSaleDto) throws DataAccessException;
	
	int insertUrgentSale(UrgentSaleDto urgentSaleDto) throws DataAccessException;
	
}
