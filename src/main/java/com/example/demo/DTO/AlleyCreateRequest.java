package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AlleyCreateRequest {
    private String name;
    private Integer maxPersons;
    private BigDecimal price;
}
