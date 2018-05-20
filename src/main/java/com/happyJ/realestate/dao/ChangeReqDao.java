package com.happyJ.realestate.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.happyJ.realestate.model.schema.ChangeReqContentsDto;
import com.happyJ.realestate.model.schema.ChangeReqDto;

@Repository
public interface ChangeReqDao {

	/**
	 * 변경신청 목록을 추가한다.
	 * @param changeReqDto
	 * @return
	 * @throws DataAccessException
	 */
	int insertApplyInfoChangeReq(ChangeReqDto changeReqDto) throws DataAccessException;

	/**
	 * 변경신청에 대한 상세변경내용을 추가한다.
	 * @param changeReqContentsDto
	 * @return
	 * @throws DataAccessException
	 */
	int insertChangeReqContents(ChangeReqContentsDto changeReqContentsDto) throws DataAccessException;

	/**
	 * 자신이 변경요청한 목록수를 가지고 온다.
	 * @param applyDto
	 * @return
	 * @throws DataAccessException
	 */
	int selectChangeReqListCounts(ChangeReqDto applyDto) throws DataAccessException;
	
	
	/**
	 * 모든 사용자의 변경요청한 목록수를 가지고 온다.
	 * @param applyDto
	 * @return
	 * @throws DataAccessException
	 */
	int selectAdminChangeReqListCounts(ChangeReqDto applyDto) throws DataAccessException;

	/**
	 * 자신이 변경요청한 목록을 가지고 온다.
	 * @param changeReqDto
	 * @return
	 * @throws DataAccessException
	 */
	List<ChangeReqDto> selectChangeReqList(ChangeReqDto changeReqDto) throws DataAccessException;
	
	/**
	 * 모든 사용자의 변경요청한 목록을 가지고 온다.
	 * @param changeReqDto
	 * @return
	 * @throws DataAccessException
	 */
	List<ChangeReqDto> selectAdminChangeReqList(ChangeReqDto changeReqDto) throws DataAccessException;

	/**
	 * 변경요청한 목록에 대한 상세내용을 가지고 온다. 
	 * @param changeReqDto
	 * @return
	 * @throws DataAccessException
	 */
	List<ChangeReqContentsDto> selectChangeReqContents(ChangeReqDto changeReqDto) throws DataAccessException;
	
	/**
	 * 자신이 변경요청한 목록을 가지고 온다.(엑셀파일용) 
	 * @param applyDto
	 * @return
	 * @throws DataAccessException
	 */
	List<ChangeReqDto> selectChangeReqListExcel(ChangeReqDto applyDto) throws DataAccessException;
	
	
	/**
	 * 모든 사용자의 변경요청한 목록을 가지고 온다.(엑셀파일용) 
	 * @param applyDto
	 * @return
	 * @throws DataAccessException
	 */
	List<ChangeReqDto> selectAdminChangeReqListExcel(ChangeReqDto applyDto) throws DataAccessException;
	
	/**
	 * 변경요청 정보를 삭제한다.
	 * @param changeReqDto
	 * @return
	 * @throws DataAccessException
	 */
	int deleteChangeReq(ChangeReqDto changeReqDto) throws DataAccessException;

	//int deleteChangeReqContents(Long no) throws DataAccessException;
	
	/**
	 * 변경요청한 정보를 업데이트한다.
	 * 
	 * @param changeReqDto
	 * @return
	 * @throws DataAccessException
	 */
	int updateChangeReq(ChangeReqDto changeReqDto) throws DataAccessException;

}
