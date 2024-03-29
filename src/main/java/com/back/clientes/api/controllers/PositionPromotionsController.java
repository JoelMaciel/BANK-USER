package com.back.clientes.api.controllers;

import com.back.clientes.api.dtos.request.EmployeeDTO;
import com.back.clientes.api.dtos.request.ManagerDTO;
import com.back.clientes.api.dtos.response.UserResponseDTO;
import com.back.clientes.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/position-promotions")

public class PositionPromotionsController {
    private final UserService userService;

    @PreAuthorize("hasAnyRole('MANAGER')")
    @PostMapping("/employees")
    public UserResponseDTO saveSubscriptionEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return userService.saveEmployee(employeeDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/managers")
    public UserResponseDTO saveSubscriptionManager(@RequestBody @Valid ManagerDTO managerDTO) {
        return userService.saveManager(managerDTO);
    }
}
