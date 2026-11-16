package it.esercizio.gestioneconcessionari.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Entità persistita nella tabella CONCESSIONARIO.
 * Possiede la relazione con CasaMadre perché contiene la chiave esterna casa_madre_id.
 */
@Entity
@Table(name = "CONCESSIONARIO")
@Getter
@Setter
@NoArgsConstructor
public class Concessionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String citta;

    @Column(length = 150)
    private String indirizzo;

    @Column(length = 20)
    private String telefono;

    @Column(precision = 15, scale = 2)
    private BigDecimal fatturato;

    // Il concessionario deve riferirsi a una casa madre; LAZY carica la relazione solo se serve.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "casa_madre_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CasaMadre casaMadre;

    // L'elenco clienti è il lato inverso: la FK concessionario_id si trova nella tabella CLIENTE.
    // Non serializzarlo evita il ciclo concessionario -> clienti -> concessionario.
    @JsonIgnore
    @OneToMany(mappedBy = "concessionario")
    private List<Cliente> clienti = new ArrayList<>();
}
