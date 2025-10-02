package com.bcp.atlas.services.contracts.business;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.bcp.atlas.services.contracts.model.entity.CustomerEntity;

public interface CustomerService {
    Flux<CustomerEntity> listAll();

    Mono<CustomerEntity> selectById(Integer id);

    Flux<CustomerEntity> selectByDocumentNumber(String documentNumber);
}
