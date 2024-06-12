package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.cssSelector;

public class RegisterPage {
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PASSWORD = "password";
    private static final String USER_NAME = "userName";
    private static final String IFRAME_TITLE_RE_CAPTCHA = "iframe[title='reCAPTCHA']";
    private static final String RECAPTCHA_ANCHOR = "recaptcha-anchor";
    private static final String REGISTER_BUTTON = "register";

    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public String userRegistration(String firstName, String lastname, String username, String password){
        driver.findElement(By.id(FIRST_NAME)).sendKeys(firstName);
        driver.findElement(By.id(LAST_NAME)).sendKeys(lastname);
        driver.findElement(By.id(USER_NAME)).sendKeys(username);
        driver.findElement(By.id(PASSWORD)).sendKeys(password);
        var iframe = driver.findElement(cssSelector(IFRAME_TITLE_RE_CAPTCHA));
        driver.switchTo().frame(iframe);
        driver.findElement(By.id(RECAPTCHA_ANCHOR)).click();
        driver.switchTo().parentFrame();
        driver.findElement(By.id(REGISTER_BUTTON)).click();
        return driver.switchTo().alert().getText();
    }

}