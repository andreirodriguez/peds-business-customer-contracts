package com.bcp.atlas.services.expose.web;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.bcp.atlas.services.contracts.business.ContractService;
import com.bcp.atlas.services.contracts.model.entity.ContractEntity;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/contracts")
public class ContractApiAdapter {
    private final ContractService contractService;

    @GetMapping("/{id}")
    public Mono<ContractEntity> getSelectById(@PathVariable Integer id) {
        return contractService.selectDetailById(id);
    }

    @GetMapping("/find-all")
    public Flux<ContractEntity> getListAll() {
        return contractService.listAll();
    }

    @GetMapping("/search")
    public Flux<ContractEntity> getSelectByCustomerId(@RequestParam Integer customerId) {
        return contractService.selectByCustomerId(customerId);
    }

    @PostMapping()
    public Mono<Integer> setCreate(@RequestBody ContractEntity contract) {
        return this.contractService.setRegister(contract).map(ContractEntity::getId);
    }    
}
