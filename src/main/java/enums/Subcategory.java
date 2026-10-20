package enums;

public enum Subcategory {
    MOBILE_PHONES("Мобільні телефони"),
    TV("Телевізори");

    private final String name;

    Subcategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}