package com.back.clientes.api.controllers;

import com.back.clientes.api.model.request.PasswordDTO;
import com.back.clientes.api.model.request.UserUpdateDTO;
import com.back.clientes.api.model.response.UserResponseDTO;
import com.back.clientes.domain.services.UserService;
import com.back.clientes.infrastructure.specification.SpecificationTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserResponseDTO> getAllUsers(SpecificationTemplate.UserSpec spec,
                                             @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.findAll(spec, pageable);
    }

    @GetMapping("/{userId}")
    public UserResponseDTO getOneUser(@PathVariable UUID userId) {
        return userService.findByUser(userId);
    }

    @PutMapping("/{userId}")
    public UserResponseDTO updateUser(@PathVariable UUID userId, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        return userService.updateUser(userId, userUpdateDTO);
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable UUID userId, @RequestBody @Valid PasswordDTO passwordDTO) {
        userService.updatePassword(userId, passwordDTO.getPasswordCurrent(), passwordDTO.getNewPassword());

    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable UUID userId) {
        userService.deleteUser(userId);
    }
}