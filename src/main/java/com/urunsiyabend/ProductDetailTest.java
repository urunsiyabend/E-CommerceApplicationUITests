package com.urunsiyabend;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductDetailTest {
    public WebDriver driver;
    protected String url;

    @BeforeAll
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testProductDetail() {
        driver.get("https://www.dummyecommercewebsite.com/products/");

        List<WebElement> productLinks = driver.findElements(By.className("product-link"));

        int productId = Integer.parseInt(productLinks.get(0).getAttribute("data-product-id"));

        url = "https://www.dummyecommercewebsite.com/products/" + productId;

        driver.get(url);

        assertEquals(url, driver.getCurrentUrl());

        WebElement productDetail = driver.findElement(By.id("product-detail"));

        assert productDetail.isDisplayed();

        WebElement productName = productDetail.findElement(By.className("product-name"));
        assert productName.isDisplayed();

        WebElement productPrice = productDetail.findElement(By.className("product-price"));
        assert productPrice.isDisplayed();

        WebElement productDescription = productDetail.findElement(By.className("product-description"));
        assert productDescription.isDisplayed();
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}
