package com.nicmsaraiva.goldenfishapi.model;

import com.nicmsaraiva.goldenfishapi.service.dto.expense.CreateExpenseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String category;
    @NotBlank
    private Date date;
    @NotBlank
    private Double value;
    @NotBlank
    private Payment payment;

    public Expense(CreateExpenseDTO expenseDTO) {
        this.category = expenseDTO.getCategory();
        this.date = expenseDTO.getDate();
        this.value = expenseDTO.getValue();
        this.payment = expenseDTO.getPayment();
    }
}
