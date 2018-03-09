package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by ivang on 3/7/2018.
 */
public class LoggedUserHomePage {

    WebDriver driver;
    WebDriverWait wait;


    By promotions = By.xpath("//*[@id=\"myNavbar\"]/ul[1]/li[2]/a");
    By loginBtn = By.xpath("//*[@id=\"myNavbar\"]/ul[2]/li[1]/a");
    By userEmailInput = By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-login/div/div/div/div[2]/div/ng-form/div[2]/div/div/input");
    By userPasswordInput = By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-login/div/div/div/div[2]/div/ng-form/div[4]/div/div/input");
    By loginBtn2 = By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-login/div/div/div/div[2]/div/ng-form/div[6]/div/button");
    By myPromotions = By.xpath("//*[@id=\"myNavbar\"]/ul[1]/li[2]/a");
//    By myAccount = By.xpath("//*[@id=\"myNavbar\"]/ul[2]/li[1]/a");
    By myAccount = By.xpath("//*[@id=\"PlayerAccountWrap\"]/div/div/div/div[2]/div/div[2]");
    By userId = By.xpath("//*[@id=\"myNavbar\"]/ul[2]/li[2]/a/text()");
    By logoutBtn = By.xpath("//*[@id=\"myNavbar\"]/ul[2]/li[3]/a");
//    By myAccountBtn = By.xpath("//*[@id=\"PlayerAccountWrap\"]/div/div/div/div[2]/div/div[2]");
    By myProfileTab = By.xpath("//*[@id=\"tabList\"]/li[1]/div/a");
    By personalInformation = By.xpath("//*[@id=\"profile\"]/div[1]/div[2]/div/div/div/span");
    By changePasswordBtn = By.xpath("//*[@id=\"supportForm\"]/div[10]/div[1]/a");
    By hiddenMenuBtn = By.xpath("//*[@id=\"myNavbar\"]/ul[2]/li[2]/a");
    By currentPasswordInput = By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/reset-password/div/div/div/div[2]/div/div/ng-form/div[1]/input");
    By newPasswordInput = By.name("newPassword");
    By confirmNewPasswordInput = By.name("confirmNewPass");
    By submitNewPassBtn = By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/reset-password/div/div/div/div[2]/div/div/ng-form/div[4]/button");
    By success = By.xpath("//*[@id=\"Multiwin\"]/div[1]/div/div/div/div/div/div[1]/span[2]");
    By successOkBtn = By.xpath("//*[@id=\"Multiwin\"]/div[1]/div/div/div/div/div/button");


    public LoggedUserHomePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public String getPromotionsTxt(){
        return driver.findElement(promotions).getText();
    }

    public void clickLoginBtn(){
        driver.findElement(loginBtn).click();
    }

    public String getLoginBtnTxt(){
//        wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        return driver.findElement(loginBtn).getText();
    }

    public void setUserEmail(String userEmail){
        driver.findElement(userEmailInput).sendKeys(userEmail);
    }

    public void setUserPassword(String userPassword){
        driver.findElement(userPasswordInput).sendKeys(userPassword);
    }

    public void clickToLogin(){
        driver.findElement(loginBtn2).click();
    }

    public String getMyPromotionsTxt(){
        return driver.findElement(myPromotions).getText().toUpperCase();
    }

    public String getMyAccountTxt(){
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(myAccount));
        return driver.findElement(myAccount).getText().toUpperCase();
    }

    public void clickMyAccount(){
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(myAccount));
        driver.findElement(myAccount).click();
    }

    public String getUserId(){
        return driver.findElement(userId).getText();
    }

    public String getMyProfileTabTxt(){
        return driver.findElement(myProfileTab).getText();
    }

    public String getPersonalInformationTxt(){
        return driver.findElement(personalInformation).getText();
    }

    public By btn(){
        return changePasswordBtn;
    }

    public void clickChangePassword(){
        wait = new WebDriverWait(driver, 25);
        wait.until(ExpectedConditions.elementToBeClickable(changePasswordBtn));
        driver.findElement(changePasswordBtn).click();
    }

    public void clickHideMenuBtn(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(hiddenMenuBtn));
        driver.findElement(hiddenMenuBtn).click();
    }

    public void setCurrentPassword(String currentPassword){
        driver.findElement(currentPasswordInput).sendKeys(currentPassword);
    }

    public void setNewPassword(String newPassword){
        driver.findElement(newPasswordInput).sendKeys(newPassword);
    }

    public void setConfirmNewPassword(String confirmNewPassword){
        driver.findElement(confirmNewPasswordInput).sendKeys(confirmNewPassword);
    }

    public void clickSubmitNewPassword(){
        driver.findElement(submitNewPassBtn).click();
    }

    public String getSuccessTxt(){
        return driver.findElement(success).getText().toUpperCase();
    }

    public void clickOkAfterSuccess(){
        driver.findElement(successOkBtn).click();
    }

    public void loginRegistredUser(String email, String password){
        setUserEmail(email);
        setUserPassword(password);
        clickToLogin();
    }

    public void changePassword(String oldPassword, String newPassword, String confirmNewPassword){
        setCurrentPassword(oldPassword);
        setNewPassword(newPassword);
        setConfirmNewPassword(confirmNewPassword);
        clickSubmitNewPassword();

    }







}
