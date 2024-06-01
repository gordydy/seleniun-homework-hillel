import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.cssSelector;

public class DemoqaTests {
    public static final String BASIC_URL = "https://demoqa.com/";
    public static final String SEARCH_BOX = "searchBox";
    public static final String FIRST_CHILD = "div.rt-tr-group div.rt-tr.-odd div.rt-td:first-child";
    public static final String ADD_NEW_RECORD_BUTTON = "addNewRecordButton";
    public static final String MODAL_TITLE = "registration-form-modal";
    public static final String DOUBLE_CLICK_BTN = "doubleClickBtn";
    public static final String RIGHT_CLICK_BTN = "rightClickBtn";
    public static final String DOUBLE_CLICK_MESSAGE = "doubleClickMessage";
    public static final String RIGHT_CLICK_MESSAGE = "rightClickMessage";
    public static final String IFRAME_TITLE_RE_CAPTCHA = "iframe[title='reCAPTCHA']";
    public static final String RECAPTCHA_ANCHOR = "recaptcha-anchor";
    public static final String REGISTER_BUTTON = "register";
    public static final String USER_NAME_VALUE = "userName-value";
    public static final String BUTTON_LOGIN = "button#login";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String USER_EMAIL = "userEmail";
    public static final String AGE = "age";
    public static final String SALARY = "salary";
    public static final String DEPARTMENT = "department";
    public static final String SUBMIT = "submit";
    public static final String PASSWORD = "password";
    public static final String USER_NAME = "userName";
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new FirefoxDriver();
    }

    @AfterEach
    void quit() {
        driver.quit();
    }

    @Test
    @DisplayName("Check the ability to add element to the web table")
    void addToTableTest() {
        driver.get(BASIC_URL + "webtables");
        driver.findElement(By.id(ADD_NEW_RECORD_BUTTON)).click();
        var modalTitle = driver.findElement(By.id(MODAL_TITLE)).getText();
        Assertions.assertEquals("Registration Form", modalTitle);
        driver.findElement(By.id(FIRST_NAME)).sendKeys("Bob");
        driver.findElement(By.id(LAST_NAME)).sendKeys("Marley");
        driver.findElement(By.id(USER_EMAIL)).sendKeys("test@gmail.com");
        driver.findElement(By.id(AGE)).sendKeys("20");
        driver.findElement(By.id(SALARY)).sendKeys("3500");
        driver.findElement(By.id(DEPARTMENT)).sendKeys("musician");
        driver.findElement(By.id(SUBMIT)).click();
        driver.findElement(By.id(SEARCH_BOX)).sendKeys("Bob");
        var result = driver.findElement(By.ByCssSelector.cssSelector(FIRST_CHILD)).getText();
        Assertions.assertEquals("Bob", result);
    }

    @Test
    @DisplayName("Check the double click button")
    void doubleClickClickTest() {
        driver.get(BASIC_URL + "buttons");
        var doubleClickElement = driver.findElement(By.id(DOUBLE_CLICK_BTN));
        new Actions(driver)
                .doubleClick(doubleClickElement)
                .perform();
        var message = driver.findElement(By.id(DOUBLE_CLICK_MESSAGE)).getText();
        Assertions.assertEquals("You have done a double click", message);
    }

    @Test
    @DisplayName("Check the right click button")
    void rightClickClickTest() {
        driver.get(BASIC_URL + "buttons");
        var doubleClickElement = driver.findElement(By.id(RIGHT_CLICK_BTN));
        new Actions(driver)
                .contextClick(doubleClickElement)
                .perform();
        var message = driver.findElement(By.id(RIGHT_CLICK_MESSAGE)).getText();
        Assertions.assertEquals("You have done a right click", message);
    }

    @Test
    @DisplayName("Check the registration form")
    void registrationTest() {
        driver.get(BASIC_URL + "register");
        driver.findElement(By.id(FIRST_NAME)).sendKeys("Valeriy");
        driver.findElement(By.id(LAST_NAME)).sendKeys("Zhmyshenko");
        driver.findElement(By.id(USER_NAME)).sendKeys("Zhmyleriy");
        driver.findElement(By.id(PASSWORD)).sendKeys("Qwerty123$");
        var iframe = driver.findElement(cssSelector(IFRAME_TITLE_RE_CAPTCHA));
        driver.switchTo().frame(iframe);
        driver.findElement(By.id(RECAPTCHA_ANCHOR)).click();
        driver.switchTo().parentFrame();
        driver.findElement(By.id(REGISTER_BUTTON)).click();
        var successMessage = driver.switchTo().alert().getText();
        Assertions.assertEquals("User Register Successfully.", successMessage);
    }

    @Test
    @DisplayName("Check the login form")
    void loginTest() {
        driver.get(BASIC_URL + "login");
        driver.findElement(By.id(USER_NAME)).sendKeys("Zhmyleriy");
        driver.findElement(By.id(PASSWORD)).sendKeys("Qwerty123$");
        driver.findElement(cssSelector(BUTTON_LOGIN)).click();
        var userNameElement = waitForElementVisibility(By.id(USER_NAME_VALUE));
        var userName = userNameElement.getText();
        Assertions.assertEquals("Zhmyleriy", userName);
    }

    private WebElement waitForElementVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}