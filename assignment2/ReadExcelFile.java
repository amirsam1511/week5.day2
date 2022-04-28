package week5.day2.assignment2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {
	//create readData method passing fileName
	public static String[][] readData() throws IOException {
		//Created XSSFWorkbook 
		XSSFWorkbook wb = new XSSFWorkbook(".//data//ServiceNow.xlsx");
		//Get the corresponding sheet in workbook
		XSSFSheet ws = wb.getSheet("Sheet1");
		//Get the row count
		int rowCount = ws.getLastRowNum();
		//Get the Cell Count
		int cellCount = ws.getRow(0).getLastCellNum();
		//Create a 2D string by passing row and cell count
		String data[][] = new String[rowCount][cellCount];
		//Iterate using nested for loops and get the cell value 
		for (int i = 1; i <= rowCount; i++) {
			for(int j=0;j<cellCount;j++) {
				String cellValue = ws.getRow(i).getCell(j).getStringCellValue();
				System.out.println(cellValue);
				data[i-1][j] = cellValue;
			}
		}
		//Close the workbook
		wb.close();
		return data;

	}

}
