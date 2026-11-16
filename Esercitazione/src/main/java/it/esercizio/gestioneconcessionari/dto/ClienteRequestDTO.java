package it.esercizio.gestioneconcessionari.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * Dati accettati dalle API per creare o aggiornare un cliente.
 * Le annotazioni vengono applicate automaticamente dal controller tramite @Valid.
 */
public record ClienteRequestDTO(
        @NotBlank @Size(max = 50) String nome,
        @NotBlank @Size(max = 50) String cognome,
        @Email @Size(max = 100) String email,
        @Size(max = 20) String telefono,
        @NotNull LocalDate dataRegistrazione,
        @NotNull @Positive Long concessionarioId
) {
}
