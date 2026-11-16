package it.esercizio.gestioneconcessionari.controller;

import it.esercizio.gestioneconcessionari.dto.ConcessionarioResponseDTO;
import it.esercizio.gestioneconcessionari.dto.ConcessionarioFatturatoMedioResponseDTO;
import it.esercizio.gestioneconcessionari.dto.ConcessionarioRequestDTO;
import it.esercizio.gestioneconcessionari.service.ConcessionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/** Endpoint REST CRUD e di ricerca/statistica per i concessionari. */
@RestController
@RequestMapping("/api/concessionari")
public class ConcessionarioController {

    private final ConcessionarioService concessionarioService;

    public ConcessionarioController(ConcessionarioService concessionarioService) {
        this.concessionarioService = concessionarioService;
    }

    @PostMapping
    public ResponseEntity<ConcessionarioResponseDTO> crea(
            @Valid @RequestBody ConcessionarioRequestDTO request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(concessionarioService.crea(request));
    }

    @GetMapping
    public List<ConcessionarioResponseDTO> trovaTutti() {
        return concessionarioService.trovaTutti();
    }

    @GetMapping("/{id}")
    public ConcessionarioResponseDTO trovaPerId(@PathVariable Long id) {
        return concessionarioService.trovaPerId(id);
    }

    @PutMapping("/{id}")
    public ConcessionarioResponseDTO aggiorna(
            @PathVariable Long id,
            @Valid @RequestBody ConcessionarioRequestDTO request
    ) {
        return concessionarioService.aggiorna(id, request);
    }

    @GetMapping("/casa-madre/{ragioneSociale}")
    public List<ConcessionarioResponseDTO> trovaPerCasaMadre(
            @PathVariable String ragioneSociale
    ) {
        return concessionarioService.trovaPerCasaMadre(ragioneSociale);
    }

    @GetMapping("/fatturato")
    public List<ConcessionarioResponseDTO> cercaPerFatturato(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max,
            @RequestParam(required = false) String citta
    ) {
        // min e max sono obbligatori; citta è opzionale e restringe ulteriormente la ricerca.
        return concessionarioService.cercaPerFatturato(min, max, citta);
    }

    @GetMapping("/sopra-media")
    public List<ConcessionarioResponseDTO> trovaSopraMediaFatturato() {
        return concessionarioService.trovaSopraMediaFatturato();
    }

    @GetMapping("/statistiche/fatturato-medio")
    public ConcessionarioFatturatoMedioResponseDTO trovaFatturatoMedio() {
        return concessionarioService.trovaFatturatoMedio();
    }

    @GetMapping("/con-clienti")
    public List<ConcessionarioResponseDTO> trovaConClienti() {
        return concessionarioService.trovaConClienti();
    }

    @GetMapping("/statistiche/clienti")
    public List<ConcessionarioResponseDTO> trovaStatisticheClienti() {
        return concessionarioService.trovaStatisticheClienti();
    }

    @GetMapping("/statistiche/sopra-media-clienti")
    public List<ConcessionarioResponseDTO> trovaSopraMediaClienti() {
        return concessionarioService.trovaSopraMediaClienti();
    }

    @GetMapping("/query/citta")
    public List<ConcessionarioResponseDTO> trovaPerCitta(@RequestParam String citta) {
        return concessionarioService.trovaPerCitta(citta);
    }

    @GetMapping("/query/nome-contiene")
    public List<ConcessionarioResponseDTO> trovaNomeContenente(@RequestParam String sequenza) {
        return concessionarioService.trovaNomeContenente(sequenza);
    }

    @GetMapping("/query/fatturato-superiore")
    public List<ConcessionarioResponseDTO> trovaConFatturatoMaggioreDi(
            @RequestParam BigDecimal importo
    ) {
        return concessionarioService.trovaConFatturatoMaggioreDi(importo);
    }

    @GetMapping("/query/casa-madre-id/{id}")
    public List<ConcessionarioResponseDTO> trovaPerCasaMadreId(@PathVariable Long id) {
        return concessionarioService.trovaPerCasaMadreId(id);
    }

    @GetMapping("/query/casa-madre-ragione-sociale")
    public List<ConcessionarioResponseDTO> trovaPerCasaMadreRagioneSocialeNameConvention(
            @RequestParam String ragioneSociale
    ) {
        return concessionarioService.trovaPerCasaMadreRagioneSocialeNameConvention(ragioneSociale);
    }

    @GetMapping("/query/citta-fatturato-superiore")
    public List<ConcessionarioResponseDTO> trovaPerCittaEConFatturatoMaggioreDi(
            @RequestParam String citta,
            @RequestParam BigDecimal importo
    ) {
        return concessionarioService.trovaPerCittaEConFatturatoMaggioreDi(citta, importo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        concessionarioService.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
