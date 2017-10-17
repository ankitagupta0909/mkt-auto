package com.markit.kyc.citrus.repository;

import java.util.Date;
import java.util.List;

import com.markit.kyc.citrus.domain.TestResult;
import com.markit.kyc.citrus.repository.mapper.TestResultParameterMapper;
import com.markit.kyc.citrus.repository.mapper.TestResultRowMapper;
import com.markit.kyc.commons.repository.annotation.ProxiedRepository;
import com.markit.kyc.commons.repository.annotation.QueryContext;
import com.markit.kyc.commons.repository.annotation.UpdateContext;

@ProxiedRepository(
        parameterMapperClass = TestResultParameterMapper.class, 
        rowMapperClass = TestResultRowMapper.class)
public interface TestResultRepository {

	@UpdateContext
	public void create(
			Long testId,
			Long suiteId,
			String name,
			Date startDt,
			Long executionTime,
			String status);
	
	@QueryContext
	TestResult read(Long testId);
	
	@QueryContext
	List<TestResult> findBySuite(Long suiteId);
	
	@QueryContext
	List<TestResult> findByDate(Date date);
}
