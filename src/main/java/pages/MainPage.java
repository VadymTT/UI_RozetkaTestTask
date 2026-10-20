package pages;

import base.BasePage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import enums.CategorySidebar;
import lombok.Getter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;

@Getter
public class MainPage extends BasePage {

    private final ElementsCollection categoryItems = $$x("//a[@data-testid='fat_menu_category_link']");

    public SelenideElement getCategoryByEnum(CategorySidebar category) {
        return categoryItems.findBy(text(category.getName().trim()));
    }

    public void clickOnCategory(CategorySidebar category) {
        getCategoryByEnum(category).click();
    }
}
