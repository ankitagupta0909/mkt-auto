package com.markit.kyc.citrus;

import javax.mail.internet.AddressException;

import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusXmlTest;
import com.consol.citrus.testng.AbstractTestNGCitrusTest;


/**
 * @author ankita.gupta
 * 
 */
@Test
public class SearchByLegalNameCCTest extends AbstractTestNGCitrusTest {

	  @CitrusXmlTest(name = "SearchByLegalNameCCTest")

    public void SearchByLegalNameCCTest() {}

}

