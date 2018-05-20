package com.happyJ.realestate.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/*****************************************************************************
 * 
 *  @packageName : com.happyJ.realestate.common.util
 *  @fileName : FileUploadUtils.java
 *  @author : yongpal
 *  @since 2016. 5. 17.
 *  @version 1.0 
 *  @see  :  
 *  @revision : 2016. 5. 17.
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE	           NAME			DESC
 *     -----------	 ----------   ---------------------------------------
 *     2016. 5. 17.        yongpal       create FileUploadUtils.java
 *  </pre>
 ******************************************************************************/
public class FileUploadUtils {

	static Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);
	
	 public static final int BUFF_SIZE = 2048;	
	 /**
     * 첨부로 등록된 파일을 서버에 업로드한다.
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static HashMap<String, String> uploadFile(MultipartFile file, String stordFilePath) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		//Write File 이후 Move File????
		String newName = "";
		//String stordFilePath = EgovProperties.getProperty("Globals.fileStorePath");
		String orginFileName = file.getOriginalFilename();
		
		//upload 폴더 생성여부
		File saveFolder = new File(stordFilePath);
	
		if (!saveFolder.exists() || saveFolder.isFile()) {
		    saveFolder.mkdirs();
		}
		
		//newName 은 Naming Convention에 의해서 생성
		newName = getTimeStamp() ; 
		newName += '.'+orginFileName.substring( orginFileName.lastIndexOf( "." ) + 1 );	// 확장자 넣기
		
		writeFile(file, newName, stordFilePath);

		map.put("originalFileName", orginFileName);
		map.put("uploadFileName", newName);
		map.put("filePath", stordFilePath);
		map.put("fileSize", String.valueOf(file.getSize()));
	
		return map;
    }
    
    public static HashMap<String, String> uploadCctvImageFile(MultipartFile file, String stordFilePath,String uploadFileName) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		//Write File 이후 Move File????
		String newName = "";
		//String stordFilePath = EgovProperties.getProperty("Globals.fileStorePath");
		String orginFileName = file.getOriginalFilename();
		
		//upload 폴더 생성여부
		File saveFolder = new File(stordFilePath);
	
		if (!saveFolder.exists() || saveFolder.isFile()) {
		    saveFolder.mkdirs();
		}
		
		// 업로드 파일명을 정해주면 그대로
		if (uploadFileName != null) {
			newName = uploadFileName;
			newName += '.'+orginFileName.substring( orginFileName.lastIndexOf( "." ) + 1 );	// 확장자 넣기
		} else {
			//newName 은 Naming Convention에 의해서 생성
			newName = getTimeStamp() ; 
			newName += '.'+orginFileName.substring( orginFileName.lastIndexOf( "." ) + 1 );	// 확장자 넣기
		}

		writeFile(file, newName, stordFilePath);

		map.put("originalFileName", orginFileName);
		map.put("uploadFileName", newName);
		map.put("filePath", stordFilePath);
		map.put("fileSize", String.valueOf(file.getSize()));
	
		return map;
    }
    
    public static boolean deleteFile(String stordFilePath, String fileName){
    	
    	File file = new File(stordFilePath + File.separator + fileName);

    	if (!file.isDirectory()){
    		return file.delete();
    	} else {
    		return false;
    	}
    }

    /**
     * 파일을 실제 물리적인 경로에 생성한다.
     *
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected static void writeFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
		
    	InputStream stream = null;
		OutputStream bos = null;
	
		try {
		    stream = file.getInputStream();
		    File cFile = new File(filePathBlackList(stordFilePath));
	
		    if (!cFile.isDirectory())
		    	cFile.mkdir();
		    	
		    bos = new FileOutputStream(filePathBlackList(stordFilePath + File.separator + newName));
	
		    int bytesRead = 0;
		    byte[] buffer = new byte[BUFF_SIZE];
	
		    while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
		    	bos.write(buffer, 0, bytesRead);
		    }
		} catch (Exception e) {
		    //e.printStackTrace();
		    //throw new RuntimeException(e);	// 보안점검 후속조치
			logger.error("writeFile : " , e);
		} finally {
		    if (bos != null) {
				try {
				    bos.close();
				} catch (Exception ignore) {
					//ignore.printStackTrace();
				}
		    }
		    if (stream != null) {
				try {
				    stream.close();
				} catch (Exception ignore) {
					//ignore.printStackTrace();
				}
		    }
		}
    }
    
    private static String getTimeStamp() {
    	
    	String rtnStr = null;

    	// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
    	String pattern = "yyyyMMddhhmmssSSS";

    	try {
    		SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
    		Timestamp ts = new Timestamp(System.currentTimeMillis());
    		
    		rtnStr = sdfCurrent.format(ts.getTime());
    		
		} catch (Exception e) {
			//e.printStackTrace();
			//throw new RuntimeException(e);	// 보안점검 후속조치
			logger.error("getTimeStamp : " , e);
		}
    	return rtnStr;
    }
        
    public static String filePathBlackList(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\

		return returnValue;
	}
    
}
