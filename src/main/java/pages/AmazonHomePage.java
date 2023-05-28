package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AmazonHomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By searchField = By.id("twotabsearchtextbox");
    private By searchButton = By.id("nav-search-submit-button");
    private By filterByBrand = By.xpath("//div[@id='brandsRefinements']//span/li/span/a/span");
    private By expandBrandFilter = By.xpath("//div[@id='brandsRefinements']//span[contains(text(),'See more')]");
    private By filterByWatchDisplayType = By.xpath("//div[@id='filters']/div/span[contains(text(),'Watch Display Type')]/parent::div/following-sibling::ul[1]//a/span");
    private By filterByWatchBandMaterial = By.xpath("//div[@id='filters']/div/span[contains(text(),'Band Material')]/parent::div/following-sibling::ul[1]//a/span");
    private By expandFilterWatchBandMaterial = By.xpath("//div[@id='filters']/div/span[contains(text(),'Watchband Material')]/parent::div/following-sibling::ul[1]/li/span/div/a");
    private By discountTitle = By.xpath("//div[@id='p_n_pct-off-with-tax-title']");
    private By filterByDiscount = By.xpath("//div[@id='filters']/div/span[contains(text(),'Discount')]/parent::div/following-sibling::ul[1]//li/span/a/span");
    private By filterByDiscountLink = By.xpath("//div[@id='filters']/div/span[contains(text(),'Discount')]/parent::div/following-sibling::ul[1]//li/span/a");
    private By searchResult = By.xpath("//div[@data-component-type='s-search-result']");
    private By searchProduct(int index) {
        return By.xpath("//div[@data-component-type='s-search-result'][" + index + "]");
    }
    public AmazonHomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void searchItem(String searchText) {
        driver.findElement(searchField).sendKeys(searchText);
        driver.findElement(searchButton).click();
    }

    public void filterByWatchBandDisplay(String displayType) {
        List<WebElement> displayTypes = driver.findElements(filterByWatchDisplayType);
        for (int i = 0; i < displayTypes.size(); i++) {
            if (displayTypes.get(i).getAttribute("textContent").equalsIgnoreCase(displayType)) {
                displayTypes.get(i).click();
                break;
            }
        }
    }

    public void filterByBrandMaterial(String brandMaterial) {
        List<WebElement> brandMaterials = driver.findElements(filterByWatchBandMaterial);
        for (int i = 0; i < brandMaterials.size(); i++) {
            if (brandMaterials.get(i).getAttribute("textContent").equalsIgnoreCase(brandMaterial)) {
                brandMaterials.get(i).click();
                break;
            }
        }
    }

    public void filterByBrand(String brand) {
        List<WebElement> brands = driver.findElements(filterByBrand);
        for (int i = 0; i < brands.size(); i++) {
            if (brands.get(i).getAttribute("textContent").equalsIgnoreCase(brand)) {
                brands.get(i).click();
                break;
            }
        }
    }

    public void filterByDiscount(String discount)  {

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(discount)));
        driver.findElement(By.linkText(discount)).click();

        /*
        List<WebElement> discounts = driver.findElements(filterByDiscount);
        List<WebElement> discountLinks = driver.findElements(filterByDiscountLink);
        for (int i = 0; i < discounts.size(); i++) {
            if (discounts.get(i).getAttribute("textContent").equalsIgnoreCase(discount)) {

                Actions actions = new Actions(driver);
                actions.scrollToElement(discounts.get(i)).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(discounts.get(i)));
                discounts.get(i).findElement(By.tagName("a")).click();
                //JavascriptExecutor js = (JavascriptExecutor) driver;
                //js.executeScript("arguments[0].click();", discounts.get(i));
                break;
            }
        } */
    }

    public AmazonProductDetailPage selectSearchProduct(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(searchProduct(index)));
        driver.findElement(searchProduct(index)).click();
        return new AmazonProductDetailPage(driver, wait);
    }

}
