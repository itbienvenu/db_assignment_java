package com.example.controller;

import com.example.model.Blocker;
import com.example.service.BlockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blocker")
public class BlockerController {

    @Autowired
    private BlockerService service;

    @GetMapping
    public List<Blocker> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blocker> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Blocker create(@RequestBody Blocker entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blocker> update(@PathVariable Integer id, @RequestBody Blocker entity) {
        return service.findById(id).map(existing -> {
            existing.setName(entity.getName());
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
