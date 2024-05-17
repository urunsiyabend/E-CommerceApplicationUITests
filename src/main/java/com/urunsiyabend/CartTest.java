package com.urunsiyabend;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CartTest {
    WebDriver driver;

    @BeforeAll
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testAddToCart() {
        driver.get("https://www.dummyecommercewebsite.com/products/");

        List<WebElement> productLinks = driver.findElements(By.className("product-link"));

        productLinks.get(0).click();

        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();

        WebElement cartButton = driver.findElement(By.id("cart-button"));
        cartButton.click();

        WebElement cartItems = driver.findElement(By.id("cart-items"));
        assert cartItems.isDisplayed();

        List<WebElement> items = cartItems.findElements(By.className("cart-item"));
        assert items.size() == 1;

        WebElement item = items.get(0);
        assert item.isDisplayed();

        WebElement itemName = item.findElement(By.className("item-name"));
        assert itemName.isDisplayed();

        WebElement itemPrice = item.findElement(By.className("item-price"));
        assert itemPrice.isDisplayed();

        WebElement itemQuantity = item.findElement(By.className("item-quantity"));
        assert itemQuantity.isDisplayed();

        WebElement itemTotal = item.findElement(By.className("item-total"));
        assert itemTotal.isDisplayed();

        assertEquals(itemTotal.getText(), itemPrice.getText());
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}
