package rough;

import com.w2a.utilities.Constants;
import com.w2a.utilities.ExcelReader;

public class ReadingExcelData {

    public static void main(String[] args) {
        ExcelReader excel = new ExcelReader("src/test/resources/testdata/BankManagerSuite.xlsx");
        int rows = excel.getRowCount(Constants.DATA_SHEET);
        System.out.println("Number of rows: " + rows);

        String testName = "AddCustomerTest";

        int testCaseRowNum = 1;

        for(testCaseRowNum=1; testCaseRowNum <= rows; testCaseRowNum++) {

            String testCaseName = excel.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);

            if (testCaseName.equalsIgnoreCase(testName)) break;
        }

        System.out.println("Test case starts from row num: " +testCaseRowNum);
    }
}
