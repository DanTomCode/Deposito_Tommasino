package Esercizi;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Esercizio2 {
    

     public static void main(String[] args) {
       
       // Creo la lista modificabile di frutti.
       List<String> frutta = new ArrayList<>(
        List.of("kiwi", "banana", "mela", "ananas", "pera", "fico", "arancia"));

       // Stampo tutti i frutti iniziali con lo stesso Consumer.
       Consumer<String> stampa = f -> System.out.println(f);
       frutta.forEach(stampa);
       
       // Rimuovo i frutti con quattro caratteri o meno.
       Predicate<String> corta = f -> f.length() <= 4;
       frutta.removeIf(corta);

       // Ordino per lunghezza decrescente e, a parità, alfabeticamente.
       Comparator<String> ordine = (a, b) -> {
           int confrontoLunghezza = Integer.compare(b.length(), a.length());
           if (confrontoLunghezza != 0) {
               return confrontoLunghezza;
           }
           return a.compareTo(b);
       };
       frutta.sort(ordine);
       
       // Separo l'output e stampo la lista ordinata.
       System.out.println("---");
       frutta.forEach(stampa);

        
       


    }







}
