/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 12. 13. jincheol
*****************************************************************************/
package com.happyJ.realestate.common.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.common.view
 *  @fileName : ImageView.java
 *  @author : jincheol
 *  @since 2016. 12. 13.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 12. 13.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 12. 13.   jincheol       create ImageView.java
 *  </pre>
 ******************************************************************************/
public class ImageView extends AbstractView {
	
	@Value("#{config['cctvimage.file.path']}")
	private String cctvImagePath;
 
    public ImageView() {
        setContentType("image/jpeg;charset=utf-8");
    }
 
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String managecode = (String) model.get("managecode");
 
        File file = new File(cctvImagePath + File.separator + managecode + ".jpg");
 
        if(file.exists()){
        	response.setContentType(getContentType());
            response.setContentLength((int) file.length());
            response.setHeader("Content-Transfer-Encoding", "binary");
     
            OutputStream out = response.getOutputStream();
            FileInputStream fis = null;
     
            try {
                fis = new FileInputStream(file);
                FileCopyUtils.copy(fis, out);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null)
                    fis.close();
            }
        }
    }
 
}