package com.ceffeina.gagyebu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Data
@Builder
@AllArgsConstructor

public class PageRequestDTO {

    private int page;
    private int size;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public int getPage() {
        return page <= 0 ? 1 : page;
    }

    public int getSize() {
        return size <= 0 || size > 100 ? 10 : size;
    }

    public int getOffset() {
        return (getPage() - 1) * getSize();
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(getPage() - 1, getSize(), sort);
    }

}
