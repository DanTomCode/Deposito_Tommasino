package Esercizi;

import java.util.Arrays;
import java.util.List;


public class Esercizio1 {
   
    public static void main(String[] args) {
       
        // Stampa il doppio di ogni numero della lista.
       List<Integer> numeri = List.of(1, 2, 3);
       numeri.forEach(n -> System.out.println(n * 2));

       // Ordina le parole dalla più corta alla più lunga e stampo l'array.
       String[] parole = {"mondo", "a", "ciao"};
       Arrays.sort(parole, (x, y) -> Integer.compare(x.length(), y.length()));
       System.out.println(Arrays.toString(parole));
    
    
    
    
    
    
    
    }





}














