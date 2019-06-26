package br.edu.utfpr.negocio;

import br.edu.utfpr.dao.Pais;
import br.edu.utfpr.dao.PaisDAO;
import java.util.List;

import br.edu.utfpr.dto.PaisDTO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaisNegocio {

    private final PaisDAO paisDAO;

    @Autowired
    public PaisNegocio(PaisDAO paisDAO) {
        this.paisDAO = paisDAO;
    }
    
    public Pais getPais(PaisDTO paisDTO) {
        Pais pais = new Pais();
        
        pais.setId(paisDTO.getId());
        pais.setNome(paisDTO.getNome());
        pais.setSigla(paisDTO.getSigla());
        pais.setCodigoTelefone(paisDTO.getCodigoTelefone());
        
        return pais;
    }

    public void incluir(PaisDTO pais) {

        this.paisDAO.save(getPais(pais));

    }
    
    public boolean excluir(Long id) {
        Pais cliente = this.paisDAO.findAll().stream().filter(e -> e.getId().equals(id)).findAny().get();
        
        if (cliente != null) {
            this.paisDAO.delete(cliente);
            return true;
        } else {
            return false;
        }
    }

    public List<Pais> listar() {
        
        return this.paisDAO.findAll();
    }
    
    public Optional<Pais> getById(Long id) {
        return this.paisDAO.findById(id);
    }

}