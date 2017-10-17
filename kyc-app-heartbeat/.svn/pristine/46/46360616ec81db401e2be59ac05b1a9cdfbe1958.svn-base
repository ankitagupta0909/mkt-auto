package com.markit.kyc.citrus.repository;

import java.util.Date;
import java.util.List;

import com.markit.kyc.citrus.domain.SuiteResult;
import com.markit.kyc.citrus.repository.mapper.SuiteResultParameterMapper;
import com.markit.kyc.citrus.repository.mapper.SuiteResultRowMapper;
import com.markit.kyc.commons.repository.annotation.ProxiedRepository;
import com.markit.kyc.commons.repository.annotation.QueryContext;
import com.markit.kyc.commons.repository.annotation.UpdateContext;

@ProxiedRepository(
        parameterMapperClass = SuiteResultParameterMapper.class, 
        rowMapperClass = SuiteResultRowMapper.class)
public interface SuiteResultRepository {

	@UpdateContext
	public void create(
			Long suiteId,
			String name,
			Date startDt,
			Long executionTime,
			String status);
	
	@UpdateContext
	public void update(
			Long suiteId,
			Long executionTime,
			String status);
	
	@QueryContext
	SuiteResult read(Long suiteId);
	
	@QueryContext
	List<SuiteResult> findByDate(Date date);
}
