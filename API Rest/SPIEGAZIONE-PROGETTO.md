# Come è stato creato il progetto

Il progetto usa Spring Boot per esporre un'API REST e Spring Data JPA per salvare i prodotti nel database H2.

## Struttura

- `ProductsApiApplication`: avvia l'applicazione.
- `api`: riceve le richieste HTTP e contiene i DTO usati per input e risposta.
- `model`: rappresenta il prodotto nella logica applicativa.
- `service`: gestisce la creazione del prodotto.
- `persistence`: contiene l'entità JPA e il repository per il database.

## Flusso di creazione

1. Il client invia `POST /api/products` con nome, prezzo unitario e quantità disponibile.
2. `ProductRequest` controlla che i dati siano validi e li converte nel modello `Product`.
3. `ProductService` converte il modello nell'entità `ProductEntity` e la salva tramite `ProductRepository`.
4. L'API restituisce `201 Created` con il prodotto salvato e l'ID assegnato dal database.

## Avvio e prova

Avvia il progetto dalla sua cartella con:

```bash
mvn spring-boot:run
```

In Postman usa il metodo `POST`, l'indirizzo `http://localhost:8080/api/products` e un body JSON come:

```json
{
  "nome": "Tastiera",
  "prezzoUnitario": 49.90,
  "quantitaDisponibile": 12
}
```

Il database H2 è configurato in memoria: i dati vengono conservati mentre l'applicazione è in esecuzione.
