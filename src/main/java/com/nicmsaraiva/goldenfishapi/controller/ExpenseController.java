package com.nicmsaraiva.goldenfishapi.controller;

import com.nicmsaraiva.goldenfishapi.model.Expense;
import com.nicmsaraiva.goldenfishapi.repository.ExpenseRepository;
import com.nicmsaraiva.goldenfishapi.service.dto.expense.CreateExpenseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    ExpenseRepository expenseRepository;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateExpenseDTO createExpenseDTO) {
        expenseRepository.save(new Expense(createExpenseDTO));
        return new ResponseEntity<String>("Expense created!", HttpStatus.CREATED);
    }
}
