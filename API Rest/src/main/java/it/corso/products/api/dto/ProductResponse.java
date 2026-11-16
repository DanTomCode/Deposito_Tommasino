package it.corso.products.api.dto;

import it.corso.products.model.Product;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String nome,
        BigDecimal prezzoUnitario,
        Integer quantitaDisponibile
) {

    public static ProductResponse fromModel(Product product) {
        return new ProductResponse(
                product.id(),
                product.nome(),
                product.prezzoUnitario(),
                product.quantitaDisponibile()
        );
    }
}
