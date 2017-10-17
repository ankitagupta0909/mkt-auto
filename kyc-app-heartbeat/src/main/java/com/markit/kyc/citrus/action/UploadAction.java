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
import com.markit.kyc.citrus.CsvUpdate;

/**
 * Test
 *
 * @author Michael Smolyak
 * @since 2014-11-24
 */
public class UploadAction extends AbstractTestAction {
	
	@Resource private RestTemplate customizedRestTemplate;

	private static final Logger logger = LoggerFactory.getLogger(UploadAction.class);
	
	@Override
	public void doExecute(TestContext context) {
		
		String filePath = (String)context.getVariableObject("uploadFilePath");
		String uploadUrl = (String)context.getVariableObject("uploadUrl");
		
		CsvUpdate cp=new CsvUpdate();
		
		try {
		String arr=cp.writeCsv(filePath);
		String[] parts=arr.split("_");
		context.setVariable("legalName", parts[0].toString());
		context.setVariable("internalId", parts[1].toString());
		context.setVariable("taskMcpmLegalName", parts[3].toString());
		context.setVariable("mcpmEntityId", parts[2].toString());
		} catch (IOException e) {
			System.out.println("Unable to update CSV file"+e.getMessage());
			logger.error("Unable to update CSV file"+e.getMessage());
			e.printStackTrace();
		}
			String os = System.getProperty("os.name");
		if (!os.startsWith("Windows"))
			filePath = "/usr/share/kyc-heartbeat/" + filePath;
		
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
        formData.add("file", new FileSystemResource(filePath));

        String legalName = (String)context.getVariableObject("legalName");
        String internalId=(String)context.getVariableObject("internalId");
/*
        String taskMcpmLegalName = (String)context.getVariableObject("taskMcpmLegalName");
        String mcpmEntityId= (String)context.getVariableObject("mcpmEntityId");*/
        String res = customizedRestTemplate.postForObject(uploadUrl, formData, String.class);
        try {
			CsvUpdate.setCsvDefaultState(filePath,legalName,internalId);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Unable to set CSV template to default state"+e.getMessage());
		}
        if (res != null && res.contains("errorMessage")) {
            logger.error("File {} upload error {}", filePath, res);
            //throw(new RuntimeException(res));
        } else 
        	logger.info("File {} succesfully uploaded to {}", filePath, uploadUrl);
	}

	

}
