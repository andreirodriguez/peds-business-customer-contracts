package com.bcp.atlas.services.contracts.business.impl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bcp.atlas.services.contracts.business.CustomerService;
import com.bcp.atlas.services.contracts.model.entity.CustomerEntity;
import com.bcp.atlas.services.contracts.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Qualifier("jdbcScheduler")
    private final Scheduler jdbcScheduler;

    @Override
    public Mono<CustomerEntity> selectById(Integer id) {
        return Mono.fromCallable(() -> this.customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id)))
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<CustomerEntity> listAll() {
        return Mono.fromCallable(this.customerRepository::findAll)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<CustomerEntity> selectByDocumentNumber(String documentNumber) {
        return Mono.fromCallable(() -> this.customerRepository.findByDocumentNumber(documentNumber))
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler);
    }
}
