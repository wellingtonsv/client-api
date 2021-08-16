package com.client.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.client.api.controller.data.request.ClientPartRequest;
import com.client.api.controller.data.request.ClientRequest;
import com.client.api.controller.data.response.ClientResponse;
import com.client.api.converter.ClientConverter;
import com.client.api.entity.ClientEntity;
import com.client.api.exception.ResourceNotFoundException;
import com.client.api.exception.ValidatorClientException;
import com.client.api.repository.ClientRepository;
import com.client.api.service.ClientService;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private static final String VALIDATION_01 = "VALIDATION-01";
    private static final String CLIENT_DATA_01 = "CLIENT-DATA-01";
    private static final String CLIENT_DATA_02 = "CLIENT-DATA-02";
    private static final String CLIENT_DATA_03 = "CLIENT-DATA-03";
    private static final String CLIENT_DATA_04 = "CLIENT-DATA-04";

    private final ClientRepository repository;
    private final ClientConverter converter;

    @Override
    public ClientResponse createClient(ClientRequest request) {

        if (repository.findByCpfCnpj(request.getCpfCnpj()).isPresent()) {
            throw new ValidatorClientException(VALIDATION_01, request.getCpfCnpj());
        }

        ClientEntity entity = repository.saveAndFlush(converter.toClientEntity(request));

        if (isNull(entity))
            throw new ResourceNotFoundException(CLIENT_DATA_01);

        return converter.toClientResponse(entity);
    }

    @Override
    public Page<ClientResponse> getAllClients(Pageable page) {

    	 Page<ClientEntity> pages = repository.findAll(page);

        if (isNull(pages))
            throw new ResourceNotFoundException(CLIENT_DATA_02);

        return pages.map(o -> converter.toClientResponse(o));
    }

    @Override
    public ClientResponse getByIdClient(Long clientId) {

    	ClientEntity entity = getById(clientId);

        return converter.toClientResponse(entity);
    }

    @Override
    public ClientResponse updateClient(Long clientId, ClientRequest request) {

    	ClientEntity clientEntity = converter.toClientEntityUpdate(this.getById(clientId), request);

        return update(clientEntity, request.getCpfCnpj());
    }

    @Override
    public ClientResponse updatePartClient(Long clientId, ClientPartRequest request) {

    	ClientEntity clientEntity = this.getById(clientId);

        return update(converter.toClientEntityPartUpdate(clientEntity, request), request.getName());
    }

    private ClientResponse update(ClientEntity clientEntity, String attribute) {

    	ClientEntity entity = repository.saveAndFlush(clientEntity);

        if (isNull(entity))
            throw new ResourceNotFoundException(CLIENT_DATA_04, attribute);

        return converter.toClientResponse(entity);
    }

    private ClientEntity getById(Long clientId) {
        return repository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException(CLIENT_DATA_03, clientId));
    }
}