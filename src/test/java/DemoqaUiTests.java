import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class DemoqaUiTests {
    WebDriver driver;

    public static final String BASIC_URL = "https://demoqa.com/";

    @BeforeEach
    void setup() {
        driver = new FirefoxDriver();
    }

    @AfterEach
    void exit() {
        driver.close();
    }

    @Test
    @DisplayName("Check that button appears after five seconds on page")
    void waitForButtonAppearsTest() {
        driver.get(BASIC_URL + "dynamic-properties");
        var buttonAfterFiveSec = waitTillDisplayed(By.id("visibleAfter"), Duration.ofSeconds(5));
        boolean condition = buttonAfterFiveSec.isDisplayed();
        Assertions.assertTrue(condition);
        Assertions.assertEquals("Visible After 5 Seconds", buttonAfterFiveSec.getText());
    }

    public WebElement waitTillDisplayed(By locator, Duration sec) {
        WebDriverWait wait = new WebDriverWait(driver, sec);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Test
    @DisplayName("Check that progress bar show 100%")
    void hundredPercentOnProgressBarTest() {
        driver.get(BASIC_URL + "progress-bar");
        var startStopButton = driver.findElement(By.id("startStopButton"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", startStopButton);
        waitTillDisplayed(By.id("resetButton"), Duration.ofSeconds(12));
        var hundredPercent = driver.findElement(By.className("progress")).getText();
        Assertions.assertEquals("100%", hundredPercent);
    }

    @Test
    @DisplayName("Check that button change the colour after five seconds")
    void changeColourTest() {
        driver.get(BASIC_URL + "dynamic-properties");
        var basicColor = getColorValue(By.id("colorChange"));
        waitTillDisplayed(By.id("visibleAfter"), Duration.ofSeconds(5));
        var afterFiveSecColor = getColorValue(By.id("colorChange"));
        Assertions.assertNotEquals(basicColor, afterFiveSecColor);
    }

    public String getColorValue(By locator) {
        return driver.findElement(locator).getCssValue("color");
    }

}