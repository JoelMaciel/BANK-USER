package com.back.clientes.domain.services;

import com.back.clientes.api.model.request.EmployeeDTO;
import com.back.clientes.api.model.request.UserDTO;
import com.back.clientes.api.model.request.UserUpdateDTO;
import com.back.clientes.api.model.response.UserResponseDTO;
import com.back.clientes.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface UserService {

    UserResponseDTO saveUser(UserDTO userDTO);

    void delete(UUID userId);

    UserResponseDTO findByUser(UUID userId);

    UserResponseDTO save(UserDTO userDTO);

    UserResponseDTO updateUser(UUID userId, UserUpdateDTO userUpdateDTO);


    Page<UserResponseDTO> findAll(Specification<User> spec, Pageable pageable);

    User searchOrFail(UUID userId);


    void updatePassword(UUID userId, String passwordCurrent, String newPassword);

    UserResponseDTO saveEmployee(EmployeeDTO employeeDTO);
}
