/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 28. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.protocol;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.happyJ.realestate.model.protocol.body.ItemUpdateBody;
import com.happyJ.realestate.model.protocol.header.ResponseHeader;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.protocol
 *  @fileName : ItemUpdateResponse.java
 *  @author : yongpal
 *  @since 2016. 7. 28.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 7. 28.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 7. 28.        yongpal       create ItemUpdateResponse.java
 *  </pre>
 ******************************************************************************/
@XmlRootElement(name="response")
public class ItemUpdateResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3569992722037789703L;
	
	private ResponseHeader header;
	private ItemUpdateBody body;
	
	
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
	public ItemUpdateBody getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(ItemUpdateBody body) {
		this.body = body;
	}

}
