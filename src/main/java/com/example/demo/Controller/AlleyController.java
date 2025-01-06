package com.example.demo.Controller;

import com.example.demo.DTO.AlleyCreateRequest;
import com.example.demo.Entity.Alley;
import com.example.demo.Service.AlleyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alley")
public class AlleyController {
    private final AlleyService alleyService;

    public AlleyController(AlleyService alleyService) {
        this.alleyService = alleyService;
    }

    @PostMapping
    public ResponseEntity<Alley> addAlley(@RequestBody AlleyCreateRequest alleyCreateRequest) {
        Alley newAlley = alleyService.addAlley(alleyCreateRequest);
        return ResponseEntity.ok(newAlley);
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
}
