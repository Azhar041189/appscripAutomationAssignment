package kwibal.Pages;

import org.openqa.selenium.WebDriver;

public class homepage {
    WebDriver driver;
    public homepage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHomePageVisible() {
        return driver.getTitle().contains("Kwibal - The biggest buy & sell marketplace globally in 2024");
    }

}
