package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.cssSelector;

public class LoginPage {
    private static final String PASSWORD = "password";
    private static final String USER_NAME = "userName";
    private static final String USER_NAME_VALUE = "userName-value";
    private static final String BUTTON_LOGIN = "button#login";

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String userLogin(String username, String password) {
        driver.findElement(By.id(USER_NAME)).sendKeys(username);
        driver.findElement(By.id(PASSWORD)).sendKeys(password);
        driver.findElement(cssSelector(BUTTON_LOGIN)).click();
        var userNameElement = waitForElementVisibility(By.id(USER_NAME_VALUE));
        return userNameElement.getText();
    }

    private WebElement waitForElementVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}