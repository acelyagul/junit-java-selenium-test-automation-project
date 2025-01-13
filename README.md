# Web Test Automation Project with Junit

This project implements a test automation framework using Java and Selenium WebDriver. It follows Page Object Model design pattern and utilizes JUnit for test execution, Log4j2 for logging, and Apache POI for data-driven testing. The framework is structured to be maintainable, reusable and easily extensible.

## Prerequisites

- Java JDK 11 or higher
- Maven
- Chrome Browser
- ChromeDriver (matching your Chrome version)

## Technologies Used

- Java 11
- Selenium WebDriver
- JUnit 4
- Log4j2 for logging
- Apache POI for Excel operations
- Maven for dependency management

## Project Structure

```
src/
└── test/
    ├── java/
    │   ├── pages/
    │   │   ├── BasePage.java              # Common base page functionality
    │   │   ├── HomePage.java              # Homepage interactions and navigation
    │   │   └── LinkPaymentPage.java       # Form filling and submission operations
    │   │
    │   ├── tests/
    │   │   ├── TestBase.java             # Test initialization and cleanup
    │   │   └── PaymentTest.java          # Test scenarios for payment form
    │   │
    │   └── utilities/
    │       ├── Config.java               # Configuration and constant values
    │       ├── Driver.java               # WebDriver initialization and management
    │       ├── ExcelReader.java          # Excel file reading operations
    │       ├── FileWriter.java           # File writing operations
    │       ├── LoggerUtil.java           # Logging operations
    │       └── PageHelper.java           # Common UI interaction methods
    │
    └── resources/
        ├── Form.xlsx                     # Test data in Excel format
        └── log4j2.xml                    # Logging configuration
```


### Key Components:

- **pages/**: Page Object Models for each web page
- **tests/**: Test classes containing test scenarios
- **utilities/**: Helper classes for common operations
- **resources/**: Configuration files and test data

## Data-Driven Testing

The project uses Excel files for test data management:
- Test data is read from `Form.xlsx` using Apache POI
- Each row represents a test case
- Columns contain form field values (name, email, phone etc.)

## Logging

The project uses Log4j2 for logging:
- Logs are written to `log4j2.xml`
- Logs are categorized by package and class
- Logs are written to `logs/` directory

## Key Features

- Page Object Model design pattern
- Data-driven testing with Excel integration
- Detailed logging with Log4j2
- Reusable helper methods
- Configurable test parameters

## Running the Tests

To run the tests, use the following command:

```
mvn test
```
