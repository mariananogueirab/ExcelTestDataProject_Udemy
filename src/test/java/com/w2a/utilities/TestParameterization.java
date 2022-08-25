package com.w2a.utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class TestParameterization {

    @Test(dataProvider = "getData")
    // public void testData(String runmode, String firstname, String lastname, String postcode) {
    public void testData(Hashtable<String, String> data) {
        // System.out.println(data.get("Runmode")+"---"+data.get("firstname")+"----"+data.get("lastname")+"--"+data.get("postcode"));
        System.out.println(data);
    }

    @DataProvider
    public Object[][] getData() {

        ExcelReader excel = new ExcelReader("src/test/resources/testdata/BankManagerSuite.xlsx");
        int numberOfRowsSheet = excel.getRowCount(Constants.DATA_SHEET);
        // System.out.println("Number of rows in the sheet: " + numberOfRowsSheet);

        String testName = "OpenAccountTest";

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
        int colStartColNum = testCaseRowNum +1;

        while(!excel.getCellData(Constants.DATA_SHEET, testCols, colStartColNum).isBlank()) {
            testCols++;
        }

        // System.out.println("Total of columns in test are: " + testCols);

        // Printing data

        Object[][] data = new Object[testRows][1]; // cols = 1 because of the hashdata

        int incrementTable = 0;
        for(int rowNum = dataStartRowNum; rowNum < (dataStartRowNum + testRows); rowNum++) {
            Hashtable<String, String> table = new Hashtable<>(); // cria uma hashtable pra cada row
            // Uma Hashtable é <Key, Value>
            for(int colNum = 0; colNum < testCols; colNum++) {
                // System.out.println(excel.getCellData(Constants.DATA_SHEET, colNum, rowNum));
                String testData = excel.getCellData(Constants.DATA_SHEET, colNum, rowNum); // pega os dados
                String colName = excel.getCellData(Constants.DATA_SHEET, colNum, colStartColNum); // pega o cabeçalho

                table.put(colName, testData);
                // System.out.println(table);
                // Para cada coluna que eu rodar, vai ter uma chave (key, value) na table, onte a key vai ser o nome da coluna e o value o dado
                // {postcode=A234324, Runmode=Y, lastname=Potter, firstname=Harry}
                // cada vez que rodar vai colocar um par de chave e valor desse pra row até completar todas as colunas, ai vai pro for de cima pra formar uma nova row
            }
            // coloca a linha da tabela que formou toda na mesma coluna de data
            data[incrementTable][0] = table;
            incrementTable++;
        }

        return data;
    }
}
