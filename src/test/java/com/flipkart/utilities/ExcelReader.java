package com.flipkart.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelReader
{
    private Sheet sheet;

    public ExcelReader(String filePath, String sheetName) 
    {
        try (FileInputStream fis = new FileInputStream(filePath))
        {
            Workbook workbook = new XSSFWorkbook(fis);
            this.sheet = workbook.getSheet(sheetName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Object getCellValue(int rowIndex, int colIndex) 
    {
        Row row = sheet.getRow(rowIndex);
        if (row == null) 
        	return "row is null";

        Cell cell = row.getCell(colIndex);
        if (cell == null) 
        	return "row is null";

        switch (cell.getCellType()) 
        {
            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) 
                {
                    Date date = cell.getDateCellValue();
                    return new SimpleDateFormat("yyyy-MM-dd").format(date);
                } 
                else 
                {
                    double val = cell.getNumericCellValue();
                    return (val==(int) val) ? (int) val : val;    // returns int if no decimals are there
                }

            case BOOLEAN:
                return cell.getBooleanCellValue();

            case FORMULA:
                return cell.getCellFormula();

            default:
                return "not a test data";
        }
    }

    public int getRowCount() 
    {
        return sheet.getLastRowNum();
    }

    public int getColumnCount(int rowIndex) 
    {
        Row row = sheet.getRow(rowIndex);
        return row != null ? row.getLastCellNum() : 0;
    }
}
