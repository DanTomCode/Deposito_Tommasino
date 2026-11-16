package Esercizi;

import java.util.List;
import java.util.Comparator;

public class Esercizio3{

  record Ordine(String cliente, String prodotto, int quantita, double prezzoUnitario) {
    double totale() {
        return quantita * prezzoUnitario;
    }
}



  public static void main(String[] args) {

 List<Ordine> ordini = List.of(
     new Ordine("Anna", "Mouse", 2, 25.0),
     new Ordine("Luca", "Monitor", 1, 199.0),
     new Ordine("Anna", "Tastiera", 1, 45.0),
     new Ordine("Marta", "Mouse", 3, 25.0),
     new Ordine("Luca", "Webcam", 2, 60.0),
     new Ordine("Paolo", "Monitor", 2, 199.0)
 );
  
 List<String> prodotti = ordini.stream()
        .map(Ordine::prodotto)
        .distinct()
        .sorted()
        .toList();
 
        System.out.println("Prodotti: " + prodotti);

        double totaleIncassato = ordini.stream()
        .mapToDouble(Ordine::totale)
        .sum();
        
        System.out.println("Totale incassato: " + totaleIncassato);

        List<String> clientiConOrdiniCostosi = ordini.stream()
                .filter(ordine -> ordine.totale() > 100)
                .map(Ordine::cliente)
                .distinct()
                .sorted()
                .toList();
        System.out.println("Clienti con ordini sopra 100 euro: " + clientiConOrdiniCostosi);

        Ordine ordinePiuCostoso = ordini.stream()
                .max(Comparator.comparingDouble(Ordine::totale))
                .orElseThrow();
        System.out.println("Ordine più costoso: "
                + ordinePiuCostoso.cliente() + " - " + ordinePiuCostoso.prodotto());
        








 
 }










}