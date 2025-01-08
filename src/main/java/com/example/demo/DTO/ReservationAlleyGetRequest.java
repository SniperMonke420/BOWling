package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationAlleyGetRequest {
    private Long id;
    private Long alleyId;
    private LocalDateTime reservationDateTime;
    private LocalDateTime orderDateTime;
}
