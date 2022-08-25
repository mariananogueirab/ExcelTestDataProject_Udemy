package com.w2a.utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestParameterization {

    @Test(dataProvider = "getData")
    public void testData(String runmode, String firstname, String lastname, String postcode) {

        System.out.println(runmode+"---"+firstname+"----"+lastname+"--"+postcode);
    }

    @DataProvider
    public Object[][] getData() {

        ExcelReader excel = new ExcelReader("src/test/resources/testdata/BankManagerSuite.xlsx");
        int numberOfRowsSheet = excel.getRowCount(Constants.DATA_SHEET);
        // System.out.println("Number of rows in the sheet: " + numberOfRowsSheet);

        String testName = "AddCustomerTest";

        // Find the test case start row
        int testCaseRowNum = 1;

        for(testCaseRowNum=1; testCaseRowNum <= numberOfRowsSheet; testCaseRowNum++) {

            String testCaseName = excel.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);

            if (testCaseName.equalsIgnoreCase(testName)) break;
        }

        // Checking total rows in test case
        int dataStartRowNum = testCaseRowNum + 2; // to jump the head line
        // System.out.println("Data starts row num: " + dataStartRowNum);

        int testRows = 0;
        // Enquanto a célula não for branca, vai incrementar
        while (!excel.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum+testRows).isBlank()) {
            testRows++;
        }
        // System.out.println("Total of rows in test case: " + testRows);

        // Checking total of columns in test case

        int testCols = 0;

        while(!excel.getCellData(Constants.DATA_SHEET, testCols, dataStartRowNum).isBlank()) {
            testCols++;
        }

        // System.out.println("Total of columns in test are: " + testCols);

        // Printing data

        Object[][] data = new Object[testRows][testCols];

        for(int rowNum = dataStartRowNum; rowNum < (dataStartRowNum + testRows); rowNum++) {
            for(int colNum = 0; colNum < testCols; colNum++) {
                // System.out.println(excel.getCellData(Constants.DATA_SHEET, colNum, rowNum));
                data[rowNum-dataStartRowNum][colNum] = excel.getCellData(Constants.DATA_SHEET, colNum, rowNum);
            }
        }

        return data;
    }
}
