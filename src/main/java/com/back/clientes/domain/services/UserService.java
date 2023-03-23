package com.back.clientes.domain.services;

import com.back.clientes.api.model.request.EmployeeDTO;
import com.back.clientes.api.model.request.UserDTO;
import com.back.clientes.api.model.request.UserUpdateDTO;
import com.back.clientes.api.model.response.UserSummaryDTO;
import com.back.clientes.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface UserService {

    void delete(UUID userId);

    UserSummaryDTO findByUser(UUID userId);

    UserSummaryDTO save(UserDTO userDTO);

    UserSummaryDTO updateClient(UUID userId, UserUpdateDTO userUpdateDTO);


    Page<UserSummaryDTO> findAll(Specification<User> spec, Pageable pageable);

    User searchOrFail(UUID userId);


    void updatePassword(UUID userId, String passwordCurrent, String newPassword);

    UserSummaryDTO saveEmployee(EmployeeDTO employeeDTO);
}
