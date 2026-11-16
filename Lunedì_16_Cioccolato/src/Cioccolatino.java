/** Gestisce un lotto di cioccolatini: ciascun pezzo costa 2 euro. */
public class Cioccolatino extends Cioccolato {
    // Prezzo fisso previsto per ogni cioccolatino.
    private static final double COSTO_UNITARIO = 2.0;

    // Dettagli del lotto e numero di pezzi da produrre.
    private final String forma;
    private final String ripieno;
    private final int quantita;

    public Cioccolatino(
            double percentualeCacao,
            String forma,
            String ripieno,
            int quantita
    ) {
        super("Cioccolatino", percentualeCacao);
        if (forma == null || forma.trim().isEmpty()) {
            throw new IllegalArgumentException("La forma è obbligatoria.");
        }
        if (ripieno == null || ripieno.trim().isEmpty()) {
            throw new IllegalArgumentException("Il ripieno è obbligatorio.");
        }
        if (quantita <= 0) {
            throw new IllegalArgumentException("La quantità deve essere positiva.");
        }

        this.forma = forma;
        this.ripieno = ripieno;
        this.quantita = quantita;
    }

    @Override
    public void produce() {
        super.produce();
        // Aggiunge alla stampa comune forma, ripieno e quantità del lotto.
        System.out.printf(
                "Forma: %s | Ripieno: %s | Cioccolatini prodotti: %d | Costo unitario: %.2f EUR%n",
                forma,
                ripieno,
                quantita,
                getCostoUnitario()
        );
    }

    @Override
    public int getQuantita() {
        return quantita;
    }

    @Override
    public double getCostoUnitario() {
        return COSTO_UNITARIO;
    }
}
