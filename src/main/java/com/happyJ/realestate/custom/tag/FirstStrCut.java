package com.happyJ.realestate.custom.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class FirstStrCut extends SimpleTagSupport implements DynamicAttributes {

	private static final long serialVersionUID = 5558859084651548496L;

	private Map<String, Object> attrs = new HashMap<String, Object>();

	public void doTag() throws JspException, IOException {

		JspWriter out = getJspContext().getOut();

		String value = (String) attrs.get("value");

		if (value != null) {

			int count = 0;

			StringTokenizer stn = new StringTokenizer(value, ",");

			while (stn.hasMoreTokens()) {

				String stnVal = stn.nextToken();

				if (count == 0) {
					value = stnVal;
				}

				count++;
			}

			if (count > 1) {
				out.println(value + " 외 " + (count-1) + "건");
			} else {
				out.println(value);
			}
		} else {
			out.println("");
		}

		return;
	}

	@Override
	public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
		attrs.put(localName, value);// 해쉬맵에 저장함
	}
}
