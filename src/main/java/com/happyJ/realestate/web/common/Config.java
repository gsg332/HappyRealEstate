package com.happyJ.realestate.web.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.happyJ.realestate.model.schema.ConfigDto;
import com.happyJ.realestate.model.schema.FnConfigDto;
import com.happyJ.realestate.service.FnConfigService;

public class Config {

	public static List<ConfigDto> configList;
	
	public static Map<String, Object> fnConfigList;

	
	public static Map<String, Object> getFnConfigList(FnConfigService fnConfigService){
		
		FnConfigDto fnConfigDto = new FnConfigDto();
		List<FnConfigDto> fnConfigList = fnConfigService.selectFnConfigListAll(fnConfigDto);

		Map<String, Object> fnConfig = new HashMap<String, Object>();
		
		for(int i=0; i<fnConfigList.size(); i++){
		      Map<String, Object> subFnConfigList = new HashMap<String, Object>();
		      Map<String, Object> fnConfigField = new HashMap<String, Object>();
		      
		      fnConfigDto = fnConfigList.get(i);

		      fnConfigField.put("Code", fnConfigDto.getItemCode());
		      fnConfigField.put("Value", fnConfigDto.getItemValue());
		      fnConfigField.put("Desc", fnConfigDto.getItemDescription());
		      
		      for(int y=0; y<fnConfigDto.getSubConfigList().size(); y++){
		    	  
		    	  FnConfigDto subConfigItem = fnConfigDto.getSubConfigList().get(y);
		    	  
		          Map<String, Object> subConfigField = new HashMap<String, Object>();
		          subConfigField.put("Code", subConfigItem.getItemCode());
		          subConfigField.put("Value", subConfigItem.getItemValue());
		          subConfigField.put("Desc", subConfigItem.getItemDescription());
		          subFnConfigList.put(subConfigItem.getItemCode(), subConfigField);
		      }	
		     
		      fnConfigField.put("SubConfigList", subFnConfigList);
		
		      fnConfig.put(fnConfigDto.getItemCode(), fnConfigField);
		}
		
		return fnConfig;
	}
	
	public static String getFnConfig(String... mapKeys){
		
		Map fnConfigMap = new HashMap();
		
		for(int i=0; i<mapKeys.length; i++){
			if(i == 0){
				fnConfigMap = (Map) Config.fnConfigList.get(mapKeys[i]);
			}else{
				if(fnConfigMap.get(mapKeys[i]) instanceof String){
					return (String) fnConfigMap.get(mapKeys[i]);
				}else{ //Map
					fnConfigMap = (Map) fnConfigMap.get(mapKeys[i]);	 
				}
			}
		}
		
		return fnConfigMap.toString();
	}
}
