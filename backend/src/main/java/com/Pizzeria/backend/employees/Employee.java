package com.Pizzeria.backend.employees;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    private Long employeeId;

    public void setEmployee_Id(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployee_Id() {
        return employeeId;
    }
    private String name;

    private String role; // e.g., "Cook", "Cleaner", "Waiter"

    private Long wage;
}
