package com.markit.kyc.citrus.action;
import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.consol.citrus.actions.AbstractTestAction;
import com.consol.citrus.context.TestContext;

public class MCPMUploadDoc  extends AbstractTestAction {
	
	@Resource private RestTemplate customizedRestTemplate;
	static String documentId=null;
	static String documentType=null;
	static String originalFileName=null;
	static String documentUploadedByLoginName=null;
	static String documentUploadedByFirstName=null;
	static String documentUploadedByLastName=null;

	private static final Logger logger = LoggerFactory.getLogger(MCPMUploadDoc.class);
	//static Map<String,String> jsonList= new HashMap<String, String>();
	
	@Override
	public void doExecute(TestContext context) {
		String fileContent = (String)context.getVariableObject("fileContent");
		String uploadUrl = (String)context.getVariableObject("uploadUrl");
		String fileContentType=(String)context.getVariableObject("fileContentType");
		String documentType=(String)context.getVariableObject("documentType");
		String fileName=(String)context.getVariableObject("fileName");
	
	
		String os = System.getProperty("os.name");
		if (!os.startsWith("Windows"))
			fileContent = "/usr/share/kyc-heartbeat/" + fileContent;
		
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
        formData.add("fileContent", new FileSystemResource(fileContent));
        formData.add("documentType",documentType);
        formData.add("fileName",fileName);
        formData.add("fileContentType",fileContentType);
        
        String res = customizedRestTemplate.postForObject(uploadUrl, formData, String.class);
        
        JSONObject json = new JSONObject();
        try {
			json = (JSONObject) new JSONParser().parse(res);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    /*    System.out.println("Test Json");
        System.out.println(json.get("documentId"));*/
        documentId=json.get("documentId").toString();
        context.setVariable("mcpmDocId",documentId);
        documentType=json.get("documentType").toString();
        context.setVariable("mcpmDocType",documentType);
        
        originalFileName=json.get("originalFileName").toString();
        context.setVariable("mcpmFileName",originalFileName);
        
        documentUploadedByLoginName=json.get("documentUploadedByLoginName").toString();
        context.setVariable("mcpmEmailAddress",documentUploadedByLoginName);
        
        documentUploadedByLastName=json.get("documentUploadedByLastName").toString();
        context.setVariable("mcpmLastName",documentUploadedByLastName);
        
        documentUploadedByFirstName=json.get("documentUploadedByFirstName").toString();
        context.setVariable("mcpmFirstName",documentUploadedByFirstName);
        
            
        if (res != null && res.contains("errorMessage")) {
            logger.error("File {} upload error {}", fileContent, res);
            //throw(new RuntimeException(res));
        } else 
        	logger.info("File {} succesfully uploaded to {}", fileContent, uploadUrl);
	}
        

}
