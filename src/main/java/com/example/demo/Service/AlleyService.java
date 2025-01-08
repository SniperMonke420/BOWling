package com.example.demo.Service;

import com.example.demo.DTO.AlleyCreateRequest;
import com.example.demo.DTO.AlleyGetAvailable;
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

    public List<AlleyGetAvailable> getAvailableAlleys(LocalDateTime reservationDateTime){
        List<Alley> allAlleys = alleyRepository.findAll();

        List<Long> reservedAlleyIds = reservationAlleyRepository.findReservedAlleyIdsByDateTime(reservationDateTime);

        List<AlleyGetAvailable> response = allAlleys.stream()
                .map(alley -> {
                    AlleyGetAvailable dto = new AlleyGetAvailable();
                    dto.setId(alley.getId());
                    dto.setName(alley.getName());
                    dto.setMaxPersons(alley.getMaxPersons());
                    dto.setPrice(alley.getPrice());
                    dto.setIsAvailable(!reservedAlleyIds.contains(alley.getId()));
                    return dto;
                })
                .toList();
        return response;
    }
}
