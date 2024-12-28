package com.example.demo.Controller;

import com.example.demo.Entity.Alley;
import com.example.demo.Service.AlleyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alley")
public class AlleyController {
    private final AlleyService alleyService;

    public AlleyController(AlleyService alleyService) {
        this.alleyService = alleyService;
    }

    @PostMapping
    public ResponseEntity<Alley> addAlley(@RequestBody String name) {
        Alley newAlley = alleyService.addAlley(name);
        return ResponseEntity.ok(newAlley);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlley(@PathVariable Long id) {
        alleyService.deleteAlley(id);
        return ResponseEntity.noContent().build();
    }
}
