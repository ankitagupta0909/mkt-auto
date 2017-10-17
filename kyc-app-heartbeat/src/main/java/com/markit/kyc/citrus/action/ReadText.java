package com.markit.kyc.citrus.action;
import java.io.DataInputStream;
import java.io.FileInputStream;

public class ReadText {


//	public static void main (String args[])
	
	public  static String returnJson(String filepath) throws Exception{

	/*try{*/
		//String filePath;
       
		/*String filePath = "C:\\kyc-heartbeat_v5\\kyc-app-heartbeat\\src\\data\\NewOutreachJson.txt" ;*/
		
	      DataInputStream dis =  new DataInputStream (new FileInputStream (filepath));
		       
		 byte[] datainBytes = new byte[dis.available()];
		 dis.readFully(datainBytes);
		 dis.close();
		       
		 String content = new String(datainBytes, 0, datainBytes.length);
		     
		 System.out.println(content);
		 return content;
		
		 
	/*}
	catch(Exception ex){
		ex.printStackTrace();
	}
	
	*/
		
  }
}



