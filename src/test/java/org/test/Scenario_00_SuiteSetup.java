package org.test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Scenario_00_SuiteSetup {
    @BeforeSuite
    public void copyExcelTemplate() {
        ExcelDriver excel = new ExcelDriver();
        excel.copyTemplate();
    }

    @AfterSuite
    public void mergeLogFiles() {
        LogFiles log = new LogFiles();
        log.merge();
    }
}