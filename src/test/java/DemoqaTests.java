import com.demoqa.pages.ButtonsPage;
import com.demoqa.pages.LoginPage;
import com.demoqa.pages.RegisterPage;
import com.demoqa.pages.WebTablesPage;
import org.junit.jupiter.api.*;

import static com.demoqa.ConfigFileReader.getProperty;

public class DemoqaTests extends BaseTest {

    WebTablesPage webTablesPage;
    ButtonsPage buttonsPage;
    RegisterPage registerPage;
    LoginPage loginPage;

    @BeforeEach
    void setupForTest() {
        webTablesPage = new WebTablesPage(driver);
        buttonsPage = new ButtonsPage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);

    }

    @Test
    @DisplayName("Check the ability to add element to the web table")
    void addToTableTest() {
        driver.get(getProperty("base.url") + "webtables");
        webTablesPage.addRowToTheTable("Bob", "Marley", "test@gmail.com", "20", "3500", "musician");
        var result = webTablesPage.searchAddedRowInTheTable("Bob");
        Assertions.assertEquals("Bob", result);
    }

    @Test
    @DisplayName("Check the double click button")
    void doubleClickClickTest() {
        driver.get(getProperty("base.url") + "buttons");
        var message = buttonsPage.doubleClickOnButton();
        Assertions.assertEquals("You have done a double click", message);
    }

    @Test
    @DisplayName("Check the right click button")
    void rightClickClickTest() {
        driver.get(getProperty("base.url") + "buttons");
        var message = buttonsPage.rightClickOnButton();
        Assertions.assertEquals("You have done a right click", message);
    }

    @Test
    @DisplayName("Check the registration form")
    void registrationTest() {
        driver.get(getProperty("base.url") + "register");
        var successMessage = registerPage.userRegistration("Ringo", "Star", "TheGreatest", "qwerty123");
        Assertions.assertEquals("User Register Successfully.", successMessage);
    }

    @Test
    @DisplayName("Check the login form")
    void loginTest() {
        driver.get(getProperty("base.url") + "login");
        var userName = loginPage.userLogin("TheGreatest", "qwerty123");
        Assertions.assertEquals("TheGreatest", userName);
    }

}