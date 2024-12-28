package com.example.demo.Repository;

import com.example.demo.Entity.ReservationAlley;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationAlleyRepository extends JpaRepository<ReservationAlley, Long> {
    List<ReservationAlley> findByUserId(Long userId);
    boolean existsByAlleyIdAndReservationDateTime(Long alleyId, LocalDateTime reservationDateTime);
}


