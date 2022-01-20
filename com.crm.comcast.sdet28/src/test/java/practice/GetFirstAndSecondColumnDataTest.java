package practice;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetFirstAndSecondColumnDataTest {

	public static void main(String[] args) throws Throwable  {
		// get the first and second cell
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		//
		Workbook workbook = WorkbookFactory.create(fis);
		//
		Sheet sheet = workbook.getSheet("sheet1");
		//
		int rowno = sheet.getLastRowNum();
		//
		for(int i=1;i<=rowno;i++)
		{
			Row row = sheet.getRow(i);
			String firstcolumndata = row.getCell(0).getStringCellValue();
			String secondcolumndata = row.getCell(1).getStringCellValue();
			System.out.println(firstcolumndata +"\t"+ secondcolumndata);
			
		}
		workbook.close();

	}

}
