/*****************************************************************************
 * Copyright(c) 2016 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2016. 7. 21. yongpal
*****************************************************************************/
package com.happyJ.realestate;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;

import com.happyJ.realestate.common.message.SmsMessageEnum;

/*****************************************************************************
 * 
 ******************************************************************************/
public class EnumTest {
	
	@Value("#{config['video.file.path']}")
	private static String videoFilePath;

	/**********************************************
	 *  <pre>
	 *  개요 : 
	 *  </pre>
	 * 	@Method main 
	 *  @param args
	 **********************************************/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		SmsMessageEnum smsObj = SmsMessageEnum.NEW_JOIN;
//		checkEnumValue(smsObj);
/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		String procDate = "2016-08-01 130554";
		try {
			System.out.println(sdf.parse(procDate));
			Date dd = sdf.parse(procDate);
			Date cc = new Date();
			
			System.out.println("날짜 비교"+(cc.compareTo(dd) < 0));
			
			
			System.out.println("procDate : "+dd.getTime()+", today : "+cc.getTime());
			long diff = cc.getTime() - dd.getTime();
			System.out.println("diff : "+diff);
			System.out.println("diffDays : "+ (diff / (24*60*60*1000)));
			long fileVol = 536870912;
			long speed = fileVol / (diff / 1000);
			if ((speed / 1024) > 1){
				System.out.println((speed / 1024)+"KByte/s");
			} else {
				System.out.println(speed+"Byte/s");
			}
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		*/
		/**
		 * Daum Map Local API Text
		 * *//*
		try {
			URL url = new URL("https://apis.daum.net/local/geo/coord2addr?apikey=3f46e5b5dded37a6c3817d98fe5e894f&longitude=127.10863694633468&latitude=37.40209529907863&inputCoordSystem=WGS84&output=json");
			try {
				InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObj = (JSONObject) jsonParser.parse(isr);
				
				System.out.println(jsonObj.get("code2"));
				
				System.out.println(jsonObj.toJSONString());
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		try {
			File file = new File("/ves_data/video/1697");
			
			System.out.println(file);
			
			String[] paths = file.list();
			
			System.out.println(paths);
			
			for (String path : paths){
				System.out.println(path);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private static void checkEnumValue(SmsMessageEnum smsObj){
		for(SmsMessageEnum smsMsgEnum : SmsMessageEnum.values()){
			if (smsMsgEnum.equals(smsObj)){
				System.out.println(smsMsgEnum.getSubject());
				System.out.println(smsMsgEnum.getSmsMsg());
			}
		}
	}

}
