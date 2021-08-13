package com.client.api.controller.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import com.client.api.enums.ClientStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientPartRequest implements Serializable {

    private String name;
    private String email;
    private ClientStatus status;
}