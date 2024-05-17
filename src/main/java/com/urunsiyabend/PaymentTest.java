package com.urunsiyabend;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaymentTest {
    WebDriver driver;

    @BeforeAll
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testPayment() {
        driver.get("https://www.dummyecommercewebsite.com/cart/");

        WebElement checkoutButton = driver.findElement(By.id("checkout-button"));
        checkoutButton.click();

        WebElement paymentForm = driver.findElement(By.id("payment-form"));
        assert paymentForm.isDisplayed();

        WebElement cardNumber = paymentForm.findElement(By.id("card-number"));
        WebElement cardHolder = paymentForm.findElement(By.id("card-holder"));
        WebElement expiryDate = paymentForm.findElement(By.id("expiry-date"));
        WebElement cvv = paymentForm.findElement(By.id("cvv"));

        cardNumber.sendKeys("1234 5678 1234 5678");
        cardHolder.sendKeys("John Doe 23213");
        expiryDate.sendKeys("12/23");
        cvv.sendKeys("1233445");

        WebElement submitButton = paymentForm.findElement(By.tagName("button"));
        submitButton.click();

        WebElement failedMessage = driver.findElement(By.className("alert-danger"));
        assert failedMessage.isDisplayed();

        WebElement cardNumberError = driver.findElement(By.id("card-number-error"));
        assert cardNumberError.isDisplayed();

        WebElement cardHolderError = driver.findElement(By.id("card-holder-error"));
        assert cardHolderError.isDisplayed();

        WebElement expiryDateError = driver.findElement(By.id("expiry-date-error"));
        assert expiryDateError.isDisplayed();

        WebElement cvvError = driver.findElement(By.id("cvv-error"));
        assert cvvError.isDisplayed();

        assertEquals(cardNumberError.getText(), "Invalid card number");
        assertEquals(cardHolderError.getText(), "Invalid card holder name");
        assertEquals(expiryDateError.getText(), "Invalid expiry date");
        assertEquals(cvvError.getText(), "Invalid CVV");
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}
