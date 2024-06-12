
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BaseTest {
    WebDriver driver;

    @BeforeEach
     void setUp() {
        driver = new FirefoxDriver();
    }

    @AfterEach
     void quit() {
        driver.close();
    }
}