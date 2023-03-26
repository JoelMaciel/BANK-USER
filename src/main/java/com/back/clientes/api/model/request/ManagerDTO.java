package com.back.clientes.api.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ManagerDTO {

    @NonNull
    private UUID userId;
}