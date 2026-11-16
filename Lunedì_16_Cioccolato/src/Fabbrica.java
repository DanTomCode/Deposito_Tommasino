import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/** Coordina la produzione giornaliera, stampa il resoconto e contiene il main. */
public class Fabbrica {
    // Totali del giorno corrente.
    private int giorno = 1;
    private int quantitaProdotta;
    private double incasso;
    // Quantità prodotta, raggruppata per tipo di cioccolato.
    private final Map<String, Integer> produzionePerTipo = new LinkedHashMap<>();

    public void produci(Cioccolato prodotto) {
        if (prodotto == null) {
            throw new IllegalArgumentException("Il prodotto non può essere null.");
        }
        if (quantitaProdotta + prodotto.getQuantita() > Cioccolato.MAX_PRODUZIONE_GIORNALIERA) {
            throw new IllegalArgumentException(
                    "La produzione giornaliera non può superare "
                            + Cioccolato.MAX_PRODUZIONE_GIORNALIERA + " pezzi."
            );
        }

        // Produce il lotto e aggiorna quantità e incasso della giornata.
        prodotto.produce();
        quantitaProdotta += prodotto.getQuantita();
        incasso += prodotto.getCostoUnitario() * prodotto.getQuantita();
        produzionePerTipo.put(
                prodotto.getTipoCioccolato(),
                produzionePerTipo.getOrDefault(prodotto.getTipoCioccolato(), 0)
                        + prodotto.getQuantita()
        );

        if (quantitaProdotta == Cioccolato.MAX_PRODUZIONE_GIORNALIERA) {
            stampaResoconto();
        }
    }

    public void iniziaNuovoGiorno() {
        if (quantitaProdotta != Cioccolato.MAX_PRODUZIONE_GIORNALIERA) {
            throw new IllegalStateException("Il giorno corrente non è ancora terminato.");
        }

        // Apre un nuovo giorno azzerando i totali della produzione precedente.
        giorno++;
        quantitaProdotta = 0;
        incasso = 0;
        produzionePerTipo.clear();
    }

    private void stampaResoconto() {
        // Riepiloga quantità per prodotto e incasso al raggiungimento del limite.
        System.out.println();
        System.out.println("=== Resoconto giorno " + giorno + " ===");
        for (Map.Entry<String, Integer> produzione : produzionePerTipo.entrySet()) {
            System.out.printf("%s: %d%n", produzione.getKey(), produzione.getValue());
        }
        System.out.printf("Totale prodotto: %d pezzi%n", quantitaProdotta);
        System.out.printf("Incasso: %.2f EUR%n", incasso);
        System.out.println("La fabbrica chiude per oggi.");
        System.out.println();
    }

    private void produciGiornataDimostrativa() {
        // Esempio: 40 cioccolatini, 30 tavolette e 30 cioccolate calde.
        produci(new Cioccolatino(50, "sferica", "nocciola", 40));
        produci(new Tavoletta(70, 100, "mandorle", 30));
        produci(new CioccolatoCaldo(60, 85, "densa", 25, 30));
    }

    public static void main(String[] args) {
        // Ripete la giornata dimostrativa finché l'utente vuole continuare.
        Fabbrica fabbrica = new Fabbrica();
        Scanner scanner = new Scanner(System.in);
        boolean continua = true;

        while (continua) {
            fabbrica.produciGiornataDimostrativa();

            System.out.print("Vuoi continuare con un nuovo giorno? (s/n): ");
            String risposta = scanner.nextLine().trim();
            while (!risposta.equalsIgnoreCase("s") && !risposta.equalsIgnoreCase("n")) {
                System.out.print("Rispondi con 's' oppure 'n': ");
                risposta = scanner.nextLine().trim();
            }

            if (risposta.equalsIgnoreCase("s")) {
                fabbrica.iniziaNuovoGiorno();
            } else {
                continua = false;
            }
        }

        scanner.close();
    }
}
