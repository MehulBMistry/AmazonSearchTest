package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class ExcelUtil {
    public static final String testDataExcelFileName = "resources/Testdata.xlsx"; //Global test data excel file
    private static XSSFWorkbook excelWBook; //Excel WorkBook
    private static XSSFSheet excelWSheet; //Excel Sheet
    private static XSSFCell cell; //Excel cell
    private static XSSFRow row; //Excel row
    public static int rowNumber; //Row Number
    public static int columnNumber; //Column Number

    // This method has two parameters: "Test data excel file name" and "Excel sheet name"
    // It creates FileInputStream and set excel file and excel sheet to excelWBook and excelWSheet variables.

    public static void setExcelFileSheet(String sheetName) throws IOException {

        // Open the Excel file
        FileInputStream ExcelFile = new FileInputStream(testDataExcelFileName);
        excelWBook = new XSSFWorkbook(ExcelFile);
        excelWSheet = excelWBook.getSheet(sheetName);
    }

    public static int getRowCount() {
        int rowCount = excelWSheet.getLastRowNum() - excelWSheet.getFirstRowNum();
        return rowCount;
    }

    //This method reads the test data from the Excel cell.
    //We are passing row number and column number as parameters.
    public static String getCellData(int RowNum, int ColNum) {
        cell = excelWSheet.getRow(RowNum).getCell(ColNum);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    public static int getCellIntData(int RowNum, int ColNum) {
        cell = excelWSheet.getRow(RowNum).getCell(ColNum);
        DataFormatter formatter = new DataFormatter();
        return Integer.parseInt(formatter.formatCellValue(cell));
    }

    //This method takes row number as a parameter and returns the data of given row number.
    public static XSSFRow getRowData(int RowNum) {
        row = excelWSheet.getRow(RowNum);
        return row;
    }

    //This method gets excel file, row and column number and set a value to the that cell.

    public static void setCellData(String value, int RowNum, int ColNum) throws IOException {
        row = excelWSheet.getRow(RowNum);
        cell = row.getCell(ColNum);
        if (cell == null) {
            cell = row.createCell(ColNum);
            cell.setCellValue(value);
        } else {
            cell.setCellValue(value);
        }
        // Constant variables Test Data path and Test Data file name
        FileOutputStream fileOut = new FileOutputStream(testDataExcelFileName);
        excelWBook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    public static HashMap createHashMap() throws IOException {

        FileInputStream file = new FileInputStream(testDataExcelFileName);
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sh = wb.getSheet("Sheet3");

        HashMap<String, String> map
                = new HashMap<String, String>();

        for (int r = 0; r <= sh.getLastRowNum(); r++) {
            String key = (String) sh.getRow(r)
                    .getCell(0)
                    .getStringCellValue();
            String value = sh.getRow(r)
                    .getCell(1)
                    .getStringCellValue();
            map.put(key, value);
        }

        // Displaying HashMap
        Iterator<Map.Entry<String, String>> new_Iterator
                = map.entrySet().iterator();

        while (new_Iterator.hasNext()) {
            Map.Entry<String, String> new_Map
                    = (Map.Entry<String, String>)
                    new_Iterator.next();

            //System.out.println(new_Map.getKey() + "|"
            //        + new_Map.getValue());
        }
        wb.close();
        file.close();
        return map;
    }
}
