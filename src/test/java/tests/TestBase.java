package tests;

import org.junit.Before;
import org.junit.After;
import utilities.Driver;
import utilities.Config;
import utilities.LoggerUtil;
import utilities.PageHelper;

public class TestBase {
    
    @Before
    public void setUp() {
        try {
            LoggerUtil.info("Starting the test");
            Driver.getDriver().get(Config.BASE_URL);
            Driver.getDriver().manage().window().maximize();
            PageHelper.waitForSeconds(5);
            PageHelper.acceptCookieIfExists();
        } catch (Exception e) {
            LoggerUtil.error("Error starting test: " + e.getMessage());
            throw e;
        }
    }

    @After
    public void tearDown() {
        try {
            LoggerUtil.info("Testing is ending");
            Driver.closeDriver();
        } catch (Exception e) {
            LoggerUtil.error("Error while terminating the test: " + e.getMessage());
        }
    }
} 