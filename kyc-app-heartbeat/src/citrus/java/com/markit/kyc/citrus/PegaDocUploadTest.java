package com.markit.kyc.citrus;

import javax.mail.internet.AddressException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusXmlTest;
import com.consol.citrus.testng.AbstractTestNGCitrusTest;

/**
 * Batch upload test
 *
 * @author Ankita Gupta
 * @since 2015-11-19
 */
@Test
public class PegaDocUploadTest extends AbstractTestNGCitrusTest {

    @CitrusXmlTest(name = "PegaDocUploadTest")
    public void pegaDocUploadTest() {}
    

}
