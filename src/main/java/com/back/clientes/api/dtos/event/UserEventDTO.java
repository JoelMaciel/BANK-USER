package com.back.clientes.api.model.event;

import com.back.clientes.api.model.response.UserResponseDTO;
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
    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;
    private String userType;
    private String status;
    private String actionType;

    public static UserEventDTO toUserEventDTO(UserResponseDTO userResponseDTO) {
        return UserEventDTO.builder()
                .userId(userResponseDTO.getUserId())
                .name(userResponseDTO.getName())
                .cpf(userResponseDTO.getCpf())
                .email(userResponseDTO.getEmail())
                .phoneNumber(userResponseDTO.getPhoneNumber())
                .userType(userResponseDTO.getUserType().toString())
                .status(userResponseDTO.getStatus().toString())
                .build();
    }

}
