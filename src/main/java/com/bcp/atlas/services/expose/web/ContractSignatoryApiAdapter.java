package com.bcp.atlas.services.expose.web;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.bcp.atlas.services.contracts.business.ContractSignatoryService;
import com.bcp.atlas.services.contracts.model.entity.ContractSignatoryEntity;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/contracts/signatories")
public class ContractSignatoryApiAdapter {
    private final ContractSignatoryService contractSignatoryService;

    @GetMapping("/{id}")
    public Mono<ContractSignatoryEntity> getSelectById(@PathVariable Integer id) {
        return contractSignatoryService.selectById(id);
    }

    @GetMapping("/find-all")
    public Flux<ContractSignatoryEntity> getListAll() {
        return contractSignatoryService.listAll();
    }

    @GetMapping("/search")
    public Flux<ContractSignatoryEntity> getSelectByContractId(@RequestParam Integer contractId) {
        return contractSignatoryService.selectByContractId(contractId);
    }
}
