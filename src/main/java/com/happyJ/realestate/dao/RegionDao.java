package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.dto.RegionDto;

@Repository
public interface RegionDao {

	int insertRegion(RegionDto regionDto) throws DataAccessException;

	int deleteRegion(RegionDto regionDto) throws DataAccessException;

	List<RegionDto> selectRegionList(RegionDto regionDto) throws DataAccessException;

}
