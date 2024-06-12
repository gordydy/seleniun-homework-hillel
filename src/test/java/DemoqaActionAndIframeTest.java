import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.demoqa.ConfigFileReader.getProperty;

public class DemoqaActionAndIframeTest extends BaseTest {

    public static final String PARENT_FRAME_ID = "frame1";
    public static final String PARENT_TITLE_LOCATOR = "body";
    public static final String CHILD_FRAME_TAG_NAME = "iframe";
    public static final String CHILD_TITLE_LOCATOR = "p";
    public static final String ASS_BANNER_ID = "adplus-anchor";
    public static final String DRAGGABLE_TAB_ID = "droppableExample-tab-revertable";
    public static final String SELECT_CONDITION = "aria-selected";
    public static final String DROP_BOX = "#droppableExample-tabpane-revertable #droppable";
    public static final String REVERTABLE_BOX = "#droppableExample-tabpane-revertable #revertable";
    public static final String NOT_REVERTABLE_BOX = "#droppableExample-tabpane-revertable #notRevertable";
    public static final String DROP_BOX_TITLE = "#droppableExample-tabpane-revertable #droppable";
    public static final String STYLE = "style";


    @Test
    @DisplayName("Check that one drag box is automatically reverted to the origin, but second isn't reverted")
    void frameTitleTest() {
        driver.get(getProperty("base.url") + "droppable");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        js.executeScript("arguments[0].style.display='none'", driver.findElement(By.id(ASS_BANNER_ID)));
        var revertDraggableTab = driver.findElement(By.id(DRAGGABLE_TAB_ID));

        actions
                .click(revertDraggableTab)
                .perform();

        var isSelected = revertDraggableTab.getAttribute(SELECT_CONDITION);
        Assertions.assertEquals("true", isSelected);

        js.executeScript("window.scrollBy(0, 500)");

        var dropBox = driver.findElement(By.cssSelector(DROP_BOX));
        var revertableBox = driver.findElement(By.cssSelector(REVERTABLE_BOX));
        var notRevertableBox = driver.findElement(By.cssSelector(NOT_REVERTABLE_BOX));

        actions
                .dragAndDrop(revertableBox, dropBox)
                .perform();

        var dropBoxChangedTitle = getFrameTitle(By.cssSelector(DROP_BOX_TITLE));
        Assertions.assertEquals("Dropped!", dropBoxChangedTitle);

        actions
                .dragAndDrop(notRevertableBox, dropBox)
                .perform();

        var positionOfNotRevertableBox = notRevertableBox.getAttribute(STYLE);
        wait.until(ExpectedConditions.attributeToBe(revertableBox, STYLE, "position: relative; left: 0px; top: 0px;"));
        var positionOfRevertableBox = revertableBox.getAttribute(STYLE);

        Assertions.assertEquals("position: relative; left: 0px; top: 0px;", positionOfRevertableBox);
        Assertions.assertEquals("position: relative; left: 249px; top: -17px;", positionOfNotRevertableBox);

    }

    @Test
    @DisplayName("Check titles of parent and child frames")
    void parentAndChildFramesTitlesTest() {
        driver.get(getProperty("base.url") + "nestedframes");
        var parentFrame = driver.findElement(By.id(PARENT_FRAME_ID));

        driver.switchTo().frame(parentFrame);
        var parentTitle = getFrameTitle(By.tagName(PARENT_TITLE_LOCATOR));
        Assertions.assertEquals("Parent frame", parentTitle);

        var childFrame = driver.findElement(By.tagName(CHILD_FRAME_TAG_NAME));
        driver.switchTo().frame(childFrame);
        var childTitle = getFrameTitle(By.tagName(CHILD_TITLE_LOCATOR));
        Assertions.assertEquals("Child Iframe", childTitle);

    }

    public String  getFrameTitle(By locator) {
        return driver.findElement(locator).getText();
    }

}