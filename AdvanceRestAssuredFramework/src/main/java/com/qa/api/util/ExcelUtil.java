package com.qa.api.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	public static String TESTDATA_SHEET_PATH = "C:\\Users\\pavel\\OneDrive\\Documents\\Rest-Assured-Eclipse-workspace\\AdvanceRestAssuredFramework\\src\\main\\java\\com\\qa\\api\\testdata\\GoresttestData.xlsx";
	
	public static Workbook book;
	public static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		
		try {
			FileInputStream ip = new FileInputStream(TESTDATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// To get total row will use sheet.getLastRowNum()
		// to get total number of column will use sheet.getRow(0).getLastCellNum()
		Object data [][] = new Object [sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i = 0; i<sheet.getLastRowNum(); i++) {
			
			for(int j = 0; j< sheet.getRow(0).getLastCellNum(); j++) {
				
				data[i][j] =  sheet.getRow(i+1).getCell(j).toString();
			}
			//return data;
		}
		return data;
		
	}
}
