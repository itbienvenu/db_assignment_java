package com.example.controller;

import com.example.model.Participant;
import com.example.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participant")
public class ParticipantController {

    @Autowired
    private ParticipantService service;

    @GetMapping
    public List<Participant> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Participant create(@RequestBody Participant entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participant> update(@PathVariable Integer id, @RequestBody Participant entity) {
        return service.findById(id).map(existing -> {
            existing.setName(entity.getName());
            existing.setAddress(entity.getAddress());
            existing.setContactDetails(entity.getContactDetails());
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
