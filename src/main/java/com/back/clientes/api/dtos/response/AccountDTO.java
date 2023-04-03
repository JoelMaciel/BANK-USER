package com.back.clientes.api.dtos.response;

import com.back.clientes.domain.enums.TypeAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AccountDTO {

    private String number;

    private TypeAccount type;

    private BigDecimal balance;

    @JsonIgnore
    private UUID agencyId;

}