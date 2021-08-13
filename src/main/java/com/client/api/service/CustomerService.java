package com.client.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.client.api.controller.data.request.CustomerPartRequest;
import com.client.api.controller.data.request.CustomerRequest;
import com.client.api.controller.data.response.CustomerResponse;

public interface CustomerService {

    CustomerResponse createCustomer(final CustomerRequest request);

    Page<CustomerResponse> getAllCustomers(Pageable page);

    CustomerResponse getByIdCustomer(Long customerId);

    CustomerResponse updateCustomer(Long customerId, CustomerRequest request);

    CustomerResponse updatePartCustomer(Long customerId, CustomerPartRequest request);
}