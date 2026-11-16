# Products API

Applicazione Spring Boot che espone un endpoint REST per salvare prodotti su H2.

## Avvio

```shell
mvn spring-boot:run
```

Il database H2 è in memoria e la tabella `prodotti` viene creata/aggiornata all'avvio.

## Creazione di un prodotto

`POST /api/products`

Request:

```json
{
  "nome": "Tastiera",
  "prezzoUnitario": 49.90,
  "quantitaDisponibile": 12
}
```

I campi sono obbligatori; il nome non può essere vuoto, il prezzo deve essere maggiore di zero e la quantità non può essere negativa. In caso di successo viene restituito `201 Created` con il prodotto salvato, incluso l'identificativo generato dal database.
