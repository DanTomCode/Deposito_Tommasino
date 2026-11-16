-- Seleziona lo schema e carica il dataset didattico; eseguire una sola volta su tabelle vuote.
USE gestione_concessionari;

-- La transazione evita di lasciare un caricamento parziale se un inserimento non riesce.
START TRANSACTION;

INSERT INTO CASA_MADRE
    (id, ragione_sociale, sede_legale, paese, anno_fondazione, fatturato_annuo)
VALUES
    (1, 'Auto Italia Group', 'Roma', 'Italia', 1950, 5000000.00),
    (2, 'Global Motors', 'Berlino', 'Germania', 1970, 8500000.00),
    (3, 'Japan Automotive', 'Tokyo', 'Giappone', 1960, 7200000.00),
    (4, 'Electric Mobility', 'New York', 'USA', 2010, 3000000.00);

INSERT INTO CONCESSIONARIO
    (id, nome, citta, indirizzo, telefono, fatturato, casa_madre_id)
VALUES
    (1, 'Auto Roma', 'Roma', NULL, NULL, 500000.00, 1),
    (2, 'Auto Milano', 'Milano', NULL, NULL, 750000.00, 1),
    (3, 'Motors Berlin', 'Berlino', NULL, NULL, 900000.00, 2),
    (4, 'Motors Monaco', 'Monaco', NULL, NULL, 600000.00, 2),
    (5, 'Tokyo Cars', 'Tokyo', NULL, NULL, 800000.00, 3),
    (6, 'Tokyo Motors', 'Osaka', NULL, NULL, 400000.00, 3),
    (7, 'Auto Italia Senza Clienti', 'Napoli', NULL, NULL, 250000.00, 1);

INSERT INTO CLIENTE
    (id, nome, cognome, email, telefono, data_registrazione, concessionario_id)
VALUES
    (1, 'Mario', 'Rossi', 'mario@email.it', NULL, '2025-01-15', 1),
    (2, 'Luca', 'Bianchi', 'luca@email.it', NULL, '2025-01-16', 1),
    (3, 'Anna', 'Verdi', 'anna@email.it', NULL, '2025-01-17', 2),
    (4, 'Paolo', 'Neri', 'paolo@email.it', NULL, '2025-01-18', 3),
    (5, 'Giulia', 'Gialli', 'giulia@email.it', NULL, '2025-01-19', 3),
    (6, 'Marco', 'Blu', 'marco@email.it', NULL, '2025-01-20', 4),
    (7, 'Sara', 'Rosa', 'sara@email.it', NULL, '2025-01-21', 5),
    (8, 'Andrea', 'Rossi', 'andrea@email.it', NULL, '2025-01-22', 5),
    (9, 'Francesca', 'Bianchi', 'francesca@email.it', NULL, '2025-01-23', 6);

COMMIT;
