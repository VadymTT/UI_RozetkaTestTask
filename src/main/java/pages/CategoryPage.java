package pages;

import base.BasePage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
public class CategoryPage extends BasePage {

    private final SelenideElement pageHeader = $x("//section[contains(@class, 'content')]//h1[contains(@class, 'h1')]");

    private final ElementsCollection subcategoryItems = $$x("//ul[contains(@class, 'list four')]//a[contains(@class, 'item-link relative')]");

    public CategoryPage verifyUrlAndTitle(String expectedUrlPart, String expectedTitle) {
        webdriver().shouldHave(urlContaining(expectedUrlPart));
        pageHeader.shouldHave(text(expectedTitle.trim()));
        return this;
    }

    public CategoryPage clickOnSubcategoryByText(String subcategoryName) {
        subcategoryItems.findBy(text(subcategoryName.trim())).click();
        return this;
    }
}
