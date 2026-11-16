package it.esercizio.gestioneconcessionari.controller;

import it.esercizio.gestioneconcessionari.dto.CasaMadreClientiResponseDTO;
import it.esercizio.gestioneconcessionari.dto.CasaMadreResponseDTO;
import it.esercizio.gestioneconcessionari.dto.CasaMadreRequestDTO;
import it.esercizio.gestioneconcessionari.service.CasaMadreService;
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

/** Endpoint REST CRUD, query e statistiche per le case madri. */
@RestController
@RequestMapping("/api/case-madri")
public class CasaMadreController {

    private final CasaMadreService casaMadreService;

    public CasaMadreController(CasaMadreService casaMadreService) {
        this.casaMadreService = casaMadreService;
    }

    @PostMapping
    public ResponseEntity<CasaMadreResponseDTO> crea(@Valid @RequestBody CasaMadreRequestDTO request) {
        // @Valid controlla il DTO; in caso positivo il service salva e restituisce il DTO di risposta.
        return ResponseEntity.status(HttpStatus.CREATED).body(casaMadreService.crea(request));
    }

    @GetMapping
    public List<CasaMadreResponseDTO> trovaTutte() {
        return casaMadreService.trovaTutte();
    }

    @GetMapping("/{id}")
    public CasaMadreResponseDTO trovaPerId(@PathVariable Long id) {
        return casaMadreService.trovaPerId(id);
    }

    @PutMapping("/{id}")
    public CasaMadreResponseDTO aggiorna(
            @PathVariable Long id,
            @Valid @RequestBody CasaMadreRequestDTO request
    ) {
        return casaMadreService.aggiorna(id, request);
    }

    @GetMapping("/statistiche/concessionari")
    public List<CasaMadreResponseDTO> trovaStatisticheConcessionari() {
        return casaMadreService.trovaStatisticheConcessionari();
    }

    @GetMapping("/statistiche/clienti")
    public List<CasaMadreClientiResponseDTO> trovaStatisticheClienti() {
        return casaMadreService.trovaStatisticheClienti();
    }

    @GetMapping("/statistiche/fatturato")
    public List<CasaMadreResponseDTO> trovaStatisticheFatturato() {
        return casaMadreService.trovaStatisticheFatturato();
    }

    @GetMapping("/senza-concessionari")
    public List<CasaMadreResponseDTO> trovaSenzaConcessionari() {
        return casaMadreService.trovaSenzaConcessionari();
    }

    @GetMapping("/sopra-media")
    public List<CasaMadreResponseDTO> trovaSopraMedia() {
        return casaMadreService.trovaSopraMedia();
    }

    @GetMapping("/query/ragione-sociale")
    public CasaMadreResponseDTO trovaPerRagioneSociale(@RequestParam String ragioneSociale) {
        return casaMadreService.trovaPerRagioneSociale(ragioneSociale);
    }

    @GetMapping("/query/paese")
    public List<CasaMadreResponseDTO> trovaPerPaese(@RequestParam String paese) {
        return casaMadreService.trovaPerPaese(paese);
    }

    @GetMapping("/query/paese-ignore-case")
    public List<CasaMadreResponseDTO> trovaPerPaeseIgnoreCase(@RequestParam String paese) {
        return casaMadreService.trovaPerPaeseIgnoreCase(paese);
    }

    @GetMapping("/query/fondate-prima")
    public List<CasaMadreResponseDTO> trovaFondatePrimaDi(@RequestParam Integer anno) {
        return casaMadreService.trovaFondatePrimaDi(anno);
    }

    @GetMapping("/query/fatturato-superiore")
    public List<CasaMadreResponseDTO> trovaConFatturatoMaggioreDi(@RequestParam BigDecimal importo) {
        return casaMadreService.trovaConFatturatoMaggioreDi(importo);
    }

    @GetMapping("/query/ragione-sociale-contiene")
    public List<CasaMadreResponseDTO> trovaRagioneSocialeContenente(@RequestParam String sequenza) {
        return casaMadreService.trovaRagioneSocialeContenente(sequenza);
    }

    @GetMapping("/query/paese-fatturato")
    public List<CasaMadreResponseDTO> trovaPaeseEConFatturatoMaggioreDi(
            @RequestParam String paese,
            @RequestParam BigDecimal fatturatoMinimo
    ) {
        return casaMadreService.trovaPaeseEConFatturatoMaggioreDi(paese, fatturatoMinimo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        casaMadreService.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
