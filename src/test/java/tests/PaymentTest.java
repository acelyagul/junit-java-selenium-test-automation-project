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

        assertTrue("Main title cannot be displayed", homePage.isMainTitleDisplayed());
        assertTrue("Subheadings could not be verified", homePage.validateSubTitles());

        homePage.navigateToLinkPayment();
        LinkPaymentPage linkPaymentPage = new LinkPaymentPage();

        try {
            Object[][] testData = ExcelReader.getTestData(
                Config.EXCEL_PATH, 
                Config.EXCEL_SHEET_NAME
            );

            linkPaymentPage.fillApplicationForm(
                testData[0][0].toString(), 
                testData[0][1].toString(), 
                testData[0][2].toString(), 
                testData[0][3].toString(), 
                testData[0][4].toString(), 
                testData[0][5].toString()  
            );

            linkPaymentPage.submitForm();


        } catch (Exception e) {
            logger.error("Error while filling out the form: " + e.getMessage());
            throw e;
        }
    }
} 