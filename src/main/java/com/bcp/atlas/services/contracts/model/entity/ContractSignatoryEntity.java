package com.bcp.atlas.services.contracts.model.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contract_signatory")
public class ContractSignatoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer contractId;
    private Integer signatoryDocumentType;
    private String signatoryDocumentNumber;
    private String state;
    private Boolean active;
}
