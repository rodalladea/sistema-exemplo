package br.edu.utfpr.negocio;

import br.edu.utfpr.dao.ClienteDAO;
import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.excecao.IdadeInvalida;
import br.edu.utfpr.excecao.NomeClienteExiste;

import java.util.List;

public class ClienteNegocio {

    ClienteDAO clienteDao = new ClienteDAO();

    public void inserirCliente(ClienteDTO cliente) throws NomeClienteExiste, IdadeInvalida {

        if (this.listCliente().stream().anyMatch(c -> c.getNome().equalsIgnoreCase(cliente.getNome())))
            throw new NomeClienteExiste(cliente.getNome());

        cliente.validateLimiteCredito();

        clienteDao.insertCliente(cliente);
    }

    public List<ClienteDTO> listCliente() {

        return clienteDao.getListCliente();
    }
}
