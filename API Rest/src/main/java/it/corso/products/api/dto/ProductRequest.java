package it.corso.products.api.dto;

import it.corso.products.model.Product;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank @Size(max = 150) String nome,
        @NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 8, fraction = 2)
        BigDecimal prezzoUnitario,
        @NotNull @PositiveOrZero Integer quantitaDisponibile
) {

    public Product toModel() {
        return new Product(null, nome.trim(), prezzoUnitario, quantitaDisponibile);
    }
}
