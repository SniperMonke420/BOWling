package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AlleyUpdateRequest {
    private String name;
    private BigDecimal price;
    private Long maxPersons;
}
