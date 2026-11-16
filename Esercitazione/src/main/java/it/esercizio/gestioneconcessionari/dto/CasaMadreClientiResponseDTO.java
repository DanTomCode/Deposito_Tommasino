package it.esercizio.gestioneconcessionari.dto;

import java.math.BigDecimal;

public record CasaMadreClientiResponseDTO(
        Long id,
        String ragioneSociale,
        String paese,
        BigDecimal fatturatoAnnuo,
        Long numeroClienti
) {
}
