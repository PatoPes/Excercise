package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoanPageTest {

    WebDriver driver;
    LoanPage loan;

    @BeforeClass
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver",new File(".\\src\\test\\resources\\drivers\\chromedriver.exe").getCanonicalPath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loan = new LoanPage(driver);
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    @Test(priority = 0, groups = {"ui-group"})
    public void testMinLoanAmountErrorMessage1()throws InterruptedException{
        loan.goToLoanPage();
        loan.waitForPageToLoad();
        loan.goToSignUp();
        Assert.assertEquals(loan.getMinLoanAmountErrorMsg(),"This field is required");
    }

    @Test(priority = 1, groups = {"ui-group"})
    public void testMinLoanAmountErrorMessage2(){
        loan.setDesiredAmount("500");
        loan.goToSignUp();
        Assert.assertEquals(loan.getMinLoanAmountErrorMsg(),"Please enter loan amount between $1,000 and $50,000.");
    }

    @Test(priority = 2, groups = {"ui-group"})
    public void testPurposeErrorMessage(){
        loan.setDesiredAmount("2000");
        loan.goToSignUp();
        Assert.assertEquals(loan.getPurposeErrorMsg(),"Select a purpose");
    }

}
