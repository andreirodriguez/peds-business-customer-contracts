package com.bcp.atlas.services.contracts.repository;

import org.springframework.data.repository.CrudRepository;

import com.bcp.atlas.services.contracts.model.entity.ContractEntity;

public interface ContractRepository extends CrudRepository<ContractEntity,Integer> {
    Iterable<ContractEntity> findByCustomerId(Integer customerId);
}
