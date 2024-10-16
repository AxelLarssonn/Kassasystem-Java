public class Product {
    private String name; // Produktens namn
    private double price; // Produktens pris
    private double vat; // Moms för produkten

    // Konstruktor som tar emot produktens namn, pris och moms
    public Product(String name, double price, double vat) {
        this.name = name;
        this.price = price;
        this.vat = vat;
    }

    // Getter för produktens namn
    public String getName() {
        return name;
    }

    // Getter för produktens pris
    public double getPrice() {
        return price;
    }

    // Getter för produktens moms
    public double getVat() {
        return vat;
    }
}
