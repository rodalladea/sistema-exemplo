package br.edu.utfpr.dto;

import br.edu.utfpr.excecao.IdadeInvalida;
import br.edu.utfpr.excecao.NomeClienteMenor5Caracteres;
import lombok.Data;

@Data
public class ClienteDTO {
    private int id;
    private String nome;
    private int idade;
    private String telefone;
    private double limiteCredito;
    private PaisDTO pais;

    public void setNome(String nome) throws NomeClienteMenor5Caracteres {
        if (nome.length() < 5)
            throw new NomeClienteMenor5Caracteres("Nome precisa ter mais que 5 caracteres");

        this.nome = nome;
    }

    public void validateLimiteCredito() throws IdadeInvalida {
        if (this.idade > 0 && this.idade <= 18) {
            this.limiteCredito = 100;
        } else if (this.idade > 18 && this.idade < 35) {
            this.limiteCredito = 300;
        } else if (this.idade >= 35) {
            this.limiteCredito = 500;
        } else {
            throw new IdadeInvalida(String.valueOf(this.idade));
        }

        if (this.pais.getNome().toLowerCase().equals("brasil")) {
            this.limiteCredito += 100;
        }
    }
}
