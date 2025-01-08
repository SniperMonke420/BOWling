package com.example.demo.Service;

import com.example.demo.DTO.AlleyCreateRequest;
import com.example.demo.Entity.Alley;
import com.example.demo.Repository.AlleyRepository;
import com.example.demo.Repository.ReservationAlleyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlleyService {
    @Autowired
    private AlleyRepository alleyRepository;
    @Autowired
    private ReservationAlleyRepository reservationAlleyRepository;

    public Alley addAlley(AlleyCreateRequest alleyCreateRequest) {
        Alley alley = new Alley();
        alley.setName(alleyCreateRequest.getName());
        alley.setMaxPersons(alleyCreateRequest.getMaxPersons());
        alley.setPrice(alleyCreateRequest.getPrice());
        return alleyRepository.save(alley);
    }

    public List<Alley> getAllAlleys() {
        return alleyRepository.findAll();
    }

    public void deleteAlley(Long alleyId) {
        alleyRepository.deleteById(alleyId);
    }

    public List<Alley> getAvailableAlleys(LocalDateTime reservationDateTime){
        List<Alley> allAlleys = alleyRepository.findAll();
        List<Alley> reservedAlleys = reservationAlleyRepository.findReservedAlleysByDateTime(reservationDateTime);
        List<Alley> availableAlleys = allAlleys.stream()
                .filter(alley -> !reservedAlleys.contains(alley))
                .toList();
        return availableAlleys;
    }
}
