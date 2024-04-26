package com.vikasdocker;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.google.common.util.concurrent.Uninterruptibles;
import com.vikasdocker.listener.TestListerner;
//import org.testng.annotations.Parameters;
import com.vikasdocker.util.Config;
import com.vikasdocker.util.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.*;

@Listeners(TestListerner.class)
public class AbstractTest {


    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    protected WebDriver driver;

    @BeforeSuite
    public void setupConfig() throws IOException, Exception{
        Config.initialize();
    }




    @BeforeTest
    //@Parameters({"browser"})
    public void setDriver(ITestContext ctx) throws MalformedURLException{
        this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
        ctx.setAttribute(Constants.DRIVER, this.driver);
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities = new ChromeOptions();
        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
            capabilities = new FirefoxOptions();
        }

        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        log.info("grid url: {}",url);
        return new RemoteWebDriver(new URL(url), capabilities);
    }

    private WebDriver getLocalDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
         

    }

    @AfterTest
    public void quitBrowser(){

        if (driver!=null){
            driver.quit();
        }
    }
    @AfterMethod
    public void sleep(){

        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));

    }
}
