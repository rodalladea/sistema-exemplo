package br.edu.utfpr.servico;

import br.edu.utfpr.dao.Pais;
import br.edu.utfpr.dao.PaisDAO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.dto.PaisDTO;
import io.micrometer.core.ipc.http.HttpSender.Response;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ServicoPais {
    
    private final PaisDAO paisDAO;

    @Autowired
    public ServicoPais(PaisDAO paisDAO) {
        this.paisDAO = paisDAO;
    }

    @GetMapping ("/servico/pais")
    public ResponseEntity<List<Pais>> listar() {
    // public List<PaisDTO> listar() {
        // return paises;
        return ResponseEntity.ok(this.paisDAO.findAll());
    }

    @GetMapping ("/servico/pais/{id}")
    public ResponseEntity<Pais> listarPorId(@PathVariable Long id) {
        Optional<Pais> paisEncontrado = this.paisDAO.findById(id);

        return ResponseEntity.of(paisEncontrado);
    }

    @PostMapping ("/servico/pais")
    public ResponseEntity<Pais> criar (@RequestBody Pais pais) {
        if (this.paisDAO.findAll().stream().map(Pais::getNome).anyMatch(e -> e.equals(pais.getNome()))) { 
            return ResponseEntity.noContent().build();
        } else {
            this.paisDAO.save(pais);
            return ResponseEntity.status(201).body(pais);
        }
        
//        this.paisDAO.save(pais);
//
//        return ResponseEntity.status(201).body(pais);
    }

    @DeleteMapping ("/servico/pais/{id}")
    public ResponseEntity excluir (@PathVariable Long id) {
        
        Pais p = this.paisDAO.findAll().stream().filter(e -> e.getId().equals(id)).findAny().get();
        
        if (p != null) {
            this.paisDAO.delete(p);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping ("/servico/pais/{id}")
    public ResponseEntity<Pais> alterar (@PathVariable int id, @RequestBody Pais pais) {
        Optional<Pais> paisExistente = this.paisDAO.findAll().stream().filter(p -> p.getId() == id).findAny();
        
        this.paisDAO.save(pais);
        
        return ResponseEntity.of(paisExistente);
        

//        paisExistente.ifPresent(p -> {
//            p.setNome(pais.getNome());
//            p.setSigla(pais.getSigla());
//            p.setCodigoTelefone(pais.getCodigoTelefone());
//        });

        
    }
}