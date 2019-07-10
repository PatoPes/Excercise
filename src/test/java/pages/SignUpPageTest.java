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

public class SignUpPageTest {
    WebDriver driver;
    SignUpPage sign;

    @BeforeClass
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver",new File(".\\src\\test\\resources\\drivers\\chromedriver.exe").getCanonicalPath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        sign = new SignUpPage(driver);
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    @Test(priority = 0, groups = {"ui-group"})
    public void testRequiredFieldsErrorMessages() throws InterruptedException {
        sign.goToSignUpPage();
        sign.waitForPageToLoad();
        sign.goToOfferPage();
        /*Check # of error messages displayed*/
        Assert.assertEquals(sign.getErrorMessages(),11);
    }

    @Test(priority = 1, groups = {"ui-group"})
    public void testMinAgeErrorMessage(){
        sign.setDateOfBirth("01012005");
        Assert.assertTrue(sign.msgIsDisplayed("You must be at least 18 years old."));
    }
}
