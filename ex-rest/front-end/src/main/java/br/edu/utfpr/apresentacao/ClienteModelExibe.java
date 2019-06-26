/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.apresentacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author rodrigo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteModelExibe {
    private Long id;
    private String nome;
    private int idade;
    private String telefone;
    private double limiteCredito;
    private PaisModel pais;
}
