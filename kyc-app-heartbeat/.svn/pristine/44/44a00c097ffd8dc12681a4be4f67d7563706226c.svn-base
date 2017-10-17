package com.markit.kyc.citrus.repository.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.markit.kyc.commons.repository.parametermapper.ParameterMapper;

public class SuiteResultParameterMapper implements ParameterMapper {
	
    @SuppressWarnings("serial")
    public Map<String, Object> create (
			final Long suiteId,
			final String name,
			final Date startDt,
			final Long executionTime,
			final String status) {
        return new HashMap<String, Object>() {
            {
				put("suiteId", suiteId);
				put("name", name);
				put("startDt", startDt);
				put("executionTime", executionTime);
				put("status", status);
            }
        };
    }
    
    @SuppressWarnings("serial")
    public Map<String, Object> update (
			final Long suiteId,
			final Long executionTime,
			final String status) {
        return new HashMap<String, Object>() {
            {
				put("suiteId", suiteId);
				put("executionTime", executionTime);
				put("status", status);
            }
        };
    }
    
    @SuppressWarnings("serial")
    public Map<String, Object> read(final Long suiteId) {
        return new HashMap<String, Object>() {
            {
                put("suiteId", suiteId);
            }
        };
    }
    
    @SuppressWarnings("serial")
    public Map<String, Object> findByDate(final Date date) {
        return new HashMap<String, Object>() {
            {
                put("date", date);
            }
        };
    }
}
