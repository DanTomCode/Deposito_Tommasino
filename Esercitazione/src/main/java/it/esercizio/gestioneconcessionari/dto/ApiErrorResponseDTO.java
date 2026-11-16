package it.esercizio.gestioneconcessionari.dto;

import java.time.Instant;

public record ApiErrorResponseDTO(
        Instant timestamp,
        int status,
        String error,
        String message
) {
}
