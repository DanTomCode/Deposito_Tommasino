package it.esercizio.gestioneconcessionari.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * Dati in ingresso per un concessionario. casaMadreId identifica la relazione senza
 * esporre un'entità JPA annidata nel JSON.
 */
public record ConcessionarioRequestDTO(
        @NotBlank @Size(max = 100) String nome,
        @NotBlank @Size(max = 100) String citta,
        @Size(max = 150) String indirizzo,
        @Size(max = 20) String telefono,
        @PositiveOrZero BigDecimal fatturato,
        @NotNull @Positive Long casaMadreId
) {
}
