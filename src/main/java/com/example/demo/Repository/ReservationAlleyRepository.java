package com.example.demo.Repository;

import com.example.demo.Entity.Alley;
import com.example.demo.Entity.ReservationAlley;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationAlleyRepository extends JpaRepository<ReservationAlley, Long> {
    List<ReservationAlley> findByUserId(Long userId);
    boolean existsByAlleyIdAndReservationDateTime(Long alleyId, LocalDateTime reservationDateTime);

    @Query("SELECT r.alley FROM ReservationAlley r WHERE r.reservationDateTime = :reservationDateTime")
    List<Alley> findReservedAlleysByDateTime(@Param("reservationDateTime") LocalDateTime reservationDateTime);
}


