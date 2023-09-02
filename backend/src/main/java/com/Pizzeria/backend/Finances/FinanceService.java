package com.Pizzeria.backend.Finances;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FinanceService {
    private final FinanceRepository financeRepository;

    @Autowired
    public FinanceService(FinanceRepository financeRepository) {
        this.financeRepository = financeRepository;
    }

    public Finance addFinance(Finance finance) {
        return financeRepository.save(finance);
    }

    public List<Finance> getFinancesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return financeRepository.findByTransactionDateBetween(startDate, endDate);
    }
    public List<Finance> getFinancesByDate(LocalDateTime Date) {
        return financeRepository.findByTransactionDate(Date);
    }
    public List<Finance> getFinancesByTransactionType(String transactionType) {
        return financeRepository.findByTransactionType(transactionType);
    }

    public void getFinancesByTransactionId(Long transactionId) {
        financeRepository.findByTransactionId(transactionId);
    }
}
