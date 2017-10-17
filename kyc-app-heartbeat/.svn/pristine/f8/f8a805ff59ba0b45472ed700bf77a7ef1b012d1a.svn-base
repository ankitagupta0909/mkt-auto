package com.markit.kyc.citrus.utils;

import javax.annotation.Resource;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.markit.kyc.security.AuthFailureException;
import com.markit.kyc.security.UserxUser;
import com.markit.kyc.security.service.UserxsSessionService;

public class SslCredentialsClientUserXsImpl implements SslCredentialsClient {

	private static final Logger logger = LoggerFactory.getLogger(SslCredentialsClientUserXsImpl.class.getName());

	@Resource private UserxsSessionService userxsSessionService;
	
	@Value("${uxs.user}") private String userxsUser;
	@Value("${uxs.password}") private String userxsPass;
	@Value("${uxs.useToken}") private boolean useToken;


	@Override
	public UsernamePasswordCredentials getUserCredentials() {
		UserxUser userXs;
		try {
			userXs = userxsSessionService.login(userxsUser, userxsPass);
		} catch (AuthFailureException e) {
			logger.error("AuthFailureException ", e);
			return null;
		}
		if(!useToken){
			
			return new UsernamePasswordCredentials(userxsUser, userxsPass);
		}else{

	
		return new UsernamePasswordCredentials(userXs.getName(), userXs.getTicketId());
		}
		
		
	}


}
