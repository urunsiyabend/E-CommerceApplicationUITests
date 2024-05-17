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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductDetailTest {
    public WebDriver driver;
    protected String url;

    @BeforeAll
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.dummyecommercewebsite.com/products/");

        List<WebElement> productLinks = driver.findElements(By.className("product-link"));

        productLinks.get(0).click();
    }

    @Test
    public void testProductDetail() {
        WebElement productDetail = driver.findElement(By.id("product-detail"));

        assert productDetail.isDisplayed();
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}
