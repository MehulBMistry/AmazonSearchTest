package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;

public class AmazonProductDetailPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By productSpecTable = By.xpath("//table[@id='technicalSpecifications_section_1']/tbody/tr");
    private By productTitle = By.xpath("//span[@id='productTitle']");
    private By discountApplied = By.xpath("(//div[@id='corePriceDisplay_desktop_feature_div']/div/span)[1]");
    private By freeDelivery = By.id("FREE_DELIVERY");
    private By amazonDelivered = By.id("AMAZON_DELIVERED");
    public AmazonProductDetailPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    public String getProductTitle(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(productTitle)));
        return driver.findElement(productTitle).getText();
    }

    public HashMap getProductDetails() {
        HashMap<String, String> productDetails = new HashMap<>();

        List<WebElement> productDetailList = driver.findElements(productSpecTable);
        wait.until(ExpectedConditions.visibilityOfAllElements(productDetailList));

        //System.out.println(productDetailList.size());

        for (int i = 0; i < productDetailList.size(); i++) {

            //System.out.println(productDetailList.get(i).findElement(By.xpath("th")).getText());
            //System.out.println(productDetailList.get(i).findElement(By.xpath("td")).getText());


            if (productDetailList.get(i).findElement(By.xpath("th")).getText().equals("Band Material")) {
                productDetails.put("Band Material", productDetailList.get(i).findElement(By.xpath("td")).getText());
            }
            if (productDetailList.get(i).findElement(By.xpath("th")).getText().equals("Display Type")) {
                productDetails.put("Display Type", productDetailList.get(i).findElement(By.xpath("td")).getText());
            }
            if (productDetailList.get(i).findElement(By.xpath("th")).getText().equals("Brand")) {
                productDetails.put("Brand", productDetailList.get(i).findElement(By.xpath("td")).getText());
            }
        }

        //System.out.println(productDetails);
        return productDetails;
    }

    public double discountApplied(){
        String discount = driver.findElement(discountApplied).getText();
        double discountValue = Double.parseDouble(discount.replace('%',' ').replace('-',' ').trim());
        //System.out.println(discountValue);
        return  discountValue;
    }
    public boolean isFreeDelivery(){
           return driver.findElement(freeDelivery).isDisplayed();
    }
    public boolean isAmazonDelivered(){
        return driver.findElement(amazonDelivered).isDisplayed();
    }
}
