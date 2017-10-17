package com.markit.kyc.citrus;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.Reporter;

import com.markit.kyc.commons.repository.generator.CurrentTimeMillisUniqueIdGenerator;

public class KycSuiteReporter implements ISuiteListener  {
	//private static final Logger logger = LoggerFactory.getLogger(KycSuiteReporter.class.getName());

	private static KycSuiteLogger kycSuiteLogger;
	private static CurrentTimeMillisUniqueIdGenerator uniqueIdGenerator = new CurrentTimeMillisUniqueIdGenerator();
	
	@Resource
	public void setKycSuiteLogger(KycSuiteLogger kycSuiteLogger) {
		KycSuiteReporter.kycSuiteLogger = kycSuiteLogger;
		Reporter.log("Suit Logs");
	}
	
    @Override
    public void onFinish(ISuite suite) {
    	suite.setAttribute("endDt", System.currentTimeMillis());
    	kycSuiteLogger.logSuiteResults(suite);
    	Reporter.log("On Finish");
   
    }

    @Override
    public void onStart(ISuite suite) {
    	Long suiteId = uniqueIdGenerator.generate();
    	suite.setAttribute("id", suiteId);
    	suite.setAttribute("startDt", System.currentTimeMillis());
    	
    	Reporter.log("On Start");
    }
}
