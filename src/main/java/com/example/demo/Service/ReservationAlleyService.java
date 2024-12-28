package com.example.demo.Service;

import com.example.demo.Entity.Alley;
import com.example.demo.Entity.ReservationAlley;
import com.example.demo.Repository.AlleyRepository;
import com.example.demo.Repository.ReservationAlleyRepository;
import com.example.demo.Security.Entity.User;
import com.example.demo.Security.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationAlleyService {
    private final ReservationAlleyRepository reservationAlleyRepository;
    private final AlleyRepository alleyRepository;
    private final UserRepository userRepository;

    public ReservationAlleyService(
            ReservationAlleyRepository reservationAlleyRepository,
            AlleyRepository alleyRepository,
            UserRepository userRepository) {
        this.reservationAlleyRepository = reservationAlleyRepository;
        this.alleyRepository = alleyRepository;
        this.userRepository = userRepository;
    }

    public ReservationAlley reserveAlley(Long alleyId, Long userId, LocalDateTime reservationDateTime) {
        if (reservationDateTime.getMinute() != 0 || reservationDateTime.getSecond() != 0) {
            throw new IllegalArgumentException("Reservation must start at a full hour.");
        }

        boolean isAlreadyReserved = reservationAlleyRepository.existsByAlleyIdAndReservationDateTime(alleyId, reservationDateTime);
        if (isAlreadyReserved) {
            throw new RuntimeException("The alley is already reserved at this time.");
        }

        Alley alley = alleyRepository.findById(alleyId)
                .orElseThrow(() -> new RuntimeException("Alley not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ReservationAlley reservation = new ReservationAlley();
        reservation.setAlley(alley);
        reservation.setUser(user);
        reservation.setReservationDateTime(reservationDateTime);

        return reservationAlleyRepository.save(reservation);
    }

    public void cancelReservation(Long reservationId) {
        reservationAlleyRepository.deleteById(reservationId);
    }

    public List<ReservationAlley> getReservationsByUser(Long userId) {
        return reservationAlleyRepository.findByUserId(userId);
    }
}
