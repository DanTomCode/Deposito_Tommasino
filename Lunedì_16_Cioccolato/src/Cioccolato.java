/** Classe base: raccoglie tipo e cacao e definisce il limite di 100 pezzi al giorno. */
public abstract class Cioccolato {
    // Limite condiviso da tutti i prodotti della fabbrica.
    public static final int MAX_PRODUZIONE_GIORNALIERA = 100;

    // Caratteristiche comuni a cioccolatini, tavolette e cioccolata calda.
    private final String tipoCioccolato;
    private final double percentualeCacao;

    protected Cioccolato(String tipoCioccolato, double percentualeCacao) {
        // Controlla i dati comuni prima di creare il prodotto.
        if (tipoCioccolato == null || tipoCioccolato.trim().isEmpty()) {
            throw new IllegalArgumentException("Il tipo di cioccolato è obbligatorio.");
        }
        if (percentualeCacao < 0 || percentualeCacao > 100) {
            throw new IllegalArgumentException("La percentuale di cacao deve essere tra 0 e 100.");
        }

        this.tipoCioccolato = tipoCioccolato;
        this.percentualeCacao = percentualeCacao;
    }

    public void produce() {
        // Stampa i dati comuni; le sottoclassi aggiungono quelli specifici.
        System.out.printf(
                "Tipo: %s | Cacao: %.1f%%%n",
                tipoCioccolato,
                percentualeCacao
        );
    }

    public String getTipoCioccolato() {
        return tipoCioccolato;
    }

    public double getPercentualeCacao() {
        return percentualeCacao;
    }

    // Quantità e prezzo vengono definiti da ciascun tipo di prodotto.
    public abstract int getQuantita();

    public abstract double getCostoUnitario();
}
