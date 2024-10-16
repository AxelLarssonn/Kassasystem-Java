import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class Kassasystem {
    private JFrame frame; // Huvudfönstret för GUI
    private JTextArea receiptArea; // Område för att visa kvittot
    private JTextField quantityField; // Fält där användaren skriver antal
    private JTextField productField; // Fält som visar vald produkt
    private Receipt receipt; // Objekt för att hantera kvittot
    private ArrayList<Product> products; // Lista med produkter

    public Kassasystem() {
        products = loadProductsFromFile(); // Ladda produkter från fil
        receipt = new Receipt(); // Skapa ett nytt kvitto
        initializeGUI(); // Starta grafiska användargränssnittet (GUI)
    }

    // Ladda produkter från filen Products.txt
    private ArrayList<Product> loadProductsFromFile() {
        ArrayList<Product> productList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("Products.txt")); // Skapa scanner för att läsa filen
            while (scanner.hasNextLine()) { // Läs rad för rad
                String line = scanner.nextLine();
                String[] arrOfStr = line.split(","); // Dela upp raden i produktnamn, pris och moms
                String name = arrOfStr[0];
                double price = Double.parseDouble(arrOfStr[1]);
                double vat = Double.parseDouble(arrOfStr[2]);
                productList.add(new Product(name, price, vat)); // Lägg till produkten i listan
            }
            scanner.close(); // Stäng scanner
        } catch (FileNotFoundException e) {
            System.out.println("Produktfiler hittades inte.");
            e.printStackTrace();
        }
        return productList; // Returnera produktlistan
    }

    // Starta GUI-komponenterna
    private void initializeGUI() {
        frame = new JFrame("IOT24 POS"); // Skapa fönstret med titeln "IOT24 POS"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Avsluta programmet vid stängning
        frame.setLayout(new BorderLayout()); // Använd BorderLayout för fönstret

        // Skapa panelen som innehåller produktknappar
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(1, products.size())); // Ställ in layout med lika mycket plats för varje produkt

        // Skapa en knapp för varje produkt
        for (Product product : products) {
            JButton productButton = new JButton(product.getName()); // Skapa knapp med produktnamn
            productPanel.add(productButton); // Lägg till knappen i panelen
            productButton.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    productField.setText(product.getName()); // Sätt produktnamnet i fältet när knappen klickas
                }
            });
        }

        // Skapa panel för grafiskt element, i detta fall en grön panel till vänster
        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.GREEN);
        greenPanel.setPreferredSize(new Dimension(720, 400));

        // Skapa kvittopanelen som innehåller textområdet där kvittot visas
        JPanel receiptPanel = new JPanel();
        receiptPanel.setLayout(new BorderLayout());
        receiptArea = new JTextArea(30, 40); // Textområde för kvittot
        receiptArea.setEditable(false); // Sätt textområdet till icke-redigerbart
        receiptArea.setBackground(Color.WHITE); // Vit bakgrund
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Sätt fonttyp

        JScrollPane scrollPane = new JScrollPane(receiptArea); // Lägg till scrollfunktion för kvittot
        receiptPanel.add(scrollPane, BorderLayout.CENTER);

        // Skapa panel för användarinmatning (antal och produkt)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        quantityField = new JTextField(5); // Fält för antal
        JLabel quantityLabel = new JLabel("Antal"); // Antalsfältet
        productField = new JTextField(15); // Fält för produktnamn
        productField.setEditable(false); // Sätt produktfältet till icke-redigerbart

        JButton addButton = new JButton("Add"); // Knapp för att lägga till produkt i kvittot
        JButton payButton = new JButton("Pay"); // Knapp för att genomföra betalning

        bottomPanel.add(quantityLabel); // Lägg till etiketten för antal
        bottomPanel.add(quantityField); // Lägg till fältet för antal
        bottomPanel.add(productField); // Lägg till produktfältet
        bottomPanel.add(addButton); // Lägg till knappen för att lägga till produkt
        bottomPanel.add(payButton); // Lägg till betalningsknappen

        // ActionListener för att lägga till produkt till kvittot
        addButton.addActionListener(e -> {
            String productName = productField.getText(); // Hämta produktnamn från fältet
            String quantity = quantityField.getText(); // Hämta antal från fältet
            if (!productName.isEmpty() && !quantity.isEmpty()) { // Kontrollera att båda fält är ifyllda
                try {
                    int qty = Integer.parseInt(quantity); // Försök att konvertera antal till ett heltal
                    if (qty > 0) { // Kontrollera att antal är större än 0
                        Product selectedProduct = products.stream()
                            .filter(p -> p.getName().equals(productName))
                            .findFirst().orElse(null); // Hämta vald produkt från produktlistan
                        if (selectedProduct != null) {
                            receipt.addRow(new ReceiptRow(selectedProduct, qty)); // Lägg till raden i kvittot
                            receiptArea.setText(receipt.toString()); // Uppdatera kvittovisningen
                            quantityField.setText(""); // Töm antalsfältet
                            productField.setText(""); // Töm produktfältet
                            quantityField.requestFocus(); // Fokusera på antalsfältet
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Ange ett positivt heltal."); // Felmeddelande om antal är ogiltigt
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ogiltigt antal. Ange ett positivt heltal."); // Felmeddelande om inmatning ej är ett tal
                }
            }
        });

        // ActionListener för att hantera betalning
        payButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Tack för ditt köp!\n" + receipt.toString()); // Visa kvitto i popup
            receipt = new Receipt(); // Skapa ett nytt kvitto efter betalning
            receiptArea.setText(""); // Rensa kvittovisningen
            quantityField.setText(""); // Rensa antalsfältet
            productField.setText(""); // Rensa produktfältet
            quantityField.requestFocus(); // Fokusera på antalsfältet
        });

        // Lägg till alla komponenter till huvudfönstret
        frame.add(productPanel, BorderLayout.NORTH); // Produktpanel överst
        frame.add(greenPanel, BorderLayout.WEST); // Grön panel till vänster
        frame.add(receiptPanel, BorderLayout.EAST); // Kvittopanel till höger
        frame.add(bottomPanel, BorderLayout.SOUTH); // Inmatningspanel längst ner

        frame.setSize(1000, 600); // Sätt fönstrets storlek
        frame.setVisible(true); // Gör fönstret synligt
    }

    public static void main(String[] args) {
        new Kassasystem(); // Starta kassasystemet
    }
}
