package com.markit.kyc.citrus;

import javax.annotation.Resource;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.markit.kyc.citrus.utils.MailClient;
import com.markit.kyc.commons.repository.generator.CurrentTimeMillisUniqueIdGenerator;

public class KycTestReporter extends TestListenerAdapter {
	//private static final Logger logger = LoggerFactory.getLogger(KycTestReporter.class.getName());

	private static MailClient mailClient;
	private static KycTestLogger kycTestLogger;
	private static CurrentTimeMillisUniqueIdGenerator uniqueIdGenerator = new CurrentTimeMillisUniqueIdGenerator();

	@Resource
	public void setKycTestLogger(KycTestLogger kycTestLogger) {
		KycTestReporter.kycTestLogger = kycTestLogger;
		Reporter.log("setKycTestLogger");
	}

	@Resource
	public void setMailClient(MailClient mailClient) {
		KycTestReporter.mailClient = mailClient;
		Reporter.log("setMailClient");
	}
	
	@Override
	public void onStart(org.testng.ITestContext testContext) {
		Long testId = uniqueIdGenerator.generate();
		testContext.setAttribute("id", testId);
		Reporter.log("onStart");
	}
	
	@Override
	public void onTestFailure(ITestResult tr) {
		kycTestLogger.logTestResults(tr);
		
		String subject = "KYC-Heartbeat: " + tr.getName() + " test Failed";
		String body = tr.getThrowable().getCause().toString();

		
		mailClient.sendMessage(subject, body);
		Reporter.log("onTestFailure");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		kycTestLogger.logTestResults(tr);
		Reporter.log("onTestSkipped");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		kycTestLogger.logTestResults(tr);
	/*	String subject = "KYC-Heartbeat: " + tr.getName() + " test Passed";
		String body =tr.getTestName().toString() +tr.getStatus();
		

		mailClient.sendMessage(subject, body);*/
		Reporter.log("onTestSuccess");
	}

}