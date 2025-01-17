package com.example.demo.Controller;

import com.example.demo.DTO.ReservationAlleyCreateRequest;
import com.example.demo.DTO.ReservationAlleyGetRequest;
import com.example.demo.Entity.ReservationAlley;
import com.example.demo.Security.Entity.User;
import com.example.demo.Security.Repository.UserRepository;
import com.example.demo.Service.ReservationAlleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationAlleyController {
    @Autowired
    private ReservationAlleyService reservationService;
    @Autowired
    private UserRepository userRepository;

    public ReservationAlleyController(ReservationAlleyService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Void> reserveAlley(@RequestBody ReservationAlleyCreateRequest reservationAlleyCreateRequest, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        reservationService.reserveAlley(reservationAlleyCreateRequest, user.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        reservationService.cancelReservation(reservationId, user.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<List<ReservationAlleyGetRequest>> getUserReservations(@AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;
        Long userId = user.getId();
        return ResponseEntity.ok(reservationService.getReservationsByUser(userId));
    }
}
