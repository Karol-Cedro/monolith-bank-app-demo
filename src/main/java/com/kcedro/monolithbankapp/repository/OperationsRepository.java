package com.kcedro.monolithbankapp.repository;

import com.kcedro.monolithbankapp.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperationsRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByAccountId(Long accountId);

}
