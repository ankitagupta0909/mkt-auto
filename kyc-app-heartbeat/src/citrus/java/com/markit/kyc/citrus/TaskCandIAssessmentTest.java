package com.markit.kyc.citrus;

import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusXmlTest;
import com.consol.citrus.testng.AbstractTestNGCitrusTest;


/**
 * @author ankita.gupta
 *
 */
@Test
public class TaskCandIAssessmentTest extends AbstractTestNGCitrusTest {

    @CitrusXmlTest(name = "TaskCandIAssessmentTest")
    public void taskCountryandIndustrialAssessment() {}

}