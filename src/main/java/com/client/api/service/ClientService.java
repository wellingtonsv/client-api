package com.client.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.client.api.controller.data.request.ClientPartRequest;
import com.client.api.controller.data.request.ClientRequest;
import com.client.api.controller.data.response.ClientResponse;

public interface ClientService {

    ClientResponse createClient(final ClientRequest request);

    Page<ClientResponse> getAllClients(Pageable page);

    ClientResponse getByIdClient(Long clientId);

    ClientResponse updateClient(Long clientId, ClientRequest request);

    ClientResponse updatePartClient(Long clientId, ClientPartRequest request);
}