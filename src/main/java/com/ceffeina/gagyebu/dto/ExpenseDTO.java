package com.ceffeina.gagyebu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {
    private Long eno;
    private String title;
    private String description;
    private double amount;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
