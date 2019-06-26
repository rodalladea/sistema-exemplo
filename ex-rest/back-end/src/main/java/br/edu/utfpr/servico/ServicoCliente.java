/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.servico;

import br.edu.utfpr.dao.Cliente;
import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.excecao.NomeClienteJaExisteException;
import br.edu.utfpr.negocio.ClienteNegocio;
import br.edu.utfpr.negocio.PaisNegocio;
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
    
    private final ClienteNegocio cliNeg;
    private final PaisNegocio paisNeg;
    
    @Autowired
    public ServicoCliente(ClienteNegocio cliNeg, PaisNegocio paisNeg) {
        this.cliNeg = cliNeg;
        this.paisNeg = paisNeg;
    }

    @GetMapping ("/servico/cliente")
    public ResponseEntity<List<Cliente>> listar() {
    // public List<PaisDTO> listar() {
        // return paises;
        return ResponseEntity.ok(this.cliNeg.listar());
    }

    @GetMapping ("/servico/cliente/{id}")
    public ResponseEntity<Cliente> listarPorId(@PathVariable Long id) {
        Optional<Cliente> clienteEncontrado = this.cliNeg.getById(id);

        return ResponseEntity.of(clienteEncontrado);
    }

    @PostMapping ("/servico/cliente")
    public ResponseEntity<ClienteDTO> criar (@RequestBody ClienteDTO clienteDTO) throws NomeClienteJaExisteException {
            
        this.cliNeg.incluir(clienteDTO);
        return ResponseEntity.status(201).body(clienteDTO);

    }

    @DeleteMapping ("/servico/cliente/{id}")
    public ResponseEntity excluir (@PathVariable Long id) {
        
        if (this.cliNeg.excluir(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping ("/servico/cliente/{id}")
    public ResponseEntity alterar (@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) throws NomeClienteJaExisteException {
        
        this.cliNeg.incluir(clienteDTO);
        
        return ResponseEntity.noContent().build();
    }
}
