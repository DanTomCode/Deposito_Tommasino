package it.esercizio.gestioneconcessionari.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/** Dati in ingresso per una casa madre, con controlli di validazione sui campi obbligatori. */
public record CasaMadreRequestDTO(
        @NotBlank @Size(max = 100) String ragioneSociale,
        @NotBlank @Size(max = 100) String sedeLegale,
        @NotBlank @Size(max = 50) String paese,
        @PositiveOrZero Integer annoFondazione,
        @PositiveOrZero BigDecimal fatturatoAnnuo
) {
}
