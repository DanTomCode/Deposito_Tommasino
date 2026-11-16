/** Gestisce porzioni di cioccolata calda, dal costo minimo di 20 euro ciascuna. */
public class CioccolatoCaldo extends Cioccolato {
    // Prezzo minimo indicato per ogni porzione.
    private static final double COSTO_MINIMO = 20.0;

    // Caratteristiche della bevanda e quantità preparata.
    private final double temperatura;
    private final String densita;
    private final int grammiPerPorzione;
    private final int quantita;

    public CioccolatoCaldo(
            double percentualeCacao,
            double temperatura,
            String densita,
            int grammiPerPorzione,
            int quantita
    ) {
        super("Cioccolato caldo", percentualeCacao);
        if (temperatura <= 0) {
            throw new IllegalArgumentException("La temperatura deve essere positiva.");
        }
        if (densita == null || densita.trim().isEmpty()) {
            throw new IllegalArgumentException("La densità è obbligatoria.");
        }
        if (grammiPerPorzione <= 0) {
            throw new IllegalArgumentException("I grammi per porzione devono essere positivi.");
        }
        if (quantita <= 0) {
            throw new IllegalArgumentException("La quantità deve essere positiva.");
        }

        this.temperatura = temperatura;
        this.densita = densita;
        this.grammiPerPorzione = grammiPerPorzione;
        this.quantita = quantita;
    }

    @Override
    public void produce() {
        super.produce();
        // Calcola il cioccolato totale usando grammi per porzione e numero di porzioni.
        System.out.printf(
                "Temperatura: %.1f °C | Densità: %s | Cioccolato usato: %d g | Porzioni prodotte: %d | Costo unitario: %.2f EUR%n",
                temperatura,
                densita,
                grammiPerPorzione * quantita,
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
        return COSTO_MINIMO;
    }
}
