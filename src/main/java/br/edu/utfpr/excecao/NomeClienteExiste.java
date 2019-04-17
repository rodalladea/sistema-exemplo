package br.edu.utfpr.excecao;

public class NomeClienteExiste extends Exception {
    public NomeClienteExiste(String msg) {
        super(msg);
    }
}
