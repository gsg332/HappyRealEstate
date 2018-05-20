/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 18. yongpal
*****************************************************************************/
package com.happyJ.realestate.sms;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/*****************************************************************************
 * 
 ******************************************************************************/
@Repository
public interface SmsSendDao {

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method insertSmsSendInfo 
	 *  @param smsDto
	 *  @return
	 **********************************************/
	int insertSmsSendInfo(SmsSendDto smsDto) throws DataAccessException;
	
}
