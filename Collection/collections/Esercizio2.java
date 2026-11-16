package collections;

// Importiamo ArrayList per usare una lista che può cambiare dimensione.
import java.util.ArrayList;

public class Esercizio2 {
    public static void main(String[] args) {
        // Usiamo String perché i brani sono nomi di testo.
        // ArrayList permette di aggiungere e rimuovere brani.
        ArrayList<String> playlist = new ArrayList<>();

        // add aggiunge ogni brano in fondo alla lista.
        playlist.add("Intro");
        playlist.add("Groove");
        playlist.add("Ballad");
        playlist.add("Finale");

        // remove(2) elimina l'elemento all'indice 2, cioè il terzo brano.
        playlist.remove(2);

        // add(0, ...) inserisce il brano all'inizio e sposta gli altri.
        playlist.add(0, "Sorpresa");

        // Stampiamo la lista aggiornata e size() conta quanti brani contiene.
        System.out.println("Playlist finale: " + playlist);
        System.out.println("Numero di brani: " + playlist.size());
    }
}








