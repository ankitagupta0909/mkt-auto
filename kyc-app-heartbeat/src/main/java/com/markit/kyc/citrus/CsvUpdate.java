package com.markit.kyc.citrus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class CsvUpdate {

	//public static void main(String[] args) throws IOException {
		public static String  writeCsv(String file) throws IOException {		
		//String csvFile = readFile("C:/AutomationWorkspaceAPI/kyc-app-heartbeat/src/data/uploadEntities-UAT.csv");
		String csvFile = readFile(file);
		System.out.println(csvFile);
	    EntityIdGenerator idgen = new EntityIdGenerator();		
	    long id=idgen.generate();
	    System.out.println(id);
		String legalName="Automation"+id;
		String internalId="AutoId"+id;
		String mcpmLegal="AutoMcpm"+id;
		csvFile = csvFile.replaceAll("legalName", legalName);	
		csvFile=csvFile.replaceAll("internalId", internalId);
		System.out.println(csvFile);

		//PrintWriter pw = new PrintWriter(new File("C:/AutomationWorkspaceAPI/kyc-app-heartbeat/src/data/uploadEntities-UAT.csv"));
		PrintWriter pw = new PrintWriter(new File(file));
        StringBuilder sb = new StringBuilder();
		
		sb.append(csvFile);
		pw.write(sb.toString());
		pw.close();		
		CsvUpdate.writePropertyFile(legalName,internalId,mcpmLegal,String.valueOf(id));
		return legalName+"_"+internalId+"_"+id+"_"+mcpmLegal;
		
	}
		
		
	public static void setCsvDefaultState(String file,String legalName,String internalId) throws IOException{
				
		String csvUpdatedFile = readFile(file);
		csvUpdatedFile=csvUpdatedFile.replaceAll(legalName, "legalName");
		csvUpdatedFile=csvUpdatedFile.replaceAll(internalId, "internalId");
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

	public static void writePropertyFile(String legalName,String internalId,String mcpmLegal,String id) throws IOException{
		
		FileInputStream in = new FileInputStream("src/citrus/resources/load.properties");
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream("src/citrus/resources/load.properties");
		props.setProperty("legalName",legalName);
		props.setProperty("internalId",internalId);
		props.setProperty("taskMcpmLegalName",mcpmLegal);
		props.setProperty("mcpmEntityId",id);
		props.store(out, null);
		out.close();
	
	
}
}
