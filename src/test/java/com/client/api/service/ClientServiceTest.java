package com.client.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.client.api.controller.data.request.ClientPartRequest;
import com.client.api.controller.data.request.ClientRequest;
import com.client.api.controller.data.response.ClientResponse;
import com.client.api.converter.ClientConverter;
import com.client.api.entity.ClientEntity;
import com.client.api.enums.ClientStatus;
import com.client.api.repository.ClientRepository;
import com.client.api.service.impl.ClientServiceImpl;

@ExtendWith(SpringExtension.class)
public class ClientServiceTest {

    private static final Long CLIENT_ID = 100L;
    private static final String VALIDATION_01 = "VALIDATION-01";
    private static final String CLIENT_DATA_01 = "CLIENT-DATA-01";
    private static final String CLIENT_DATA_02 = "CLIENT-DATA-02";
    private static final String CLIENT_DATA_03 = "CLIENT-DATA-03";
    private static final String CLIENT_DATA_04 = "CLIENT-DATA-04";

    @InjectMocks
    private ClientServiceImpl service;

    @Mock
    private ClientRepository repository;

    @Mock
    private ClientConverter converter;

    private ClientRequest request;
    private ClientResponse response;
    private ClientEntity entity;
    private ClientPartRequest partRequest;

    @BeforeEach
    public void setUp() {
        request = createRequest();
        entity = createEntity();
        response = createResponse();
        partRequest = createPartRequest();
    }

    @Test
    public void whenCreateClientIsSuccess() {

        try {

            when(converter.toClientEntity(any())).thenReturn(entity);
            when(repository.saveAndFlush(any())).thenReturn(entity);
            when(converter.toClientResponse(any())).thenReturn(response);

            ClientResponse result = service.createClient(request);

            verify(converter).toClientEntity(any());
            verify(repository).saveAndFlush(any());
            verify(converter).toClientResponse(any());

            assertThat(result).isNotNull();
            assertThat(result.getName()).isEqualTo(response.getName());
            assertThat(result.getEmail()).isEqualTo(response.getEmail());
            assertThat(result.getCpfCnpj()).isEqualTo(response.getCpfCnpj());
            assertThat(result.getBirthDate()).isEqualTo(response.getBirthDate());
            assertThat(result.getClientId()).isEqualTo(response.getClientId());
            assertThat(result.getStatus()).isEqualTo(response.getStatus());

        } catch (Exception ex) {
            fail("Não deve cair aqui...");
        }
    }

    @Test
    public void whenCreateClientEntityNullIsExceptionError() {

        try {

            when(repository.saveAndFlush(any())).thenReturn(null);
            service.createClient(request);

            fail("Não deve cair aqui...");

        } catch (Exception ex) {
            verify(repository).saveAndFlush(any());
            assertThat(ex.getMessage()).isEqualToIgnoringCase(CLIENT_DATA_01);
        }
    }

    @Test
    public void whenCreateClientIsExistsClient() {

        try {

            when(repository.findByCpfCnpj(any())).thenReturn(Optional.of(entity));
            service.createClient(request);

            fail("Não deve cair aqui...");

        } catch (Exception ex) {
            verify(repository).findByCpfCnpj(any());
            assertThat(ex.getMessage()).isEqualToIgnoringCase(VALIDATION_01);
        }
    }

    @Test
    public void whenUpdateClientIsSuccess() {

        try {

            when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
            when(converter.toClientEntityUpdate(any(), any())).thenReturn(entity);
            when(repository.saveAndFlush(any())).thenReturn(entity);
            when(converter.toClientResponse(any())).thenReturn(response);

            ClientResponse result = service.updateClient(CLIENT_ID, request);

            verify(repository).findById(anyLong());
            verify(converter).toClientEntityUpdate(any(), any());
            verify(repository).saveAndFlush(any());
            verify(converter).toClientResponse(any());

            assertThat(result).isNotNull();
            assertThat(result.getClientId()).isEqualTo(response.getClientId());

        } catch (Exception ex) {
            fail("Não deve cair aqui...");
        }
    }

    @Test
    public void whenUpdateClientEntityNullIsExceptionError() {

        try {

            when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
            when(repository.saveAndFlush(any())).thenReturn(null);
            service.updateClient(CLIENT_ID, request);

            fail("Não deve cair aqui...");

        } catch (Exception ex) {
            verify(repository).findById(anyLong());
            verify(repository).saveAndFlush(any());
            assertThat(ex.getMessage()).isEqualToIgnoringCase(CLIENT_DATA_04);
        }
    }

    @Test
    public void whenGetByIdClientEntityIsSuccess() {

        try {

            when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
            when(converter.toClientResponse(any())).thenReturn(response);

            ClientResponse result = service.getByIdClient(CLIENT_ID);

            verify(repository).findById(anyLong());
            verify(converter).toClientResponse(any());

            assertThat(result).isNotNull();

        } catch (Exception ex) {
            fail("Não deve cair aqui...");
        }
    }

    @Test
    public void whenUpdatePartClientIsSuccess() {

        try {

            when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
            when(converter.toClientEntityPartUpdate(any(), any())).thenReturn(entity);
            when(repository.saveAndFlush(any())).thenReturn(entity);
            when(converter.toClientResponse(any())).thenReturn(response);

            ClientResponse result = service.updatePartClient(CLIENT_ID, partRequest);

            verify(repository).findById(anyLong());
            verify(converter).toClientEntityPartUpdate(any(), any());
            verify(repository).saveAndFlush(any());
            verify(converter).toClientResponse(any());

            assertThat(result).isNotNull();
            assertThat(result.getClientId()).isEqualTo(response.getClientId());

        } catch (Exception ex) {
            fail("Não deve cair aqui...");
        }
    }

    @Test
    public void whenUpdatePartClientEntityNullIsExceptionError() {

        try {

            when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
            when(converter.toClientEntityPartUpdate(any(), any())).thenReturn(entity);
            when(repository.saveAndFlush(any())).thenReturn(null);
            service.updatePartClient(CLIENT_ID, partRequest);

            fail("Não deve cair aqui...");

        } catch (Exception ex) {
            verify(repository).findById(anyLong());
            verify(converter).toClientEntityPartUpdate(any(), any());
            verify(repository).saveAndFlush(any());
            assertThat(ex.getMessage()).isEqualToIgnoringCase(CLIENT_DATA_04);
        }
    }

    @Test
    public void whenGetAllClientEntityIsSuccess() {

        try {

            Page<ClientEntity> page = mock(Page.class);
            page.getContent().add(entity);
            Pageable pageable = PageRequest.of(0, 5, Sort.by("asc", "name"));

            when(repository.findAll(pageable)).thenReturn(page);
            when(converter.toClientResponse(any())).thenReturn(response);

            Page<ClientResponse> result = service.getAllClients(pageable);

            verify(repository).findAll(pageable);

            assertThat(result).isNull();

        } catch (Exception ex) {
            fail("Não deve cair aqui...");
        }
    }

    @Test
    public void whenGetAllClientEntityIsError() {

        Pageable pageable = PageRequest.of(0, 5, Sort.by("asc", "name"));

        try {

            when(repository.findAll(pageable)).thenReturn(null);

            service.getAllClients(pageable);

            fail("Não deve cair aqui...");

        } catch (Exception ex) {
            verify(repository).findAll(pageable);
            assertThat(ex.getMessage()).isEqualToIgnoringCase(CLIENT_DATA_02);
        }
    }

    private ClientRequest createRequest() {
        return ClientRequest.builder()
                .name("Wellington Vieira")
                .email("wellingtonsv@gmail.com")
                .cpfCnpj("12345678910")
                .birthDate(LocalDate.of(1979, 02, 18))
                .build();
    }

    private ClientEntity createEntity() {
        return ClientEntity.builder()
                .id(100L)
                .name("Wellington Vieira")
                .email("wellingtonsv@gmail.com")
                .cpfCnpj("12345678910")
                .birthDate(LocalDate.of(1979, 02, 18))
                .status(ClientStatus.ACTIVE)
                .build();
    }

    private ClientResponse createResponse() {
        return ClientResponse.builder()
                .clientId(100L)
                .name("Wellington Vieira")
                .email("wellingtonsv@gmail.com")
                .cpfCnpj("12345678910")
                .birthDate(LocalDate.of(1979, 02, 18))
                .age(37)
                .status(ClientStatus.ACTIVE)
                .build();
    }

    public ClientPartRequest createPartRequest() {
        return ClientPartRequest.builder()
                .name("Wellington Vieira")
                .email("wellingtonsv2@gmail.com")
                .build();
    }
}