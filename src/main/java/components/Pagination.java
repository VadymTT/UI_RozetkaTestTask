package components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class Pagination {

    private final SelenideElement paginationSection = $x("//nav[@data-testid='paginator']");

    public void scrollToPagination() {
        paginationSection.scrollIntoView(true);
    }
}
