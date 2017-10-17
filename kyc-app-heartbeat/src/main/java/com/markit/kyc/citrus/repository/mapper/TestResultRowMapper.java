package com.markit.kyc.citrus.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.markit.kyc.citrus.domain.TestResult;
import com.markit.kyc.commons.repository.rowmapper.BaseRowMapper;

public class TestResultRowMapper extends BaseRowMapper<TestResult> {
    
	@Override
	public TestResult mapRow(ResultSet rs, int rowNum) throws SQLException {

		TestResult testResult = new TestResult();

		testResult.setTestId(getLong(rs, "testId"));
		testResult.setSuiteId(getLong(rs, "suiteId"));
		testResult.setName(getString(rs, "name"));
		testResult.setStartDt(getTimestamp(rs, "startDt"));
		testResult.setExecutionTime(getLong(rs, "executionTime"));
		testResult.setStatus(getString(rs, "status"));
		
		return testResult;
	}
}
