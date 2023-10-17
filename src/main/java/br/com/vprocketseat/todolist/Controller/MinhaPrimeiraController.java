package br.com.vprocketseat.todolist.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primeiraRota")
//http://localhost:8080/primeiraRota ....
public class MinhaPrimeiraController {

    /**
     * Métodos de acesso do HTTP
     * GET - Buscar uma informação
     * POST - Adicionar dado/informação
     * PUT - Alterar um dado/info
     * DELETE - remover um dado
     * PATCH - Alterar somente uma parte da info/dado
     * 
     */

    // Método (funcionalidade) de uma classe
    @GetMapping("/")
    public String primeiraMensagem() {
        return "Funcionou";
    }
}
