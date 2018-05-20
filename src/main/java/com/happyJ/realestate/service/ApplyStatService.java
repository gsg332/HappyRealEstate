/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 28. yongpal
*****************************************************************************/
package com.happyJ.realestate.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyJ.realestate.common.util.StringUtil;
import com.happyJ.realestate.dao.ApplyStatDao;
import com.happyJ.realestate.model.custom.ApplyStatCustomDto;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.service
 *  @fileName : ApplyStatisticsService.java
 *  @author : yongpal
 *  @since 2016. 4. 28.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 28.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 28.        yongpal       create ApplyStatisticsService.java
 *  </pre>
 ******************************************************************************/
@Service
public class ApplyStatService {

	@Autowired
	private ApplyStatDao applyStatDao;
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method userMainTotalCounts 
	 **********************************************/
	public ApplyStatCustomDto userMainTotalCounts(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.userMainTotalCounts(applyStatDto);
	}

	/**********************************************
	 *  <pre>
	 *  개요 : 사용자 신청현황 그래프
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method userMainDateGraphCounts 
	 **********************************************/
	public List<ApplyStatCustomDto> userMainDateGraphCounts(ApplyStatCustomDto applyStatDto){
		
		return applyStatDao.userMainDateGraphCounts(applyStatDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method userMainResultCounts 
	 **********************************************/	
	public ApplyStatCustomDto userMainResultCounts(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.userMainResultCounts(applyStatDto);
	}	

	/**********************************************
	 *  <pre>
	 *  개요 : 만료예정 영상 리스트
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method userMainLimitDateList 
	 **********************************************/
	public List<ApplyStatCustomDto> userMainLimitDateList(ApplyStatCustomDto applyStatDto){
		
		return applyStatDao.userMainLimitDateList(applyStatDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method userMainLimitDateCounts 
	 **********************************************/
	public ApplyStatCustomDto userMainLimitDateCounts(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.userMainLimitDateCounts(applyStatDto);
	}	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method userStatSolveList 
	 **********************************************/
	public List<ApplyStatCustomDto> userStatSolveList(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.userStatSolveList(applyStatDto);
	}	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method userStatReasonList 
	 **********************************************/
	public List<ApplyStatCustomDto> userStatReasonList(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.userStatReasonList(applyStatDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method userStatDetailEtcReasonList 
	 **********************************************/
	public List<ApplyStatCustomDto> selectDetailEtcReasonList(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.selectDetailEtcReasonList(applyStatDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method selectDetailEtcReasonListCount 
	 **********************************************/
	public int selectDetailEtcReasonListCount(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.selectDetailEtcReasonListCount(applyStatDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method userStatAllList 
	 **********************************************/
	public String[] userStatAllList(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.userStatAllList(applyStatDto);
	}	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method userStatYears 
	 **********************************************/
	public String[] userStatYears(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.userStatYears(applyStatDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 사용자 소속 조회
	 *  </pre>
	 * 	@param applyStatCustomDto 
	 * 	@Method selectPartList 
	 **********************************************/
	public List<ApplyStatCustomDto> selectPartList(ApplyStatCustomDto applyStatCustomDto) {

		return applyStatDao.selectPartList(applyStatCustomDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method resultStatApplyList 
	 **********************************************/
	public List<ApplyStatCustomDto> resultStatApplyList(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.resultStatApplyList(applyStatDto);
	}	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method resultStatPositionList 
	 **********************************************/
	public List<ApplyStatCustomDto> resultStatPositionList(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.resultStatPositionList(applyStatDto);
	}	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method resultStatDepartList 
	 **********************************************/
	public List<ApplyStatCustomDto> resultStatDepartList(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.resultStatDepartList(applyStatDto);
	}	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method resultStatUserList 
	 **********************************************/
	public List<ApplyStatCustomDto> resultStatUserList(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.resultStatUserList(applyStatDto);
	}	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method resultStatCrimeList 
	 **********************************************/
	public List<ApplyStatCustomDto> resultStatCrimeList(ApplyStatCustomDto applyStatDto) {

		List<ApplyStatCustomDto> list = applyStatDao.resultStatCrimeList(applyStatDto);
		
		for(int i=0; i<list.size(); i++){
			
			ApplyStatCustomDto applyStatCutomDto = list.get(i); 
			
			int murderCnt = 0;
			int robberCnt = 0;
			int rapeCnt = 0;
			int theftCnt = 0;
			int violenceCnt = 0;
			int accidentCnt = 0;
			int destroyCnt = 0;
			int etcCnt = 0;
			
			if(applyStatCutomDto != null){
				murderCnt = applyStatCutomDto.getMurderCnt() != null ? Integer.parseInt(applyStatCutomDto.getMurderCnt()) : 0;
				robberCnt = applyStatCutomDto.getRobberCnt() != null ? Integer.parseInt(applyStatCutomDto.getRobberCnt()) : 0;
				rapeCnt = applyStatCutomDto.getRapeCnt() != null ? Integer.parseInt(applyStatCutomDto.getRapeCnt()) : 0;
				theftCnt = applyStatCutomDto.getTheftCnt() != null ? Integer.parseInt(applyStatCutomDto.getTheftCnt()) : 0;
				violenceCnt = applyStatCutomDto.getViolenceCnt() != null ? Integer.parseInt(applyStatCutomDto.getViolenceCnt()) : 0;
				accidentCnt = applyStatCutomDto.getAccidentCnt() != null ? Integer.parseInt(applyStatCutomDto.getAccidentCnt()) : 0;
				destroyCnt = applyStatCutomDto.getDestroyCnt() != null ? Integer.parseInt(applyStatCutomDto.getDestroyCnt()) : 0;
				etcCnt = applyStatCutomDto.getEtcCnt() != null ? Integer.parseInt(applyStatCutomDto.getEtcCnt()) : 0;
			}
			
			Map<String, Integer> m = new HashMap<String, Integer>();
	        m.put("murderCnt", murderCnt);
	        m.put("robberCnt", robberCnt);
	        m.put("rapeCnt", rapeCnt);
	        m.put("theftCnt", theftCnt);
	        m.put("violenceCnt", violenceCnt);
	        m.put("accidentCnt", accidentCnt);
	        m.put("destroyCnt", destroyCnt);
	        m.put("etcCnt", etcCnt);

	        int rank = 0;
	        int preCnt = 0;
	        int currCnt = 0;
	        
	        for (Iterator y = sortByValue(m).iterator(); y.hasNext(); ) {
	            String key = (String) y.next();
	            
	            currCnt = (int) m.get(key);
	            
	            switch (key) {
				case "murderCnt":
					if(currCnt == preCnt) list.get(i).setMurderRank(rank);
					else list.get(i).setMurderRank(++rank);
					break;
				case "robberCnt":
					if(currCnt == preCnt) list.get(i).setRobberRank(rank);
					else list.get(i).setRobberRank(++rank);
					break;
				case "rapeCnt":
					if(currCnt == preCnt) list.get(i).setRapeRank(rank);
					else list.get(i).setRapeRank(++rank);
					break;
				case "theftCnt":
					if(currCnt == preCnt) list.get(i).setTheftRank(rank);
					else list.get(i).setTheftRank(++rank);
					break;
				case "violenceCnt":
					if(currCnt == preCnt) list.get(i).setViolenceRank(rank);
					else list.get(i).setViolenceRank(++rank);
					break;
				case "accidentCnt":
					if(currCnt == preCnt) list.get(i).setAccidentRank(rank);
					else list.get(i).setAccidentRank(++rank);
					break;
				case "destroyCnt":
					if(currCnt == preCnt) list.get(i).setDestroyRank(rank);
					else list.get(i).setDestroyRank(++rank);
					break;
				case "etcCnt":
					if(currCnt == preCnt) list.get(i).setEtcRank(rank);
					else list.get(i).setEtcRank(++rank);
					break;
				default:
					break;
				}
	            preCnt = currCnt;
	        }
		}
		
		return list;
	}
	
	public static List sortByValue(final Map m) {
		List keys = new ArrayList();
		keys.addAll(m.keySet());
	    Collections.sort(keys, new Comparator() {
	    	public int compare(Object o1, Object o2) {
	    		Object v1 = m.get(o1);
	            Object v2 = m.get(o2);
	            if (v1 == null) {
	            	return (v2 == null) ? 0 : 1;
	            }else if (v1 instanceof Comparable) {
	            	return ((Comparable) v1).compareTo(v2);
	            }else {
	            	return 0;
	            }
	    	}
	    });
	    Collections.reverse(keys);
	    return keys;
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method resultStatCrimeListRank 
	 **********************************************/
	/*
	public List<ApplyStatCustomDto> resultStatCrimeListRank(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.resultStatCrimeListRank(applyStatDto);
	}
	 */	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method resultStatApprTimeList 
	 **********************************************/
	public List<ApplyStatCustomDto> resultStatApprTimeList(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.resultStatApprTimeList(applyStatDto);
	}	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method resultStatPlayList 
	 **********************************************/
	public List<ApplyStatCustomDto> resultStatPlayList(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.resultStatPlayList(applyStatDto);
	}	
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method resultStatVolumeList 
	 **********************************************/
	public List<ApplyStatCustomDto> resultStatVolumeList(ApplyStatCustomDto applyStatDto) {
		
		List<ApplyStatCustomDto> resultStatVolumeList = applyStatDao.resultStatVolumeList(applyStatDto);
		
		if (!resultStatVolumeList.isEmpty()){
			//for(int i =0; i <resultStatVolumeList.size(); i++){
			for(ApplyStatCustomDto resultStatVolume : resultStatVolumeList){
				
				resultStatVolume.setMaxValTr(StringUtil.convertVolumeStr(resultStatVolume.getMaxVal()));
				
				resultStatVolume.setMinValTr(StringUtil.convertVolumeStr(resultStatVolume.getMinVal()));
				
				resultStatVolume.setAvgValTr(StringUtil.convertVolumeStr(resultStatVolume.getAvgVal()));
				
			}
		}
		
		return resultStatVolumeList;
//		return applyStatDao.resultStatVolumeList(applyStatDto);		
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method resultStatTimeList 
	 **********************************************/
	public List<ApplyStatCustomDto> resultStatTimeList(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.resultStatTimeList(applyStatDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method applyBusinessList 
	 **********************************************/
	public ApplyStatCustomDto applyBusinessList(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.applyBusinessList(applyStatDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param applyStatDto 
	 * 	@Method itemUseResultDiff 
	 **********************************************/
	public List<ApplyStatCustomDto> itemUseResultDiff(ApplyStatCustomDto applyStatDto) {

		return applyStatDao.itemUseResultDiff(applyStatDto);
	}
	
	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * @param  
	 * 	@Method selectItemMinDate 
	 **********************************************/
	public String selectItemMinDate() {

		return applyStatDao.itemMinDate();
	}
}
