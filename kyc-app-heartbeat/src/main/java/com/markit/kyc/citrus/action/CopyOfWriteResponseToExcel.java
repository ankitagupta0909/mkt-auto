package com.markit.kyc.citrus.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.consol.citrus.actions.AbstractTestAction;
import com.consol.citrus.context.TestContext;
import com.google.gson.Gson;
import com.markit.kyc.citrus.ExcelUtil;

public class CopyOfWriteResponseToExcel  extends AbstractTestAction {

	@Resource private RestTemplate customizedRestTemplate;

	private static final Logger logger = LoggerFactory.getLogger(CopyOfWriteResponseToExcel.class);

	@Override
	public void doExecute(TestContext context) {
		//public static void main(String args[]){		
		String rows1=context.getVariable("rows");
		System.out.println("Executed from WriteResponseJava"+rows1);

		String response_payload=context.getVariable("response_payload");
		System.out.println("Executed from WriteResponseJava"+response_payload);
	
		Gson gson=new Gson();
		Response resp=gson.fromJson(response_payload, Response.class);
		System.out.println("gson response  "+resp.rows.size());		
		System.out.println("row1"+resp.rows.get(1));
					
		int rowNum=1;
	    for(User u :resp.rows){
	    ExcelUtil.setColumnDataByColNameFirstTime("C:/AutomationWorkspaceAPI/kyc-app-heartbeat/src/data/ResponseSheet.xlsx", "users", "USERID", rowNum, u.getUSERID());
	    ExcelUtil.setColumnDataByColName("C:/AutomationWorkspaceAPI/kyc-app-heartbeat/src/data/ResponseSheet.xlsx", "users", "LASTNAME", rowNum, u.getLASTNAME());
	    ExcelUtil.setColumnDataByColName("C:/AutomationWorkspaceAPI/kyc-app-heartbeat/src/data/ResponseSheet.xlsx", "users", "COMPID", rowNum, u.getCOMPID());
	    ExcelUtil.setColumnDataByColName("C:/AutomationWorkspaceAPI/kyc-app-heartbeat/src/data/ResponseSheet.xlsx", "users", "EMAILID", rowNum, u.getEMAILID());
	    ExcelUtil.setColumnDataByColName("C:/AutomationWorkspaceAPI/kyc-app-heartbeat/src/data/ResponseSheet.xlsx", "users", "FIRSTNAME", rowNum, u.getFIRSTNAME());
	    ExcelUtil.setColumnDataByColName("C:/AutomationWorkspaceAPI/kyc-app-heartbeat/src/data/ResponseSheet.xlsx", "users", "LEADCONTACT", rowNum, u.getLEADCONTACT());        
	    rowNum++;
	    }
	    
	}

	}
