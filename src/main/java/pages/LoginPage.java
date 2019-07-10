package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    //WebDriver driver;
    String baseUrl = "https://www.credify.tech/portal/login";

    @FindBy(name = "username")
    WebElement username;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/div/div/form/button")
    WebElement btnLogin;

    public  LoginPage (WebDriver driver){
        super(driver);
    }

    public void setUsername(String username) /*throws InterruptedException*/ {
        //Thread.sleep(5000);
        this.username.sendKeys(username);
    }

    public void setPassword(String password) {
        this.password.sendKeys(password);
    }

    public OfferPage signIn(){
        btnLogin.click();
        return new OfferPage(driver);
    }

    public void goToLoginPage(){
        driver.get(baseUrl);
    }
}
