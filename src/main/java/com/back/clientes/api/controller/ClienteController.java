package com.back.clientes.api.controller;

import com.back.clientes.api.converter.ClienteToDto;
import com.back.clientes.api.model.ClienteDto;
import com.back.clientes.domain.model.Cliente;
import com.back.clientes.domain.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteToDto clienteToDto;


    @GetMapping
    public List<ClienteDto> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{clienteId}")
    public ClienteDto buscar(@PathVariable UUID clienteId) {
        Cliente cliente = clienteService.buscarOuFalhar(clienteId);
        return  clienteToDto.converter(cliente);
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable UUID clienteId) {
        clienteService.excluir(clienteId);
    }
}
