package pages;

import org.testng.annotations.*;
import utilities.Util;
import com.arakelian.faker.model.Address;
import com.arakelian.faker.model.Person;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ExerciseTest {
    static Integer desiredAmount = 2000;
    static String purpose = "OTHER";
    static int annualIncome = 121000;
    static int additionalIncome = 5001;
    WebDriver driver;
    LoanPage loan;
    SignUpPage sign;
    OfferPage offer;
    LoginPage login;
    LogOutPage logout;
    Person person;
    Address address;
    PersonalInformation info;
    String password = "Th1s1s4P4ssw0rd";
    String domain = "@upgrade-challenge.com";
    String email;
    String expectedLoanAmount = "2,000";

    @BeforeClass
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver",new File(".\\src\\test\\resources\\drivers\\chromedriver.exe").getCanonicalPath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    @Test (priority = 1, groups = {"ui-group"})
    public void testExcerciseWorkflow() throws InterruptedException {
        person = Util.getARandonCustomer();
        address = Util.getARandomAddress();
        loan = new LoanPage(driver);
        loan.goToLoanPage();
        loan.waitForPageToLoad();
        String loanTitle = "Apply for a personal loan.";
        //Check for loan page title
        Assert.assertEquals(loan.getTitle(),loanTitle);
        loan.setDesiredAmount(desiredAmount.toString());
        loan.setPurpose(purpose);
        sign = loan.goToSignUp();
        //sign.waitForPageToLoad();
        String signTitle1 = "Let's get started with some basic information";
        String signTitle2 = "Check your rate with just one click";
        //Check for sign up page title
        Assert.assertTrue(signTitle1.equals(sign.getTitle()) || signTitle2.equals(sign.getTitle()));
        //Complete form with customer personal data
        sign.setName(person.getFirstName());
        sign.setLastName(person.getLastName());
        sign.setHomeAddress(address.getStreet());
        sign.setCity(address.getCity());
        sign.setState(address.getState());
        sign.setZipCode(address.getPostalCode());
        sign.setDateOfBirth(Util.generatesRandomDate(1930,2000));
        sign.setIndividualAnnualIncome(annualIncome);
        sign.setAdditionalAnnualIncome(additionalIncome);
        //Generate an email to be used
        email = person.getFirstName() + Util.generatesRandomIntBetween(1,1000) + domain;
        sign.setEmail(email);
        sign.setPassword(password);
        sign.checkTermsOfUse();
        offer = sign.goToOfferPage();
        offer.waitForPageToLoad();
        String offerTitle = "Great news, here are your offers!";
        //Check for offer page title
        Assert.assertEquals(offer.getTitle(),offerTitle);
        //Assert loan amount added previously by customer
        Assert.assertEquals(offer.getLoanAmount(), expectedLoanAmount);
        //Update variables with each value
        String expectedMonthlyPayment = offer.getMonthlyPayment();
        String apr = offer.getAPR();
        String interestRate = offer.getInterestRate();
        String loanTerm = offer.getLoanTerm();
        info = offer.storeFinancialData();
        info.waitForPageToLoad();
        String infoTitle = "We'd like to get to know you better.";
        //Check for personal information page title
        Assert.assertEquals(info.getTitle(),infoTitle);
        info.clickOnMenu();
        logout = info.signOut();
        String logOutTitle = "You've been successfully logged out.\n" + "See you later.";
        //Check for logout page title
        Assert.assertEquals(logout.getTitle(),logOutTitle);
        login = new LoginPage(driver);
        login.goToLoginPage();
        login.setUsername(email);
        login.setPassword(password);
        offer = login.signIn();
        offer.waitForPageToLoad();
        //Check for required validations
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.credify.tech/phone/offer-page");
        Assert.assertEquals(offer.getTitle(),offerTitle);
        Assert.assertEquals(offer.getLoanAmount(), expectedLoanAmount);
        Assert.assertEquals(offer.getMonthlyPayment(), expectedMonthlyPayment);
        Assert.assertEquals(offer.getLoanTerm(),loanTerm);
        Assert.assertEquals(offer.getAPR(),apr);
        Assert.assertEquals(offer.getInterestRate(),interestRate);
    }
}
