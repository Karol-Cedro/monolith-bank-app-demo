package com.kcedro.monolithbankapp.repository;

import com.kcedro.monolithbankapp.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByMobileNumber(String mobileNumber);

    @Transactional
    @Modifying
    void deleteByAccountId(Long accountId);

}
