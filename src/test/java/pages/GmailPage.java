package pages;

import com.sun.glass.events.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivang on 3/8/2018.
 */
public class GmailPage {

    WebDriver driver;
    WebDriverWait wait;

    By signIn = By.xpath("/html/body/nav/div/a[2]");
    By emailInput = By.id("identifierId");
    By emailNextBtn = By.id("identifierNext");
    By passwordInput = By.name("password");
    By passwordNextBtn = By.id("passwordNext");
    By basicHTMGmailBtn = By.xpath("/html/body/div[2]/div[2]/section/div/div[1]/article/section/div/p[7]/a");
    By refreshBtn = By.xpath("/html/body/table[2]/tbody/tr/td[2]/table[1]/tbody/tr/td[2]/form/table[1]/tbody/tr/td[1]/a");
    By allMails = By.xpath("/html/body/table[2]/tbody/tr/td[2]/table[1]/tbody/tr/td[2]/form/table[2]");
    By titleOfEmailTbl = By.xpath("/html/body/table[2]/tbody/tr/td[2]/table[1]/tbody/tr/td[2]/table[3]");
    By mailBody = By.xpath("/html/body/table[2]/tbody/tr/td[2]/table[1]/tbody/tr/td[2]/table[4]/tbody/tr/td/table[1]");






    public GmailPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickSignInBtn(){
        driver.findElement(signIn).click();
    }

    public By emailNxtBtn(){
        return emailNextBtn;
    }

    public void setEmail(String email){
        driver.findElement(emailInput).sendKeys(email);
    }

    public void clickToOpenBasicHtmlGmail() throws InterruptedException {
        driver.findElement(basicHTMGmailBtn).click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        Thread.sleep(400);
        //switch to newly opened tab
        driver.switchTo().window(tabs.get(1));
    }

    public void clickNextEmailBtn(){
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(emailNextBtn));
        driver.findElement(emailNextBtn).click();
    }

    public void setPassword(String password){
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickNextPasswordBtn(){
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(passwordNextBtn));
        driver.findElement(passwordNextBtn).click();
    }

    //Method to signin user to gmail account
    public void gmailUserSignin(String gmailEmail, String gmailPassword){
        setEmail(gmailEmail);
        clickNextEmailBtn();
        setPassword(gmailPassword);
        clickNextPasswordBtn();
    }

    public void clickRefreshBtn(){
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(refreshBtn));
        driver.findElement(refreshBtn).click();
    }

    public String getRefreshBtnTxt(){
        return driver.findElement(refreshBtn).getText();
    }

    public void openFirstMail(){
        WebElement allMailsAvailable = driver.findElement(allMails);
        List<WebElement> firstMail = allMailsAvailable.findElements(By.tagName("td"));
        firstMail.get(2).click();
//        System.out.println(firstMail.size());
    }

    //Navigate to first mail in inbox and click activation link
    public void clickActivationLinkUrl(){
        WebElement bodyOfMail = driver.findElement(mailBody);
        List<WebElement> allContentOfMail = bodyOfMail.findElements(By.tagName("td"));
        List<WebElement> partsOfContent = allContentOfMail.get(4).findElements(By.tagName("p"));
        System.out.println(partsOfContent.get(2).getText());
//        System.out.println(partsOfContent.size());
    }

    //After user open email, this method is used to get the full name of that user in email
    public String getUsernameInMail(){
        WebElement bodyOfMail = driver.findElement(mailBody);
        List<WebElement> allContentOfMail = bodyOfMail.findElements(By.tagName("td"));
        List<WebElement> partsOfContent = allContentOfMail.get(4).findElements(By.tagName("p"));
        String txt2Parse = partsOfContent.get(0).getText();
        String[] userName = txt2Parse.split(", ");
        System.out.println("User full name in welcome message is: "+userName[1]);
        return userName[1];
//        System.out.println(partsOfContent.size());
    }

    //Method to get the title of the first email
    public String getEmailTitleTxt(){
        WebElement emailTitle = driver.findElement(titleOfEmailTbl);
        List<WebElement> nameOfEmail = emailTitle.findElements(By.tagName("td"));
        String txt = nameOfEmail.get(0).getText();
        String[] splTxt = txt.split("   ");
        return splTxt[0];
//        System.out.println(splTxt[0]);
    }

    //This method is used to open new tab and to switch to that tab
    public void openNewTab() throws AWTException, InterruptedException {
        //To open new tab
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_T);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_T);
        //To switch to the new tab
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        Thread.sleep(400);
        driver.switchTo().window(tabs.get(1));
    }

    //This method is used for closing currently opened tab, and switching to the first tab
    public void closeCurrentTab() throws AWTException, InterruptedException {
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_W);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_W);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }




}
