package com.back.clientes.domain.services.impl;

import com.back.clientes.api.converter.ClienteToDto;
import com.back.clientes.api.model.ClienteDto;
import com.back.clientes.domain.exception.ClienteNaoEncontrado;
import com.back.clientes.domain.exception.EntidadeEmUsoException;
import com.back.clientes.domain.model.Cliente;
import com.back.clientes.domain.repository.ClienteRepository;
import com.back.clientes.domain.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final String MSG_CLIENTE_EM_USO =
            "Cliente de código %s não pode ser removido, pois está em uso";

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteToDto clienteToDto;

    @Override
    public List<ClienteDto> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return  clienteToDto.toCollectionDto(clientes);
    }

    @Transactional
    @Override
    public void excluir(UUID clienteId) {
        try {
            clienteRepository.deleteById(clienteId);
            clienteRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw  new ClienteNaoEncontrado(clienteId);

        } catch (DataIntegrityViolationException e) {
            throw  new EntidadeEmUsoException(
                    String.format(MSG_CLIENTE_EM_USO, clienteId));
        }
    }

    @Override
    public Cliente buscarOuFalhar(UUID clienteId) {
        return  clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontrado(clienteId));
    }
}
