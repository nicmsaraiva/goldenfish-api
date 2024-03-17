package com.nicmsaraiva.goldenfishapi.controller;

import com.nicmsaraiva.goldenfishapi.model.Expense;
import com.nicmsaraiva.goldenfishapi.repository.ExpenseRepository;
import com.nicmsaraiva.goldenfishapi.service.dto.expense.CreateExpenseDTO;
import com.nicmsaraiva.goldenfishapi.service.dto.expense.ReadExpenseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ReadExpenseDTO>> readAll() {
        List<Expense> expenses = expenseRepository.findAll();
        List<ReadExpenseDTO> readExpenseDTOS = new ArrayList<>();
        for(Expense e : expenses) {
            readExpenseDTOS.add(new ReadExpenseDTO(e));
        }
        return new ResponseEntity<List<ReadExpenseDTO>>(readExpenseDTOS, HttpStatus.FOUND);
    }
}
