package com.bcp.atlas.services.contracts.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "contract")
public class ContractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer customerId;
    private String code;
    private String version;
    private LocalDateTime effectiveDate;
    private LocalDateTime endDate;
    private String productType;
    private String product;
    private String operationCode;
    private Integer organizationDocumentType;
    private String organizationDocumentNumber;
    private String summary;
    private String proposal;
    private String clauses;
    private String fullAddressFiscal;
    private String state;

    @Transient
    private CustomerEntity customer;

    @Transient
    private Iterable<ContractSignatoryEntity> signatories;
}
