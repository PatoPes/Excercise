package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogOutPage extends BasePage{

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[2]/div/h1")
    WebElement msg;

    public LogOutPage(WebDriver driver){
        super(driver);
    }

    public String getTitle(){
        return msg.getText();
    }
}
