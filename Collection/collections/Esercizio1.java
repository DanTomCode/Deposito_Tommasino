package collections;


public class Esercizio1 {
   
   
   
   
    public static void main(String[] args) {
     // Usiamo un array di double per conservare temperature con decimali.
     double[] temperature = {12.5, 15.0, 9.8, 18.2, 14.0, 11.3, 16.7};
     // Questo array collega ogni temperatura al giorno con lo stesso indice.
     String[] giorni = {"lunedì", "martedì", "mercoledì", "giovedì",
                "venerdì", "sabato", "domenica"};

     // Usiamo la prima temperatura come valore iniziale da confrontare.
     double temperaturaMassima = temperature[0];
     // Salviamo l'indice per risalire al giorno della temperatura massima.
     int indiceGiornoMassimo = 0;

     // Partiamo da 1 perché l'indice 0 è già il valore iniziale.
     // Usiamo length per controllare tutti gli elementi dell'array.
     for (int i = 1; i < temperature.length; i++) {
            // Se troviamo una temperatura più alta, aggiorniamo valore e indice.
            if (temperature[i] > temperaturaMassima) {
                temperaturaMassima = temperature[i];
                indiceGiornoMassimo = i;
            }
        }

     // Usiamo l'indice salvato per stampare il giorno corrispondente.
     System.out.println("La temperatura più alta è " + temperaturaMassima
                + "°C, registrata di " + giorni[indiceGiornoMassimo] + ".");
    }






}
