/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 26. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.protocol;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.happyJ.realestate.model.protocol.body.RealPriceBody;
import com.happyJ.realestate.model.protocol.header.ResponseHeader;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.protocol
 *  @fileName : VMSLoginResponseDto.java
 *  @author : yongpal
 *  @since 2016. 7. 26.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 7. 26.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 7. 26.        yongpal       create VMSLoginResponseDto.java
 *  </pre>
 ******************************************************************************/
@XmlRootElement(name="response")
public class APIRealPriceResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -87408420527621638L;
	
	private ResponseHeader header;
	private RealPriceBody body;
	
	
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
	public RealPriceBody getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(RealPriceBody body) {
		this.body = body;
	}
	
}
