package com.urunsiyabend;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserLoginTest {
    public WebDriver driver;
    protected String url = "https://www.dummyecommercewebsite.com/account/login";

    @BeforeAll
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testUserLogin() {
        driver.get(url);

        WebElement loginForm = driver.findElement(By.id("login-form"));

        assertTrue(loginForm.isDisplayed());

        WebElement email = loginForm.findElement(By.id("email"));
        WebElement password = loginForm.findElement(By.id("password"));

        email.sendKeys("email@domain.com");
        password.sendKeys("password");

        WebElement submitButton = loginForm.findElement(By.tagName("button"));
        submitButton.click();

        WebElement successMessage = driver.findElement(By.className("alert-success"));
        assertTrue(successMessage.isDisplayed());

        WebElement logoutButton = driver.findElement(By.id("logout-button"));
        assertTrue(logoutButton.isDisplayed());
    }

    @Test
    public void testUserLoginWithInvalidCredentials() {
        driver.get(url);

        WebElement loginForm = driver.findElement(By.id("login-form"));

        assertTrue(loginForm.isDisplayed());

        WebElement email = loginForm.findElement(By.id("email"));
        WebElement password = loginForm.findElement(By.id("password"));

        email.sendKeys("invalid@credentials.com");
        password.sendKeys("invalidpassword");

        WebElement submitButton = loginForm.findElement(By.tagName("button"));
        submitButton.click();

        WebElement errorMessage = driver.findElement(By.className("alert-danger"));
        assertEquals("Invalid email or password", errorMessage.getText());
        assertTrue(errorMessage.isDisplayed());
    }

    @Test
    public void testUserLoginWithEmptyCredentials() {
        driver.get(url);

        WebElement loginForm = driver.findElement(By.id("login-form"));

        assertTrue(loginForm.isDisplayed());

        WebElement email = loginForm.findElement(By.id("email"));
        WebElement password = loginForm.findElement(By.id("password"));

        email.sendKeys("");
        password.sendKeys("");

        WebElement submitButton = loginForm.findElement(By.tagName("button"));
        submitButton.click();

        WebElement errorMessage = driver.findElement(By.className("alert-danger"));
        assertEquals("Email and password are required", errorMessage.getText());
        assertTrue(errorMessage.isDisplayed());
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}
