package com.markit.kyc.citrus;

import javax.mail.internet.AddressException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusXmlTest;
import com.consol.citrus.testng.AbstractTestNGCitrusTest;

@Test
public class TaskBatchUploadFundTest extends AbstractTestNGCitrusTest {

    @CitrusXmlTest(name = "TaskBatchUploadFundTest")
    public void batchUploadfundTest() {}
    

}


