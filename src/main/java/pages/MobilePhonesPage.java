package pages;

import base.BasePage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import models.Product;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class MobilePhonesPage extends BasePage {

    private final String titles = ".//a[contains(@class, 'tile-title')]",
            prices = ".//rz-tile-price//div[contains(@class, 'price leading-none')]";

    private final ElementsCollection productTiles = $$x("//rz-catalog-goods[@data-testid='category_goods']//rz-product-tile"),
            productTilesWithTopSalesLabels = $$x("//rz-product-tile[.//rz-promo-label[contains(@class, 'promo-label_type_popularity')]]");

    public MobilePhonesPage verifyRedirectUrl(String expectedUrlPart) {
        webdriver().shouldHave(urlContaining(expectedUrlPart));
        return this;
    }

    @Step("Retrieve product titles and prices from the first catalog page")
    public List<Product> getProductsNamesAndPrices() {
        return getProductTitleAndPrice(productTiles);
    }

    @Step("Retrieve titles and prices of top-selling products")
    public List<Product> getTopSalesProductsNamesAndPrices() {
    return getProductTitleAndPrice(productTilesWithTopSalesLabels);
    }

    private List<Product> getProductTitleAndPrice(ElementsCollection elementsCollection) {
        elementsCollection.shouldHave(sizeGreaterThan(0));
        List<Product> topProducts = new ArrayList<>();
        for (SelenideElement tile : elementsCollection) {
            String title = tile.$x(titles)
                    .shouldBe(visible)
                    .getText()
                    .trim();

            String price = tile.$x(prices)
                    .shouldBe(visible)
                    .getText()
                    .replaceAll("\\s+", "")
                    .trim();
            topProducts.add(new Product(title, price));
        }
        return topProducts;
    }
}