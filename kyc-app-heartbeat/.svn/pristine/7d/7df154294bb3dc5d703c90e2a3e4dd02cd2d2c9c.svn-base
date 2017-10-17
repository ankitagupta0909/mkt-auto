package com.markit.kyc.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class HtmlWriterAspect {
	
	
	//@Before ("execution(* AbstractDatabaseConnectingTestAction.doExecute(..))")
	@Before ("execution(* generateTestResults(..))")  
	public void getNameAdvice(){
	        System.out.println("==========================================================================================================================Executing Advice on getName()");

	}

	@Before ("execution(* com.consol.citrus.actions.*ExecuteSQLQueryAction.performValidation.*(..))")  
	public void getNameAdvice1(){
	        System.out.println("PERFORM VALIADATION=========================================================================================================Executing Advice on getName()");
	    }
	
	//Pointcut to execute on all the methods of classes in a package
    @Pointcut("within(com.consol.citrus.*)") 
    public void allMethodsPointcut(){}
	
}
