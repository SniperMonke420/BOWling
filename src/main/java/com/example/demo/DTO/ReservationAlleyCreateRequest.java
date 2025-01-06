package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationAlleyCreateRequest {
    private Long alleyId;
    private LocalDateTime reservationDateTime;
}
