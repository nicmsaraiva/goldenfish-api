package com.nicmsaraiva.goldenfishapi.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicmsaraiva.goldenfishapi.controller.ExpenseController;
import com.nicmsaraiva.goldenfishapi.model.Expense;
import com.nicmsaraiva.goldenfishapi.model.PaymentType;
import com.nicmsaraiva.goldenfishapi.repository.ExpenseRepository;
import com.nicmsaraiva.goldenfishapi.service.dto.expense.CreateExpenseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ExpenseControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ExpenseController expenseController;

    @Mock
    private ExpenseRepository expenseRepository;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(expenseController).build();
    }

    @Test
    public void testCreateExpense_ValidData() throws Exception {
        CreateExpenseDTO createExpenseDTO = new CreateExpenseDTO();
        createExpenseDTO.setCategory("Food");
        createExpenseDTO.setPaymentType(PaymentType.CARD);
        createExpenseDTO.setExpenseDate(new Date());
        createExpenseDTO.setDescription("Carbonara");
        createExpenseDTO.setExpenseValue(100.0);

        when(expenseRepository.save(any(Expense.class))).thenReturn(new Expense(createExpenseDTO));

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(createExpenseDTO);

        mockMvc.perform(post("/expense")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateExpense_InvalidData() throws Exception {
        CreateExpenseDTO createExpenseDTO = new CreateExpenseDTO();
        createExpenseDTO.setCategory("Food");
        createExpenseDTO.setPaymentType(PaymentType.CARD);
        createExpenseDTO.setExpenseDate(null);
        createExpenseDTO.setDescription("Carbonara");
        createExpenseDTO.setExpenseValue(100.0);

        when(expenseRepository.save(any(Expense.class))).thenReturn(null);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(createExpenseDTO);

        mockMvc.perform(post("/expense")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testReadById_ExistingExpense() throws Exception {
        Long existingId = 1L;
        Expense existingExpense = new Expense();
        existingExpense.setId(1L);

        when(expenseRepository.findById(existingId)).thenReturn(Optional.of(existingExpense));

        mockMvc.perform(get("/expense/{id}", existingId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingId));
    }

    @Test
    public void testReadById_NonExistingExpense() throws Exception {
        Long nonExistingId = 12L;
        when(expenseRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/expense/{id}", nonExistingId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}