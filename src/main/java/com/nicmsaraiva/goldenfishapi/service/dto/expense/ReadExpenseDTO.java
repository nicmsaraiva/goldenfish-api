package com.nicmsaraiva.goldenfishapi.service.dto.expense;

import com.nicmsaraiva.goldenfishapi.model.Expense;
import com.nicmsaraiva.goldenfishapi.model.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadExpenseDTO {
    private Long id;
    private String category;
    private Date expenseDate;
    private Double expenseValue;
    private PaymentType paymentType;

    public ReadExpenseDTO(Expense expense) {
        this.id = expense.getId();
        this.category = expense.getCategory();
        this.expenseDate = expense.getExpenseDate();
        this.expenseValue = expense.getExpenseValue();
        this.paymentType = expense.getPaymentType();
    }
}
