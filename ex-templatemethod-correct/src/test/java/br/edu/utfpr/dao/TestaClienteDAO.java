package br.edu.utfpr.dao;

import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.dto.PaisDTO;
import br.edu.utfpr.excecao.NomeClienteMenor5Caracteres;
import br.edu.utfpr.excecao.NomePaisNulo;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TestaClienteDAO {

    private static ClienteDAO clienteDao;

    @BeforeClass
    public static void setup() {
        clienteDao = new ClienteDAO();
    }

    @Test
    public void testaInserir() throws NomePaisNulo, NomeClienteMenor5Caracteres {
        ClienteDTO cliente = new ClienteDTO();
        PaisDTO pais = new PaisDTO();

        pais.setId(1);
        pais.setNome("Brasil");
        pais.setSigla("BRA");
        pais.setCodTelefone(55);

        cliente.setNome("Rodrigo");
        cliente.setIdade(22);
        cliente.setTelefone("14997554755");
        cliente.setLimiteCredito(500);
        cliente.setPais(pais);

        assertTrue(clienteDao.insertCliente(cliente));

    }

    @Test
    public void testaListCliente() {
        assertTrue(clienteDao.getListCliente().size() > 0);
    }
}
