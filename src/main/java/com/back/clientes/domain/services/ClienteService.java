package com.back.clientes.domain.services;

import com.back.clientes.api.model.ClienteDto;
import com.back.clientes.domain.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
    List<ClienteDto> listar();

    public void excluir(UUID clienteId);

    public Cliente buscarOuFalhar(UUID clienteId) ;

    Cliente salvar(Cliente cliente);

    boolean existeClienteCpf(String cpf);

    boolean existeClienteEmail(String email);

    Page<ClienteDto> findAll(Specification<Cliente> spec, Pageable pageable);
}
