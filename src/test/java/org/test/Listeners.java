package org.test;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listeners implements ITestListener {

    private static final Logger logger = LogManager.getLogger(ITestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.trace("[" + result.getTestClass().getName() + "] " + result.getName() + " - START");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.trace("[" + result.getTestClass().getName() + "] " + result.getName() + " - SUCCESS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("[" + result.getTestClass().getName() + "] " + result.getName() + " - FAIL");
        logger.error(result.getThrowable());

        try {
            Object currentClass = result.getInstance();
            WebDriver driver = ((Base) currentClass).getDriver();

            File scrShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = "scrShot_" + result.getTestClass().getName() + "_" + result.getName() + "_" + System.currentTimeMillis() + ".png";
            FileUtils.copyFile(scrShot, new File(System.getProperty("user.dir") + "\\screenshots\\" + fileName));
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}