package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebTablesPage {
    private static final String ADD_NEW_RECORD_BUTTON = "addNewRecordButton";
    private static final String MODAL_TITLE = "registration-form-modal";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String USER_EMAIL = "userEmail";
    private static final String AGE = "age";
    private static final String SALARY = "salary";
    private static final String DEPARTMENT = "department";
    private static final String SUBMIT = "submit";
    private static final String SEARCH_BOX = "searchBox";
    private static final String FIRST_CHILD = "div.rt-tr-group div.rt-tr.-odd div.rt-td:first-child";

    WebDriver driver;

    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addRowToTheTable(String firstName, String lastName, String userEmail, String age, String salary, String department) {
        driver.findElement(By.id(ADD_NEW_RECORD_BUTTON)).click();
        driver.findElement(By.id(MODAL_TITLE)).getText();
        driver.findElement(By.id(FIRST_NAME)).sendKeys(firstName);
        driver.findElement(By.id(LAST_NAME)).sendKeys(lastName);
        driver.findElement(By.id(USER_EMAIL)).sendKeys(userEmail);
        driver.findElement(By.id(AGE)).sendKeys(age);
        driver.findElement(By.id(SALARY)).sendKeys(salary);
        driver.findElement(By.id(DEPARTMENT)).sendKeys(department);
        driver.findElement(By.id(SUBMIT)).click();
    }

    public String searchAddedRowInTheTable(String firstname) {
        driver.findElement(By.id(SEARCH_BOX)).sendKeys(firstname);
        return driver.findElement(By.ByCssSelector.cssSelector(FIRST_CHILD)).getText();
    }

}