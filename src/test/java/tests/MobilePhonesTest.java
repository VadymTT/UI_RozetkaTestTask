package tests;

import components.Pagination;
import constants.PageUrls;
import enums.CategorySidebar;
import enums.Subcategory;
import models.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.MainPage;
import pages.MobilePhonesPage;

import java.util.List;

public class MobilePhonesTest extends TestInit {
    MainPage mainPage = new MainPage();
    CategoryPage categoryPage = new CategoryPage();
    MobilePhonesPage mobilePhonesPage = new MobilePhonesPage();
    Pagination pagination = new Pagination();

    @BeforeMethod
    public void navigateToMobilePhonesPage() {
        mainPage.clickOnCategory(CategorySidebar.ELECTRONICS);
        categoryPage.verifyUrlAndTitle(PageUrls.ELECTRONICS_CATEGORY, CategorySidebar.ELECTRONICS.getName())
                .clickOnSubcategoryByText(Subcategory.MOBILE_PHONES.getName());
        mobilePhonesPage.verifyRedirectUrl(PageUrls.MOBILE_PHONES_CATEGORY);
        pagination.scrollToPagination();
    }

    @Test(description = "Retrieve the titles and prices of all products on the first page")
    public void getProductsTitleAndPricesTest() {
        List<Product> mobileDevices = mobilePhonesPage.getProductsNamesAndPrices();

        Assert.assertFalse(mobileDevices.isEmpty(), "The list of products is empty!");
        Assert.assertEquals(mobileDevices.size(), 60, "The number of items on the first page is not 60!");

        for (Product device : mobileDevices) {
            Assert.assertNotNull(device.getTitle(), "The product name is null!");
            Assert.assertFalse(device.getTitle().trim().isEmpty(), "The product name is empty!");

            Assert.assertNotNull(device.getPrice(), "The price of this item is null!");
            Assert.assertFalse(device.getPrice().trim().isEmpty(), "The price of this item is missing!");
            Assert.assertTrue(device.getPriceAsInt() > 0,
                    "Product Price '" + device.getTitle() + "' should be greater than 0, but it was: " + device.getPriceAsInt());
        }

        System.out.println("Items received: " + mobileDevices.size());
        System.out.println("----------------------------------------");

        for (int i = 0; i < mobileDevices.size(); i++) {
            System.out.println((i + 1) + ". " + mobileDevices.get(i));
        }
    }

    @Test(description = "Retrieve the titles and prices of top-selling products")
    public void getTopSalesProductsTitleAndPricesTest() {
        List<Product> topSalesDevices = mobilePhonesPage.getTopSalesProductsNamesAndPrices();

        Assert.assertFalse(topSalesDevices.isEmpty(), "No items marked ‘TOP SELLERS’ were found!");

        for (Product device : topSalesDevices) {
            Assert.assertNotNull(device.getTitle(), "The ‘TOP SELLERS’ name for this product is null!");
            Assert.assertFalse(device.getTitle().trim().isEmpty(), "The ‘TOP SELLERS’ title for this product is empty!");

            Assert.assertNotNull(device.getPrice(), "The ‘TOP SELLERS’ price for this item is null!");
            Assert.assertFalse(device.getPrice().trim().isEmpty(), "The ‘TOP SELLERS’ price for this item is empty!");
            Assert.assertTrue(device.getPriceAsInt() > 0,
                    "‘TOP SELLERS’ Price for this item '" + device.getTitle() + "' should be greater than 0, but it was: " + device.getPriceAsInt());
        }

        System.out.println("Items marked as ‘TOP SELLERS’ have been received: " + topSalesDevices.size());
        System.out.println("----------------------------------------");

        for (int i = 0; i < topSalesDevices.size(); i++) {
            System.out.println((i + 1) + ". " + topSalesDevices.get(i));
        }
    }
}
