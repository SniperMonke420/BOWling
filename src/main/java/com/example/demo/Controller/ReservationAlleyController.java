package com.example.demo.Controller;

import com.example.demo.Entity.ReservationAlley;
import com.example.demo.Service.ReservationAlleyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservation")
public class ReservationAlleyController {
    private final ReservationAlleyService reservationService;

    public ReservationAlleyController(ReservationAlleyService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationAlley> reserveAlley(@RequestBody Map<String, Object> request) {
        Long alleyId = ((Number) request.get("alleyId")).longValue();
        Long userId = ((Number) request.get("userId")).longValue();
        LocalDateTime reservationDateTime = LocalDateTime.parse((String) request.get("reservationDateTime"));

        return ResponseEntity.ok(reservationService.reserveAlley(alleyId, userId, reservationDateTime));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationAlley>> getUserReservations(@PathVariable Long userId) {
        return ResponseEntity.ok(reservationService.getReservationsByUser(userId));
    }
}

