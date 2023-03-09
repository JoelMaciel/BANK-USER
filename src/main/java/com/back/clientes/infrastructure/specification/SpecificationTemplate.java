package com.back.clientes.infrastructure.specification;

import com.back.clientes.domain.model.Client;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationTemplate {

    @And({
            @Spec(path = "nome", spec = Like.class),
            @Spec(path = "cpf", spec = Equal.class),
            @Spec(path = "email", spec = Like.class)
    })
    public interface ClienteSpec extends Specification<Client> {}
}
