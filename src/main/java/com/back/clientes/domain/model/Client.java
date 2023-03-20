package com.back.clientes.domain.model;

import com.back.clientes.domain.enums.ClientType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID clientId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime creationDate;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime updateDate;

    @Embedded
    private Address address;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<ClientAccount> clientsAccounts;

    public ClientAccount converterToClientAccount(UUID accountId) {
        return new ClientAccount(null, this, accountId);
    }

    public boolean passwordMatches(String passwordCurrent) {
        return getPassword().equals(passwordCurrent);
    }

    public boolean passwordDoesNotMatch(String passwordCurrent) {
        return !passwordMatches(passwordCurrent);
    }

}
