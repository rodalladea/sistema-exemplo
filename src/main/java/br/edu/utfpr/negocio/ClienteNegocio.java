package br.edu.utfpr.negocio;

import br.edu.utfpr.dao.ClienteDAO;
import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.excecao.NomeClienteExiste;

import java.util.List;

public class ClienteNegocio {

    public void verifyNomeCliente(ClienteDTO cliente) throws NomeClienteExiste {

        if (this.listCliente().stream().anyMatch(c -> c.getNome().equalsIgnoreCase(cliente.getNome())))
            throw new NomeClienteExiste(cliente.getNome());
    }

    public List<ClienteDTO> listCliente() {
        ClienteDAO clienteDao = new ClienteDAO();

        return clienteDao.getListCliente();
    }
}
