package com.back.clientes.api.controllers;

import com.back.clientes.api.dtos.request.UserDTO;
import com.back.clientes.api.dtos.response.UserResponseDTO;
import com.back.clientes.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO registerUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }
}
