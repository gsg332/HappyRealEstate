package com.happyJ.realestate.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class JavaWebCrawler {

	public static void main(String[] args) throws ClientProtocolException, IOException {

		HttpPost http = new HttpPost("http://land.naver.com/article/articleList.nhn?rletTypeCd=A01&tradeTypeCd=A1&rletNo=5957&cortarNo=3017011200&hscpTypeCd=A01%3AA03%3AA04&mapX=127.3920215&mapY=36.3594112&mapLevel=13&page=&articlePage=&ptpNo=&rltrId=&mnex=&bildNo=&articleOrderCode=3&cpId=&period=&prodTab=&atclNo=&atclRletTypeCd=&location=&bbs_tp_cd=&sort=&siteOrderCode=&schlCd=&tradYy=&exclsSpc=&splySpcR=&cmplYn=");
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse response = httpClient.execute(http);
		HttpEntity entity = response.getEntity();
		ContentType contentType = ContentType.getOrDefault(entity);
		Charset charset = contentType.getCharset();
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
		StringBuffer sb = new StringBuffer();
		String line = "";

		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		Document doc = Jsoup.parse(sb.toString());
		
		Elements thElements = doc.getElementsByTag("thead").get(0).getElementsByTag("th");
		Elements trElements = doc.getElementsByTag("tbody").get(0).getElementsByTag("tr"); 
		
		String[] crawlerInfo = {"거래","확인일자","매물명","면적","동","층","매물가","연락처"};
				
		JSONArray jsonArr = new JSONArray();
		
		for(int i=0; i<trElements.size(); i+=2){
			JSONObject jsonObj = new JSONObject();
			for(int y=0; y<thElements.size(); y++){
				for(String info : crawlerInfo){
					if(thElements.get(y).text().trim().indexOf(info) > -1){
						int tdIndex = y;
						if(tdIndex > 2){
							tdIndex--;
						}
						jsonObj.put(info, trElements.get(i).getElementsByTag("td").get(tdIndex).text());
					}
				}
			}
			jsonObj.put("기타", trElements.get(i+1).getElementsByTag("td").get(0).text());
			
			jsonArr.add(jsonObj);
		}
		System.out.println(jsonArr.toJSONString());
	}
}
