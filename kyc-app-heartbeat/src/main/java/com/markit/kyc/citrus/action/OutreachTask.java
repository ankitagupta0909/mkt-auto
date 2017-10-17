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

public class OutreachTask  extends AbstractTestAction {
	
	@Resource private RestTemplate customizedRestTemplate;

	private static final Logger logger = LoggerFactory.getLogger(OutreachTask.class);
	
	@Override
	public void doExecute(TestContext context) {
		
		String URL = (String)context.getVariableObject("createOutreachQues");
		String filePath = (String)context.getVariableObject("uploadJson");
		
			String os = System.getProperty("os.name");
		if (!os.startsWith("Windows"))
			filePath = "/usr/share/kyc-heartbeat/" + filePath;

		String outreachRequestJson="";
		try {
			outreachRequestJson = ReadText.returnJson(filePath);
		} catch (Exception e) {
			logger.error("Unable to read JsonFile. filePath ="+filePath);
			e.printStackTrace();
			
		}
		
	

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
       
        formData.add("outreachRequestJson",outreachRequestJson);
 
        
        String res = customizedRestTemplate.postForObject(URL, formData, String.class);
       
        logger.info("Response object: "+res);

  
        
        

}
}












//String outreachRequestJson="{       \"forms\":[           {               \"id\":\"root\",             \"name\":\"Kyc Entity Profile\",             \"fields\":[                   {                       \"name\":\"Company.tradingAs\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.shortName\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.entityName.previousLegalName\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.formation.ongoingExistance\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.legalName\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.legalForm\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.entityType\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.structure\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.formation.ownershipType\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.contact.role\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.addresses.addressType\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.formation.website\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.countryOfBusiness\",                     \"index\":0,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.bearerShare.articlesAllowIssuance\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.bearerShare.jurisdictionAllowsIssuance\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.bearerShare.legalFormAllowsIssuance\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.bearerShare.attestationCompleted\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.bearerShare.haveBeenIssued\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.bearerShare.percentageIssued\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.bearerShare.ownershipStructureIssued\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.bearerShare.heldInCustody\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.bearerShare.writtenConfirmationCollected\",                     \"index\":-1,                     \"value\":\"\"                 }             ]         },         {               \"id\":\"Company.immediateParent\",             \"name\":\"Parent Entity\",             \"fields\":[                   {                       \"name\":\"Company.countryOfBusiness.country\",                     \"index\":0,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.entityType\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.formation.incorporationCountry\",                     \"index\":0,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.governmentIDInfo\",                     \"index\":-1,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.legalName\",                     \"index\":0,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.listedInfo.isPubliclyListed\",                     \"index\":0,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.listedInfo.primaryExchange\",                     \"index\":0,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.regulatedInfo.isRegulated\",                     \"index\":0,                     \"value\":\"\"                 },                 {                       \"name\":\"Company.regulatedInfo.regulatoryAuthority\",                     \"index\":0,                     \"value\":\"\"                 }             ]         },         {               \"id\":\"Company.controllerDirector\",             \"name\":\"Key Controller & Directors\",             \"fields\":[                   {                       \"name\":\"Task.request.controllerDirectorType\",                     \"index\":-1,                     \"value\":\"\"                 }             ]         },         {               \"id\":\"Company.authorizedSignatory\",             \"name\":\"Authorised Signatory\",             \"fields\":[                   {                       \"name\":\"Individual.firstName\",                     \"index\":-1,                     \"value\":\"\"                 }             ]         }     ] }";
