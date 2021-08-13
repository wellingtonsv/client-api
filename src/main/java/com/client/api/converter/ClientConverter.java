package com.client.api.converter;

import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.client.api.controller.data.request.ClientPartRequest;
import com.client.api.controller.data.request.ClientRequest;
import com.client.api.controller.data.response.ClientResponse;
import com.client.api.entity.ClientEntity;
import com.client.api.service.BirthDateService;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Component
@Generated
@RequiredArgsConstructor
public class ClientConverter {

    private final BirthDateService service;

    public ClientEntity toClientEntity(final ClientRequest request) {
        return ClientEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .cpfCnpj(request.getCpfCnpj())
                .birthDate(request.getBirthDate())
                .build();
    }

    public ClientEntity toClientEntityUpdate(final ClientEntity entity, final ClientRequest request) {
        return ClientEntity.builder()
                .id(entity.getId())
                .name(request.getName())
                .email(request.getEmail())
                .cpfCnpj(request.getCpfCnpj())
                .birthDate(request.getBirthDate())
                .status(entity.getStatus())
                .build();
    }

    public ClientEntity toClientEntityPartUpdate(final ClientEntity entity, final ClientPartRequest request) {

        entity.setName(nonNull(request.getName()) ? request.getName() : entity.getName());
        entity.setEmail(nonNull(request.getEmail()) ? request.getEmail() : entity.getEmail());
        entity.setStatus(nonNull(request.getStatus()) ? request.getStatus() : entity.getStatus());

        return entity;
    }

    public ClientResponse toClientResponse(final ClientEntity entity) {
        return ClientResponse.builder()
                .clientId(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .cpfCnpj(entity.getCpfCnpj())
                .birthDate(entity.getBirthDate())
                .age(service.getAge(entity.getBirthDate()))
                .status(entity.getStatus())
                .build();
    }

    public List<ClientResponse> toClientResponseList(final List<ClientEntity> entities) {
        return entities.stream().map(this::toClientResponse).collect(Collectors.toList());
    }
}