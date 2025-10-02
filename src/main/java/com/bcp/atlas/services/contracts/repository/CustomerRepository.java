package com.bcp.atlas.services.contracts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bcp.atlas.services.contracts.model.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity,Integer> {
    Iterable<CustomerEntity> findByDocumentNumber(String documentNumber);
}
