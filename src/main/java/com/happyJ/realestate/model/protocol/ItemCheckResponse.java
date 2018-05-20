/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 29. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.protocol;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.happyJ.realestate.model.protocol.body.ItemCheckBody;
import com.happyJ.realestate.model.protocol.header.ResponseHeader;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.protocol
 *  @fileName : ItemCheckResponse.java
 *  @author : yongpal
 *  @since 2016. 7. 29.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 7. 29.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 7. 29.        yongpal       create ItemCheckResponse.java
 *  </pre>
 ******************************************************************************/
@XmlRootElement(name="response")
public class ItemCheckResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1921583934414029453L;
	
	private ResponseHeader header;
	private ItemCheckBody body;
	
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
	public ItemCheckBody getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(ItemCheckBody body) {
		this.body = body;
	}

}
