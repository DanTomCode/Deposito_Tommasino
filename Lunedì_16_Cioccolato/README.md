# Fabbrica di cioccolato

## Metodologia

Il progetto usa la programmazione a oggetti: `Cioccolato` raccoglie i dati comuni, mentre `Cioccolatino`, `Tavoletta` e `CioccolatoCaldo` descrivono i prodotti specifici.

`Fabbrica` gestisce la produzione giornaliera, fino a 100 pezzi, calcola l'incasso e stampa il resoconto. Il `main` esegue un esempio di produzione e chiede se iniziare un nuovo giorno.

## Perché

La struttura evita di ripetere il codice comune e rende più semplice aggiungere nuovi prodotti. I prezzi dell'esempio sono: 2 EUR per cioccolatino, 4 EUR ogni 100 g di tavoletta e almeno 20 EUR per porzione di cioccolata calda.

## File Java

- `Cioccolato.java`: definisce i dati comuni dei prodotti.
- `Cioccolatino.java`: descrive i cioccolatini e il loro prezzo.
- `Tavoletta.java`: descrive le tavolette e calcola il prezzo in base al peso.
- `CioccolatoCaldo.java`: descrive la cioccolata calda e le sue caratteristiche.
- `Fabbrica.java`: gestisce la produzione, il resoconto e contiene il `main`.
