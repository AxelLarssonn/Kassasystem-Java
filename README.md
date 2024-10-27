Kassasystem
Ett kassasystem skapat i Java för att hantera försäljning och kvittohantering. Systemet tillåter användare att lägga till produkter, beräkna totalbelopp och hantera moms. Projektet är uppdelat i flera klasser och använder objektorienterade principer.

Funktioner
Lägga till produkter och mängder i kassan.
Visa totalbelopp och moms för varje produkt.
Generera kvitton med unika kvittonummer, datum och tid.
Spara produktdata dynamiskt från en fil (produkter.txt).
Projektstruktur
Projektet består av följande filer:

Kassasystem.java - Huvudklassen som hanterar kassafunktionaliteten.
Product.java - Innehåller information om varje produkt.
Receipt.java - Hanterar kvittoinformation.
ReceiptRow.java - Representerar en rad på kvittot.
produkter.txt - Filen som innehåller produktinformation (produktnamn, pris, moms).
Installation
Klona detta repo:
bash
Kopiera kod
git clone https://github.com/[ditt-användarnamn]/Kassasystem.git
Öppna projektet i din Java IDE (som IntelliJ IDEA eller Eclipse).
Säkerställ att Java JDK 8 eller senare är installerat.
Användning
Starta programmet genom att köra Kassasystem.java.
Lägg till produkter genom att mata in produktnamn och mängd.
Systemet genererar ett kvitto som visar totalbelopp och moms för varje produkt.
Kvitton sparas med unika kvittonummer, samt datum och tid.
Exempel på produkter.txt
Filen produkter.txt innehåller en lista av produkter i följande format:

Kopiera kod
Kaffe,51.00,12
Nallebjörn,110.00,25
Mugg,25.00,25
Chips,20.00,12
Yoghurt,10.00,12
Daim,15.00,25
Varje rad innehåller produktens namn, pris och momssats.

Systemkrav
Java JDK 8 eller senare
Framtida utveckling
Lägg till möjlighet för användaren att ta bort produkter från kvittot.
Implementera olika betalningsmetoder.
Lägg till en grafisk användargränssnitt (GUI) med Java Swing.
