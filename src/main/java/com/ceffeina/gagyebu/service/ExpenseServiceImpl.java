package com.ceffeina.gagyebu.service;

import com.ceffeina.gagyebu.dto.ExpenseDTO;
import com.ceffeina.gagyebu.dto.PageRequestDTO;
import com.ceffeina.gagyebu.dto.PageResultDTO;
import com.ceffeina.gagyebu.entity.Expense;
import com.ceffeina.gagyebu.repostitory.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository repository;

    @Override
    public Long register(ExpenseDTO dto) {
        Expense expense = dtoToEntity(dto);
        repository.save(expense);
        return expense.getEno();
    }

    @Override
    public PageResultDTO<ExpenseDTO, Expense> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("eno").descending());
        Page<Expense> result = repository.findAll(pageable);
        Function<Expense, ExpenseDTO> fn = (this::entityToDto);
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public ExpenseDTO read(Long eno) {
        Expense expense = repository.findById(eno).orElseThrow(() -> new IllegalArgumentException("Invalid eno: " + eno));
        return entityToDto(expense);
    }

    @Override
    public void remove(Long eno) {
        repository.deleteById(eno);
    }

    @Override
    public void modify(ExpenseDTO dto) {
        Expense expense = repository.findById(dto.getEno()).orElseThrow(() -> new IllegalArgumentException("Invalid eno: " + dto.getEno()));
        expense.changeTitle(dto.getTitle());
        expense.changeDescription(dto.getDescription());
        expense.changeAmount(dto.getAmount());
        repository.save(expense);
    }

    private Expense dtoToEntity(ExpenseDTO dto) {
        return Expense.builder()
                .eno(dto.getEno())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .build();
    }

    private ExpenseDTO entityToDto(Expense entity) {
        return ExpenseDTO.builder()
                .eno(entity.getEno())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .amount(entity.getAmount())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }
}
