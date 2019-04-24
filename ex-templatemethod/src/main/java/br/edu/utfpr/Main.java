package br.edu.utfpr;


import br.edu.utfpr.dao.ClienteDAO;
import br.edu.utfpr.dao.PaisDAO;
import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.dto.PaisDTO;
import br.edu.utfpr.excecao.*;
import br.edu.utfpr.negocio.ClienteNegocio;
import br.edu.utfpr.negocio.PaisNegocio;

public class Main {

    public static void main(String[] args) {
        PaisDAO paisDao = new PaisDAO();
        ClienteDAO cliDao = new ClienteDAO();
        ClienteDTO cliente = new ClienteDTO();
        PaisDTO pais = new PaisDTO();
        ClienteNegocio cliNeg = new ClienteNegocio();
        PaisNegocio paisNeg = new PaisNegocio();



        try {
            pais.setNome("Estados Unidos da America");
            pais.setSigla("EUA");
            pais.setCodTelefone(10);

            paisNeg.inserirPais(pais);
        } catch (NomePaisExiste nomePaisExiste) {
          nomePaisExiste.printStackTrace();
        } catch (NomePaisNulo nomePaisNulo) {
            nomePaisNulo.printStackTrace();
        }


        try {
            cliente.setNome("Rodrigo");
            cliente.setIdade(22);
            cliente.setTelefone("1014997554755");
            cliente.setPais(paisDao.getPaisByNome("Estados Unidos da America"));
            cliente.validateLimiteCredito();

            cliNeg.inserirCliente(cliente);

        } catch (NomeClienteMenor5Caracteres nomeClienteMenor5Caracteres) {
            nomeClienteMenor5Caracteres.printStackTrace();
        } catch (NomeClienteExiste nomeClienteExiste) {
            nomeClienteExiste.printStackTrace();
        } catch (IdadeInvalida idadeInvalida) {
            idadeInvalida.printStackTrace();
        }

        System.out.println(cliDao.getListCliente());
        System.out.println(paisDao.getListPais());
    }
}
