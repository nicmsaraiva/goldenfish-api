package com.nicmsaraiva.goldenfishapi.repository;

import com.nicmsaraiva.goldenfishapi.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
