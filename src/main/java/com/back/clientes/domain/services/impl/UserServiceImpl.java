package com.back.clientes.domain.services.impl;

import com.back.clientes.api.dtos.event.UserEventDTO;
import com.back.clientes.api.dtos.request.EmployeeDTO;
import com.back.clientes.api.dtos.request.ManagerDTO;
import com.back.clientes.api.dtos.request.UserDTO;
import com.back.clientes.api.dtos.request.UserUpdateDTO;
import com.back.clientes.api.dtos.response.UserResponseDTO;
import com.back.clientes.api.publishers.UserEventPublisher;
import com.back.clientes.domain.enums.ActionType;
import com.back.clientes.domain.enums.UserStatus;
import com.back.clientes.domain.enums.UserType;
import com.back.clientes.domain.exception.DuplicateDataException;
import com.back.clientes.domain.exception.InvalidDataException;
import com.back.clientes.domain.exception.InvalidPasswordException;
import com.back.clientes.domain.exception.UserNotFound;
import com.back.clientes.domain.model.Address;
import com.back.clientes.domain.model.User;
import com.back.clientes.domain.repository.UserRepository;
import com.back.clientes.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private static final String MSG_CLIENT_IN_USE =
            "Client code %s cannot be removed as it is in use";
    private static final String DUPLICATE_DATA =
            "There is already a user registered with this CPF or Email or Phone Number.";
    private static final String MSG_INVALID_DATA =
            "The name must have a minimum of 15 and a maximum of 50 characters";
    private static final String MSG_INVALID_CPF =
            "The CPF you entered is invalid or does not exist.";

    private final UserRepository userRepository;

    private final UserEventPublisher userEventPublisher;

    @Override
    public Page<UserResponseDTO> findAll(Specification<User> user, Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        List<UserResponseDTO> agencyList = usersPage.getContent().stream()
                .map(UserResponseDTO::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(agencyList, pageable, usersPage.getTotalElements());
    }

    public UserResponseDTO findByUser(UUID userId) {
        return UserResponseDTO.toDTO(searchOrFail(userId));
    }


    @Transactional
    @Override
    public UserResponseDTO saveUser(UserDTO userDTO) {
        try {
            var userResponseDTO = save(userDTO);
            userEventPublisher.publisherUserEvent(UserEventDTO.toUserEventDTO(userResponseDTO), ActionType.CREATE);
            return userResponseDTO;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateDataException(DUPLICATE_DATA);
        }

    }

    @Transactional
    @Override
    public UserResponseDTO save(UserDTO userDTO) {
        try {
            var newUser = UserDTO.toEntity(userDTO).toBuilder()
                    .userType(UserType.CUSTOMER)
                    .status(UserStatus.ACTIVE).build();
            userRepository.save(newUser);
            userRepository.flush();
            return UserResponseDTO.toDTO(newUser);
        } catch (JpaSystemException e) {
            throw new InvalidDataException(MSG_INVALID_DATA);
        } catch (ConstraintViolationException e) {
            throw new InvalidDataException(MSG_INVALID_CPF);
        }
    }

    @Transactional
    @Override
    public UserResponseDTO saveEmployee(EmployeeDTO employeeDTO) {
        var user = searchOrFail(employeeDTO.getUserId())
                .toBuilder()
                .userType(UserType.EMPLOYEE)
                .updateDate(OffsetDateTime.now())
                .build();
        var employee = UserResponseDTO.toDTO(userRepository.save(user));
        userEventPublisher.publisherUserEvent(UserEventDTO.toUserEventDTO(employee), ActionType.UPDATE);
        return employee;
    }

    @Transactional
    @Override
    public UserResponseDTO saveManager(ManagerDTO managerDTO) {
        var user = searchOrFail(managerDTO.getUserId())
                .toBuilder()
                .userType(UserType.MANAGER)
                .updateDate(OffsetDateTime.now())
                .build();
        var manager = UserResponseDTO.toDTO(userRepository.save(user));
        userEventPublisher.publisherUserEvent(UserEventDTO.toUserEventDTO(manager), ActionType.UPDATE);
        return manager;
    }


    @Transactional
    @Override
    public UserResponseDTO updateUser(UUID userId, UserUpdateDTO userUpdateDTO) {
        var userUpdate = update(userId, userUpdateDTO);
        userEventPublisher.publisherUserEvent(UserEventDTO.toUserEventDTO(userUpdate), ActionType.UPDATE);
        return userUpdate;
    }

    @Transactional
    @Override
    public UserResponseDTO update(UUID userId, UserUpdateDTO userUpdateDTO) {
        try {
            User user = searchOrFail(userId).toBuilder()
                    .name(userUpdateDTO.getName())
                    .email(userUpdateDTO.getEmail())
                    .phoneNumber(userUpdateDTO.getPhoneNumber())
                    .address(Address.toEntity(userUpdateDTO.getAddress()))
                    .build();
            userRepository.save(user);
            userRepository.flush();
            return UserResponseDTO.toDTO(user);
        } catch (JpaSystemException e) {
            throw new InvalidDataException(MSG_INVALID_DATA);
        }

    }

    @Transactional
    @Override
    public void deleteUser(UUID userId) {
        var user = searchOrFail(userId);
        delete(userId);
        userEventPublisher.publisherUserEvent(UserEventDTO.toUserEventDTO(UserResponseDTO.toDTO(user)), ActionType.DELETE);
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


    public User searchOrFail(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));
    }

}

