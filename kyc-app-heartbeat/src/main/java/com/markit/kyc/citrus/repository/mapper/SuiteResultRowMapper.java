package com.markit.kyc.citrus.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.markit.kyc.citrus.domain.SuiteResult;
import com.markit.kyc.commons.repository.rowmapper.BaseRowMapper;

public class SuiteResultRowMapper extends BaseRowMapper<SuiteResult> {
    
	@Override
	public SuiteResult mapRow(ResultSet rs, int rowNum) throws SQLException {

		SuiteResult suiteResult = new SuiteResult();
		
		suiteResult.setSuiteId(getLong(rs, "suiteId"));
		suiteResult.setName(getString(rs, "name"));
		suiteResult.setStartDt(getTimestamp(rs, "startDt"));
		suiteResult.setExecutionTime(getLong(rs, "executionTime"));
		suiteResult.setStatus(getString(rs, "status"));
		
		return suiteResult;
	}
}
