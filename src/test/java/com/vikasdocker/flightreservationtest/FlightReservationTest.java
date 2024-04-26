package com.vikasdocker.flightreservationtest;
import org.testng.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.vikasdocker.flightreservation.FlightSearchpage;
import com.vikasdocker.flightreservation.FlightsSelectionPage;
import com.vikasdocker.flightreservation.RegistrationConfirmationPage;
import com.vikasdocker.flightreservation.RegistrationPage;
import com.vikasdocker.flightreservationtest.model.FlightReservationTestData;
import com.vikasdocker.util.Config;
import com.vikasdocker.util.Constants;
import com.vikasdocker.util.JsonUtil;
import com.vikasdocker.AbstractTest;
import com.vikasdocker.flightreservation.FlightConfirmationPage;



public class FlightReservationTest extends AbstractTest {


    private String expectedPrice; 
    private String noOfPassengers;
    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters({ "testDataPath"})
    public void setParameters(String testDataPath ){
       this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    
    }

    @Test
    public void userRegistrationTest(){

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterUserCredintials(testData.email(), testData.password());
        registrationPage.enterAddress(testData.street(), testData.city(), testData.zip());
        registrationPage.register();
    
    }
    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTEST(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getFirstname(), testData.firstName());
        registrationConfirmationPage.goTOFlightSearch();

    }

    @Test(dependsOnMethods = "registrationConfirmationTEST")
    public void flightSearchTest(){
        FlightSearchpage flightSearchpage = new FlightSearchpage(driver);
        Assert.assertTrue(flightSearchpage.isAt());
        flightSearchpage.selectPassenger(testData.passengersCount());
        flightSearchpage.searchFlight();
    }
    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest(){
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());
        flightsSelectionPage.selectFlights();
        flightsSelectionPage.confirmFlight();

    }
    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationConfirmationTest(){
    	
  
    	FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());
    }

   


}
