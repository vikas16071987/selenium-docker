package com.vikasdocker.flightreservation;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.vikasdocker.AbstractPage;

public class FlightsSelectionPage extends AbstractPage{

    @FindBy(name = "departure-flight")
    private List<WebElement> departureFlightsOption;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlightsOption;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightsButton;

    public FlightsSelectionPage(WebDriver driver) {
        super(driver);
      
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightsButton));
        return this.confirmFlightsButton.isDisplayed();

    }

    public void selectFlights(){
        int random =ThreadLocalRandom.current().nextInt(0,departureFlightsOption.size());
        this.departureFlightsOption.get(random).click();
        this.arrivalFlightsOption.get(random).click();

    }

    public void confirmFlight(){
        this.confirmFlightsButton.click();
    }

}
