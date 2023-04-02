package com.back.clientes.api.dtos.response;

import com.back.clientes.domain.enums.UserStatus;
import com.back.clientes.domain.enums.UserType;
import com.back.clientes.domain.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserResponseDTO {

   @JsonIgnore
    private UUID userId;
    private String username;
    private String fullName;
    private String cpf;
    private String email;
    private String phoneNumber;
    private UserType userType;
    private UserStatus status;
    private AddressDTO address;



    public static UserResponseDTO toDTO(User user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .cpf(user.getCpf())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .userType(user.getUserType())
                .status(user.getStatus())
                .address(AddressDTO.toDTO(user.getAddress()))
                .build();
    }

}
