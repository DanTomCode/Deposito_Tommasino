package it.esercizio.gestioneconcessionari.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entità persistita nella tabella CLIENTE; concessionario_id è la chiave esterna proprietaria.
 */
@Entity
@Table(name = "CLIENTE")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String nome;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String cognome;

    // Il vincolo unique viene applicato anche dal database, oltre alla validazione dell'indirizzo.
    @Email
    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String telefono;

    @NotNull
    @Column(name = "data_registrazione", nullable = false)
    private LocalDate dataRegistrazione;

    // Ogni cliente deve avere un concessionario esistente; il caricamento della relazione è lazy.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "concessionario_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Concessionario concessionario;
}
