/*****************************************************************************
 * Copyright(c) 2015 TAK Information System. All rights reserved.
 * This software is the proprietary information of TAK Information System. 
 * 
 * Description : 
 * Create on 2015. 12. 23. yongpal
*****************************************************************************/
package com.happyJ.realestate.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/*****************************************************************************
 * 
 *  @packageName : com.tis.xinc.web.common.util
 *  @fileName : AppContextProvider.java
 *  @author : yongpal
 *  @since 2015. 12. 23.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2015. 12. 23.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2015. 12. 23.        yongpal       create AppContextProvider.java
 *  </pre>
 ******************************************************************************/
public class AppContextProvider implements ApplicationContextAware {
	
	private static ApplicationContext appContext = null;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		AppContextProvider.appContext = applicationContext;
	}
	
	public static ApplicationContext getAppContext(){
		return appContext;
	}

}
