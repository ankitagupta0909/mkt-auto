package com.markit.kyc.citrus;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtil {
	public static List<String> TestDataList;



	/** Returns the Workbook object
	 * @param filepath
	 * @return
	 */
	public static Workbook getWorkBookObject (String filepath) 
	{

		FileInputStream fis=null;
		try {
			fis = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		File file = new File(filepath);
		String fileName = file.getName();

		String[] arrpath= fileName.split("\\.");
		String ext=arrpath[1];
		Workbook wb=null;
		if(ext.equalsIgnoreCase("XLSX"))
		{
			try {
				wb=new XSSFWorkbook(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
			
				e.printStackTrace();
			}
		}
		else if(ext.equalsIgnoreCase("XLS"))
		{
			try {
				wb= new HSSFWorkbook(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
	
				e.printStackTrace();
			}
		}
		return wb;
	}

	/**Returns cell value
	 * @param cellObj
	 * @return
	 */
	public static String fn_GetCellData(Cell cellObj){
		int cellTypeNo=cellObj.getCellType();
		String cellvalue="";
		if(cellTypeNo==Cell.CELL_TYPE_NUMERIC){
			Double dblcellvalue=cellObj.getNumericCellValue();
			Integer intcellValue=dblcellvalue.intValue();
			cellvalue=intcellValue.toString();
		}else if(cellTypeNo==Cell.CELL_TYPE_STRING){
			cellvalue=cellObj.getStringCellValue();
		}else if(cellTypeNo==Cell.CELL_TYPE_BLANK){
			cellvalue="";
		}
		return cellvalue;
	}



	/** Returns column number as per passed column name 
	 * @param argColumnName
	 * @param fstRowObj
	 * @return
	 */
	public static int getColumnNumberByColumnName(String argColumnName, Row fstRowObj){
		int ColumnNumber=0;

		for(int i=0; i<=fstRowObj.getLastCellNum()-1;i++){
			Cell cellObj=fstRowObj.getCell(i, Row.CREATE_NULL_AS_BLANK);
			String xlcolumnName=cellObj.getStringCellValue();
			if(xlcolumnName.equalsIgnoreCase(argColumnName)){
				ColumnNumber=i;
				break;
			}
		}
		return ColumnNumber;

	}

	/**Returns the count of rows available on the excel sheet
	 * @param testDataPath
	 * @return
	 */
	public static int countOfTheRowsInSheet(String testDataPath,String SheetName) {

		Workbook wbookObj=getWorkBookObject(testDataPath);
		Sheet sheetObj=wbookObj.getSheet(SheetName);
		
		int  count=sheetObj.getLastRowNum();
		
		return count;
	}

	

	
	
	
	/** Save written data to excel
	 * @param filePath
	 * @param wb
	 */
	public static void writeExcel(String filePath,Workbook wb){

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filePath);
			wb.write(fos);
			fos.close();
			 wb.close();
	
		} catch (Exception e) {
			e.printStackTrace();
		
		} 

	}

	

/** get Col Data by colName
 * @param wbPath
 * @param SheetName
 * @param refColumnName
 * @param rowmNum
 * @return
 */
public static String getColumnDataByColName(String wbPath, String SheetName, String refColumnName,int rowmNum) 
{
	Workbook wbookObj=getWorkBookObject(wbPath);
	Sheet sheetObj=wbookObj.getSheet(SheetName);					
	Integer columnNo = null;		
	String value=null;

	Row row = sheetObj.getRow(0);
	columnNo=getColumnNumberByColumnName(refColumnName, row);
	
	if (columnNo!=null){
		 row = sheetObj.getRow(rowmNum);
		Cell c=row.getCell(columnNo);
		if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK){ 
			value="";
		}
		else{
		 value=c.getStringCellValue();
		}
	}
	return value;
}		
		

/** set Col Data by colName
 * @param wbPath
 * @param SheetName
 * @param refColumnName
 * @param rowmNum
 * @param setValue -- the value that needs to be set
 * @return
 */
public static void setColumnDataByColNameFirstTime(String wbPath, String SheetName, String refColumnName,int rowmNum, String setValue) 
{
	Workbook wbookObj=getWorkBookObject(wbPath);
	Sheet sheetObj=wbookObj.getSheet(SheetName);					
	Integer columnNo = null;		

	Row row = sheetObj.getRow(0);
	columnNo=getColumnNumberByColumnName(refColumnName, row);
	
	if (columnNo!=null){
		 
		row = sheetObj.getRow(rowmNum);
		if(row==null){
			row = sheetObj.createRow(rowmNum);
		}
		 
		 Cell c = row.createCell(columnNo, Cell.CELL_TYPE_NUMERIC);
		 c.setCellValue(setValue);
		}

	writeExcel(wbPath, wbookObj);

}	

public static void setColumnDataByColName(String wbPath, String SheetName, String refColumnName,int rowmNum, String setValue) 
{
	Workbook wbookObj=getWorkBookObject(wbPath);
	Sheet sheetObj=wbookObj.getSheet(SheetName);					
	Integer columnNo = null;		

	Row row = sheetObj.getRow(0);
	columnNo=getColumnNumberByColumnName(refColumnName, row);
	
	if (columnNo!=null){
		 row = sheetObj.getRow(rowmNum);
		Cell c = row.createCell(columnNo, Cell.CELL_TYPE_NUMERIC);
		c.setCellValue(setValue);

		}

	writeExcel(wbPath, wbookObj);

}	

public static List<String> getHeaderOfExcel(Sheet sheetObj){
	
	List<String> header=new ArrayList<>();
	Row rowObj=sheetObj.getRow(0);
	for(int i=0; i<=rowObj.getLastCellNum()-1;i++){		
		Cell cellObj=rowObj.getCell(i,Row.CREATE_NULL_AS_BLANK);			
		String cellvalue=cellObj.getStringCellValue();
		header.add(cellvalue);
	}	
			
	return header;
}
}