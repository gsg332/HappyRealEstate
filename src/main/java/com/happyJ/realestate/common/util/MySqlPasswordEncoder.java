/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 4. 6. yongpal
*****************************************************************************/
package com.happyJ.realestate.common.util;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.common.util
 *  @fileName : MySqlPasswordEncoder.java
 *  @author : yongpal
 *  @since 2016. 4. 6.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 4. 6.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 4. 6.        yongpal       create MySqlPasswordEncoder.java
 *  </pre>
 ******************************************************************************/
@Component
public class MySqlPasswordEncoder implements PasswordEncoder{

	/* (non-Javadoc)
	 * @see org.springframework.security.crypto.password.PasswordEncoder#encode(java.lang.CharSequence)
	 */
	@Override
	public String encode(CharSequence rawPassword) {

		if (rawPassword == null) {
			throw new NullPointerException();
		}

		byte[] bpara = new byte[rawPassword.length()];

		byte[] rethash;
		int i;
		for (i = 0; i < rawPassword.length(); i++)
			bpara[i] = (byte) (rawPassword.charAt(i) & 0xff);
		try {
			MessageDigest sha1er = MessageDigest.getInstance("SHA1");
			rethash = sha1er.digest(bpara); // stage1
			rethash = sha1er.digest(rethash); // stage2
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
		StringBuffer r = new StringBuffer(41);
		r.append("*");
		for (i = 0; i < rethash.length; i++) {
			String x = Integer.toHexString(rethash[i] & 0xff).toUpperCase();
			if (x.length() < 2)
				r.append("0");
			r.append(x);
		}

		return r.toString();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.crypto.password.PasswordEncoder#matches(java.lang.CharSequence, java.lang.String)
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		
		if (encodedPassword == null || rawPassword == null) {
			return false;
		}

		if (!encodedPassword.equals(encode(rawPassword))) {
			return false;
		}

		return true;
	}

}
