package com.example.demo.Service;

import com.example.demo.Entity.Alley;
import com.example.demo.Repository.AlleyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlleyService {
    @Autowired
    private AlleyRepository alleyRepository;

    public Alley addAlley(String name) {
        Alley alley = new Alley();
        alley.setName(name);
        return alleyRepository.save(alley);
    }

    public void deleteAlley(Long alleyId) {
        alleyRepository.deleteById(alleyId);
    }
}
