package com.client.api.controller.data.response;

import com.client.api.enums.ClientStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"clientId", "name", "email", "cpfCnpj", "birthDate", "age", "status"})
public class ClientResponse implements Serializable {

    @JsonProperty("client_id")
    private Long clientId;

    private String name;

    private String email;

    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    private int age;

    private ClientStatus status;
}