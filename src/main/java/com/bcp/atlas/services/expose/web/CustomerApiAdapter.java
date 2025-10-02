package com.bcp.atlas.services.expose.web;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.bcp.atlas.services.contracts.business.CustomerService;
import com.bcp.atlas.services.contracts.model.entity.CustomerEntity;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/customers")
public class CustomerApiAdapter {
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public Mono<CustomerEntity> getSelectById(@PathVariable Integer id) {
        return customerService.selectById(id);
    }

    @GetMapping("/find-all")
    public Flux<CustomerEntity> getListAll() {
        return customerService.listAll();
    }

    @GetMapping("/search")
    public Flux<CustomerEntity> getSelectByDocumentNumber(@RequestParam String documentNumber) {
        return customerService.selectByDocumentNumber(documentNumber);
    }
}
