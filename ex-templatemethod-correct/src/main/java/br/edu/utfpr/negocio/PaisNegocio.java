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

        paisDao.insert(pais);
    }

    public List<PaisDTO> listPais() {
        return paisDao.getList();
    }

    public void atualizaPais(PaisDTO pais) { paisDao.update(pais); }

    public void excluiPais(int id) { paisDao.remove(id); }

    public PaisDTO paisPorID(int id) { return paisDao.getById(id); }

    public PaisDTO paisPorNome(String nome) { return paisDao.getByNome(nome); }

}
