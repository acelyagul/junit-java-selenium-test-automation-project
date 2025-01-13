package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.File;
import java.io.FileInputStream;


public class ExcelReader {
    public static Object[][] getTestData(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                LoggerUtil.error("Sheet '" + sheetName + "' not found in Excel file");
                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found");
            }
            
            int rowCount = sheet.getPhysicalNumberOfRows() - 1;
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            
            LoggerUtil.info("Found " + rowCount + " rows and " + colCount + " columns in Excel");
            
            Object[][] data = new Object[rowCount][colCount];
            
            for (int i = 0; i < rowCount; i++) {
                XSSFRow row = sheet.getRow(i + 1);
                for (int j = 0; j < colCount; j++) {
                    data[i][j] = getCellValue(row.getCell(j));
                }
            }
            
            return data;
            
        } catch (Exception e) {
            LoggerUtil.error("Error reading Excel file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    private static String getCellValue(XSSFCell cell) {
        if (cell == null) return "";
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf((long)cell.getNumericCellValue());
            default:
                return "";
        }
    }
} 