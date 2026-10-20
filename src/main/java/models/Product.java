package models;

public class Product {
    private final String title;
    private final String price;

    public Product(String title, String price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public int getPriceAsInt() {
        if (price == null || price.trim().isEmpty()) {
            return 0;
        }

        String digitsOnly = price.replaceAll("[^0-9]", "");
        if (digitsOnly.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(digitsOnly);
    }

    @Override
    public String toString() {
        return "Назва: '" + title + "' | Ціна: " + price + " грн";
    }
}
