package com.markit.kyc.citrus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonUpdate {

	//public static void main(String[] args) throws IOException {
		public static void jsonUpdate(String file,String payloadClientId,String kycComp,String mcpmEntId,String mcpmName,String entityType) throws IOException{
		//String file="src/data/mcpmCreateEntity.json";
		String csvFile = readFile(file);
		System.out.println(csvFile);

		csvFile = csvFile.replaceAll("id", payloadClientId);	
		csvFile = csvFile.replaceAll("KycComp", kycComp);	
		csvFile = csvFile.replaceAll("entityId", mcpmEntId);	
		csvFile = csvFile.replaceAll("taskMcpmLegalName", mcpmName);	
		csvFile = csvFile.replaceAll("entityType", entityType);	
		
		System.out.println(csvFile);

		//PrintWriter pw = new PrintWriter(new File("C:/AutomationWorkspaceAPI/kyc-app-heartbeat/src/data/uploadEntities-UAT.csv"));
		PrintWriter pw = new PrintWriter(new File(file));
        StringBuilder sb = new StringBuilder();
		
		sb.append(csvFile);
		pw.write(sb.toString());
		pw.close();		
	
		
		
	}
		
		
	public static void setJsonDefaultState(String file,String payloadClientId,String kycComp,String mcpmEntId,String mcpmName,String entityType) throws IOException{
				
		String csvUpdatedFile = readFile(file);
		csvUpdatedFile=csvUpdatedFile.replaceAll(payloadClientId, "id");
		csvUpdatedFile=csvUpdatedFile.replaceAll(kycComp, "KycComp");
		csvUpdatedFile=csvUpdatedFile.replaceAll(mcpmEntId, "entityId");
		csvUpdatedFile=csvUpdatedFile.replaceAll(mcpmName, "taskMcpmLegalName");
		csvUpdatedFile=csvUpdatedFile.replaceAll(entityType, "entityType");

		PrintWriter pw = new PrintWriter(new File(file));
        StringBuilder sb = new StringBuilder();
		
		sb.append(csvUpdatedFile);
		pw.write(sb.toString());
		pw.close();
	}
			
	
	private static String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");
	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }
	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}
	}


