# Gestione concessionari

Backend REST realizzato con Spring Boot, Spring Data JPA e MySQL per gestire case madri,
concessionari e clienti.

## Struttura

- Una **Casa Madre** può avere più concessionari.
- Ogni **Concessionario** appartiene a una Casa Madre e può gestire più clienti.
- Ogni **Cliente** è associato a un concessionario.

Il progetto usa entità JPA, repository, service transazionali, controller REST, DTO di richiesta
e risposta, un mapper e un gestore centralizzato delle eccezioni. Le risposte non espongono
direttamente le entità.

## Avvio

Requisiti: Java 21+, Maven e MySQL.

1. Crea il database `gestione_concessionari` eseguendo
   [01-create-database.sql](./database/01-create-database.sql) da MySQL Workbench.
2. Controlla le credenziali MySQL in `src/main/resources/application.properties`.
   L'indirizzo predefinito è `localhost:3306/gestione_concessionari`.
3. Dalla cartella del progetto, quella che contiene `pom.xml`, avvia l'app:

   ```powershell
   mvn spring-boot:run
   ```

   Hibernate crea e aggiorna le tabelle all'avvio.

4. Per inserire i dati di prova, esegui
   [02-populate-data.sql](./database/02-populate-data.sql) da Workbench. Va eseguito una sola
   volta su tabelle vuote: inserisce ID predefiniti. Crea 4 case madri, 7 concessionari e
   9 clienti; include una casa madre senza concessionari e un concessionario senza clienti.

Per verificare la compilazione e i test:

```powershell
mvn test
```

## API REST

Base URL: `http://localhost:8080`.

| Operazioni CRUD | Endpoint |
|---|---|
| Case Madri | `/api/case-madri` |
| Concessionari | `/api/concessionari` |
| Clienti | `/api/clienti` |

Per ogni endpoint sono disponibili `POST` (creazione), `GET` (elenco), `GET /{id}`,
`PUT /{id}` e `DELETE /{id}`. Le richieste usano DTO: per esempio, il concessionario riceve
`casaMadreId` e il cliente `concessionarioId`, non oggetti annidati.

Esempio di richiesta per creare un cliente:

```json
{
  "nome": "Mario",
  "cognome": "Rossi",
  "email": "mario.rossi@example.com",
  "dataRegistrazione": "2025-01-15",
  "concessionarioId": 1
}
```

Le richieste sono validate. Email duplicata e cancellazione di una casa madre con concessionari
o di un concessionario con clienti restituiscono `409 Conflict`. Un'entità o una relazione
inesistente restituisce `404 Not Found`; dati non validi `400 Bad Request`.

### Ricerche e statistiche

Le ricerche sono realizzate con metodi derivati Spring Data JPA (name convention) e query JPQL
con `@Query`, senza SQL nativo. Sono disponibili ricerche per campi, relazioni, parametri
facoltativi e intervalli, oltre a query aggregate e sottoquery.

Esempi:

| Funzionalità | Endpoint |
|---|---|
| Clienti per concessionario | `GET /api/clienti/concessionario/{id}` |
| Clienti per casa madre | `GET /api/clienti/casa-madre/{id}` |
| Clienti per città o paese | `GET /api/clienti/citta/{citta}`, `GET /api/clienti/paese/{paese}` |
| Ricerca clienti per nome/cognome | `GET /api/clienti/search?nome=Mario&cognome=Rossi` |
| Concessionari per casa madre | `GET /api/concessionari/casa-madre/{ragioneSociale}` |
| Concessionari per intervallo di fatturato | `GET /api/concessionari/fatturato?min=100000&max=900000` |
| Statistiche per casa madre | `/api/case-madri/statistiche/concessionari`, `/api/case-madri/statistiche/clienti`, `/api/case-madri/statistiche/fatturato` |
| Statistiche per concessionario | `/api/concessionari/statistiche/clienti`, `/api/concessionari/statistiche/sopra-media-clienti` |
| Entità sopra la media o senza relazioni | `/api/concessionari/sopra-media`, `/api/concessionari/con-clienti`, `/api/case-madri/sopra-media`, `/api/case-madri/senza-concessionari` |

Sono inoltre esposti gli endpoint per gli esercizi di name convention:
`/api/clienti/query/...`, `/api/concessionari/query/...` e `/api/case-madri/query/...`.
Le query aggregate usano projection tramite interfacce e DTO JPQL con `SELECT NEW`.

## DTO, validazione e transazioni

Le API restituiscono DTO per clienti, concessionari e case madri; le statistiche includono
conteggi e fatturati aggregati. I DTO d'ingresso applicano vincoli Bean Validation, tra cui
`@NotBlank`, `@NotNull`, `@Email`, `@Size` e vincoli numerici. Il mapper converte DTO ed entità.
I service gestiscono le operazioni con transazioni e l'advice REST uniforma gli errori (`400`,
`404`, `409` e `500`).

## Postman

Importa in Postman
[Gestione-concessionari.postman_collection.json](./postman/Gestione-concessionari.postman_collection.json).
La raccolta contiene sette cartelle: CRUD Case Madri, CRUD Concessionari, CRUD Clienti, Query
Name Convention, Query JPQL, Statistiche e aggregazioni, Test di gestione errori. Avvia prima
Spring Boot; carica il dataset SQL prima di provare le ricerche e le statistiche.

Per una prova manuale, seleziona `GET` in Postman e invia, ad esempio:

```text
http://localhost:8080/api/clienti/concessionario/1
```
