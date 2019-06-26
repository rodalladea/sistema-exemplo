/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.servico;

import br.edu.utfpr.dao.Cliente;
import br.edu.utfpr.dao.ClienteDAO;
import br.edu.utfpr.dao.PaisDAO;
import br.edu.utfpr.dto.ClienteDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rodrigo
 */
@RestController
public class ServicoCliente {
    private final ClienteDAO clienteDAO;
    private final PaisDAO paisDAO;

    @Autowired
    public ServicoCliente(ClienteDAO clienteDAO, PaisDAO paisDAO) {
        this.clienteDAO = clienteDAO;
        this.paisDAO = paisDAO;
    }

    @GetMapping ("/servico/cliente")
    public ResponseEntity<List<Cliente>> listar() {
    // public List<PaisDTO> listar() {
        // return paises;
        return ResponseEntity.ok(this.clienteDAO.findAll());
    }

    @GetMapping ("/servico/cliente/{id}")
    public ResponseEntity<Cliente> listarPorId(@PathVariable Long id) {
        Optional<Cliente> clienteEncontrado = this.clienteDAO.findById(id);

        return ResponseEntity.of(clienteEncontrado);
    }

    @PostMapping ("/servico/cliente")
    public ResponseEntity<Cliente> criar (@RequestBody ClienteDTO clienteDTO) {
        if (this.clienteDAO.findAll().stream().map(Cliente::getNome).anyMatch(e -> e.equals(clienteDTO.getNome()))) { 
            return ResponseEntity.noContent().build();
        } else {
            Cliente cliente = new Cliente();
            
            cliente.setNome(clienteDTO.getNome());
            cliente.setIdade(clienteDTO.getIdade());
            cliente.setTelefone(clienteDTO.getTelefone());
            cliente.setLimiteCredito(clienteDTO.getLimiteCredito());
            cliente.setPais(this.paisDAO.findById(clienteDTO.getPais()).get());
            
            this.clienteDAO.save(cliente);
            return ResponseEntity.status(201).body(cliente);
        }
        
//        this.paisDAO.save(pais);
//
//        return ResponseEntity.status(201).body(pais);
    }

    @DeleteMapping ("/servico/cliente/{id}")
    public ResponseEntity excluir (@PathVariable Long id) {
        
        Cliente cliente = this.clienteDAO.findAll().stream().filter(e -> e.getId().equals(id)).findAny().get();
        
        if (cliente != null) {
            this.clienteDAO.delete(cliente);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping ("/servico/cliente/{id}")
    public ResponseEntity<Cliente> alterar (@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Optional<Cliente> clienteExistente = this.clienteDAO.findAll().stream().filter(c -> c.getId() == id).findAny();
        
        Cliente cliente = new Cliente();
        
        cliente.setId(id);
        cliente.setNome(clienteDTO.getNome());
        cliente.setIdade(clienteDTO.getIdade());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setLimiteCredito(clienteDTO.getLimiteCredito());
        cliente.setPais(this.paisDAO.findById(clienteDTO.getPais()).get());
        
        this.clienteDAO.save(cliente);
        
        return ResponseEntity.of(clienteExistente);


        
    }
}
