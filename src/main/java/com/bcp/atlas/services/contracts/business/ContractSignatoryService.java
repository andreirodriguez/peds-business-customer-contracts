package com.bcp.atlas.services.contracts.business;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.bcp.atlas.services.contracts.model.entity.ContractSignatoryEntity;

public interface ContractSignatoryService {
    Mono<ContractSignatoryEntity> selectById(Integer id);
    Flux<ContractSignatoryEntity> listAll();
    Flux<ContractSignatoryEntity> selectByContractId(Integer contractId);
    Mono<Void> setRegisters(Iterable<ContractSignatoryEntity> contractSignatories);
}
