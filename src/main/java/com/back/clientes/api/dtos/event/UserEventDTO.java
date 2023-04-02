package com.back.clientes.api.dtos.event;

import com.back.clientes.api.dtos.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEventDTO {

    @Id
    private UUID userId;
    private String username;
    private String fullName;
    private String cpf;
    private String email;
    private String phoneNumber;
    private String userType;
    private String status;
    private String actionType;

    public static UserEventDTO toUserEventDTO(UserResponseDTO userResponseDTO) {
        return UserEventDTO.builder()
                .userId(userResponseDTO.getUserId())
                .username(userResponseDTO.getUsername())
                .fullName(userResponseDTO.getFullName())
                .cpf(userResponseDTO.getCpf())
                .email(userResponseDTO.getEmail())
                .phoneNumber(userResponseDTO.getPhoneNumber())
                .userType(userResponseDTO.getUserType().toString())
                .status(userResponseDTO.getStatus().toString())
                .build();
    }

}
