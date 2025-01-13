package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.PageHelper;
import java.util.List;
import utilities.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import utilities.Config;
import utilities.Driver;

public class HomePage extends BasePage {
    
    @FindBy(css = "section.payment-channel h2.section-title")
    private WebElement mainTitle;
    
    @FindBy(css = "div.swiper-slide[role='group'][aria-label$='/ 12']")
    private List<WebElement> subTitles;
    
    @FindBy(css = ".menu__item.js-menu-item")
    private WebElement productsMenu;
    
    @FindBy(css = "a[href='/linkle-odeme']")
    private WebElement linkPaymentButton;
    
    public boolean isMainTitleDisplayed() {
        try {
            PageHelper.waitForSeconds(1);
            PageHelper.scrollToElement(mainTitle);
            PageHelper.waitForElementVisible(mainTitle, 10);
            
            String actualText = mainTitle.getText().trim().replaceAll("[.\\s]+$", "");
            String expectedText = "Ödemeler İçin İhtiyacınız Olan Her Şey";
            
            LoggerUtil.info("Actual text: [" + actualText + "]");
            LoggerUtil.info("Expected text: [" + expectedText + "]");
            
            return PageHelper.isElementDisplayed(mainTitle) && actualText.equals(expectedText);
        } catch (Exception e) {
            LoggerUtil.error("Error in isMainTitleDisplayed: " + e.getMessage());
            return false;
        }
    }
    
    public boolean validateSubTitles() {
        try {
            PageHelper.waitForSeconds(3);
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.numberOfElementsToBe(
                By.cssSelector("div.swiper-slide[role='group'][aria-label$='/ 12']"), 12));
            
            List<WebElement> slides = driver.findElements(
                By.cssSelector("div.swiper-slide[role='group'][aria-label$='/ 12']"));
            
            LoggerUtil.info("Number of slides found: " + slides.size());
            
            for (WebElement slide : slides) {
                LoggerUtil.info("Slide aria-label: " + slide.getAttribute("aria-label"));
            }
            
            return slides.size() == 12;
            
        } catch (Exception e) {
            LoggerUtil.error("Error while validating subtitles: " + e.getMessage());
            return false;
        }
    }
    
    public void navigateToLinkPayment() {
            PageHelper.waitForSeconds(2);
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(productsMenu));
            PageHelper.clickElement(menu);
            
            WebElement linkButton = wait.until(ExpectedConditions.elementToBeClickable(linkPaymentButton));
            PageHelper.clickElement(linkButton);
            
            if (!PageHelper.verifyURLContains("/linkle-odeme")) {
                Driver.getDriver().get(Config.LINK_PAYMENT_URL);
            }
            
            LoggerUtil.info("Successfully navigated to Link Payment page");
            
    }
    
    public WebElement getMainTitle() {
        return mainTitle;
    }
} 