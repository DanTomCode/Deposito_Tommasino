package it.corso.products.model;

import java.math.BigDecimal;

public record Product(
        Long id,
        String nome,
        BigDecimal prezzoUnitario,
        Integer quantitaDisponibile
) {
}
