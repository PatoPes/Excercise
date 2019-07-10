package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PersonalInformation extends BasePage {

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/header/div/label")
    WebElement menu;

    @FindBy(linkText = "Sign Out")
    WebElement signOut;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[1]/div/h2")
    WebElement msg;

    public PersonalInformation(WebDriver driver){
        super(driver);
    }

    public void clickOnMenu(){
        menu.click();
    }

    public LogOutPage signOut(){
        signOut.click();
        return new LogOutPage(driver);
    }

    public String getTitle(){
        return msg.getText();
    }
}