package it.esercizio.gestioneconcessionari.controller;

import it.esercizio.gestioneconcessionari.dto.ClienteResponseDTO;
import it.esercizio.gestioneconcessionari.dto.ClienteRequestDTO;
import it.esercizio.gestioneconcessionari.service.ClienteService;
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

import java.util.List;
import java.time.LocalDate;

/**
 * Espone le operazioni REST sui clienti. Il controller gestisce HTTP e delega le regole al service.
 */
@RestController
@RequestMapping("/api/clienti")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crea(@Valid @RequestBody ClienteRequestDTO request) {
        // @Valid applica i vincoli del DTO prima di chiamare il service; la creazione risponde 201.
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.crea(request));
    }

    @GetMapping
    public List<ClienteResponseDTO> trovaTutte() {
        return clienteService.trovaTutti();
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO trovaPerId(@PathVariable Long id) {
        return clienteService.trovaPerId(id);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO aggiorna(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO request
    ) {
        return clienteService.aggiorna(id, request);
    }

    @GetMapping("/concessionario/{id}")
    public List<ClienteResponseDTO> trovaPerConcessionario(@PathVariable Long id) {
        return clienteService.trovaPerConcessionario(id);
    }

    @GetMapping("/query/concessionario/{id}")
    public List<ClienteResponseDTO> trovaPerConcessionarioNameConvention(@PathVariable Long id) {
        return clienteService.trovaPerConcessionarioNameConvention(id);
    }

    @GetMapping("/casa-madre/{id}")
    public List<ClienteResponseDTO> trovaPerCasaMadre(@PathVariable Long id) {
        return clienteService.trovaPerCasaMadre(id);
    }

    @GetMapping("/citta/{citta}")
    public List<ClienteResponseDTO> trovaPerCitta(@PathVariable String citta) {
        return clienteService.trovaPerCittaConcessionario(citta);
    }

    @GetMapping("/paese/{paese}")
    public List<ClienteResponseDTO> trovaPerPaese(@PathVariable String paese) {
        return clienteService.trovaPerPaeseCasaMadre(paese);
    }

    @GetMapping("/search")
    public List<ClienteResponseDTO> cerca(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cognome
    ) {
        return clienteService.cerca(nome, cognome);
    }

    @GetMapping("/query/nome")
    public List<ClienteResponseDTO> trovaPerNome(@RequestParam String nome) {
        return clienteService.trovaPerNome(nome);
    }

    @GetMapping("/query/cognome")
    public List<ClienteResponseDTO> trovaPerCognome(@RequestParam String cognome) {
        return clienteService.trovaPerCognome(cognome);
    }

    @GetMapping("/query/email")
    public ClienteResponseDTO trovaPerEmail(@RequestParam String email) {
        return clienteService.trovaPerEmail(email);
    }

    @GetMapping("/query/nome-contiene")
    public List<ClienteResponseDTO> trovaNomeContenente(@RequestParam String sequenza) {
        return clienteService.trovaNomeContenente(sequenza);
    }

    @GetMapping("/query/cognome-inizia")
    public List<ClienteResponseDTO> trovaCognomeConPrefisso(@RequestParam String prefisso) {
        return clienteService.trovaCognomeConPrefisso(prefisso);
    }

    @GetMapping("/query/registrati-dopo")
    public List<ClienteResponseDTO> trovaRegistratiDopo(@RequestParam LocalDate data) {
        return clienteService.trovaRegistratiDopo(data);
    }

    @GetMapping("/query/registrati-tra")
    public List<ClienteResponseDTO> trovaRegistratiTra(
            @RequestParam LocalDate inizio,
            @RequestParam LocalDate fine
    ) {
        return clienteService.trovaRegistratiTra(inizio, fine);
    }

    @GetMapping("/query/nome-cognome")
    public List<ClienteResponseDTO> trovaPerNomeECognome(
            @RequestParam String nome,
            @RequestParam String cognome
    ) {
        return clienteService.trovaPerNomeECognome(nome, cognome);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        clienteService.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
