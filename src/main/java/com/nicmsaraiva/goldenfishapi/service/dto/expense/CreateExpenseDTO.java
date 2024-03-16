package com.nicmsaraiva.goldenfishapi.service.dto.expense;

import com.nicmsaraiva.goldenfishapi.model.Expense;
import com.nicmsaraiva.goldenfishapi.model.Payment;
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
    private Date date;
    private Double value;
    private Payment payment;
}
