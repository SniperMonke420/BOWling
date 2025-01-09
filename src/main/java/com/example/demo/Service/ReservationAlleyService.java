package com.example.demo.Service;

import com.example.demo.DTO.ReservationAlleyCreateRequest;
import com.example.demo.DTO.ReservationAlleyGetRequest;
import com.example.demo.Entity.Alley;
import com.example.demo.Entity.ReservationAlley;
import com.example.demo.Repository.AlleyRepository;
import com.example.demo.Repository.ReservationAlleyRepository;
import com.example.demo.Security.Entity.User;
import com.example.demo.Security.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationAlleyService {
    @Autowired
    private ReservationAlleyRepository reservationAlleyRepository;
    @Autowired
    private AlleyRepository alleyRepository;
    @Autowired
    private UserRepository userRepository;


    public ReservationAlley reserveAlley(ReservationAlleyCreateRequest reservationAlleyCreateRequest, Long userId) {
        if (reservationAlleyCreateRequest.getReservationDateTime().getMinute() != 0 || reservationAlleyCreateRequest.getReservationDateTime().getSecond() != 0) {
            throw new IllegalArgumentException("Reservation must start at a full hour.");
        }

        boolean isAlreadyReserved = reservationAlleyRepository.existsByAlleyIdAndReservationDateTime(reservationAlleyCreateRequest.getAlleyId(), reservationAlleyCreateRequest.getReservationDateTime());
        if (isAlreadyReserved) {
            throw new RuntimeException("The alley is already reserved at this time.");
        }

        Alley alley = alleyRepository.findById(reservationAlleyCreateRequest.getAlleyId())
                .orElseThrow(() -> new RuntimeException("Alley not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ReservationAlley reservation = new ReservationAlley();
        reservation.setAlley(alley);
        reservation.setUser(user);
        reservation.setReservationDateTime(reservationAlleyCreateRequest.getReservationDateTime());
        reservation.setOrderDateTime(LocalDateTime.now());

        System.out.println("User ID: " + userId);
        System.out.println("Alley ID: " + reservationAlleyCreateRequest.getAlleyId());
        System.out.println("Reservation DateTime: " + reservationAlleyCreateRequest.getReservationDateTime());

        return reservationAlleyRepository.save(reservation);
    }

    public void cancelReservation(Long reservationId) {
        reservationAlleyRepository.deleteById(reservationId);
    }

    public List<ReservationAlleyGetRequest> getReservationsByUser(Long userId) {
        List<ReservationAlley> reservationList = reservationAlleyRepository.findByUserId(userId);
        List<ReservationAlleyGetRequest> reservationListDto = new ArrayList<>();

        for (ReservationAlley reservation : reservationList) {
            ReservationAlleyGetRequest dto = new ReservationAlleyGetRequest();
            dto.setId(reservation.getId());
            dto.setAlleyId(reservation.getAlley().getId());
            dto.setPrice(reservation.getAlley().getPrice());
            dto.setMaxPersons(reservation.getAlley().getMaxPersons());
            dto.setReservationDateTime(reservation.getReservationDateTime());
            dto.setOrderDateTime(reservation.getOrderDateTime());
            reservationListDto.add(dto);
        }
        return reservationListDto;
    }

}
