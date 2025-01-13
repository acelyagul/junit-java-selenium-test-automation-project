package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import pages.HomePage;
import pages.LinkPaymentPage;
import utilities.ExcelReader;
import utilities.Config;
import utilities.PageHelper;

import static org.junit.Assert.*;

public class PaymentTest extends TestBase {
    private static final Logger logger = LogManager.getLogger(PaymentTest.class);

    @Test
    public void testPaytrApplication() {

        HomePage homePage = new HomePage();
        PageHelper.waitForElementVisible(homePage.getMainTitle());

        assertTrue("Main title is not visible", homePage.isMainTitleDisplayed());
        assertTrue("Subtitles validation failed", homePage.validateSubTitles());

        homePage.navigateToLinkPayment();
        LinkPaymentPage linkPaymentPage = new LinkPaymentPage();

        Object[][] firstFormData = ExcelReader.getTestData(Config.EXCEL_PATH, Config.EXCEL_SHEET_NAME);
        
        linkPaymentPage.fillApplicationForm(
            firstFormData[0][0].toString(),
            firstFormData[0][1].toString(),
            firstFormData[0][2].toString(),
            firstFormData[0][3].toString(),
            firstFormData[0][4].toString(),
            firstFormData[0][5].toString()
        );

        linkPaymentPage.submitForm();
        PageHelper.waitForSeconds(2);

        Object[][] secondFormData = ExcelReader.getTestData(Config.EXCEL_PATH, Config.SECOND_FORM_SHEET_NAME);
        
        linkPaymentPage.fillSecondForm(
            secondFormData[0][0].toString(),
            secondFormData[0][1].toString(), 
            secondFormData[0][2].toString(), 
            secondFormData[0][3].toString()  
        );
        linkPaymentPage.submitLastForm();
        
        linkPaymentPage.verifyAndSaveReferenceNumber();
    }
} 