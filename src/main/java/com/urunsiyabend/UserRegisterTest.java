package com.urunsiyabend;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRegisterTest {
    public WebDriver driver;
    protected String url = "https://www.dummyecommercewebsite.com/account/register";

    @BeforeAll
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testUserRegister() {
        driver.get(url);

        WebElement registerForm = driver.findElement(By.id("register-form"));

        assert registerForm.isDisplayed();

        WebElement firstName = registerForm.findElement(By.id("first-name"));
        WebElement lastName = registerForm.findElement(By.id("last-name"));
        WebElement email = registerForm.findElement(By.id("email"));
        WebElement password = registerForm.findElement(By.id("password"));
        WebElement confirmPassword = registerForm.findElement(By.id("confirm-password"));

        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        email.sendKeys("john@doe.com");
        password.sendKeys("password");
        confirmPassword.sendKeys("password");

        WebElement submitButton = registerForm.findElement(By.tagName("button"));
        submitButton.click();

        WebElement successMessage = driver.findElement(By.className("alert-success"));
        Assertions.assertTrue(successMessage.isDisplayed());
    }

    @Test
    public void testUserRegisterFormReturnsErrorForInvalidData() {
        driver.get(url);

        WebElement registerForm = driver.findElement(By.id("register-form"));

        assert registerForm.isDisplayed();

        WebElement firstName = registerForm.findElement(By.id("first-name"));
        WebElement lastName = registerForm.findElement(By.id("last-name"));
        WebElement email = registerForm.findElement(By.id("email"));
        WebElement password = registerForm.findElement(By.id("password"));
        WebElement confirmPassword = registerForm.findElement(By.id("confirm-password"));

        List<WebElement> products = driver.findElements(By.className("product"));

        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        email.sendKeys("invalidemail");
        password.sendKeys("password");
        confirmPassword.sendKeys("password");

        WebElement submitButton = registerForm.findElement(By.tagName("button"));
        submitButton.click();

        WebElement errorMessage = driver.findElement(By.className("alert-danger"));
        Assertions.assertTrue(errorMessage.isDisplayed());
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}
