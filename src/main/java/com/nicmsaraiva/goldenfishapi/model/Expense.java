package com.nicmsaraiva.goldenfishapi.model;

import com.nicmsaraiva.goldenfishapi.service.dto.expense.CreateExpenseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Expense")
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String category;

    @NotBlank
    private Date expenseDate;

    @NotBlank
    private Double expenseValue;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private PaymentType paymentType;

    private String description;

    public Expense(CreateExpenseDTO expenseDTO) {
        this.category = expenseDTO.getCategory();
        this.expenseDate = expenseDTO.getExpenseDate();
        this.expenseValue = expenseDTO.getExpenseValue();
        this.paymentType = expenseDTO.getPaymentType();
        this.description = expenseDTO.getDescription();
    }
}

