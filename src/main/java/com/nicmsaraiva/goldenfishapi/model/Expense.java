package com.nicmsaraiva.goldenfishapi.model;

import com.nicmsaraiva.goldenfishapi.service.dto.expense.CreateExpenseDTO;
import com.nicmsaraiva.goldenfishapi.service.dto.expense.ReadExpenseDTO;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "expense")
@Table(name = "expenses")
@EqualsAndHashCode(of = "id")
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
    @NotBlank
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private String description;

    public Expense(CreateExpenseDTO expenseDTO) {
        this.category = expenseDTO.getCategory();
        this.expenseDate = expenseDTO.getExpenseDate();
        this.expenseValue = expenseDTO.getExpenseValue();
        this.paymentType = expenseDTO.getPaymentType();
        this.description = expenseDTO.getDescription();
    }

    public Expense(ReadExpenseDTO readExpenseDTO) {
        this.id = readExpenseDTO.getId();
        this.category = readExpenseDTO.getCategory();
        this.expenseDate = readExpenseDTO.getExpenseDate();
        this.expenseValue = readExpenseDTO.getExpenseValue();
        this.paymentType = readExpenseDTO.getPaymentType();
        this.description = readExpenseDTO.getDescription();
    }
}
