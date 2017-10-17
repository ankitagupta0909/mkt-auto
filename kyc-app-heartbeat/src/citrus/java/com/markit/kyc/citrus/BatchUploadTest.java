package com.markit.kyc.citrus;

import javax.mail.internet.AddressException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusXmlTest;
import com.consol.citrus.testng.AbstractTestNGCitrusTest;

/**
 * Batch upload test
 *
 * @author Michael Smolyak
 * @since 2014-12-18
 */
@Test
public class BatchUploadTest extends AbstractTestNGCitrusTest {

    @CitrusXmlTest(name = "BatchUploadTest")
    public void batchUploadTest() {}
    
   
}


