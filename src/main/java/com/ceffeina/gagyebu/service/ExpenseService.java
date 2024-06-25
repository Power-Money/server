package com.ceffeina.gagyebu.service;

import com.ceffeina.gagyebu.dto.ExpenseDTO;
import com.ceffeina.gagyebu.dto.PageRequestDTO;
import com.ceffeina.gagyebu.dto.PageResultDTO;
import com.ceffeina.gagyebu.entity.Expense;

public interface ExpenseService {
    Long register(ExpenseDTO dto);
    ExpenseDTO read(Long eno);
    void remove(Long eno);
    void modify(ExpenseDTO dto);
    PageResultDTO<ExpenseDTO, Expense> getList(PageRequestDTO requestDTO);
}
