package com.markit.kyc.citrus.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;












import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.consol.citrus.actions.AbstractTestAction;
import com.consol.citrus.context.TestContext;
import com.google.gson.Gson;
import com.markit.kyc.citrus.ExcelUtil;

public class WriteResponseToExcel  extends AbstractTestAction {

	@Resource private RestTemplate customizedRestTemplate;

	private static final Logger logger = LoggerFactory.getLogger(WriteResponseToExcel.class);

	@Override
	public void doExecute(TestContext context) {
		//public static void main(String args[]){		
		String rows=context.getVariable("rows");
		System.out.println("Executed from WriteResponseJava"+rows);
		String response_payload=context.getVariable("response_payload");
		System.out.println("Executed from WriteResponseJava"+response_payload);

		JSONParser parser=new JSONParser();
		JSONObject json=null;
		try {
			json=(JSONObject) parser.parse(response_payload);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObj=(JSONObject) json;

		JSONArray rowssArray = (JSONArray) jsonObj.get("rows");
int rowNum=1;

		for (int i=0;i<rowssArray.size();i++){
			JSONObject elemObj=(JSONObject) rowssArray.get(i);                       
			//System.out.println(elemObj.get("LASTNAME"));      
			Iterator<String> it=elemObj.keySet().iterator();
			while(it.hasNext()){
				String key=it.next();
				System.out.println(key);
				String value=elemObj.get(key).toString();
				System.out.println(value);

			ExcelUtil.setColumnDataByColNameFirstTime("src/data/ResponseSheet.xlsx", "users", key, rowNum,value);		
		}
				rowNum++;	
		}
	
	}
}

	