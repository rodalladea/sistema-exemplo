/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.apresentacao;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author rodrigo
 */
@Controller
public class ClienteController {
    
    @GetMapping("/cliente")
    public String inicial(Model data) throws JsonSyntaxException, UnirestException {

        ClienteModelExibe arrayClientes[] = new Gson()
                    .fromJson(
                        Unirest
                            .get("http://localhost:8081/servico/cliente")
                            .asJson()
                            .getBody()
                            .toString(), 
                        ClienteModelExibe[].class
                    );
        
        PaisModel arrayPaises[] = new Gson()
                    .fromJson(
                        Unirest
                            .get("http://localhost:8081/servico/pais")
                            .asJson()
                            .getBody()
                            .toString(),
                        PaisModel[].class
                    );

        data.addAttribute("clientes", arrayClientes);
        data.addAttribute("paises", arrayPaises);

        return "cliente-view";
    }

    @PostMapping ("/cliente/criar")
    public String criar(ClienteModel cliente) throws UnirestException {
        
        Unirest.post("http://localhost:8081/servico/cliente")
            .header("Content-type", "application/json")
            .header("accept", "application/json")
            .body(new Gson().toJson(cliente, ClienteModel.class))
            .asJson();

        return "redirect:/cliente";
    }

    @GetMapping ("/cliente/excluir")
    public String excluir (@RequestParam int id) throws UnirestException {

        Unirest
            .delete("http://localhost:8081/servico/cliente/{id}")
            .routeParam("id", String.valueOf(id))
            .asJson();

        return "redirect:/cliente";
    }

    @GetMapping ("/cliente/prepara-alterar")
    public String preparaAlterar (@RequestParam Long id, Model data) throws JsonSyntaxException, UnirestException {

        ClienteModelExibe clienteExistente = new Gson()
            .fromJson(
                Unirest
                    .get("http://localhost:8081/servico/cliente/{id}")
                    .routeParam("id", String.valueOf(id))
                    .asJson()
                    .getBody()
                    .toString(),
                ClienteModelExibe.class
            );

        data.addAttribute("clienteAtual", clienteExistente);

        ClienteModelExibe arrayClientes[] = new Gson()
        .fromJson(
            Unirest
                .get("http://localhost:8081/servico/cliente")
                .asJson()
                .getBody()
                .toString(), 
            ClienteModelExibe[].class
        );

        data.addAttribute("clientes", arrayClientes);
        
        PaisModel arrayPaises[] = new Gson()
                    .fromJson(
                        Unirest
                            .get("http://localhost:8081/servico/pais")
                            .asJson()
                            .getBody()
                            .toString(),
                        PaisModel[].class
                    );
        
        data.addAttribute("paises", arrayPaises);
        
        return "cliente-view-alterar";
    }

    @PostMapping ("/cliente/alterar")
    public String alterar (ClienteModel clienteAlterado) throws UnirestException {

        Unirest
            .put("http://localhost:8081/servico/cliente/{id}")
            .routeParam("id", String.valueOf(clienteAlterado.getId()))
            .header("Content-type", "application/json")
            .header("accept", "application/json")
            .body(new Gson().toJson(clienteAlterado, ClienteModel.class))
            .asJson();

        return "redirect:/cliente";
    }
}
