package genericUitility;


import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	/**
	 * This Method will return string value from excel sheet
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @return
	 * @throws Throwable
	 * @throws IOException
	 */
	public String getStringCellData(String sheetName,int rowNo,int cellNo) throws Throwable, IOException {
		 FileInputStream file = new FileInputStream(IpathConstants.EXCEL_FILEPATH);
		 Workbook workbook = WorkbookFactory.create(file);
		 Sheet sheet = workbook.getSheet(sheetName);
		 Row row = sheet.getRow(rowNo);
		 Cell cell = row.getCell(cellNo);
		 return cell.getStringCellValue();
		 
	}
	/**
	 * 
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @return
	 * @throws Throwable
	 */
	
	public double getNumericCellData(String sheetName,int rowNo,int cellNo) throws Throwable {
		FileInputStream file = new FileInputStream(IpathConstants.EXCEL_FILEPATH);
		 Workbook workbook = WorkbookFactory.create(file);
		 return workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getNumericCellValue();
	}
	public Object[][] getMultipleData(String sheetname) throws Throwable {
	FileInputStream file = new FileInputStream(IpathConstants.EXCEL_FILEPATH);
	Workbook workbook = WorkbookFactory.create(file);
	Sheet sheet = workbook.getSheet(sheetname);
	int rowNo = sheet.getLastRowNum();
	int cellNo = sheet.getRow(0).getLastCellNum();
	Object[][] data = new Object[rowNo][cellNo];
	for(int i=0;i < rowNo; i++)
	{
		for (int j = 0; j< cellNo; j++) {
			data[i][j] = sheet.getRow(i).getCell(j).toString();
		}
	}
	return data;
	

}}
