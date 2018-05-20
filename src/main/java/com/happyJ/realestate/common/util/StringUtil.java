package com.happyJ.realestate.common.util;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/******************************************************************************
 *  @packageName : com.tis.xinc.web.common.util
 *  @fileName : StringUtil.java
 *  @content : 문자열 관련 함수
 *  
 *  <pre>
 *  << Modification Information >>
 *    DATE            NAME         DESC
 *    -------------   ----------   ---------------------------------------
 *    2015. 11. 1.                 create
 *  </pre>
 ******************************************************************************/

public class StringUtil {
	
	private static final Pattern NON_CHARACTER_PATTERN = Pattern.compile("([^\\d])");//숫자
	private static final Pattern NUMBER_PATTERN = Pattern.compile("([^\\w])");//알파벳이나숫자
	private static final Pattern IP_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
	private static final Pattern SPECIAL_PATTERN = Pattern.compile(".*[^가-힣a-zA-Z0-9].*");//특수문자 체크
	private static final Pattern ETC_PATTERN= Pattern.compile("[\\s]");//줄바꿈.현재 안되는듯?(\r\n|\n|\r) 안됨 (s) 안되는듯
	private final static String EMPTY_STRING = "";
	
	
	/**
     * null 인 문자열을 "" 로 치환한다.
     * @param str 문자열
     * @return null 을 제거한 문자열
     */
	public static String nvl(Object str){
		String val = "" ;
		
		if(str == null){
			val = "" ;
		}else{
			val = str.toString().trim() ;
		}
		
		return val ;
	}
	
	/**
     * null 인 문자열을 "" 로 치환한다.
     * @param str 문자열, defaultValue 기본값
     * @return null 을 제거한 문자열
     */
	public static String nvl(Object str, String defaultValue){
		String val = "" ;
		
		if(str == null||str.equals("")){
			val = defaultValue ;
		}else{
			val = str.toString().trim() ;
		}
		
		return val ;
	}
	
	/**
     * null 인 문자열을 "" 로 치환한 후, int 형으로 변환한다.
     * @param str 문자열
     * @return int
     */
	public static int nvlInt(String str){
		int page = 1 ;
		
		try{
    		page = Integer.parseInt(str) ;
    		if(page < 0) page = 1 ;
    	}catch(Exception e){
    		page = 1 ;
    	}
		
		return page ;
	}
	
    /**
     * 파일 경로에서 상위 경로 문자열을 제거한다.
     * @param s 파일 경로
     * @return 상위 경로 문자열이 제거된 파일 경로
     */
    public static String getPath(String s) {
        String r;
        if (s == null) return EMPTY_STRING;
        
        r = StringUtil.replace(s, "..", "");
        
        return r;
    }
    
    /**
     * 문자열 중 치환 대상 문자열을 치환 문자열을 변환한다.
     * 
     * @param s 원본 문자열
     * @param old 치환 대상 문자열
     * @param replacement 치환 문자열
     * @return 변환된 문자열
     */
    public static String replace(String s, String old, String replacement) {
        if (s == null || old == null)
            return EMPTY_STRING;
        int i = s.indexOf(old);
        StringBuffer r = new StringBuffer();

        if (i == -1)
            return s;
        r.append(s.substring(0, i)).append(replacement);
        if (i + old.length() < s.length())
            r.append(replace(s.substring(i + old.length(), s.length()), old, replacement));

        return r.toString();
    }
    
    public static String encode_in(String str) {

		str = replace(str, "&", "&amp;");//
		str = replace(str, "<", "&lt;");//
		str = replace(str, ">", "&gt;");//	
		str = replace(str, "\"", "&quot;");// 문자열 내의 HTML코드 무력화
		str = replace(str, "\'", "&#039;");// 문자열 내의 HTML코드 무력화
		str = replace(str, "'", "&#039;");// 문자열 내의 HTML코드 무력화

		return str;
	}

	public static String decodestr(String str) {

		str = str.trim();
		str = replace(str, "&amp;", "&");//
		str = replace(str, "&lt;", "<");//
		str = replace(str, "&gt;", ">");//
		str = str.replaceAll("&quot;", "\"");// 문자열 내의 HTML코드 무력화
		// html 편집기에서는 " 이게 &quot;걸로 입력되어야 나옴. 그래서 뺌.

		str = str.replaceAll("&#039;", "'");// 문자열 내의 HTML코드 무력화
		return str;
	}
	
    /**
     * JavaScript를 Disable시킨다.
     * 
     * @param param 원본 문자열
     * @return JavaScript가 Disable된 문자열
     */
    public static String disableScript(String str) {
    	if(str == null)
    		return null;
    	
		String result = str;
		int inx = 0;
		
		inx = result.toLowerCase().indexOf("<script");
		
		if(inx!=-1) {
			result = replaceToLtGt(result, inx);
		}
		
		inx = result.toLowerCase().indexOf("</script");
		
		if(inx!=-1) {
			result = replaceToLtGt(result, inx);
		}
		
		return result;
	}
	
	private static String replaceToLtGt(String str, int inx) {
		str = str.substring(0, inx) + "&lt;" + str.substring(inx+1);
		
		inx = str.indexOf(">", inx);
		
		if(inx!=-1) {
			str = str.substring(0, inx) + "&gt;" + str.substring(inx+1);
		}
		
		return str;
	}
	
    /**
     * 문자열을 지정한 분리자에 의해 배열로 리턴하는 메서드.
     * @param source 원본 문자열
     * @param separator 분리자
     * @return result 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator) throws NullPointerException {
        String[] returnVal = null;
        int cnt = 1;

        int index = source.indexOf(separator);
        int index0 = 0;
        while (index >= 0) {
            cnt++;
            index = source.indexOf(separator, index + 1);
        }
        returnVal = new String[cnt];
        cnt = 0;
        index = source.indexOf(separator);
        while (index >= 0) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);

        return returnVal;
    }
    
    /**
     * 숫자이외를 제거하고 순수숫자만 표현합니다.
     * @param orgString
     * @return pureString
     */
    public static String getOnlyNumber(String orgString){
        StringBuffer  destStringBuffer = new StringBuffer();
        Matcher m = NON_CHARACTER_PATTERN.matcher(orgString);
        
        while(m.find()){
            m.appendReplacement(destStringBuffer, "");
        }
        m.appendTail(destStringBuffer);
        return destStringBuffer.toString().toLowerCase();
    }
    
    /**
     * 특수문자를 제거하고 순수캐릭터만 반환.
     * @param orgString
     * @return pureString
     */
    public static String getOnlyString(String orgString){
        StringBuffer  destStringBuffer = new StringBuffer();
        Matcher m = NUMBER_PATTERN.matcher(orgString);
        
        while(m.find()){
            m.appendReplacement(destStringBuffer, "");
        }
        m.appendTail(destStringBuffer);
        return destStringBuffer.toString().toLowerCase();
    }
    
    public static void main(String[] args) throws Exception {
    	System.out.println(StringUtil.getEtcString("333\n3	4 556\n"));
    	System.out.println(isSpecial("tkatlrdl"));
    	System.out.println(isSpecial("삼식이tkatlrdl!@#$%12345"));
    	System.out.println(isSpecial("356622050043505"));
    }
    
    /**
     * 
     * 줄바꿈과 공백제거.
     * 
     * @파라미터 : 
     * @파라미터 : 
     * @return : String
     * @exception :
     */
    public static String getEtcString(String orgString){
    	StringBuffer  destStringBuffer = new StringBuffer();
        Matcher m = ETC_PATTERN.matcher(orgString);
        
        while(m.find()){
            m.appendReplacement(destStringBuffer, "");
        }
        m.appendTail(destStringBuffer);
        
        orgString=destStringBuffer.toString().toLowerCase();
        orgString=StringReplace(orgString);
        return orgString;
    }
    
    /**
     * 
     * 특수문자체크
     * 
     * @파라미터 : 
     * @파라미터 : 
     * @return : boolean
     * @exception :
     */
    public static boolean isSpecial(final String input) {
    	//System.out.println("=================isIPv4Address : "+input);
        return SPECIAL_PATTERN.matcher(input).matches();
    }
    /**
     * 
     * ip4형식의 검증
     * 
     * @파라미터 : 
     * @파라미터 : 
     * @return : boolean
     * @exception :
     */
    public static boolean isIPv4Address(final String input) {
    	//System.out.println("=================isIPv4Address : "+input);
        return IP_PATTERN.matcher(input).matches();
    }
    /**
     * 
     * 숫자만 검증.
     * 
     * @파라미터 : 
     * @파라미터 : 
     * @return : boolean
     * @exception :
     */
    public static boolean isNumber(final String input) {
        return NON_CHARACTER_PATTERN.matcher(input).matches();
    }
    
    /**
     * 
     * 특수문자제거 (한글,영문,숫자 잔존..)
     * 
     * @파라미터 : 
     * @파라미터 : 
     * @return : String
     * @exception :
     */
    public static String StringReplace(String str){       
        //String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
    	String match = "[\\s]";
        str =str.replaceAll(match, "");
        return str;
     }
    
    /**
     * insert,update,delete 등의 결과값을 가져와서 성공실패 code로 반환
     * @param int
     * @return String
     */
    public static String successYn(int result){
    	
    	String errCode = "";
    	if ( result < 1 )  {                             
            errCode = "FAIL_ERROR" ;        // 삭제시 오류발생
        }else {
            errCode = "SUCCESS" ;           // 정상삭제
        }
    	return errCode;
    }
    
    /**
     * 천단위마다 콤마 넣기
     * @param Double
     * @return String
     * */
    public static String toNumComma(double num) {
    	DecimalFormat df = new DecimalFormat("#,###.##");
    	return df.format(num);
    }
    
    /**
	 * (length - str.length) 만큼 앞에 0을 추가한다.
	 * @param str
	 * @param length
	 * @return
	 */
	public static String addZero (String str, int length) {
		String temp = "";
		for (int i = str.length(); i < length; i++)
			temp += "0";
		temp += str;
		return temp;
	}

	public static boolean isEmpty(String str) {
		if(str == null || str.length() == 0) {
			return true;
		}else {
			return false;
		}
	}

	public static String convertVolumeStr(String volume){
		
		String retFormat = "0";
       	String[] s = { "bytes", "KB", "MB", "GB", "TB", "PB" };
       	
       	String bytes = volume;
       	Double size;
       	
       	if (bytes != null){
       		size = Double.parseDouble(bytes);
       		
       		if (bytes != "0") {
                int idx = (int) Math.floor(Math.log(size) / Math.log(1024));
                DecimalFormat df = new DecimalFormat("#,###.##");
                double ret = ((size / Math.pow(1024, Math.floor(idx))));
                retFormat = df.format(ret) + " " + s[idx];
           	} else {
                retFormat += " " + s[0];
           	}
       		
       	} else {
       		retFormat = null;
       	}
       	
		return retFormat;
	}
}
