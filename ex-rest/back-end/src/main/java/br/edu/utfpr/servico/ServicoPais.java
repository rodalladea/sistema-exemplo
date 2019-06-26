package br.edu.utfpr.servico;

import br.edu.utfpr.dao.Pais;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.dto.PaisDTO;
import br.edu.utfpr.negocio.PaisNegocio;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ServicoPais {
    
    private final PaisNegocio paisNeg;
    
    @Autowired
    public ServicoPais(PaisNegocio paisNeg) {
        this.paisNeg = paisNeg;
    }

    @GetMapping ("/servico/pais")
    public ResponseEntity<List<Pais>> listar() {
    // public List<PaisDTO> listar() {
        // return paises;
        return ResponseEntity.ok(this.paisNeg.listar());
    }

    @GetMapping ("/servico/pais/{id}")
    public ResponseEntity<Pais> listarPorId(@PathVariable Long id) {
        Optional<Pais> paisEncontrado = this.paisNeg.getById(id);

        return ResponseEntity.of(paisEncontrado);
    }

    @PostMapping ("/servico/pais")
    public ResponseEntity<PaisDTO> criar (@RequestBody PaisDTO paisDTO) {
        if (this.paisNeg.listar().stream().map(Pais::getNome).anyMatch(e -> e.equals(paisDTO.getNome()))) { 
            return ResponseEntity.noContent().build();
        } else {
            this.paisNeg.incluir(paisDTO);
            return ResponseEntity.status(201).body(paisDTO);
        }

    }

    @DeleteMapping ("/servico/pais/{id}")
    public ResponseEntity excluir (@PathVariable Long id) {
        
        Pais p = this.paisNeg.listar().stream().filter(e -> e.getId().equals(id)).findAny().get();
        
        if (this.paisNeg.excluir(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping ("/servico/pais/{id}")
    public ResponseEntity alterar (@PathVariable int id, @RequestBody PaisDTO paisDTO) {
        
        this.paisNeg.incluir(paisDTO);
        
        return ResponseEntity.noContent().build();
    }
}