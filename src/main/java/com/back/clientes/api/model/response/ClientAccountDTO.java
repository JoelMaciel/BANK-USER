package com.back.clientes.api.model.response;

import com.back.clientes.domain.model.Client;
import com.back.clientes.domain.model.ClientAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientAccountDTO {

    private UUID clientId;

    @NotNull
    private UUID accountId;

    public static ClientAccountDTO converterToDTO(ClientAccount clientAccount) {
        return ClientAccountDTO.builder()
                .clientId(clientAccount.getClient().getClientId())
                .accountId(clientAccount.getAccountId())
                .build();
    }
}
