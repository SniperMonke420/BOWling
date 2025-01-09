package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationAlleyGetRequest {
    private Long id;
    private Long alleyId;
    private BigDecimal price;
    private Long maxPersons;
    private LocalDateTime reservationDateTime;
    private LocalDateTime orderDateTime;
}
