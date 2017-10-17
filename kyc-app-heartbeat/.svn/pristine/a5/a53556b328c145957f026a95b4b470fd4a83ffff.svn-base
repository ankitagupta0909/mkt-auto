package com.markit.kyc.citrus;

import gelf4j.sender.Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.mail.internet.AddressException;


public class ConsoleFileWriter {

public static void logDetails()throws FileNotFoundException, IOException, AddressException {
//	public static void main(String args[])throws FileNotFoundException, IOException, AddressException{
	{

 boolean shallWrite=false;
  boolean ifFound = false;
    	File file = new File("C:\\kyc-heartbeat_v5\\kyc-app-heartbeat\\logs1.txt");
    	FileWriter fw = new FileWriter("C:\\kyc-heartbeat_v5\\kyc-app-heartbeat\\logs.txt"); 
    	BufferedWriter bw = new BufferedWriter(fw);
    	
    	Scanner sc = new Scanner(file);
    	int linenumber=1;
    	while (sc.hasNextLine()) {
    		/*while(sc.hasNext("C I T R U S  T E S T S  2.0")){
    			String line=sc.nextLine();*/
    		String line=sc.nextLine();
    			if (line.contains("TEST STEP")&& !ifFound){
    				int i=linenumber;
    				System.out.println(i);  				
    			shallWrite=true;
    			ifFound=true;
    			}
    			if(shallWrite){
 
    				bw.write(line);
    				bw.newLine();
    				
    			}
    			
          }
    	
        sc.close();
        fw.close();
      
   
    
    }       
}
}
