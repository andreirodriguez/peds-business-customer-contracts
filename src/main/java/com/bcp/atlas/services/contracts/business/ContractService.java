package com.bcp.atlas.services.contracts.business;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.bcp.atlas.services.contracts.model.entity.ContractEntity;

public interface ContractService {
    Mono<ContractEntity> selectById(Integer id);
    Mono<ContractEntity> selectDetailById(Integer id);
    Flux<ContractEntity> listAll();
    Flux<ContractEntity> selectByCustomerId(Integer customerId);
    Mono<ContractEntity> setRegister(ContractEntity contract);
}
