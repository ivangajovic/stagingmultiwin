package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by ivang on 3/5/2018.
 */
public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    By logo = By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[1]/div/div/nav/div/div[1]/a/img");
    By loginBtn = By.xpath("//*[@id=\"myNavbar\"]/ul[2]/li[1]/a");
    By promotions = By.xpath("//*[@id=\"myNavbar\"]/ul[1]/li[2]/a");
    By gettingStarted = By.xpath("//*[@id=\"myNavbar\"]/ul[1]/li[3]/a");
    By registerBtn = By.xpath("//*[@id=\"myNavbar\"]/ul[2]/li[2]/a");
    By emailInput = By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-register/div/div/div/div/form/div[1]/ng-form/div/div[1]/div[1]/div/input");
    By usernameInput = By.name("userName");
    By passwordInput = By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-register/div/div/div/div/form/div[1]/ng-form/div/div[3]/div[1]/div/input");
    By cnfmPasswordInput = By.name("confirmPassword");
    By step2Btn = By.cssSelector(".step-2-btn");
    By firstNameInput = By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-register/div/div/div/div/form/div[2]/ng-form/div[1]/div[1]/div[1]/div/input");
    By lastNameInput = By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-register/div/div/div/div/form/div[2]/ng-form/div[1]/div[1]/div[2]/div/input");
    By dob = By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-register/div/div/div/div/form/div[2]/ng-form/div[1]/div[1]/div[3]/div/div[1]/span/button/i");
    By cityInput = By.name("townCity");
    By postCodeInput = By.name("postCode");
    By addressInput = By.name("address1");
    By genderInput = By.id("gender");
    By countryInput = By.name("country");
    By prefixInput = By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-register/div/div/div/div/form/div[2]/ng-form/div[1]/div[5]/div[3]/div/div[1]/select");
    By phoneInput = By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-register/div/div/div/div/form/div[2]/ng-form/div[1]/div[5]/div[3]/div/div[2]/div/input");
    By agreeTermsCheckbox = By.cssSelector(".pointer");
    By confirmBtn = By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-register/div/div/div/div/form/div[2]/ng-form/div[2]/div/div[3]/div/button");
    By success = By.xpath("//*[@id=\"Multiwin\"]/div[1]/div/div/div/div/div/div[1]/span[2]");
    By okBtn = By.xpath("//*[@id=\"Multiwin\"]/div[1]/div/div/div/div/div/button");


    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean checkImage(){
        WebElement imageFile = driver.findElement(logo);
        boolean imagePresent = (Boolean)((JavascriptExecutor)driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", imageFile);
        return imagePresent;
    }

    public String getPromotionsTxt(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(promotions));
        return driver.findElement(promotions).getText();
    }

    public String getGettingStartedTxt(){
        return driver.findElement(gettingStarted).getText();
    }

    public String getTxtRegBtn(){
        return driver.findElement(registerBtn).getText();
    }

    public String getTxtLoginBtn(){
        return driver.findElement(loginBtn).getText();
    }

    public void clickRegister(){
        driver.findElement(registerBtn).click();
    }

    public void setEmail(String emailStr){
        driver.findElement(emailInput).sendKeys(emailStr);
    }

    public void setUsername(String userStr){
        driver.findElement(usernameInput).sendKeys(userStr);
    }

    public void setPassword(String passwordStr){
        driver.findElement(passwordInput).sendKeys(passwordStr);
    }

    public void setConfirmPassword(String cnfPasswordStr){
        driver.findElement(cnfmPasswordInput).sendKeys(cnfPasswordStr);
    }

    public void clickStep2(){
        driver.findElement(step2Btn).click();
    }

    public void setFirstName(String firstNameStr){
        driver.findElement(firstNameInput).sendKeys(firstNameStr);
    }

    public void setLastName(String lastNameStr){
        driver.findElement(lastNameInput).sendKeys(lastNameStr);
    }

    public void setDOB(){

        driver.findElement(dob).click();
        WebElement dateWidget = driver.findElement(By.xpath("\n" +
                "    //*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-register/div/div/div/div/form/div[2]/ng-form/div[1]/div[1]/div[3]/div/div[1]/div/ul/li[1]/div/div/div/table"));
        List<WebElement> upperPartTbl = dateWidget.findElements(By.tagName("th"));
        upperPartTbl.get(1).click();

        WebElement monthTbl = driver.findElement(By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-register/div/div/div/div/form/div[2]/ng-form/div[1]/div[1]/div[3]/div/div[1]/div/ul/li[1]/div/div/div/table"));
        List<WebElement> upperPartTbl1 = monthTbl.findElements(By.tagName("th"));
        upperPartTbl1.get(1).click();

        WebElement yearTbl = driver.findElement(By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-register/div/div/div/div/form/div[2]/ng-form/div[1]/div[1]/div[3]/div/div[1]/div/ul/li[1]/div/div/div/table"));
        List<WebElement> upperPartTbl2 = yearTbl.findElements(By.tagName("th"));
        upperPartTbl2.get(0).click();

        WebElement yearTbl1 = driver.findElement(By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-register/div/div/div/div/form/div[2]/ng-form/div[1]/div[1]/div[3]/div/div[1]/div/ul/li[1]/div/div/div/table"));
        List<WebElement> year1988Cell = yearTbl1.findElements(By.tagName("td"));
        year1988Cell.get(7).click();

        WebElement monthTbl1 = driver.findElement(By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-register/div/div/div/div/form/div[2]/ng-form/div[1]/div[1]/div[3]/div/div[1]/div/ul/li[1]/div/div/div/table"));
        List<WebElement> monthOctoberCell = monthTbl1.findElements(By.tagName("td"));
        monthOctoberCell.get(9).click();

        WebElement dateTbl = driver.findElement(By.xpath("//*[@id=\"Multiwin\"]/div[2]/top-menu/div[2]/user-register/div/div/div/div/form/div[2]/ng-form/div[1]/div[1]/div[3]/div/div[1]/div/ul/li[1]/div/div/div/table"));
        List<WebElement> date10Cell = dateTbl.findElements(By.tagName("td"));
        date10Cell.get(18).click();
    }


    public void setCity(String cityStr){
        driver.findElement(cityInput).sendKeys(cityStr);
    }

    public void setPostCode(String postCodeStr){
        driver.findElement(postCodeInput).sendKeys(postCodeStr);
    }

    public void setAddress(String addressStr){
        driver.findElement(addressInput).sendKeys(addressStr);
    }

    public void setGender(String genderStr){
        WebElement listGender = driver.findElement(genderInput);
        Select openGender = new Select(listGender);
        openGender.selectByValue(genderStr);// genderStr = male || female
    }

    public void setCountry(String countryCodeStr){
        WebElement listCountry = driver.findElement(countryInput);
        Select openListCountry = new Select(listCountry);
        openListCountry.selectByValue(countryCodeStr);//countryCodeStr = 225 for Serbia... etc
    }

    public void setPrefix(String prefixValueStr){
        WebElement listPrefix = driver.findElement(prefixInput);
        Select openListsPrefix = new Select(listPrefix);
        openListsPrefix.selectByValue(prefixValueStr); //prefixValueStr = 225 for Serbia... etc
    }

    public void setMobilePhone(String mobilePhoneNum){
        driver.findElement(phoneInput).sendKeys(mobilePhoneNum);//must be unique and 6 figures number
    }

    public void clickAgreeTerms(){
        driver.findElement(agreeTermsCheckbox).click();
    }

    public void clickConfirm(){
        driver.findElement(confirmBtn).click();
    }

    public String successRegister(){
        return driver.findElement(success).getText();
    }

    public void clickOkAfterSuccess(){
        driver.findElement(okBtn).click();
    }

    public void registerToMultiwinCasino(String emailStr, String userStr, String passwordStr, String cnfPasswordStr,
                                         String firstNameStr, String lastNameStr, String cityStr, String postCodeStr,
                                         String addressStr, String genderStr, String countryCodeStr, String prefixValueStr,
                                         String mobilePhoneNum){
        this.clickRegister();
        this.setEmail(emailStr);
        this.setUsername(userStr);
        this.setPassword(passwordStr);
        this.setConfirmPassword(cnfPasswordStr);
        this.clickStep2();
        this.setFirstName(firstNameStr);
        this.setLastName(lastNameStr);
        this.setDOB();
        this.setCity(cityStr);
        this.setPostCode(postCodeStr);
        this.setAddress(addressStr);
        this.setGender(genderStr);
        this.setCountry(countryCodeStr);
        this.setPrefix(prefixValueStr);
        this.setMobilePhone(mobilePhoneNum);
        this.clickAgreeTerms();
        this.clickConfirm();
    }

//    public void automateGmail(String username, String password){
//        Folder folder = null;
//        Store store = null;
//        System.out.println("***Reading mailbox...");
//        try {
//            Properties props = new Properties();
//            props.put("mail.store.protocol", "imaps");
//            Session session = Session.getInstance(props);
//            store = session.getStore("imaps");
//            store.connect("imap.gmail.com", username, password);
//            folder = store.getFolder("INBOX");
//            folder.open(Folder.READ_ONLY);
//            Message[] messages = folder.getMessages();
//            System.out.println("No. of messages: " + folder.getMessageCount());
//            System.out.println("No. of unread messages: " + folder.getUnreadMessageCount());
//            for (int i=0; i<messages.length-1; i++){
//                System.out.println("Reading message # " + (i+1) + "...");
//                Message msg = messages[i];
//                String strMailSubject = "", strMailBody = "";
//                //Getting mail subject
//                Object subject = msg.getSubject();
//                //Getting mail body
//                Object content = msg.getContent();
//                strMailSubject = (String)subject;
//                strMailBody = (String)content;
//                //Printing mail subject and body
//                System.out.println(strMailSubject);
//                System.out.println(strMailBody);
//            }
//        } catch (MessagingException msge) {
//            msge.printStackTrace();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }finally {
//            if (folder != null){
//                try {
//                    folder.close();
//                } catch (MessagingException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (store != null){
//                try {
//                    store.close();
//                } catch (MessagingException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }



}