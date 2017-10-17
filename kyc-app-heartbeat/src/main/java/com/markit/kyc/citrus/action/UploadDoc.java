package com.markit.kyc.citrus.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.consol.citrus.actions.AbstractTestAction;
import com.consol.citrus.context.TestContext;

public class UploadDoc  extends AbstractTestAction {
	
	@Resource private RestTemplate customizedRestTemplate;

	private static final Logger logger = LoggerFactory.getLogger(UploadDoc.class);
	
	@Override
	public void doExecute(TestContext context) {
		
		String filePath = (String)context.getVariableObject("uploadFilePath");
		String uploadUrl = (String)context.getVariableObject("uploadUrl");


		String os = System.getProperty("os.name");
		if (!os.startsWith("Windows"))
			filePath = "/usr/share/kyc-heartbeat/" + filePath;
		
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
        formData.add("file", new FileSystemResource(filePath));

        String res = customizedRestTemplate.postForObject(uploadUrl, filePath, String.class);
       
        if (res != null && res.contains("errorMessage")) {
            logger.error("File {} upload error {}", filePath, res);
            //throw(new RuntimeException(res));
        } else 
        	logger.info("File {} succesfully uploaded to {}", filePath, uploadUrl);
	}
	
	
}
