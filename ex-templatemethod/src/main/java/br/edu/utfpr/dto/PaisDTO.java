package br.edu.utfpr.dto;

import br.edu.utfpr.excecao.NomePaisNulo;
import lombok.Data;

@Data
public class PaisDTO {
    private int id;
    private String nome;
    private String sigla;
    private int codTelefone;

    public void setNome(String nome) throws NomePaisNulo {
        if(nome.isEmpty())
            throw new NomePaisNulo(nome);

        this.nome = nome;
    }

}
