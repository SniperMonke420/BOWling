package com.example.demo.Controller;

import com.example.demo.DTO.AlleyCreateRequest;
import com.example.demo.DTO.AlleyGetAvailable;
import com.example.demo.DTO.AlleyUpdateRequest;
import com.example.demo.Entity.Alley;
import com.example.demo.Service.AlleyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/alley")
public class AlleyController {
    private final AlleyService alleyService;

    public AlleyController(AlleyService alleyService) {
        this.alleyService = alleyService;
    }

    @PostMapping
    public ResponseEntity<Void> addAlley(@RequestBody AlleyCreateRequest alleyCreateRequest) {
        Alley newAlley = alleyService.addAlley(alleyCreateRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alley> updateAlley(
            @PathVariable Long id,
            @RequestBody AlleyUpdateRequest alleyUpdateRequest) {
        Alley updatedAlley = alleyService.updateAlley(id, alleyUpdateRequest);
        return ResponseEntity.ok(updatedAlley);
    }

    @GetMapping
    public ResponseEntity<List<Alley>> getAllAlleys() {
        List<Alley> alleys = alleyService.getAllAlleys();
        return ResponseEntity.ok(alleys);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlley(@PathVariable Long id) {
        alleyService.deleteAlley(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available/{reservationDateTime}")
    public ResponseEntity<List<AlleyGetAvailable>> checkAvailability(@PathVariable LocalDateTime reservationDateTime) {
        List<AlleyGetAvailable> response = alleyService.getAvailableAlleys(reservationDateTime);
        return ResponseEntity.ok(response);
    }
}
