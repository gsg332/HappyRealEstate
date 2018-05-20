/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 26. yongpal
*****************************************************************************/
package com.happyJ.realestate.model.protocol;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.happyJ.realestate.model.protocol.body.ItemList;
import com.happyJ.realestate.model.protocol.header.ResponseHeader;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.model.protocol
 *  @fileName : ItemListResponseDto.java
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
 *     2016. 7. 26.        yongpal       create ItemListResponseDto.java
 *  </pre>
 ******************************************************************************/
@XmlRootElement(name="response")
public class ItemListResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2930548458662269788L;
	
	private ResponseHeader header;
	private List<ItemList> list;


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
	 * @return the list
	 */
	public List<ItemList> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<ItemList> list) {
		this.list = list;
	}


}
