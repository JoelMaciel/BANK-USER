package com.back.clientes.api.model.response;

import com.back.clientes.domain.enums.TypeAccount;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AccountDTO {

    private String number;

    private TypeAccount type;

    private BigDecimal balance;

    private UUID agencyId;

}