package com.markit.kyc.citrus;

import java.io.FileNotFoundException;
import java.io.IOException;

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
public class TaskAuthorisedSignTest extends AbstractTestNGCitrusTest {

    @CitrusXmlTest(name = "TaskAuthorisedSignTest")
    public void taskAuthorisedSignTest() {}

}