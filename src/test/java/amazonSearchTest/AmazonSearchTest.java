package amazonSearchTest;

import base.BaseTests;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AmazonHomePage;
import pages.AmazonProductDetailPage;
import utils.ExcelUtil;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AmazonSearchTest extends BaseTests {
    @Test
    public void amazonCartTest() throws InterruptedException, IOException {

        SoftAssert softAssert = new SoftAssert();

        AmazonHomePage amazonHomePage = homePage.openAmazonHome();

        ExcelUtil.setExcelFileSheet("searchCriteria");

        amazonHomePage.searchItem(ExcelUtil.getCellData( 0, 0));
        amazonHomePage.filterByWatchBandDisplay(ExcelUtil.getCellData(1, 0));
        amazonHomePage.filterByBrandMaterial(ExcelUtil.getCellData(2, 0));
        amazonHomePage.filterByBrand(ExcelUtil.getCellData(3, 0));
        amazonHomePage.filterByDiscount(ExcelUtil.getCellData(4, 0));

        ExcelUtil.setExcelFileSheet("Sheet4");
        for (int i = 0; i < ExcelUtil.getRowCount(); i++) {

            AmazonProductDetailPage amazonProductDetailPage = amazonHomePage.selectSearchProduct(Integer.parseInt(ExcelUtil.getCellData(i + 1, 0)));
            //Thread.sleep(18000);

            String parent = getWindowManager().setParentTab();
            getWindowManager().switchToNewTab();

            System.out.println(amazonProductDetailPage.getProductTitle());

            amazonProductDetailPage.getProductDetails();
            amazonProductDetailPage.discountApplied();

            //System.out.println(ExcelUtil.createHashMap());

            softAssert.assertEquals(amazonProductDetailPage.getProductDetails(), ExcelUtil.createHashMap());

            softAssert.assertTrue(amazonProductDetailPage.discountApplied() >= (Integer.parseInt(ExcelUtil.getCellData(1, 1))), "Discount applied is not same as filtered" );
            softAssert.assertTrue(amazonProductDetailPage.isFreeDelivery(), "Product is not free delivered");
            softAssert.assertTrue(amazonProductDetailPage.isAmazonDelivered(), "Product is not delivered by Amazon");

            getWindowManager().closeTabAndSwitchToLastWindow();
            getWindowManager().switchToParentTab(parent);
        }
        softAssert.assertAll();
    }
}
