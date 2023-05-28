package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
    public AmazonHomePage openAmazonHome(){
        driver.get("https://www.amazon.in");
        return new AmazonHomePage(driver, wait);
    }
}