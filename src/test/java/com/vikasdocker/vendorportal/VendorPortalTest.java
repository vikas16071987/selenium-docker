package com.vikasdocker.vendorportal;


import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.vikasdocker.util.Config;
import com.vikasdocker.AbstractTest;
import com.vikasdocker.util.Constants;
import com.vikasdocker.util.JsonUtil;
import com.vikasdocker.vendorportal.model.VendorPortalTestData;




public class VendorPortalTest extends AbstractTest {

   
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters({ "testDataPath"})
    public void setPageObjects(String testDataPath){
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());

    }

    @Test(dependsOnMethods = "loginTest")
    public void dashBoardTest(){
   
       Assert.assertTrue(dashboardPage.isAt());
       Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
       Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
       Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
       Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());
       //oderHistory
       dashboardPage.searchOderHistoryBy("adams");
       Assert.assertEquals(dashboardPage.getSearchResultCount(), testData.searchResultsCount());
       

    }

    @Test(dependsOnMethods = "dashBoardTest")
    public void logoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }
   
}