package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ButtonsPage {
    private static final String DOUBLE_CLICK_BTN = "doubleClickBtn";
    private static final String DOUBLE_CLICK_MESSAGE = "doubleClickMessage";
    private static final String RIGHT_CLICK_BTN = "rightClickBtn";
    private static final String RIGHT_CLICK_MESSAGE = "rightClickMessage";

    WebDriver driver;

    public ButtonsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String doubleClickOnButton() {
        var doubleClickElement = driver.findElement(By.id(DOUBLE_CLICK_BTN));
        new Actions(driver)
                .doubleClick(doubleClickElement)
                .perform();
        return driver.findElement(By.id(DOUBLE_CLICK_MESSAGE)).getText();
    }

    public String rightClickOnButton() {
        var doubleClickElement = driver.findElement(By.id(RIGHT_CLICK_BTN));
        new Actions(driver)
                .contextClick(doubleClickElement)
                .perform();
        return driver.findElement(By.id(RIGHT_CLICK_MESSAGE)).getText();
    }

}