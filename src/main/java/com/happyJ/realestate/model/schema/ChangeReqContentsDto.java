package com.happyJ.realestate.model.schema;

import com.happyJ.realestate.model.CommonDto;

public class ChangeReqContentsDto extends CommonDto {

	private static final long serialVersionUID = 5687934972399543067L;

	private long no;
	private String typeDepth1;
	private String typeDepth2;
	private String requestValue;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getTypeDepth1() {
		return typeDepth1;
	}

	public void setTypeDepth1(String typeDepth1) {
		this.typeDepth1 = typeDepth1;
	}

	public String getTypeDepth2() {
		return typeDepth2;
	}

	public void setTypeDepth2(String typeDepth2) {
		this.typeDepth2 = typeDepth2;
	}

	public String getRequestValue() {
		return requestValue;
	}

	public void setRequestValue(String requestValue) {
		this.requestValue = requestValue;
	}

}
