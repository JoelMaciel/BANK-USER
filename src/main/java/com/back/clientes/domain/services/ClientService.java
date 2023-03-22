package com.back.clientes.domain.services;

import com.back.clientes.api.model.request.ClientDTO;
import com.back.clientes.api.model.request.EmployeeDTO;
import com.back.clientes.api.model.response.ClientSummaryDTO;
import com.back.clientes.api.model.request.ClientDTOUpdate;
import com.back.clientes.domain.model.Client;
import com.back.clientes.infrastructure.specification.SpecificationTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface ClientService {

     void delete(UUID clientId);

    ClientSummaryDTO findByClient(UUID clientId);

    ClientSummaryDTO save(ClientDTO clientDTO);

    ClientSummaryDTO updateClient(UUID clientId , ClientDTOUpdate clientUpdate);


    Page<ClientSummaryDTO> findAll(Specification<Client> spec, UUID accountId, Pageable pageable);

     Client searchOrFail(UUID clientId) ;


     void updatePassword(UUID clientId, String passwordCurrent, String newPassword);

    ClientSummaryDTO saveEmployee(EmployeeDTO employeeDTO);
}
