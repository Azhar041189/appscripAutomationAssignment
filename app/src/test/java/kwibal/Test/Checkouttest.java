package kwibal.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import kwibal.Base.base;

public class Checkouttest extends base {

        String baseURL = "https://charlesfish.co.uk/";

        @Test
        public void CheckoutScenario() throws Exception {
                driver.get(baseURL);

                // Assert correct URL loaded
                Assert.assertTrue(driver.getCurrentUrl().contains("charlesfish.co.uk"), "Homepage URL is incorrect");
                
                // Accept cookies
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement privacyPopup = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("(//button[@class='cky-btn cky-btn-accept'])[1]")));
                privacyPopup.click();

                // Click on header logo
                WebElement headerLogo = driver
                                .findElement(By.xpath("//*[@id='shopify-section-header']/sticky-header/header/h1/a"));
                headerLogo.click();

                // Scroll slightly
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,10)");

                // Switch to iframe for offer popup
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.xpath("(//iframe[@src='about:blank'])[1]")));
                driver.switchTo().frame(iframe);

                WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[text() = 'YES, CONTINUE']")));
                yesButton.click();

                WebElement declineButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[text()= 'Decline offer']")));
                declineButton.click();

                driver.switchTo().defaultContent();

                // Hover and click on product category
                WebElement categoryElement = driver.findElement(By.xpath("//*[@id='slick-slide10']/div/div/span/a"));
                Assert.assertTrue(categoryElement.isDisplayed(), "Category element not visible");
                new Actions(driver).moveToElement(categoryElement).click().perform();

                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,700)");

                // Click on first product
                WebElement necklaceElement = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//*[@id=\"product-grid\"]/li[1]/div/div/div[2]/div/div[2]/a")));
                necklaceElement.click();

                // Add to cart
                WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[@name='add']")));
                addToCartButton.click();

                // View cart
                WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[@href='/cart' and contains(text(),' View Basket')]")));
                viewCart.click();

                // Update quantity
                Select quantityDropdown = new Select(driver.findElement(By.id("quantity")));
                quantityDropdown.selectByValue("4");

                WebElement updateButton = driver.findElement(By.xpath("//button[text() = 'Update']"));
                updateButton.click();

                // Proceed to checkout
                WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
                Assert.assertTrue(checkoutButton.isDisplayed(), "Checkout button not visible");
                checkoutButton.click();

                // Wait for Continue button to be visible
                WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//span[text()='Continue checkout']")));
                // Scroll it into view
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
                                continueBtn);
                // Wait for any overlay/spinner to disappear (update selector as needed)
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading, .backdrop")));
                // Click using JS to avoid interception
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);

                // Final step: Assert on checkout URL or next page
                Assert.assertTrue(driver.getCurrentUrl().contains("/checkouts"), "Did not navigate to checkout page");
        }

}

    

