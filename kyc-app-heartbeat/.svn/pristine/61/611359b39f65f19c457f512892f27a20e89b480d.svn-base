package com.markit.kyc.citrus;

import java.text.DateFormat;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

import com.markit.kyc.citrus.repository.TestResultRepository;

public class KycTestLogger {
	private static final Logger logger = LoggerFactory.getLogger(KycTestLogger.class.getName());

	private static TestResultRepository testResultRepository;

	@Resource
	public void setTestResultRepository(TestResultRepository testResultRepository) {
		KycTestLogger.testResultRepository = testResultRepository;
	}

	public void logTestResults(ITestResult tr) {
		Long testId = (Long)tr.getTestContext().getAttribute("id");
		String testName = tr.getName();
		Long suiteId = (Long)tr.getTestContext().getSuite().getAttribute("id");
		String suiteName = tr.getTestContext().getSuite().getName();
		java.sql.Timestamp startDt = new java.sql.Timestamp(tr.getStartMillis());
		Long executionTime = tr.getEndMillis() - tr.getStartMillis();
		String status = null;
		int statusCode = tr.getStatus();
		
		switch (statusCode) {
			case ITestResult.SUCCESS:
				status = "SUCCESS";
				break;
			case ITestResult.FAILURE:
				status = "FAILURE";
				break;
			case ITestResult.SKIP:
				status = "SKIP";
				break;
			case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
				status = "SUCCESS_PERCENTAGE_FAILURE";
				break;
			case ITestResult.STARTED:
				status = "STARTED";
				break;
		}
		
		logger.info("Test Suite {} {}", suiteId, suiteName);
		logger.info("Test       {} {}", testId, testName);
		logger.info("Test InstanceName  {} ", tr.getInstanceName());
		logger.info("Start Time    {}", DateFormat.getInstance().format(startDt));
		logger.info("ExecutionTime {}", executionTime);
		logger.info("Status        {}", status);
		if (statusCode == ITestResult.FAILURE || statusCode == ITestResult.SKIP) {
			logger.info("Test failed: {}", tr.getThrowable().getCause().toString());
		}
		
		testResultRepository.create(testId, suiteId, testName, startDt, executionTime, status);
	}

}
