package com.ceffeina.gagyebu.repostitory;

import com.ceffeina.gagyebu.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
