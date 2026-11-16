package it.esercizio.gestioneconcessionari.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
 * Entità persistita nella tabella CASA_MADRE.
 * La lista dei concessionari è il lato inverso: la chiave esterna è gestita da Concessionario.
 */
@Entity
@Table(name = "CASA_MADRE")
@Getter
@Setter
@NoArgsConstructor
public class CasaMadre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "ragione_sociale", nullable = false, length = 100)
    private String ragioneSociale;

    @NotBlank
    @Column(name = "sede_legale", nullable = false, length = 100)
    private String sedeLegale;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String paese;

    @Column(name = "anno_fondazione")
    private Integer annoFondazione;

    @Column(name = "fatturato_annuo", precision = 15, scale = 2)
    private BigDecimal fatturatoAnnuo;

    // mappedBy indica che la relazione è posseduta da Concessionario.casaMadre.
    // Ignorare la lista evita ricorsioni nella serializzazione delle relazioni bidirezionali.
    @JsonIgnore
    @OneToMany(mappedBy = "casaMadre")
    private List<Concessionario> concessionari = new ArrayList<>();
}
