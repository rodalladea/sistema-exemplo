package br.edu.utfpr.negocio;

import br.edu.utfpr.dao.ClienteDAO;
import br.edu.utfpr.dao.PaisDAO;
import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.excecao.IdadeInvalida;
import br.edu.utfpr.excecao.NomeClienteExiste;

import java.util.List;

public class ClienteNegocio {

    ClienteDAO clienteDao = new ClienteDAO();

    public void inserirCliente(ClienteDTO cliente) throws NomeClienteExiste, IdadeInvalida {
        PaisDAO paisDao = new PaisDAO();

        if (this.listCliente().stream().anyMatch(c -> c.getNome().equalsIgnoreCase(cliente.getNome())))
            throw new NomeClienteExiste(cliente.getNome());


        cliente.validateLimiteCredito();

        clienteDao.insertCliente(cliente);
    }

    public List<ClienteDTO> listCliente() {
        return clienteDao.getListCliente();
    }

    public void atualizaCliente(ClienteDTO cliente) { clienteDao.updateCliente(cliente); }

    public void excluiCliente(int id) { clienteDao.removeCliente(id); }

    public ClienteDTO clientePorID(int id) { return clienteDao.getClienteById(id); }

    public ClienteDTO clientePorNome(String nome) { return clienteDao.getClienteByNome(nome); }
}
