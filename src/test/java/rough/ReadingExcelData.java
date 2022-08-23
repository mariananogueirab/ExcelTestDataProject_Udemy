package rough;

import com.w2a.utilities.Constants;
import com.w2a.utilities.ExcelReader;

public class ReadingExcelData {

    public static void main(String[] args) {
        ExcelReader excel = new ExcelReader("src/test/resources/testdata/BankManagerSuite.xlsx");
        int numberOfRowsSheet = excel.getRowCount(Constants.DATA_SHEET);
        System.out.println("Number of rows in the sheet: " + numberOfRowsSheet);

        String testName = "AddCustomerTest";

        // Find the test case start row
        int testCaseRowNum = 1;

        for(testCaseRowNum=1; testCaseRowNum <= numberOfRowsSheet; testCaseRowNum++) {

            String testCaseName = excel.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);

            if (testCaseName.equalsIgnoreCase(testName)) break;
        }

        // Checking total rows in test case
        int dataStartRowNum = testCaseRowNum + 2; // to jump the head line
        System.out.println("Data starts row num: " + dataStartRowNum);

        int testRows = 0;
        // Enquanto a célula não for branca, vai incrementar
        while (!excel.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum+testRows).isBlank()) {
            testRows++;
        }
        System.out.println("Total of rows in test case: " + testRows);
    }
}
