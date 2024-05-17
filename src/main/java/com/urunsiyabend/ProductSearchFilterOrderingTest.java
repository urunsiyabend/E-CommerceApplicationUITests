package com.urunsiyabend;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductSearchFilterOrderingTest {
    public WebDriver driver;
    protected String url = "https://www.dummyecommercewebsite.com/products";

    @BeforeAll
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testProductSearch() {
        driver.get(url);

        WebElement searchForm = driver.findElement(By.id("search-form"));

        assertTrue(searchForm.isDisplayed());

        WebElement searchInput = searchForm.findElement(By.id("search-input"));
        searchInput.sendKeys("laptop");

        WebElement searchButton = searchForm.findElement(By.tagName("button"));
        searchButton.click();

        assert driver.getCurrentUrl().contains("search=laptop");

        WebElement searchResults = driver.findElement(By.id("search-results"));
        assert searchResults.isDisplayed();
    }

    @Test
    public void testProductFilter() {
        driver.get(url);

        WebElement filterForm = driver.findElement(By.id("filter-form"));

        assertTrue(filterForm.isDisplayed());

        // filter category is a select element
        WebElement filterCategory = filterForm.findElement(By.id("filter-category"));
        Select categorySelect = new Select(filterCategory);
        String category = categorySelect.getOptions().get(1).getText();
        categorySelect.selectByIndex(1);

        WebElement filterButton = filterForm.findElement(By.tagName("button"));
        filterButton.click();

        assertTrue(driver.getCurrentUrl().contains("category=" + category));

        WebElement filterResults = driver.findElement(By.id("filter-results"));
        assertTrue(filterResults.isDisplayed());
    }

    @Test
    public void testProductFilterByPrice() {
        driver.get(url);

        WebElement filterForm = driver.findElement(By.id("filter-form"));

        assert filterForm.isDisplayed();

        WebElement minPrice = filterForm.findElement(By.id("min-price"));
        WebElement maxPrice = filterForm.findElement(By.id("max-price"));

        minPrice.sendKeys("1000");
        maxPrice.sendKeys("2000");

        WebElement filterButton = filterForm.findElement(By.tagName("button"));
        filterButton.click();

        assertTrue(driver.getCurrentUrl().contains("min-price=1000"));
        assertTrue(driver.getCurrentUrl().contains("max-price=2000"));

        WebElement filterResults = driver.findElement(By.id("filter-results"));
        assertTrue(filterResults.isDisplayed());

        driver.navigate().back();

        minPrice = filterForm.findElement(By.id("min-price"));
        maxPrice = filterForm.findElement(By.id("max-price"));

        minPrice.sendKeys("2000");
        maxPrice.sendKeys("1000");

        filterButton = filterForm.findElement(By.tagName("button"));
        filterButton.click();

        assertTrue(driver.getCurrentUrl().contains("min-price=2000"));
        assertTrue(driver.getCurrentUrl().contains("max-price=1000"));

        filterResults = driver.findElement(By.id("filter-results"));
        assert filterResults.isDisplayed();

        assertEquals(driver.findElements(By.className("product")).size(), 0);
    }

    @Test
    public void testProductFilterByRating() {
        driver.get(url);

        WebElement filterForm = driver.findElement(By.id("filter-form"));

        assert filterForm.isDisplayed();

        WebElement minRating = filterForm.findElement(By.id("min-rating"));
        WebElement maxRating = filterForm.findElement(By.id("max-rating"));

        minRating.sendKeys("4");
        maxRating.sendKeys("5");

        WebElement filterButton = filterForm.findElement(By.tagName("button"));
        filterButton.click();

        assert driver.getCurrentUrl().contains("min-rating=4");
        assert driver.getCurrentUrl().contains("max-rating=5");

        WebElement filterResults = driver.findElement(By.id("filter-results"));
        assert filterResults.isDisplayed();
    }

    @Test
    public void testProductOrdering() {
        driver.get(url);

        WebElement orderingForm = driver.findElement(By.id("ordering-form"));

        assert orderingForm.isDisplayed();

        // ordering option is a select element
        WebElement orderingOption = orderingForm.findElement(By.id("ordering-option"));
        Select orderingSelect = new Select(orderingOption);
        String ordering = orderingSelect.getOptions().get(1).getText();
        orderingSelect.selectByIndex(1);

        WebElement orderingButton = orderingForm.findElement(By.tagName("button"));
        orderingButton.click();

        assert driver.getCurrentUrl().contains("ordering=" + ordering);

        WebElement orderingResults = driver.findElement(By.id("ordering-results"));
        assert orderingResults.isDisplayed();
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}
