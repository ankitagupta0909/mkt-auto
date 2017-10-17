/**
 * 
 */
package com.markit.kyc.restclient;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.markit.kyc.security.AuthFailureException;
import com.markit.kyc.security.UserxUser;
import com.markit.kyc.security.service.UserxsSessionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath*:userXsClient-context.xml",
		"classpath:dataSource-context.xml",
		})
/**
 * @author michael.smolyak
 *
 */
public class UserAccessServiceTest {
	@Resource private UserxsSessionService userxsSessionService;

	/**
	 * Test method for {@link com.markit.kyc.restclient.impl.ReportsRestClientImpl#saveReport(java.lang.String, java.lang.String, java.lang.String, boolean, java.io.OutputStream)}.
	 * @throws AuthFailureException 
	 */
	@Test
	public void testTestLogin() throws AuthFailureException {
		String userLogin = "michael.smolyak@markit.com";
		String password = "Pom12$anm";
		
		UserxUser user = userxsSessionService.login(userLogin, password);
		
		String[] userName = user.getName().split("/");
  	    Assert.assertEquals(userName[userName.length - 1], "michael.smolyak");
	}


}
