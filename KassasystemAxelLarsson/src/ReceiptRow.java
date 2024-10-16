public class ReceiptRow {
    private Product product; // Produkten i kvittoraden
    private int quantity; // Antal av produkten

    // Konstruktor som tar emot en produkt och dess antal
    public ReceiptRow(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Beräkna totalpriset för raden (pris * antal * moms)
    public double getTotal() {
        return product.getPrice() * quantity * (1 + product.getVat()); // Inkluderar moms i beräkningen
    }

    // Returnera en strängrepresentation av kvittoraden (tex "Kaffe 2 st: 114.24 kr")
    public String toString() {
        return product.getName() + " - " + quantity + " st: " + String.format("%.2f", getTotal()) + " kr";
    }
}
