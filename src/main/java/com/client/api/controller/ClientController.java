package com.client.api.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.client.api.controller.data.request.ClientPartRequest;
import com.client.api.controller.data.request.ClientRequest;
import com.client.api.controller.data.response.ClientEnvelope;
import com.client.api.controller.data.response.ClientResponse;
import com.client.api.service.ClientService;

@RestController
@RequiredArgsConstructor
public class ClientController implements ClientEndpoint {

    private final ClientService service;
    private final PagedResourcesAssembler<ClientResponse> assembler;

    @Override
    public ResponseEntity<ClientEnvelope> create(final ClientRequest request) {

    	ClientResponse response = service.createClient(request);

        return ResponseEntity.ok(ClientEnvelope.builder().data(response).build());
    }

    @Override
    public ResponseEntity<?> findAll(Pageable page) {

    	Page<ClientResponse> response = service.getAllClients(page);

        return new ResponseEntity<>(assembler.toModel(response), HttpStatus.OK);
    }

    @Override
    public ClientEnvelope findById(final Long clientId) {

    	ClientResponse response = service.getByIdClient(clientId);

        return ClientEnvelope.builder().data(response).build();
    }

    @Override
    public ClientEnvelope update(final Long clientId, final ClientRequest request) {

    	ClientResponse response = service.updateClient(clientId, request);

        return ClientEnvelope.builder().data(response).build();
    }

    @Override
    public ClientEnvelope updatePart(final Long clientId, final ClientPartRequest request) {

    	ClientResponse response = service.updatePartClient(clientId, request);

        return ClientEnvelope.builder().data(response).build();
    }
}