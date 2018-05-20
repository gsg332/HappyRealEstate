/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 8. 2. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.protocol;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.happyJ.realestate.model.protocol.body.SyncCctvBody;
import com.happyJ.realestate.model.protocol.header.ResponseHeader;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.protocol
 *  @fileName : SyncCctvResponse.java
 *  @author : yongpal
 *  @since 2016. 8. 2.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 8. 2.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 8. 2.        yongpal       create SyncCctvResponse.java
 *  </pre>
 ******************************************************************************/
@XmlRootElement(name="response")
public class SyncCctvResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 934233947271406635L;
	
	private ResponseHeader header;
	private SyncCctvBody body;
	
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
	public SyncCctvBody getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(SyncCctvBody body) {
		this.body = body;
	}

}
