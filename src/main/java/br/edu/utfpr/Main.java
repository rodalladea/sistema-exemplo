package br.edu.utfpr;

import br.edu.utfpr.dao.ClienteDAO;
import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.excecao.NomeClienteMenor5Caracteres;

public class Main {

    public static void main(String[] args) {
        ClienteDAO cli = new ClienteDAO();


        System.out.println(cli.getListCliente());


    }
}
