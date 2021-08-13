package com.client.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.client.api.enums.ClientStatus;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long id;

    @Column(name = "name_client", nullable = false)
    private String name;

    @Column(name = "email_client", nullable = false)
    private String email;

    @Column(name = "cpf_cnpj_client", nullable = false)
    private String cpfCnpj;

    @Column(name = "birth_date_client", nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_client", nullable = false)
    private ClientStatus status;

    @PrePersist
    void createClient() {
        status = ClientStatus.ACTIVE;
    }
}