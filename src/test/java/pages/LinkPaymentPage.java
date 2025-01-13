package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.PageHelper;
import utilities.LoggerUtil;
import java.util.List;
import utilities.FileWriter;
import utilities.Config;

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
    
    @FindBy(id = "company_title")
    private WebElement isletmeUnvaniInput;
    
    @FindBy(id = "tc_no")
    private WebElement tcKimlikInput;
    
    @FindBy(id = "tax_office")
    private WebElement vergiDairesiInput;
    
    @FindBy(id = "monthly_sale")
    private WebElement aylikCiroInput;

    @FindBy(css = ".style_button__38lBq")
    private WebElement submitLastButton;
    
    @FindBy(css = "h3")
    private WebElement thankYouMessage;
    
    @FindBy(css = ".style_reference___1Gx7")
    private WebElement referenceNumber;
    
    public void fillApplicationForm(String authorizedName, String authorizedSurname, 
                                  String authorizedEmail, String website,
                                  String authorizedPhone, String businessType) {
        try {
            PageHelper.waitForSeconds(2);
            
            PageHelper.waitForElementVisible(yetkiliAdiInput);
            LoggerUtil.info("Filling the form");
            
            PageHelper.sendKeys(yetkiliAdiInput, authorizedName);
            PageHelper.sendKeys(yetkiliSoyadiInput, authorizedSurname);
            
            PageHelper.waitForElementVisible(yetkiliEPostaInput);
            PageHelper.forceSendKeys(yetkiliEPostaInput, authorizedEmail);
            
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
        PageHelper.waitForSeconds(2);
        PageHelper.waitForElementClickable(submitButton);
        PageHelper.clickElement(submitButton);
    }
    
    public void fillSecondForm(String isletmeUnvani, String tcKimlik, 
                             String vergiDairesi, String aylikCiro) {
            PageHelper.waitForSeconds(3);
            PageHelper.waitForElementVisible(isletmeUnvaniInput);
            
            LoggerUtil.info("Filling second form");
            
            PageHelper.sendKeys(isletmeUnvaniInput, isletmeUnvani);
            PageHelper.scrollToElement(vergiDairesiInput);
            PageHelper.sendKeys(vergiDairesiInput, vergiDairesi);
            PageHelper.scrollToElement(tcKimlikInput);
            PageHelper.sendKeys(tcKimlikInput, tcKimlik);
            PageHelper.sendKeys(aylikCiroInput, aylikCiro);
                        

    }

    public void submitLastForm() {
        PageHelper.scrollToElement(submitLastButton);
        PageHelper.waitForElementClickable(submitLastButton);
        PageHelper.clickElement(submitLastButton);
    }
    
    public String getThankYouMessage() {
        PageHelper.waitForElementVisible(thankYouMessage);
        return thankYouMessage.getText().trim();
    }
    
    public String getReferenceNumber() {
        PageHelper.waitForElementVisible(referenceNumber);
        String refText = referenceNumber.getText().trim();
        return refText.replace("Referans no: ", "").trim();
    }
    
    public void verifyAndSaveReferenceNumber() {
        try {
            PageHelper.waitForSeconds(2);
            
            String actualMessage = getThankYouMessage();
            String expectedMessage = "Teşekkürler, ön başvurunuz tarafımıza ulaşmıştır.";
            
            if (!actualMessage.equals(expectedMessage)) {
                throw new AssertionError("Thank you message mismatch. Expected: " + expectedMessage + ", but got: " + actualMessage);
            }
            

            String refNumber = getReferenceNumber();
            LoggerUtil.info("Reference number obtained: " + refNumber);
            

            FileWriter.saveReferenceNumber(refNumber, Config.REFERENCE_FILE_PATH);
            LoggerUtil.info("Reference number saved to file: " + Config.REFERENCE_FILE_PATH);
            
        } catch (Exception e) {
            LoggerUtil.error("Error verifying and saving reference number: " + e.getMessage());
            throw e;
        }
    }
} 