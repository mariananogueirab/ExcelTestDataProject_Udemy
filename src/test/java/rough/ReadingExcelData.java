package rough;

import com.w2a.utilities.Constants;
import com.w2a.utilities.ExcelReader;

public class ReadingExcelData {

    public static void main(String[] args) {
        ExcelReader excel = new ExcelReader("src/test/resources/testdata/BankManagerSuite.ods");
        int rows = excel.getRowCount(Constants.DATA_SHEET);

    }
}
