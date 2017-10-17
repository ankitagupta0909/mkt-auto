package com.markit.kyc.citrus.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.consol.citrus.actions.AbstractTestAction;
import com.consol.citrus.context.TestContext;

/**
 * Test
 *
 * @author Michael Smolyak
 * @since 2014-12-29
 */
public class RemoveEntityReportsAction extends AbstractTestAction {
	
    @Resource private DataSource dataSource;

	private static final Logger logger = LoggerFactory.getLogger(RemoveEntityReportsAction.class);
	
	@Override
	public void doExecute(TestContext context) {
		String entityId = (String)context.getVariableObject("entityId");
	
		Map<String, Object> params = new  HashMap<String, Object>();
		params.put("v_entity_id", entityId);
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("REMOVE_TEST_ENTITY_REPORTS");
		SqlParameterSource in = new MapSqlParameterSource(params);
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
        logger.info("Entity reports {} succesfully deleted, result size {}", entityId, simpleJdbcCallResult.size());
	}
}
