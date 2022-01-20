
package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataFormExcelTest {

	public static void main(String[] args) throws Throwable {
		//
		 FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		 Workbook workbook = WorkbookFactory.create(fis);
		     Sheet sheet = workbook.getSheet("sheet1");
		     Row row = sheet.getRow(2);
		     row.createCell(2).setCellValue("Wipro");
		     
		     FileOutputStream fos = new FileOutputStream("./src/test/resources/TestData.xlsx");
		     workbook.write(fos);
		     workbook.close();
		     

	}

}
