package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	
//	public static String TESTDATA_SHEET_PATH = "C:\\Users\\Hareesh Manam\\eclipse-workspace\\FreeCrmTest\\src\\main\\java\\com\\crm\\qa\\testdata";
//	public static String File_Name = "FreeCrmTestData.xlsx";
//	public static String Sheet_Name = "Contacts";
	
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\Hareesh Manam\\eclipse-workspace\\FreeCrmTest\\src\\main\\java\\com\\crm\\qa\\testdata";
	public static String File_Name = "FreeCrmTestData.xlsx";
	public static String Sheet_Name = "Deals";
	
	static Workbook book;
	static Sheet sheet;
	
	public void switchToFrame() 
	{
		driver.switchTo().frame("mainpanel");
		
	}
	
	
	public static Object[][] getTestData(String sheetName)throws IOException
	{
		File file = new File(TESTDATA_SHEET_PATH+"\\"+File_Name);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook guru99Workbook = null;
		guru99Workbook  = new XSSFWorkbook(inputStream);
		Sheet DealsSheet = guru99Workbook.getSheet(sheetName);
		int rowCount = DealsSheet.getLastRowNum() - DealsSheet.getFirstRowNum();
		int colCount = DealsSheet.getRow(0).getLastCellNum();
		Object[][] data =new Object[rowCount][colCount];
		
		for(int i=1; i < rowCount+1; i++)
		{
			Row row = DealsSheet.getRow(i);
			for(int j = 0; j < colCount; j++)
			{
				data[i-1][j] = GetValue(row.getCell(j));
				System.out.println(data[i-1][j]);
			}
			
		}
		
		return data;	
	}
	 public static String GetValue(Cell cell)
	 {
		 DataFormatter df=new DataFormatter();
		 return df.formatCellValue(cell);
		 
	 }
	
	
	
	
//	public static Object[][] getTestData(String sheetName)throws IOException
//	{
//		File file = new File(TESTDATA_SHEET_PATH+"\\"+File_Name);
//		FileInputStream inputStream = new FileInputStream(file);
//		Workbook guru99Workbook = null;
//		guru99Workbook  = new XSSFWorkbook(inputStream);
//		Sheet ContactsSheet = guru99Workbook.getSheet(sheetName);
//		int rowCount = ContactsSheet.getLastRowNum() - ContactsSheet.getFirstRowNum();
//		int colCount = ContactsSheet.getRow(0).getLastCellNum();
//		Object[][] data =new Object[rowCount][colCount];
//		
//		for(int i=1; i < rowCount+1; i++)
//		{
//			Row row = ContactsSheet.getRow(i);
//			for(int j = 0; j < colCount; j++)
//			{
//				data[i-1][j] = row.getCell(j).getStringCellValue();
//				System.out.println(data[i-1][j]);
//			}
//			
//		}
//		
//		return data;
//		
//		
//		
//	}
	
	public static boolean verifyDataExists(String tableXpath, String colName, String expectedvalue)
	{
		boolean flag =false;
		int index = GetColumnIndex(tableXpath,colName);
		WebElement WebTable = driver.findElement(By.xpath(tableXpath));
		List<WebElement> rows =  WebTable.findElements(By.tagName("tr"));
		for(int i=4;i<=rows.size()-1;i++)
		{
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
			String colValue = cols.get(index).getText();
			colValue = colValue.trim();
			if(colValue.equals(expectedvalue))
			{
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static int GetColumnIndex(String tableXpath, String colName)
	{
		int index = 0;
		boolean flag = false;			
		WebElement WebTable = driver.findElement(By.xpath(tableXpath));
		List<WebElement> rows = WebTable.findElements(By.tagName("tr"));
		for(int i=1; i<=rows.size()-1; i++)
		{
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
			for(int j=0; j<cols.size(); j++)
			{
				String colValue = cols.get(j).getText();

				if(colValue.equals(colName))				
				{
					index = j;
					flag = true;
					break;
				}
			}
			if(flag)
			break;
		}
		return index;
	}
}

			// TODO Auto-generated method stub

		
//		public static void VerifyDataExists(String tableXpath)
//		{
//			WebElement WebTable = driver.findElement(By.xpath(tableXpath));
//			List<WebElement> cols = WebTable.findElements(By.tagName("td"));
//			System.out.println("No of cols are : " +cols.size());
//			List<WebElement> rows = WebTable.findElements(By.tagName("tr"));
//			System.out.println("No of rows are : " +rows.size());
//			} 
//}