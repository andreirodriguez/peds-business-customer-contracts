package com.bcp.atlas.services.contracts.business.impl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bcp.atlas.services.contracts.repository.ContractSignatoryRepository;
import com.bcp.atlas.services.contracts.business.ContractSignatoryService;
import com.bcp.atlas.services.contracts.model.entity.ContractSignatoryEntity;

@Service
@RequiredArgsConstructor
public class ContractSignatoryServiceImpl implements ContractSignatoryService {
    private final ContractSignatoryRepository contractSignatoryRepository;

    @Qualifier("jdbcScheduler")
    private final Scheduler jdbcScheduler;

    @Override
    public Mono<ContractSignatoryEntity> selectById(Integer id) {
        return Mono.fromCallable(() -> this.contractSignatoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract Signatory not found with id: " + id)))
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<ContractSignatoryEntity> listAll() {
        return Mono.fromCallable(this.contractSignatoryRepository::findAll)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<ContractSignatoryEntity> selectByContractId(Integer contractId) {
        return Mono.fromCallable(() -> this.contractSignatoryRepository.findByContractId(contractId))
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<Void> setRegisters(Iterable<ContractSignatoryEntity> contractSignatories) {
        return Mono.just(this.contractSignatoryRepository.saveAll(contractSignatories)).then();
    }    
}
