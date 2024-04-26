package com.vikasdocker.listener;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.vikasdocker.util.Constants;

public class TestListerner implements ITestListener {


    public void onTestFailure(ITestResult result){

       TakesScreenshot driver = (TakesScreenshot)result.getTestContext().getAttribute(Constants.DRIVER);
       String screenshot = driver.getScreenshotAs(OutputType.BASE64);
       String htmlImageFormat = "<img width =700px src='data:image/png;base64,%s' />";
       String htmlImage = String.format(htmlImageFormat, screenshot);
       Reporter.log(htmlImage);




    }

}
