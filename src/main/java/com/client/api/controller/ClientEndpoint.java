package com.client.api.controller;

import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.client.api.controller.data.request.ClientPartRequest;
import com.client.api.controller.data.request.ClientRequest;
import com.client.api.controller.data.response.ClientEnvelope;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(tags = "Clients", value = "The Clients API")
@RequestMapping("/api/clients")
public interface ClientEndpoint {

    @ApiOperation(value = "Create Client", response = ClientEnvelope.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create Success Client"),
            @ApiResponse(code = 400, message = "Client invalid data"),
            @ApiResponse(code = 500, message = "Client error into server")
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    ResponseEntity<ClientEnvelope> create(
            @ApiParam(value = "Payload Client", required = true)
            @Valid @RequestBody final ClientRequest request);

    @ApiOperation(value = "FindAll Client", notes = "Endpoint with paging and HATEOAS.", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "FindAll Clients"),
            @ApiResponse(code = 400, message = "Client invalid data"),
            @ApiResponse(code = 500, message = "Client error into server")
    })
    @GetMapping
    ResponseEntity<?> findAll(
            @ApiParam("Pageable Client")
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC, page = 0, size = 10) final Pageable page);

    @ApiOperation(value = "FindById Client",  response = ClientEnvelope.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Find Unique Client"),
            @ApiResponse(code = 400, message = "Client invalid data"),
            @ApiResponse(code = 500, message = "Client error into server")
    })
    @GetMapping("/{client_id}")
    ClientEnvelope findById(
            @ApiParam(value = "Identifier Client", required = true)
            @PathVariable("client_id") @NotNull(message = "{clientid.notnull}") final Long clientId);

    @ApiOperation(value = "Update Client",  response = ClientEnvelope.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update Client"),
            @ApiResponse(code = 400, message = "Client invalid data"),
            @ApiResponse(code = 500, message = "Client error into server")
    })
    @PutMapping("/{client_id}")
    ClientEnvelope update(
            @ApiParam(value = "Identifier Client", required = true)
            @PathVariable("client_id") @NotNull(message = "{clientid.notnull}") final Long clientId,

            @ApiParam(value = "Payload Client", required = true)
            @Valid @RequestBody final ClientRequest request);

    @ApiOperation(value = "Update Part Client",  response = ClientEnvelope.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update Part Client"),
            @ApiResponse(code = 400, message = "Client invalid data"),
            @ApiResponse(code = 500, message = "Client error into server")
    })
    @PatchMapping("/{client_id}")
    ClientEnvelope updatePart(
            @ApiParam(value = "Identifier Client", required = true)
            @PathVariable("client_id") @Valid @NotNull(message = "{clientid.notnull}") final Long clientId,

            @ApiParam(value = "Payload Part Client", required = true)
            @Valid @RequestBody final @NotNull ClientPartRequest request);
}