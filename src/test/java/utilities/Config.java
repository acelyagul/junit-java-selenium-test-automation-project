package utilities;

public class Config {
    public static final String BASE_URL = "https://www.paytr.com";
    public static final String LINK_PAYMENT_URL = BASE_URL + "/linkle-odeme";
    
    public static final String EXCEL_PATH = System.getProperty("user.dir") + "/src/test/resources/Form.xlsx";
    public static final String REFERENCE_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/referenceNumber.txt";
    
    public static final int IMPLICIT_WAIT = 10;
    public static final int PAGE_LOAD_TIMEOUT = 15;
    public static final int EXPLICIT_WAIT = 15;
    
    public static final String BROWSER = "chrome"; 
    
    public static final String EXCEL_SHEET_NAME = "Sayfa1";
    public static final String SECOND_FORM_SHEET_NAME = "Sayfa2";
}