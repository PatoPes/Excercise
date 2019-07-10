package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class LoanPage extends BasePage{

    private static String baseUrl = "https://www.credify.tech/phone/nonDMFunnel";

    @FindBy(name = "desiredAmount")
    WebElement amount;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/div/div/div[2]/div[2]/form/div/div/div[2]/div/select")
    WebElement select;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/div/div/div[2]/div[2]/form/div/div/div[3]/button")
    WebElement btnSignUp;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/div/div/div[1]/div/h2")
    WebElement msg;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/div/div/div[2]/div[2]/form/div/div/div[1]/div/div/div[2]")
    WebElement minLoanAmountMessage;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/div/div/div[2]/div[2]/form/div/div/div[2]/div/div")
    WebElement purposeMessage;

    public LoanPage (WebDriver driver){
        super(driver);
    }

    public void setDesiredAmount(String amount){
        this.amount.sendKeys(amount);
    }

    public void setPurpose(String purpose){
        Select drpPurpose = new Select(select);
        drpPurpose.selectByValue(purpose);
    }

    public SignUpPage goToSignUp(){
        btnSignUp.click();
        return new SignUpPage(driver);
    }

    public void goToLoanPage(){
        driver.get(baseUrl);
    }

    public String getTitle(){
        return msg.getText();
    }

    public String getMinLoanAmountErrorMsg(){
        return minLoanAmountMessage.getText();
    }

    public String getPurposeErrorMsg(){
        return purposeMessage.getText();
    }
}
