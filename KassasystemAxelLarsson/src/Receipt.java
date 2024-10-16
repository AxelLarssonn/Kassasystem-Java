import java.util.ArrayList;

public class Receipt {
    private ArrayList<ReceiptRow> rows; // Lista över rader i kvittot
    private static int receiptCount = 0; // Räknare för kvittonummer (ökar automatiskt)
    private int receiptNumber; // Kvittonummer för det aktuella kvittot

    // Konstruktor som skapar ett nytt kvitto och ökar kvittonumret
    public Receipt() {
        this.rows = new ArrayList<>(); // Initiera listan med kvittorader
        this.receiptNumber = ++receiptCount; // Sätt kvittonummer och öka räknaren
    }

    // Lägg till en rad i kvittot
    public void addRow(ReceiptRow row) {
        rows.add(row);
    }

    // Returnerar en strängrepresentation av kvittot
    public String toString() {
        StringBuilder sb = new StringBuilder()
            .append("Kvittonummer: ").append(receiptNumber).append("\n")
            .append("Datum och tid: ").append(java.time.LocalDateTime.now()).append("\n")
            .append("-----------------------------------\n");

        // Lägg till alla kvittorader i strängen
        rows.forEach(row -> sb.append(row).append("\n"));

        // Lägg till totalkostnaden längst ner
        return sb.append("-----------------------------------\n")
            .append("Totalt: ").append(getTotal()).append(" kr\n")
            .toString();
    }

    // Beräkna och returnera den totala summan för alla rader
    private double getTotal() {
        double total = 0;
        for (ReceiptRow row : rows) {
            total += row.getTotal(); // Summera varje kvittorads totala pris
        }
        return total;
    }
}
