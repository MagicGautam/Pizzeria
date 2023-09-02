package com.Pizzeria.backend.Finances;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long> {
    List<Finance> findByTransactionDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Finance> findByTransactionDate(LocalDateTime date);

    List<Finance> findByTransactionType(String transactionType);

    void findByTransactionId(Long transactionId);
}
