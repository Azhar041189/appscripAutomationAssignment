package kwibal.Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import kwibal.Base.base;
import kwibal.Pages.homepage;

public class SearchProducttest extends base {
    String baseURL = "https://web.platform.kwibal.com/";

    @Test
    public void SearchProduct() throws InterruptedException {
        // Navigate to the base URL
        driver.get(baseURL);

        Assert.assertTrue(driver.getCurrentUrl().contains("https://web.platform.kwibal.com/"));
        homepage homePage = new homepage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page not loaded");

        Thread.sleep(5000); // Allow time for page load

        WebElement searchBox = driver
                .findElement(By.xpath("//input[@placeholder= 'Search for anything and everything']"));
        searchBox.sendKeys("books");

        WebElement searchBtn = driver.findElement(By.xpath("//button[text()='Search']"));
        searchBtn.click();

        Thread.sleep(5000); // Wait for results to load

        List<WebElement> products = driver.findElements(By.xpath("//h1[text()=' search results for \"']"));

        if (products.size() > 0) {
            System.out.println(" Product items found: " + products.size());
        } else {
            System.out.println(" No product items found.");
        }
        {
            driver.quit();
        }
    }
}