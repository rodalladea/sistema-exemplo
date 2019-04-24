package br.edu.utfpr.negocio;

import br.edu.utfpr.dao.PaisDAO;
import br.edu.utfpr.dto.PaisDTO;
import br.edu.utfpr.excecao.NomePaisExiste;

import java.util.List;

public class PaisNegocio {

    private PaisDAO paisDao = new PaisDAO();

    public void inserirPais(PaisDTO pais) throws NomePaisExiste {

        if (this.listPais().stream().anyMatch(c -> c.getNome().equalsIgnoreCase(pais.getNome())))
            throw new NomePaisExiste(pais.getNome());

        paisDao.insertPais(pais);
    }

    public List<PaisDTO> listPais() {
        return paisDao.getListPais();
    }

}
