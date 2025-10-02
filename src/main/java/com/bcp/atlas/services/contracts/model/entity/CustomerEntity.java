package com.bcp.atlas.services.contracts.model.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer documentType;
    private String documentNumber;
    private String name;
    private String lastName;
    private String country;
    private String state;
    private String zipCode;
    private String addressFiscal;
    private Boolean active;    
}
