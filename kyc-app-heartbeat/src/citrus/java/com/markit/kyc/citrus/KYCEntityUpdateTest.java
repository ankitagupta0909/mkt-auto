package com.markit.kyc.citrus;

import javax.mail.internet.AddressException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusXmlTest;
import com.consol.citrus.testng.AbstractTestNGCitrusTest;


/**
 * @author ankita.gupta
 * 
 */
@Test
public class KYCEntityUpdateTest extends AbstractTestNGCitrusTest {
	
	

    @CitrusXmlTest(name = "KYCEntityUpdateTest")
    public void mcpmEntityUpdateTest() {}
    
/*    @AfterTest 
    public void emailtest() throws AddressException
    {
    	EmailNotification.emailNotification("Entity Update API");
    }*/
}

