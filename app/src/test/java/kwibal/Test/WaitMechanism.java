package kwibal.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import kwibal.Base.base;
import kwibal.Pages.homepage;

public class WaitMechanism extends base {
    String baseURL = "https://web.platform.kwibal.com/";

    @Test
    public void SearchProduct() throws InterruptedException {
        // Navigate to the base URL
        driver.get(baseURL);

        Assert.assertTrue(driver.getCurrentUrl().contains("https://web.platform.kwibal.com/"));
        homepage homePage = new homepage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page not loaded");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Submit']")));
        button.click();

        System.out.println("Button clicked using explicit wait.");
        

        
        {
            driver.quit();
        }
    }
    
    
    
}
