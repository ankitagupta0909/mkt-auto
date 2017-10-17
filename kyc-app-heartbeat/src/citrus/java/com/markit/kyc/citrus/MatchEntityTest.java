package com.markit.kyc.citrus;

import javax.mail.internet.AddressException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusXmlTest;
import com.consol.citrus.testng.AbstractTestNGCitrusTest;

/**
 * Test
 *
 * @author Michael Smolyak
 * @since 2014-11-24
 */
@Test
public class MatchEntityTest extends AbstractTestNGCitrusTest {

    @CitrusXmlTest(name = "MatchEntityTest")
    
    public void matchEntityTest() {}
    
 }
