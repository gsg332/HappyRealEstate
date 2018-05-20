/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 8. 1. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.protocol;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.happyJ.realestate.model.protocol.header.ResponseHeader;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.protocol
 *  @fileName : GetConfigResponse.java
 *  @author : yongpal
 *  @since 2016. 8. 1.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 8. 1.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 8. 1.        yongpal       create GetConfigResponse.java
 *  </pre>
 ******************************************************************************/
@XmlRootElement(name="response")
public class GetConfigResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8724543828771553513L;
	
	private ResponseHeader header;
	private Map<String, String> body;
	
	
	/**
	 * @return the header
	 */
	public ResponseHeader getHeader() {
		return header;
	}
	/**
	 * @param header the header to set
	 */
	public void setHeader(ResponseHeader header) {
		this.header = header;
	}
	/**
	 * @return the body
	 */
	public Map<String, String> getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(Map<String, String> body) {
		this.body = body;
	}

}
