package com.example.controller;

import com.example.model.Position;
import com.example.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/position")
public class PositionController {

    @Autowired
    private PositionService service;

    @GetMapping
    public List<Position> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Position create(@RequestBody Position entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Position> update(@PathVariable Integer id, @RequestBody Position entity) {
        return service.findById(id).map(existing -> {
            existing.setParticipantId(entity.getParticipantId());
            existing.setSecurityId(entity.getSecurityId());
            existing.setQuantity(entity.getQuantity());
            existing.setAveragePrice(entity.getAveragePrice());
            existing.setEntryDate(entity.getEntryDate());
            existing.setExitDate(entity.getExitDate());
            return ResponseEntity.ok(service.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
