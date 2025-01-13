package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.PageHelper;
import utilities.LoggerUtil;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class LinkPaymentPage extends BasePage {
    
    @FindBy(id = "first-name")
    private WebElement yetkiliAdiInput;
    
    @FindBy(css = "input[name='surname']")
    private WebElement yetkiliSoyadiInput;
    
    @FindBy(id = "email")
    private WebElement yetkiliEPostaInput;
    
    @FindBy(css = "input[name='website']")
    private WebElement websiteInput;
    
    @FindBy(css = "input[name='tel']")
    private WebElement yetkiliTelefonInput;
    
    @FindBy(css = ".select-selected")
    private WebElement isletmeTipiDropdown;
    
    @FindBy(css = ".select-items div")
    private List<WebElement> isletmeTipiOptions;
    
    @FindBy(css = "#contact-form .button-primary-light")
    private WebElement submitButton;
    
    @FindBy(css = ".checkbox__check.size\\:medium.viewType\\:square")
    private WebElement confirmForm;

    public void fillApplicationForm(String authorizedName, String authorizedSurname, 
                                  String authorizedEmail, String website,
                                  String authorizedPhone, String businessType) {
        try {
            PageHelper.waitForSeconds(2);
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(yetkiliAdiInput));
            
            LoggerUtil.info("Filling the form...");
            LoggerUtil.info("Email value from Excel: " + authorizedEmail);
            
            PageHelper.sendKeys(yetkiliAdiInput, authorizedName);
            PageHelper.sendKeys(yetkiliSoyadiInput, authorizedSurname);
            
            String formattedEmail = authorizedEmail.toLowerCase().trim();
            LoggerUtil.info("Converted email to lowercase: " + formattedEmail);
            PageHelper.sendKeys(yetkiliEPostaInput, formattedEmail);
            
            PageHelper.scrollToElement(websiteInput);
            LoggerUtil.info("Website value: " + website);
            PageHelper.sendKeys(websiteInput, website);
            
            PageHelper.scrollToElement(yetkiliTelefonInput);
            PageHelper.sendKeys(yetkiliTelefonInput, "0" + authorizedPhone);
            
            PageHelper.scrollToElement(isletmeTipiDropdown);
            PageHelper.clickElement(isletmeTipiDropdown);
            PageHelper.waitForSeconds(1);
            
            for (WebElement option : isletmeTipiOptions) {
                if (option.getText().trim().equals(businessType)) {
                    PageHelper.clickElement(option);
                    break;
                }
            }
            
            LoggerUtil.info("Form filled successfully");
            
        } catch (Exception e) {
            LoggerUtil.error("Error while filling form: " + e.getMessage());
            throw e;
        }
    }
    
    public void submitForm() {
        PageHelper.scrollToElement(confirmForm);
        PageHelper.clickElement(confirmForm);
        PageHelper.waitForElementClickable(submitButton);
        PageHelper.clickElement(submitButton);
    }

} 