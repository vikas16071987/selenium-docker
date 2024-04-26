package com.vikasdocker.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.vikasdocker.AbstractPage;

public class FlightSearchpage extends AbstractPage{


    @FindBy(id="passengers")
    private WebElement passengerSelect;

    @FindBy(id="search-flights")
    private WebElement searchFlightButton;

    public FlightSearchpage(WebDriver driver) {
        super(driver);
       
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengerSelect));
        return this.passengerSelect.isDisplayed();
    }

    public void selectPassenger(String noOfPassengers){
        Select passengers = new Select(this.passengerSelect);
        passengers.selectByValue(noOfPassengers);
    }

    public void searchFlight(){
        this.searchFlightButton.click();
    }

}
