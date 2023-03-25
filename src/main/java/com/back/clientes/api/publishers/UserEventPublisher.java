package com.back.clientes.api.publishers;

import com.back.clientes.api.model.event.UserEventDTO;
import com.back.clientes.api.model.response.UserResponseDTO;
import com.back.clientes.domain.enums.ActionType;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value(value = "${bank.broker.exchange.userEvent}")
    private String exchangeUserEvent;

    public void publisherUserEvent(UserEventDTO userEventDTO, ActionType actionType) {
        userEventDTO.setActionType(actionType.toString());
        rabbitTemplate.convertAndSend(exchangeUserEvent, "", userEventDTO);

    }


}
