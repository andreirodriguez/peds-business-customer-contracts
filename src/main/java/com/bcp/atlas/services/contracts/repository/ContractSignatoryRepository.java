package com.bcp.atlas.services.contracts.repository;

import org.springframework.data.repository.CrudRepository;

import com.bcp.atlas.services.contracts.model.entity.ContractSignatoryEntity;

public interface ContractSignatoryRepository extends CrudRepository<ContractSignatoryEntity,Integer> {
    Iterable<ContractSignatoryEntity> findByContractId(Integer contractId);
}
