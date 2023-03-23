package com.back.clientes.api.model.response;

import com.back.clientes.domain.enums.UserType;
import com.back.clientes.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder//(toBuilder = true)
public class UserSummaryDTO {

    private UUID userId;
    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;
    private UserType userType;
    private AddressDTO address;


    public static UserSummaryDTO toDTO(User user) {
        return UserSummaryDTO.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .cpf(user.getCpf())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .userType(user.getUserType())
                .address(AddressDTO.toDTO(user.getAddress()))
                .build();
    }

}
