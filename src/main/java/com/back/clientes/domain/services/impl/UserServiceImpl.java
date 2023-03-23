package com.back.clientes.domain.services.impl;

import com.back.clientes.api.model.request.EmployeeDTO;
import com.back.clientes.api.model.request.UserDTO;
import com.back.clientes.api.model.request.UserUpdateDTO;
import com.back.clientes.api.model.response.UserSummaryDTO;
import com.back.clientes.domain.enums.UserType;
import com.back.clientes.domain.exception.InvalidPasswordException;
import com.back.clientes.domain.exception.UserNotFound;
import com.back.clientes.domain.model.Address;
import com.back.clientes.domain.model.User;
import com.back.clientes.domain.repository.UserRepository;
import com.back.clientes.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private static final String MSG_CLIENT_IN_USE =
            "Client code %s cannot be removed as it is in use";

    private final UserRepository userRepository;

    @Override
    public Page<UserSummaryDTO> findAll(Specification<User> user, Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        List<UserSummaryDTO> agencyList = usersPage.getContent().stream()
                .map(UserSummaryDTO::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(agencyList, pageable, usersPage.getTotalElements());
    }

    public UserSummaryDTO findByUser(UUID userId) {
        return UserSummaryDTO.toDTO(searchOrFail(userId));
    }

    @Transactional
    @Override
    public UserSummaryDTO updateClient(UUID userId, UserUpdateDTO userUpdateDTO) {
        User user = searchOrFail(userId).toBuilder()
                .name(userUpdateDTO.getName())
                .email(userUpdateDTO.getEmail())
                .phoneNumber(userUpdateDTO.getPhoneNumber())
                .address(Address.toEntity(userUpdateDTO.getAddress()))
                .build();
        userRepository.save(user);
        return UserSummaryDTO.toDTO(user);
    }

    // nao está tratando DataIntegrityViolationException
    @Transactional
    @Override
    public UserSummaryDTO save(UserDTO userDTO) {
        User user = userRepository.save(UserDTO.toEntity(userDTO));
        return UserSummaryDTO.toDTO(user);
    }

    @Transactional
    @Override
    public void delete(UUID userId) {
        userRepository.delete(searchOrFail(userId));
    }

    @Transactional
    @Override
    public void updatePassword(UUID userId, String passwordCurrent, String newPassword) {
        User user = searchOrFail(userId);

        if (user.passwordDoesNotMatch(passwordCurrent)) {
            throw new InvalidPasswordException("Error: Mismatched old password");
        }
        user.setPassword(newPassword);
    }

    @Override
    public UserSummaryDTO saveEmployee(EmployeeDTO employeeDTO) {
        var user = searchOrFail(employeeDTO.getUserId())
                .toBuilder()
                .userType(UserType.EMPLOYEE)
                .updateDate(OffsetDateTime.now())
                .build();
        userRepository.save(user);
        return UserSummaryDTO.toDTO(user);
    }

    public User searchOrFail(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));
    }


}

