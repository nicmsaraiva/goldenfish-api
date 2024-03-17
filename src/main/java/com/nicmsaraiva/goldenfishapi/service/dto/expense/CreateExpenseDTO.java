package com.nicmsaraiva.goldenfishapi.service.dto.expense;

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
public class CreateExpenseDTO {
    private String category;
    private Date expenseDate;
    private Double expenseValue;
    private PaymentType paymentType;
    private String description;
}
