# Guida semplice al progetto

Questa guida spiega con parole semplici come è organizzato il progetto di gestione dei
concessionari. Il README principale contiene invece le istruzioni rapide per avviarlo.

## 1. Che cosa fa il programma?

È un **backend**, cioè la parte di un'applicazione che riceve richieste, applica le regole del
programma e legge o salva dati nel database. Non mostra pagine grafiche: le richieste si possono
provare con Postman.

Il progetto gestisce tre tipi di dati:

- **CasaMadre**: l'azienda automobilistica.
- **Concessionario**: un punto vendita appartenente a una casa madre.
- **Cliente**: una persona registrata presso un concessionario.

Le relazioni sono:

```text
CasaMadre 1 ---- molti Concessionari 1 ---- molti Clienti
```

In altre parole, una casa madre può avere molti concessionari, ma ogni concessionario appartiene
a una sola casa madre. Un concessionario può avere molti clienti, ma ogni cliente è associato a
un solo concessionario.

## 2. Che cos'è Spring Boot?

**Spring Boot** è uno strumento Java che aiuta a creare applicazioni web senza dover configurare
manualmente ogni parte del server. Avvia il server web, collega i componenti dell'applicazione e
offre funzioni per lavorare con API e database.

La classe `GestioneConcessionariApplication` è il punto di partenza: il metodo `main` avvia
Spring Boot. Le librerie e le dipendenze usate dal progetto sono dichiarate in `pom.xml`.

## 3. Che cos'è un'API REST?

Un'**API** è un modo per chiedere dati o operazioni a un programma. In questo progetto l'API
usa HTTP e scambia dati in formato JSON. Per esempio, un client come Postman può inviare una
richiesta al server:

```text
GET http://localhost:8080/api/case-madri
```

Il server risponde con un codice HTTP e, normalmente, con un contenuto JSON.

I metodi HTTP più usati sono:

| Metodo | Significato | Esempio |
|---|---|---|
| `GET` | Legge dati | Elenca le case madri |
| `POST` | Crea un dato nuovo | Registra un cliente |
| `PUT` | Modifica un dato esistente | Aggiorna un concessionario |
| `DELETE` | Elimina un dato | Rimuove un cliente |

Un **endpoint** è l'indirizzo che identifica una funzione dell'API. Per esempio,
`/api/clienti/1` indica il cliente con ID 1.

## 4. Il percorso di una richiesta

Quando Postman invia una richiesta, i componenti collaborano in questo ordine:

```text
Postman -> Controller -> Service -> Repository -> MySQL
                               <-             <-
Postman <- risposta JSON <- DTO
```

Esempio: per cercare tutti i clienti di un concessionario, il controller riceve l'indirizzo e
l'ID, il service chiede i dati al repository e il risultato torna a Postman.

### Controller: riceve le richieste

Un **controller** collega gli indirizzi HTTP ai metodi Java. Riceve i parametri e il corpo JSON,
poi delega il lavoro al service. Non dovrebbe contenere tutta la logica dell'applicazione.

Per esempio, `ClienteController` gestisce gli indirizzi che iniziano con
`/api/clienti`; `@GetMapping`, `@PostMapping`, `@PutMapping` e `@DeleteMapping` indicano quale
metodo HTTP attiva ciascun metodo Java.

### Service: applica le regole

Un **service** contiene le regole del progetto. Decide, per esempio, se il cliente esiste, se
l'email è già usata e se è possibile eliminare un concessionario. Poi chiede al repository di
leggere o salvare i dati.

La classe `ClienteService` gestisce le operazioni sui clienti; esistono service analoghi per
case madri e concessionari.

### Repository: comunica con il database

Un **repository** è il componente che legge e scrive i dati nel database. Estendendo
`JpaRepository`, ottiene già operazioni comuni come `findAll`, `findById`, `save` e `delete`.

Alcuni metodi descrivono la ricerca nel loro nome. Per esempio:

```java
findByCognome(String cognome)
```

chiede a Spring Data JPA di trovare i clienti con quel cognome.

Altre ricerche usano `@Query` e JPQL. **JPQL** assomiglia a SQL, ma usa i nomi delle entità e
delle proprietà Java invece dei nomi fisici delle tabelle. In questo progetto serve, per esempio,
per collegare cliente, concessionario e casa madre o per calcolare conteggi e fatturati.

### Entity: rappresenta una tabella

Un'entità Java rappresenta un tipo di dato salvato nel database. `@Entity` dichiara che la
classe è gestita da JPA; `@Table` specifica la tabella e `@Id` indica la chiave primaria.

Le relazioni JPA corrispondono alle chiavi esterne:

- `@ManyToOne`: molti concessionari possono riferirsi alla stessa casa madre; molti clienti
  possono riferirsi allo stesso concessionario.
- `@OneToMany`: una casa madre può contenere una lista di concessionari; un concessionario
  può contenere una lista di clienti.
- `@JoinColumn`: indica la colonna della chiave esterna.
- `mappedBy`: indica il lato inverso della relazione; la chiave esterna è gestita dall'altra
  entità.

Le relazioni `LAZY` vengono caricate quando servono. Le entità hanno anche annotazioni Jackson
per evitare che le relazioni bidirezionali provochino cicli durante la serializzazione JSON.

### DTO: definisce i dati scambiati con l'API

Un **DTO** è un oggetto semplice usato per ricevere o restituire dati. Separa il formato
dell'API dalle entità che rappresentano le tabelle.

Per creare un cliente, per esempio, la richiesta contiene `concessionarioId`, non un oggetto
concessionario completo:

```json
{
  "nome": "Mario",
  "cognome": "Rossi",
  "email": "mario@example.com",
  "dataRegistrazione": "2025-01-15",
  "concessionarioId": 1
}
```

Il **mapper** `EntityDtoMapper` copia i dati tra DTO ed entità. Il service verifica che il
concessionario con ID 1 esista prima di salvare il cliente.

### Validazione: controlla i dati ricevuti

Le annotazioni come `@NotBlank`, `@NotNull`, `@Email`, `@Size` e `@Positive` dichiarano quali
valori sono accettabili. `@Valid` nel controller chiede a Spring di controllare il DTO prima
di eseguire il service. Per esempio, una richiesta con un'email malformata viene rifiutata.

### Transazioni: mantengono coerenti i dati

Una **transazione** raggruppa un'operazione sul database. Se qualcosa fallisce, le modifiche
non devono lasciare i dati in uno stato incoerente. In questo progetto i metodi di scrittura
dei service usano `@Transactional`; le letture usano `@Transactional(readOnly = true)`.

### Gestione degli errori

`GlobalExceptionHandler`, annotato con `@RestControllerAdvice`, intercetta gli errori e li
trasforma in risposte HTTP comprensibili:

- `400 Bad Request`: richiesta o dati non validi.
- `404 Not Found`: elemento o relazione non trovata.
- `409 Conflict`: email duplicata o eliminazione impedita da una relazione.
- `500 Internal Server Error`: errore imprevisto del server.

## 5. Dove trovare i componenti

Le classi Java si trovano sotto `src/main/java/it/esercizio/gestioneconcessionari`:

| Cartella | Contenuto |
|---|---|
| `controller` | Endpoint HTTP |
| `service` | Regole e operazioni applicative |
| `repository` | Accesso ai dati e query |
| `entity` | Entità collegate alle tabelle |
| `dto` | Oggetti di richiesta, risposta e statistiche |
| `mapper` | Conversioni tra entità e DTO |
| `exception` | Formattazione centralizzata degli errori |

La configurazione del database è in `src/main/resources/application.properties`.
Hibernate crea o aggiorna le tabelle all'avvio dell'applicazione.

## 6. Esempio completo: cercare clienti

Per chiamare l'endpoint che restituisce i clienti associati al concessionario 1:

```text
GET http://localhost:8080/api/clienti/concessionario/1
```

1. `ClienteController` riceve la richiesta `GET` e legge l'ID `1`.
2. Il controller chiama il metodo corrispondente in `ClienteService`.
3. Il service chiede al `ClienteRepository` i clienti con quel concessionario.
4. Il repository usa JPA/JPQL per interrogare MySQL.
5. Il mapper trasforma ogni entità `Cliente` in `ClienteResponseDTO`.
6. Spring restituisce i DTO come JSON a Postman.

## 7. Avviare e provare il progetto

Servono Java 21+, Maven e MySQL. Crea lo schema con
[01-create-database.sql](./database/01-create-database.sql), controlla le credenziali in
`application.properties`, poi avvia dalla cartella che contiene `pom.xml`:

```powershell
mvn spring-boot:run
```

Hibernate crea le tabelle al primo avvio. Per caricare i dati di prova, esegui
[02-populate-data.sql](./database/02-populate-data.sql) una sola volta su tabelle vuote.
Poi prova le richieste con Postman; per esempio:

```text
GET http://localhost:8080/api/clienti/concessionario/1
```

La raccolta Postman completa è
[Gestione-concessionari.postman_collection.json](./postman/Gestione-concessionari.postman_collection.json).
