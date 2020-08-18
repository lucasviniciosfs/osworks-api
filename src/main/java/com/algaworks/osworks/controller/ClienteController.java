package com.algaworks.osworks.controller;

import com.algaworks.osworks.model.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        var cliente1 = new Cliente();
        var cliente2 = new Cliente();
        
        cliente1.setId(1L);
        cliente1.setEmail("lucas@email.com");
        cliente1.setNome("Lucas");
        cliente1.setTelefone("62983122871");

        cliente2.setId(2L);
        cliente2.setEmail("lucas2@email.com");
        cliente2.setNome("Lucas2");
        cliente2.setTelefone("62983122871");
        
        return Arrays.asList(cliente1,cliente2);
    }
}
