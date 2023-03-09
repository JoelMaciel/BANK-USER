package com.back.clientes.api.controller;

import com.back.clientes.api.model.ClienteDto;
import com.back.clientes.api.model.converter.ClienteInputToDomain;
import com.back.clientes.api.model.converter.ClienteInputUpdatePasswordToDomain;
import com.back.clientes.api.model.converter.ClienteInputUpdateToDomain;
import com.back.clientes.api.model.converter.ClienteToDto;
import com.back.clientes.api.model.input.ClienteInputUpdate;
import com.back.clientes.api.model.input.ClienteInputUpdatePassword;
import com.back.clientes.domain.model.Cliente;
import com.back.clientes.domain.services.ClienteService;
import com.back.clientes.infrastructure.specification.SpecificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteToDto clienteToDto;

    @Autowired
    private ClienteInputToDomain clienteInputToDomain;

    @Autowired
    private ClienteInputUpdateToDomain clienteInputUpdateToDomain;

    @Autowired
    private ClienteInputUpdatePasswordToDomain clienteUpdatePasswordToDomain;


    @GetMapping
    public Page<ClienteDto> listar(SpecificationTemplate.ClienteSpec spec,
        @PageableDefault(page = 0, size = 10, sort = "clienteId", direction = Sort.Direction.ASC) Pageable pageable) {
        return clienteService.findAll(spec, pageable);
    }

    @GetMapping("/{clienteId}")
    public ClienteDto buscar(@PathVariable UUID clienteId) {
        Cliente cliente = clienteService.buscarOuFalhar(clienteId);
        return  clienteToDto.converter(cliente);
    }

    @PutMapping("/{clienteId}")
    public ClienteDto atualizar(@PathVariable UUID clienteId ,
                                @RequestBody @Valid ClienteInputUpdate clienteUpdate) {
        Cliente clienteAtual = clienteService.buscarOuFalhar(clienteId);

        clienteInputUpdateToDomain.copyToDomainObject(clienteUpdate, clienteAtual);
        clienteAtual.setDataAtualizacao(OffsetDateTime.now());
        var novoCliente = clienteService.salvar(clienteAtual);

        return clienteToDto.converter(novoCliente);
    }

    @PutMapping("/{clienteId}/password")
    public ResponseEntity<Object> updatePassword(@PathVariable UUID clienteId ,
                                                 @RequestBody @Valid ClienteInputUpdatePassword clienteUpdate) {
        Cliente cliente = clienteService.buscarOuFalhar(clienteId);

        if(!cliente.getSenha().equals(clienteUpdate.getSenhaAntiga())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Mismatched old password");
        }
        clienteUpdatePasswordToDomain.copyToDomainObject(clienteUpdate, cliente);
        cliente.setDataAtualizacao(OffsetDateTime.now());
        clienteService.salvar(cliente);

        return ResponseEntity.status(HttpStatus.OK).body(clienteToDto.converter(cliente)) ;

    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable UUID clienteId) {
        clienteService.excluir(clienteId);
    }
}
