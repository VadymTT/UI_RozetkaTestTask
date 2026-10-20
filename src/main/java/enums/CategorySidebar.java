package enums;

public enum CategorySidebar {
    ELECTRONICS("Смартфони, ТВ і електроніка"),
    CLOTHING("Одяг, взуття та прикраси"),
    HOME_GOODS("Товари для дому");

    private final String name;

    CategorySidebar(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
