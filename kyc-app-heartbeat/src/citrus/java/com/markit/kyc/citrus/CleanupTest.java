package com.markit.kyc.citrus;

import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusXmlTest;
import com.consol.citrus.testng.AbstractTestNGCitrusTest;

/**
 * Cleanup test
 *
 * @author Michael Smolyak
 * @since 2014-12-18
 */
@Test
public class CleanupTest extends AbstractTestNGCitrusTest {

    @CitrusXmlTest(name = "CleanupTest")
    public void cleanupTest() {}
}
