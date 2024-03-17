package com.nicmsaraiva.goldenfishapi.controller;

import com.nicmsaraiva.goldenfishapi.model.Expense;
import com.nicmsaraiva.goldenfishapi.repository.ExpenseRepository;
import com.nicmsaraiva.goldenfishapi.service.dto.expense.CreateExpenseDTO;
import com.nicmsaraiva.goldenfishapi.service.dto.expense.ReadExpenseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    ExpenseRepository expenseRepository;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CreateExpenseDTO createExpenseDTO) {
        try {
            expenseRepository.save(new Expense(createExpenseDTO));
            return new ResponseEntity<String>("Expense created!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Failed to create a expense!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<ReadExpenseDTO>> readAll() {
        List<ReadExpenseDTO> readExpenseDTOS = expenseRepository.findAll().stream().map(ReadExpenseDTO::new).toList();
        return new ResponseEntity<List<ReadExpenseDTO>>(readExpenseDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadExpenseDTO> readById(@PathVariable Long id) {
        Optional<Expense> expenseOptional = expenseRepository.findById(id);
        if (expenseOptional.isPresent()) {
            ReadExpenseDTO readExpenseDTO = new ReadExpenseDTO(expenseOptional.get());
            return ResponseEntity.ok(readExpenseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/values")
    public ResponseEntity<Double> readAllExpenseValues() {
        Double totalValue = expenseRepository.findAll().stream().mapToDouble(Expense::getExpenseValue).sum();
        return ResponseEntity.ok(totalValue);
    }
}
