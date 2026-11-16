package it.esercizio.gestioneconcessionari.dto;

import java.math.BigDecimal;

public record CasaMadreStatisticheDTO(
        Long id,
        String ragioneSociale,
        String paese,
        BigDecimal fatturatoAnnuo,
        Long numeroConcessionari,
        Long numeroClienti,
        BigDecimal fatturatoTotaleConcessionari
) {
}
