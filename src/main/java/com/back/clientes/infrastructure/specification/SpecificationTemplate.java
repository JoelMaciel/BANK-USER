package com.back.clientes.infrastructure.specification;

import com.back.clientes.domain.model.Client;
import com.back.clientes.domain.model.ClientAccount;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.UUID;

public class SpecificationTemplate {

    @And({
            @Spec(path = "nome", spec = Like.class),
            @Spec(path = "cpf", spec = Equal.class),
            @Spec(path = "email", spec = Like.class)
    })
    public interface ClientSpec extends Specification<Client> {}

    public static Specification<Client> clientAccountId(final UUID accountId) {
        return (root, query, cb) -> {
            query.distinct(true);
            Join<Client, ClientAccount> clientProd = root.join("clientsAccounts");
            return cb.equal(clientProd.get("accountId"), accountId);
        };
    }
}
