package com.markit.kyc.citrus.utils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.Assert;

/**
 * Factory for creating a ClientHttpRequestFactory based on the HttpComponentsClientHttpRequestFactory implementation
 * Automatically configures for use over SSL
 * Optionally configures for default credentials
 * @author samuel.sanders (michael.smolyak)
 *
 */
public class SslClientHttpRequestFactory implements FactoryBean<ClientHttpRequestFactory>, InitializingBean {
    
    private static final Logger logger = LoggerFactory.getLogger(SslClientHttpRequestFactory.class);
    
	@Resource private SslCredentialsClient sslCredentialsClient;
	
    @Override
    public ClientHttpRequestFactory getObject() throws Exception {
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        //httpClientBuilder.set
        httpClientBuilder.setSSLSocketFactory(createSslFactory());
      
        UsernamePasswordCredentials usernamePasswordCredentials = sslCredentialsClient.getUserCredentials();
        
        if (usernamePasswordCredentials != null) {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, usernamePasswordCredentials);
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
        }

        return new HttpComponentsClientHttpRequestFactory(httpClientBuilder.build());
    }


    @Override
    public Class<?> getObjectType() {
    	return ClientHttpRequestFactory.class;
    }

    @Override
    public boolean isSingleton() {
    	return true;
    }    
    
//    public static ClientHttpRequestFactory create() {
//        
//    	SslClientHttpRequestFactory factory = new SslClientHttpRequestFactory();
//    	return factory.init();
//    }
//    
//    public ClientHttpRequestFactory init() {
//        
//        HttpClientBuilder httpClientBuilder = HttpClients.custom();
//        httpClientBuilder.setSSLSocketFactory(createSslFactory());
//        
//        UsernamePasswordCredentials usernamePasswordCredentials = sslCredentialsClient.getUserCredentials();
//        if (usernamePasswordCredentials != null) {
//            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//            credentialsProvider.setCredentials(AuthScope.ANY, usernamePasswordCredentials);
//            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//        }
//
//        return new HttpComponentsClientHttpRequestFactory(httpClientBuilder.build());
//        
//    }
    
    private static SSLConnectionSocketFactory createSslFactory() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContexts.custom().build();
            // set up a TrustManager that trusts everything
            sslContext.init(null, new TrustManager[] { new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    System.out.println("getAcceptedIssuers =============");
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    System.out.println("checkClientTrusted =============");
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    System.out.println("checkServerTrusted =============");
                }
            } }, new SecureRandom());

        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            logger.debug("caught exception " + e, e);
            throw new IllegalStateException("Unable to initialize ssl context", e);
        }

        return new SSLConnectionSocketFactory(sslContext);
    }
    
    
	/* SPRING RELATED METHODS */
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(sslCredentialsClient);
	}

	public void setSslCredentialsClient(SslCredentialsClient sslCredentialsClient) {
		this.sslCredentialsClient = sslCredentialsClient;
	}
}
