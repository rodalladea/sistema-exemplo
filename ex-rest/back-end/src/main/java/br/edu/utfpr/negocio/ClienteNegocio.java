package br.edu.utfpr.negocio;

import br.edu.utfpr.dao.Cliente;
import br.edu.utfpr.dao.ClienteDAO;
import br.edu.utfpr.dao.PaisDAO;
import java.util.List;

import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.excecao.NomeClienteJaExisteException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteNegocio {
    
    private final ClienteDAO clienteDAO;
    private final PaisDAO paisDAO;

    @Autowired
    public ClienteNegocio(ClienteDAO clienteDAO, PaisDAO paisDAO) {
        this.clienteDAO = clienteDAO;
        this.paisDAO = paisDAO;
    }
    
    public Cliente getCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setIdade(clienteDTO.getIdade());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setLimiteCredito(clienteDTO.getLimiteCredito());
        cliente.setPais(this.paisDAO.findById(clienteDTO.getPais()).get());
        
        return cliente;
    }

    public void incluir(ClienteDTO cliente) throws NomeClienteJaExisteException {

        if (this.listar().stream().anyMatch(c -> c.getNome().equalsIgnoreCase(cliente.getNome())))
            throw new NomeClienteJaExisteException(cliente.getNome());

        this.clienteDAO.save(getCliente(cliente));

    }
    
    public boolean excluir(Long id) {
        Cliente cliente = this.clienteDAO.findAll().stream().filter(e -> e.getId().equals(id)).findAny().get();
        
        if (cliente != null) {
            this.clienteDAO.delete(cliente);
            return true;
        } else {
            return false;
        }
    }

    public List<Cliente> listar() {
        
        return this.clienteDAO.findAll();
    }
    
    public Optional<Cliente> getById(Long id) {
        return this.clienteDAO.findById(id);
    }
}