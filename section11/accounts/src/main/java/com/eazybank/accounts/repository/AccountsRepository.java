package com.eazybank.accounts.repository;

import com.eazybank.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional //any runtime error will be rolled-back
    @Modifying
    void deleteByCustomerId(Long customerId);
}
