package practice;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class CheckFirstCellValueAndSecondCellTest {

	public static void main(String[] args) throws Throwable {
		FileInputStream fis = new FileInputStream("./src/test/resource/TestData.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("sheet1");
		String celldata = "CreateOrganisation";
		
		int rowno = sheet.getLastRowNum();
		for(int i=1;i<=rowno;i++) {
			Row row = sheet.getRow(i);
			String firstcelldata = row.getCell(0).getStringCellValue();
			if(firstcelldata.equals(celldata))
			{
				String secondcellvalue = row.getCell(1).getStringCellValue();
				System.out.println(secondcellvalue);
			}
			workbook.close();
			   
		}

	}

}
