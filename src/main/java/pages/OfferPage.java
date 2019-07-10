package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OfferPage extends BasePage{

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[2]/div[1]/div/div[1]/div[1]/div[2]/div/div/div/div[2]/div/button")
    private WebElement btnStore;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/header/div/label")
    WebElement menu;

    @FindBy(linkText = "Sign Out")
    WebElement signOut;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/div[2]/span[2]")
    WebElement loanAmount;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[2]/div[1]/div/div[1]/div[1]/div[2]/div/div/div/div[1]/div/div/span")
    WebElement monthlyPayment;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[1]/div/h2")
    WebElement msg;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[2]/div[1]/div/div[1]/div[1]/div[2]/div/div/div/div[2]/div/div/div[1]")
    WebElement loanTerm;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[2]/div[1]/div/div[1]/div[1]/div[2]/div/div/div/div[2]/div/div/div[2]")
    WebElement interestRate;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[2]/div[1]/div/div[1]/div[1]/div[2]/div/div/div/div[2]/div/div/div[3]/div/div")
    WebElement apr;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[2]/div[1]/div/div[1]/div[1]/div[2]/div/div/div")
    WebElement module;

    public OfferPage(WebDriver driver){
        super(driver);
    }

    public PersonalInformation storeFinancialData(){
            btnStore.click();
            return new PersonalInformation(driver);
    }

    public void clickOnMenu(){
        menu.click();
    }

    public LogOutPage signOut(){
        signOut.click();
        return new LogOutPage(driver);
    }

    public String getLoanAmount(){
        return loanAmount.getText();
    }

    public String getMonthlyPayment(){
        return monthlyPayment.getText();
    }

    public String getTitle(){
        return msg.getText();
    }

    public String getLoanTerm(){
        return loanTerm.getText();
    }

    public String getInterestRate(){
        return interestRate.getText();
    }

    public String getAPR(){
        return apr.getText();
    }
}