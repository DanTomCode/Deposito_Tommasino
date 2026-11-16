/** Gestisce un lotto di tavolette: il prezzo varia con il peso, da 4 a 24 euro. */
public class Tavoletta extends Cioccolato {
    // Peso e ingredienti aggiunti a ogni tavoletta del lotto.
    private final int pesoGrammi;
    private final String aggiunte;
    private final int quantita;

    public Tavoletta(
            double percentualeCacao,
            int pesoGrammi,
            String aggiunte,
            int quantita
    ) {
        super("Tavoletta", percentualeCacao);
        if (pesoGrammi < 100 || pesoGrammi > 600) {
            throw new IllegalArgumentException("Il peso deve essere tra 100 e 600 grammi.");
        }
        if (aggiunte == null || aggiunte.trim().isEmpty()) {
            throw new IllegalArgumentException("Indicare le aggiunte oppure 'nessuna'.");
        }
        if (quantita <= 0) {
            throw new IllegalArgumentException("La quantità deve essere positiva.");
        }

        this.pesoGrammi = pesoGrammi;
        this.aggiunte = aggiunte;
        this.quantita = quantita;
    }

    @Override
    public void produce() {
        super.produce();
        System.out.printf(
                "Peso: %d g | Aggiunte: %s | Tavolette prodotte: %d | Costo unitario: %.2f EUR%n",
                pesoGrammi,
                aggiunte,
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
        // 4 euro ogni 100 grammi: 100 g costano 4 euro e 600 g ne costano 24.
        return pesoGrammi / 25.0;
    }
}
