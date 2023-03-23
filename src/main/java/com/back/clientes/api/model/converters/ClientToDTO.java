package com.back.clientes.api.model.converters;


import com.back.clientes.api.model.response.UserSummaryDTO;
import com.back.clientes.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClientToDTO {
    private final ModelMapper modelMapper;

    public UserSummaryDTO converter(User user) {
        return modelMapper.map(user, UserSummaryDTO.class);
    }


    public Page<UserSummaryDTO> convertToPageDto(Page<User> clientsPage, Pageable pageable) {
            Page<UserSummaryDTO> clientDTOPage = clientsPage.map(
                    client -> modelMapper.map(client, UserSummaryDTO.class));
            return clientDTOPage;
    }

}
