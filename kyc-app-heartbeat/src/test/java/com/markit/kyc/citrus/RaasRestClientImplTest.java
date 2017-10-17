/**
 * 
 */
package com.markit.kyc.citrus;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath*:raasRestClient-context.xml",
		"classpath*:sslClientHttpRequestFactory-context.xml",
		"classpath*:userXsClient-context.xml",
//		"classpath:dataSource-context.xml",
		})
/**
 * @author michael.smolyak
 *
 */
public class RaasRestClientImplTest {
	
	private static final Logger logger = LoggerFactory.getLogger(RaasRestClientImplTest.class.getName());

	@Resource private RaasRestClient raasRestClient;

	/**
	 * Test method for {@link com.markit.kyc.restclient.impl.ReportsRestClientImpl#getReportContent(java.lang.String)}.
	 */
	@Test
	public void testCreateCompany() {
		String legalName = "roga i kopita";
		String country = "US";
		raasRestClient.createCompany(legalName, country);
		Assert.assertTrue(true);
	}
	/**
	 * Test method for {@link com.markit.kyc.restclient.impl.ReportsRestClientImpl#getReportContent(java.lang.String)}.
	 */
	@Test
    public void testFindCompany() {
		String legalName = "morgan";
		String country = "US";
		String companyListJson = raasRestClient.findCompany(legalName, country);
		logger.debug(companyListJson);
		Assert.assertTrue(companyListJson.length() > 0);
	}

}
