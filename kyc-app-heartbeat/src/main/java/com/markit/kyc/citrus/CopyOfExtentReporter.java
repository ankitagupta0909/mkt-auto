package com.markit.kyc.citrus;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
 
public class CopyOfExtentReporter implements IReporter,ITestListener {
    private ExtentReports extent;
  
 
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,String outputDirectory) {
       
    	
    	extent = new ExtentReports(outputDirectory + File.separator + "Extent.html", true);
 
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
 
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
 
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
                
        
            }
        }
       
        extent.flush();
        extent.close();

       // String testCaseName=Globals.testName;
        String testCaseName="KYC HeartBeat Flow1";
        String testCaseStatus=null;
        for (int i=0;i<Globals.listTestStatus.size();i++){
        	
        	String status=Globals.listTestStatus.get(i);
        	if(status.equalsIgnoreCase("fail")){
        		 testCaseStatus="Failed";
        		break;
        	}       	
        	else{
        		testCaseStatus="Passed";
        	}
        }
         
        System.out.println("statusssssss"+testCaseStatus);
  	//   	 String testCasestatus=Globals.testStatus;

  		 //	ConsoleFileWriter.logDetails();
	//	EmailNotification.emailNotification(testCaseName,testCasestatus);
	
		
        
    }
 
    private void buildTestNodes(IResultMap tests, LogStatus status) {
        ExtentTest test;
 
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.startTest(result.getMethod().getMethodName());
 
                test.setStartedTime(getTime(result.getStartMillis()));
                test.setEndedTime(getTime(result.getEndMillis()));
 
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
 
                if (result.getThrowable() != null) {
                    //test.log(status, result.getThrowable());
                	test.log(status, result.getThrowable().getCause().toString());
                	test.log(status, result.getThrowable().getMessage());
                	
                }
                else { 
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }
 
                extent.endTest(test);
            }
        }
    }
 
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();        
    }

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		int status=result.getStatus();
		String tcstatus=resultStatus(status);
		Globals.listTestStatus.add(tcstatus);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		int status=result.getStatus();
		String tcstatus=resultStatus(status);
		Globals.listTestStatus.add(tcstatus);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		int status=result.getStatus();
		String tcstatus=resultStatus(status);
		Globals.listTestStatus.add(tcstatus);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		Globals.testName=context.getName();
		
		System.out.println("Test Case Name: "+context.getName());
		
	}
	
	public static String resultStatus(int status)
	{
		String statusName=null;
		switch(status) {
		 case 1:
		 return statusName="Pass";
		 
		 case 2:
		 return statusName="Fail";
		 		
		 case 3:
		 return statusName="Skip";
		 
		 case 4:
		 return "SUCCESS_PERCENTAGE_FAILURE";
		 
		 case 16:
		 return "Started";
	 
		 
	}
		return statusName;
	}
	

}