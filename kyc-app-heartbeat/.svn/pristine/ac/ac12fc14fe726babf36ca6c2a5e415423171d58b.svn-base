package com.markit.kyc.citrus.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testng.reporters.jq.Main;

import com.consol.citrus.actions.AbstractTestAction;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.exceptions.ValidationException;

public class CompareBJ  extends AbstractTestAction {
	
	@Resource private RestTemplate customizedRestTemplate;

	private static final Logger logger = LoggerFactory.getLogger(CompareBJ.class);
	
	@Override
	public void doExecute(TestContext context) {
	 //public static void main(String args[]){
		String dbBj = (String)context.getVariableObject("databaseBJ");
		String bookingJur = (String)context.getVariableObject("bookingJurisdiction");
		
		//String a="Hong Kong | United Kingdom | United States";
		//String b="Hong Kong, United Kingdom, United States";

	String c= bookingJur.replace("|", ",");
	
	String bj=c.replaceAll("\\s","");
	String db=dbBj.replaceAll("\\s","");
			
System.out.println(bj + db);
		
     if (bj.equals(db)){
    	 System.out.println(bj+ " and "+db);
    	 logger.info("booking jurisdiction validation ok");
     }
     else{
    	 logger.error("false");
    	 throw new ValidationException("Booking Jurisdiction does not match, API Response:"+bj+"DB Response"+db);

     }
	}
}
