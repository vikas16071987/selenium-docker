package com.vikasdocker.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.vikasdocker.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage {


    public RegistrationConfirmationPage(WebDriver driver){

        super(driver);
    }

    @FindBy(id="go-to-flights-search")
    private WebElement goTOFlightSearchButton;
    @FindBy(css = "#registration-confirmation-section p b")
    private WebElement firsntNameElement;


    


    @Override
    public boolean isAt() {
      
        
        this.wait.until(ExpectedConditions.visibilityOf(this.goTOFlightSearchButton));
        return this.goTOFlightSearchButton.isDisplayed();
    
    }
    
    public void goTOFlightSearch(){
        this.goTOFlightSearchButton.click();
    }

    public String getFirstname(){
        return this.firsntNameElement.getText();
    }

}
