package com.markit.kyc.citrus;

import javax.mail.internet.AddressException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusXmlTest;
import com.consol.citrus.testng.AbstractTestNGCitrusTest;

/**
 * Test
 *
 * @author Ankita Gupta
 * @since 2015-11-16
 */
@Test
public class PegaCreateMeiTest extends AbstractTestNGCitrusTest {

    @CitrusXmlTest(name = "PegaCreateMeiTest")
    public void pegaCreateMeiTest() {}

}
