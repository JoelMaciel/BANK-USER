package com.back.clientes.api.controllers;

import com.back.clientes.api.model.request.ClientDTO;
import com.back.clientes.api.model.request.EmployeeDTO;
import com.back.clientes.api.model.response.ClientSummaryDTO;
import com.back.clientes.domain.services.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/employees")
public class EmployeeController {

    private final ClientService clientService;

    @PostMapping("/subscription")
    public ClientSummaryDTO saveSubscriptionEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return clientService.saveEmployee(employeeDTO);
    }
}
