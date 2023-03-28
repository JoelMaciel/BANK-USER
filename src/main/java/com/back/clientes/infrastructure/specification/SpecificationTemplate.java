package com.back.clientes.infrastructure.specification;

import com.back.clientes.domain.model.User;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationTemplate {

    @And({
            @Spec(path = "name", spec = Like.class),
            @Spec(path = "cpf", spec = Equal.class),
            @Spec(path = "email", spec = Like.class)
    })
    public interface UserSpec extends Specification<User> {}

}
