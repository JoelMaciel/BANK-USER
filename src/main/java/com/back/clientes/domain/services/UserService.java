package com.back.clientes.domain.services;

import com.back.clientes.api.dtos.request.EmployeeDTO;
import com.back.clientes.api.dtos.request.ManagerDTO;
import com.back.clientes.api.dtos.request.UserDTO;
import com.back.clientes.api.dtos.request.UserUpdateDTO;
import com.back.clientes.api.dtos.response.UserResponseDTO;
import com.back.clientes.core.security.dtos.JwtDTO;
import com.back.clientes.core.security.dtos.LoginDTO;
import com.back.clientes.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

public interface UserService {

    UserResponseDTO saveUser(UserDTO userDTO);

    void deleteUser(UUID userId);

    UserResponseDTO updateUser(UUID userId,UserUpdateDTO userUpdateDTO);

    void delete(UUID userId);

    UserResponseDTO findByUser(UUID userId);

    UserResponseDTO save(UserDTO userDTO);

    UserResponseDTO update(UUID userId, UserUpdateDTO userUpdateDTO);


    Page<UserResponseDTO> findAll(Specification<User> spec, Pageable pageable);

    User searchOrFail(UUID userId);


    void updatePassword(UUID userId, String passwordCurrent, String newPassword);

    UserResponseDTO saveEmployee(EmployeeDTO employeeDTO);
    UserResponseDTO saveManager(ManagerDTO managerDTO);

    JwtDTO authenticationUserLogin(LoginDTO loginDTO);
}
