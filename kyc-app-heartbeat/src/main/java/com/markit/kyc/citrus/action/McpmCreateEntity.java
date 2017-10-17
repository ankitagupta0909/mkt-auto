package com.markit.kyc.citrus.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.consol.citrus.actions.AbstractTestAction;
import com.consol.citrus.context.TestContext;
import com.markit.kyc.citrus.JsonUpdate;

public class McpmCreateEntity  extends AbstractTestAction {
	
	@Resource private RestTemplate customizedRestTemplate;

	private static final Logger logger = LoggerFactory.getLogger(McpmCreateEntity.class);
	
	@Override
	public void doExecute(TestContext context) {
		
		String URL = (String)context.getVariableObject("createEntity");
		String filePath = (String)context.getVariableObject("entityJson");
		String mcpmCompanyID=(String)context.getVariableObject("payloadClientId");
		String companyLegalName=(String)context.getVariableObject("KycComp");
		String mcpmEntityId=(String)context.getVariableObject("mcpmEntityId");
		String legalName=(String)context.getVariableObject("taskMcpmLegalName");
		String mcpmEntityType=(String)context.getVariableObject("entityType");
		
			String os = System.getProperty("os.name");
		if (!os.startsWith("Windows"))
			filePath = "/usr/share/kyc-heartbeat/" + filePath;

		try {
			JsonUpdate.jsonUpdate(filePath, mcpmCompanyID, companyLegalName, mcpmEntityId, legalName, mcpmEntityType);
		} catch (IOException e) {
			e.printStackTrace();
		}
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
       
        formData.add("jsonFile", new FileSystemResource(filePath));
        
        String res = customizedRestTemplate.postForObject(URL, formData, String.class);
       
        logger.info("Response object: "+res);

        try {
			JsonUpdate.setJsonDefaultState(filePath, mcpmCompanyID, companyLegalName, mcpmEntityId, legalName, mcpmEntityType);
		} catch (IOException e) {
			e.printStackTrace();
		}
        if (res != null && res.contains("errorMessage")) {
            logger.error("File {} upload error {}", filePath, res);
            //throw(new RuntimeException(res));
        } else 
        	logger.info("File {} succesfully uploaded to {}", filePath, URL);
	}
        
        

}














