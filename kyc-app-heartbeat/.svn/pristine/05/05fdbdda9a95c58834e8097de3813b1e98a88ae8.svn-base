package com.markit.kyc.citrus;

import java.text.DateFormat;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.ISuiteResult;

import com.markit.kyc.citrus.repository.SuiteResultRepository;

public class KycSuiteLogger {
	private static final Logger logger = LoggerFactory.getLogger(KycSuiteLogger.class.getName());

	private static SuiteResultRepository suiteResultRepository;

	@Resource
	public void setSuiteResultRepository(SuiteResultRepository suiteResultRepository) {
		KycSuiteLogger.suiteResultRepository = suiteResultRepository;
	}

	public void createSuite(ISuite suite) {
		String name = suite.getName();
		Long suiteId = (Long)suite.getAttribute("id");
		Long startTime = (Long)suite.getAttribute("startDt");
		java.sql.Timestamp startDt = new java.sql.Timestamp(startTime);
		Long executionTime =  0L;
		String status = "STARTED";

		logger.info("Started test suite {} {}", suiteId, name);
		logger.info("Start Time          {}", DateFormat.getInstance().format(startDt));
		
		suiteResultRepository.update(suiteId, executionTime, status);
	}
	
	public void logSuiteResults(ISuite suite) {
		String name = suite.getName();
		Long suiteId = (Long)suite.getAttribute("id");
		Long startTime = (Long)suite.getAttribute("startDt");
		Long endTime = (Long)suite.getAttribute("endDt");
		java.sql.Timestamp startDt = new java.sql.Timestamp(startTime);
		Long executionTime =  endTime - startTime;
		String status = suite.getSuiteState().isFailed() ? "FAILURE" : "SUCCESS";

		logger.info("Complete test suite {} {}", suiteId, name);
		logger.info("Start Time          {}", DateFormat.getInstance().format(startDt));
		logger.info("ExecutionTime       {} ms", executionTime);
		logger.info("Status              {}", status);
		
		logger.info("********Results*******");
		Map<String, ISuiteResult> results = suite.getResults();
		for (String key : results.keySet()) {
			ISuiteResult value = results.get(key);
			logger.info("{} = {}", key, value.getTestContext().getName());
			logger.info("{} = {}", key, value.toString());
		}
		logger.info("********Results Ends*******");
		
	    // logger.info("Host Name:"+arg0.getHost());
	    // Returns null if it runs locally
		
		suiteResultRepository.create(suiteId, name, startDt, executionTime, status);
	}
}
