package com.bcp.atlas.services.contracts.business.impl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

import com.bcp.atlas.services.contracts.repository.ContractRepository;
import com.bcp.atlas.services.contracts.business.ContractService;
import com.bcp.atlas.services.contracts.business.ContractSignatoryService;
import com.bcp.atlas.services.contracts.business.CustomerService;
import com.bcp.atlas.services.contracts.model.entity.ContractEntity;
import com.bcp.atlas.services.contracts.model.entity.CustomerEntity;
import com.bcp.atlas.services.contracts.model.entity.ContractSignatoryEntity;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final TransactionTemplate transactionTemplate;
    
    private final ContractRepository contractRepository;
    private final ContractSignatoryService contractSignatoryService;
    private final CustomerService customerService;

    @Qualifier("jdbcScheduler")
    private final Scheduler jdbcScheduler;

    @Override
    public Mono<ContractEntity> selectById(Integer id) {
        return Mono.fromCallable(() -> this.contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with id: " + id)))
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<ContractEntity> selectDetailById(Integer id) {
        return this.selectById(id)
                .flatMap(contract -> {
                    Mono<CustomerEntity> customerMono = customerService.selectById(contract.getCustomerId());
                    Flux<ContractSignatoryEntity> signatoriesFlux = contractSignatoryService.selectByContractId(contract.getId());
                    
                    return Mono.zip(
                            Mono.just(contract),
                            customerMono,
                            signatoriesFlux.collectList()
                    ).map(tuple -> {
                        ContractEntity contractDetail = tuple.getT1();
                        CustomerEntity customer = tuple.getT2();
                        List<ContractSignatoryEntity> signatories = tuple.getT3();
                        
                        contractDetail.setCustomer(customer);
                        contractDetail.setSignatories(signatories);
                        
                        return contractDetail;
                    });
                });
    }    

    @Override
    public Flux<ContractEntity> listAll() {
        return Mono.fromCallable(this.contractRepository::findAll)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<ContractEntity> selectByCustomerId(Integer customerId) {
        return Mono.fromCallable(() -> this.contractRepository.findByCustomerId(customerId))
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<ContractEntity> setRegister(ContractEntity contract) {
        return Mono.fromCallable(() -> transactionTemplate.execute(transactionScope -> 
            Mono.fromCallable(() -> this.contractRepository.save(contract))
            .flatMap(savedContract -> {
                contract.getSignatories().forEach(signatory -> 
                    signatory.setContractId(savedContract.getId())
                );
                return contractSignatoryService.setRegisters(contract.getSignatories())
                    .then(Mono.just(savedContract)); 
            }).block()
        )).subscribeOn(jdbcScheduler);
    }    
}
