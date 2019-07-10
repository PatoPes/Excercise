package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class SignUpPage extends BasePage{

    @FindBy(name = "borrowerFirstName")
    WebElement firstName;

    @FindBy(name = "borrowerLastName")
    WebElement lastName;

    @FindBy(name = "borrowerStreet")
    WebElement street;

    @FindBy(name = "borrowerCity")
    WebElement city;

    @FindBy(name = "borrowerState")
    WebElement state;

    @FindBy(name = "borrowerZipCode")
    WebElement zipCode;

    @FindBy(name = "borrowerDateOfBirth")
    WebElement date;

    @FindBy(name = "borrowerIncome")
    WebElement income;

    @FindBy(name = "borrowerAdditionalIncome")
    WebElement additionalIncome;

    @FindBy(name = "username")
    WebElement username;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[1]/div[2]/div[1]/div/div/form/div[3]/div/label/div[1]")
    WebElement terms;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[1]/div[2]/div[1]/div/div/form/div[4]/button")
    WebElement btnCheckyourRate;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[1]/div[1]/div/h2")
    WebElement title;

    @FindBy(className = "sc-ckVGcZ")
    List<WebElement> messages;

    String baseUrl = "https://www.credify.tech/phone/personal-information-1/OTHER/2000";

    public SignUpPage(WebDriver driver){
        super(driver);
    }

    public void setName(String name){
        firstName.sendKeys(name);
    }

    public void setLastName(String lastname){
        lastName.sendKeys(lastname);
    }

    public void setHomeAddress(String address){
        street.sendKeys(address);
    }

    public void setCity(String city){
        this.city.sendKeys(city);
    }

    public void setState(String state){
        this.state.sendKeys(state);
    }

    public void setZipCode(String code){
        zipCode.sendKeys(code);
    }

    public void setDateOfBirth(String dateOfBirth){
        date.sendKeys(dateOfBirth);
    }

    public void setIndividualAnnualIncome(Integer annualIncome){
        income.sendKeys(annualIncome.toString());
    }

    public void setAdditionalAnnualIncome(Integer additionalIncome){
        this.additionalIncome.sendKeys(additionalIncome.toString());
    }

    public void setEmail(String username){
        this.username.sendKeys(username);
    }

    public void setPassword(String password){
        this.password.sendKeys(password);
    }

    public void checkTermsOfUse(){
        terms.click();
    }

    public OfferPage goToOfferPage(){
        btnCheckyourRate.click();
        return new OfferPage(driver);
    }

    public String getTitle(){
        return title.getText();
    }

    public void goToSignUpPage(){
        driver.get(baseUrl);
    }

    public int getErrorMessages(){
        return messages.size();
    }

    public boolean msgIsDisplayed(String message){
        return messages.stream().filter(o -> o.getText().equals(message)).findFirst().isPresent();
    }
}
