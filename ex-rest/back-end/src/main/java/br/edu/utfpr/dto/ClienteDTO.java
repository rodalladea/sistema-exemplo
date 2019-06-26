package br.edu.utfpr.dto;

import lombok.Builder;
import lombok.Data;
import br.edu.utfpr.excecao.NomeClienteMenor5CaracteresException;

@Data
@Builder
public class ClienteDTO {
    private Long id;
    private String nome;
    private int idade;
    private String telefone;
    private double limiteCredito;
    private Long pais;

    public void setNome(String nome) throws NomeClienteMenor5CaracteresException {
        if (nome.length() < 5)
            throw new NomeClienteMenor5CaracteresException(nome);

        this.nome = nome;
    }
    
}