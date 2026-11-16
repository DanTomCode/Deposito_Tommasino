package it.corso.products.persistence;

import it.corso.products.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "prodotti")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(name = "prezzo_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzoUnitario;

    @Column(name = "quantita_disponibile", nullable = false)
    private Integer quantitaDisponibile;

    protected ProductEntity() {
    }

    private ProductEntity(String nome, BigDecimal prezzoUnitario, Integer quantitaDisponibile) {
        this.nome = nome;
        this.prezzoUnitario = prezzoUnitario;
        this.quantitaDisponibile = quantitaDisponibile;
    }

    public static ProductEntity fromModel(Product product) {
        return new ProductEntity(
                product.nome(),
                product.prezzoUnitario(),
                product.quantitaDisponibile()
        );
    }

    public Product toModel() {
        return new Product(id, nome, prezzoUnitario, quantitaDisponibile);
    }
}
