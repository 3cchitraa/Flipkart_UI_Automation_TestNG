package com.flipkart.utilities;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviderUtil 
{
	@DataProvider(name = "searchItemData")
	public Object[][] getProductSearchItem()
	{
		
		return new Object[][]
		{
			{"iPhone 15"}
		};
	}
	
	
	@DataProvider(name = "excelTestData")
    public Object[][] getExcelTestData() 
	{
		 String filePath = TestDataReader.getTestData("excel_file_path");
	     String sheetName =  TestDataReader.getTestData("excel_sheet_name");   
	     String targetColumnName = TestDataReader.getTestData("excel_column_name");  
		
//        String filePath = "src/test/resources/excelTestData.xlsx";
//        String sheetName = "FlipkartSeacrhProductData";
//        String targetColumnName = "InvalidProduct";            // Column header to fetch

        ExcelReader reader = new ExcelReader(filePath, sheetName);

        int totalRows = reader.getRowCount();
        int headerCols = reader.getColumnCount(0);
        int targetColIndex = -1;

        // Dynamically finding the column index
        for (int j = 0; j < headerCols; j++) 
        {
            Object headerValue = reader.getCellValue(0, j);
            if (headerValue != null && headerValue.toString().equalsIgnoreCase(targetColumnName)) 
            {
                targetColIndex = j;
                break;
            }
        }

        if (targetColIndex == -1) {
            throw new RuntimeException("Column '" + targetColumnName + "' not found in Excel sheet.");
        }

       // Count non-null test data rows
        List<Object[]> validData = new ArrayList<>();

        for (int i = 1; i <= totalRows; i++)
        {
            Object value = reader.getCellValue(i, targetColIndex);
            if (value != null && !value.toString().trim().isEmpty() && !value.toString().equalsIgnoreCase("row is null") &&
                !value.toString().equalsIgnoreCase("not a test data")) 
            {
                
            	validData.add(new Object[] { value });
            }
        }
        
        // Convert list to Object[][]
        Object[][] dataArray = new Object[validData.size()][1];
        
        for (int i = 0; i < validData.size(); i++) 
        {
            dataArray[i] = validData.get(i);
        }

        return dataArray;
        
//        int totalRows = reader.getRowCount();             // Total rows including header
//        Object[][] data = new Object[totalRows - 1][1];  // Excluding header row
//
//        for (int i = 1; i < totalRows; i++)     // Start from row 1 (skip header)
//        {           
//            data[i - 1][0] = reader.getCellValue(i, targetColIndex);
//        }
//
//        return data;
    }

}
