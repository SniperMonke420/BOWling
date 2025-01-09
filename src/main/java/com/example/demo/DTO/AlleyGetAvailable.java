package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AlleyGetAvailable {
    private Long id;
    private String name;
    private Long maxPersons;
    private BigDecimal price;
    private Boolean isAvailable;
}
