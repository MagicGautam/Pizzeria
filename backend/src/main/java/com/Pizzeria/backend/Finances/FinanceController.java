package com.Pizzeria.backend.Finances;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/inventory/finance")
public class FinanceController {
    private final FinanceService financeService;

    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping(path = "/getBetweenDates")
    public void getFinancesByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        financeService.getFinancesByDateRange(startDate.atStartOfDay(), endDate.atStartOfDay());
    }
    @GetMapping(path = "/getByDate")
    public void getFinancesByDate(@RequestParam LocalDate Date) {
        financeService.getFinancesByDate(Date.atStartOfDay());
    }
    @GetMapping(path = "/getByTransactionType")
    public void getFinancesByTransactionType(@RequestParam String transactionType) {
        financeService.getFinancesByTransactionType(transactionType);
    }
    @GetMapping(path = "/getByTransactionId")
    public void getFinancesByTransactionId(@RequestParam Long transactionId) {
        financeService.getFinancesByTransactionId(transactionId);
    }
}
