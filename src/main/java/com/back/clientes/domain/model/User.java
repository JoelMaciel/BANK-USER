package com.back.clientes.domain.model;

import com.back.clientes.domain.enums.UserStatus;
import com.back.clientes.domain.enums.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID userId;


    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    @CPF
    private String cpf;

    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime creationDate;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime updateDate;

    @Embedded
    private Address address;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();

    public boolean passwordMatches(String passwordCurrent) {
        return getPassword().equals(passwordCurrent);
    }

    public boolean passwordDoesNotMatch(String passwordCurrent) {
        return !passwordMatches(passwordCurrent);
    }

}

