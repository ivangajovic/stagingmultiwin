package testing;

import com.sun.glass.events.KeyEvent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.GmailPage;
import pages.LoggedUserHomePage;
import pages.LoginPage;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by ivang on 3/6/2018.
 */
public class TestMultiWin {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    LoggedUserHomePage loggedUserHomePage;
    GmailPage gmailPage;

    String baseURL = "https://staging.multiwincasino.com/";
    String gmailURL = "https://support.google.com/mail/answer/15049?hl=en";//"https://mail.google.com/mail/?ui=html";

    //Data for user registration in MWC
    String emailStr = "ivanalteatec+7@gmail.com";
    String userStr = "ivan7";
    String passwordStr = "Test#1234";
    String cnfPasswordStr = "Test#1234";
    String firstNameStr = "Ivan7";
    String lastNameStr = "Altea";
    String cityStr = "Belgrade";
    String postCodeStr = "11000";
    String addressStr = "Neka ul.";
    String genderStr = "male";
    String countryCodeStr = "225";
    String prefixValueStr = "225";
    String mobilePhoneNum = "606464";

    //Title of welcome message email to validate against
    String welcomeEmailTitle = "MultiWinCasino.com - Welcome to Multiwincasino.com";

    //Title of account activation email to validate against
    String accAcountActivationTitle = "MultiWinCasino.com - Account Activation";

    //Full name to validate in welcome message
    String fullName = firstNameStr+" "+lastNameStr+"!";

    //For user to login to MWC
    String gUsername = "ivanalteatec+7@gmail.com";
    String gPassword = "Test#1234";

    //For user to change password in MWC
    String oldPassword = "Test#1234";
    String newPassword = "Test#1234";
    String confirmNewPassword = "Test#1234";

    //Gmail credentials
    String email = "ivanalteatec";
    String password = "ivanaltea1234";

    /*
     * setUp method is used to start either chrome or firefox web driver,
     * based on the parameter that is passed from testNG xml file
     */
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUp(String browser){
        if (browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }
        if (browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseURL);
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void test_Home_Page_Appear_Correct() throws InterruptedException {
        System.out.println("Start: ---test_Home_Page_Appear_Correct---");
        loginPage = new LoginPage(driver);

        String promotionsTxt = loginPage.getPromotionsTxt();
        String gettingStartedTxt = loginPage.getGettingStartedTxt();

        Thread.sleep(1000);
        Assert.assertEquals(promotionsTxt, "PROMOTIONS");
        Assert.assertEquals(gettingStartedTxt, "GETTING STARTED");
        Assert.assertTrue(loginPage.checkImage(), "true");
        Assert.assertEquals(loginPage.getTxtLoginBtn(), "Login");
        Assert.assertEquals(loginPage.getTxtRegBtn(), "Register");
        System.out.println("End: ---test_Home_Page_Appear_Correct---");
        Thread.sleep(1000);
    }

    @Test
    public void registerUser() throws InterruptedException {
        System.out.println("Start: ---regisetrUser---");
        loginPage = new LoginPage(driver);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

        loginPage.registerToMultiwinCasino(emailStr, userStr, passwordStr, cnfPasswordStr, firstNameStr, lastNameStr,
                cityStr, postCodeStr, addressStr, genderStr, countryCodeStr, prefixValueStr, mobilePhoneNum);

        Assert.assertEquals(loginPage.successRegister(), "SUCCESS");
        loginPage.clickOkAfterSuccess();

//        Thread.sleep(60000);
        System.out.println("End: ---regisetrUser---");
    }



    @Test(priority = 2)
    public void find_User_Activation_Link_In_Gmail() throws AWTException, InterruptedException {
        Thread.sleep(1000);
        System.out.println("Start: ---find_User_Activation_Link_In_Gmail---");
        gmailPage = new GmailPage(driver);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get(gmailURL);
        gmailPage.clickToOpenBasicHtmlGmail();

        gmailPage.clickSignInBtn();
        Assert.assertEquals(driver.findElement(gmailPage.emailNxtBtn()).getText(), "NEXT");
        gmailPage.gmailUserSignin(email, password);
        Thread.sleep(500);
        gmailPage.closeCurrentTab();
        gmailPage.clickToOpenBasicHtmlGmail();
        Thread.sleep(500);
        ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs1.get(1));
        System.out.println(gmailPage.getRefreshBtnTxt());
        gmailPage.clickRefreshBtn();
        Thread.sleep(800);
        gmailPage.openFirstMail();
        Assert.assertEquals(gmailPage.getEmailTitleTxt(), accAcountActivationTitle);
        gmailPage.clickActivationLinkUrl();
        System.out.println("End: ---find_User_Activation_Link_In_Gmail---");
    }


    @Test
    public void loginUser() throws InterruptedException {
        System.out.println("Start: ---loginUser---");
        loggedUserHomePage = new LoggedUserHomePage(driver, wait);

        String promotionsTxt = loggedUserHomePage.getPromotionsTxt();

        Assert.assertEquals(promotionsTxt, "PROMOTIONS");
        loggedUserHomePage.clickLoginBtn();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loggedUserHomePage.loginRegistredUser(gUsername, gPassword);
        String myAccTxt = loggedUserHomePage.getMyAccountTxt();
        String myPromotionsTxt = loggedUserHomePage.getMyPromotionsTxt();
        Assert.assertEquals(myAccTxt, "MY ACCOUNT");
        Assert.assertEquals(myPromotionsTxt, "MY PROMOTIONS");
        loggedUserHomePage.clickMyAccount();

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,2500)", "");

        String myProfileTxt = loggedUserHomePage.getMyProfileTabTxt();
        String personalInfo = loggedUserHomePage.getPersonalInformationTxt();
        Assert.assertEquals(myProfileTxt, "MY PROFILE");
        Assert.assertEquals(personalInfo, "PERSONAL INFORMATION");
        Thread.sleep(400);


        loggedUserHomePage.clickChangePassword();
//        loggedUserHomePage.clickHideMenuBtn();
        loggedUserHomePage.clickHideMenuBtn();
        loggedUserHomePage.changePassword(oldPassword, newPassword, confirmNewPassword);
        String successTxt = loggedUserHomePage.getSuccessTxt();
        Assert.assertEquals(successTxt, "SUCCESS");
        loggedUserHomePage.clickOkAfterSuccess();
        Thread.sleep(500);
        String loginAfterCPTxt = loggedUserHomePage.getLoginBtnTxt();
        Assert.assertEquals(loginAfterCPTxt, "Login");
        System.out.println("End: ---loginUser---");
    }

    @Test(priority = 3)
    public void checkIfWelcomeMessageIsRecived(){
        System.out.println("Start: ---checkIfWelcomeMessageIsRecived---");
        gmailPage = new GmailPage(driver);

        Assert.assertEquals(gmailPage.getEmailTitleTxt(), welcomeEmailTitle);
        Assert.assertEquals(gmailPage.getUsernameInMail(), fullName);
        System.out.println(gmailPage.getEmailTitleTxt());
        System.out.println("End: ---checkIfWelcomeMessageIsRecived---");
    }





//  tearDown method is used to stop web driver
    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }


}
