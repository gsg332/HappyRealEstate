/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 11. yongpal
*****************************************************************************/
package com.happyJ.realestate.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.FilterChainProxy;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.common
 *  @fileName : AccessIpFilter.java
 *  @author : yongpal
 *  @since 2016. 7. 11.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 7. 11.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 7. 11.        yongpal       create AccessIpFilter.java
 *  </pre>
 ******************************************************************************/
public class AccessIpFilter extends FilterChainProxy{

	static Logger logger = LoggerFactory.getLogger( AccessIpFilter.class );

	/* (non-Javadoc)
	 * @see org.springframework.security.web.FilterChainProxy#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.doFilter(request, response, chain);
	}
}
