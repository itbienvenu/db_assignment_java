package com.example.controller;

import com.example.model.Security;
import com.example.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    @Autowired
    private SecurityService service;

    @GetMapping
    public List<Security> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Security> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Security create(@RequestBody Security entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Security> update(@PathVariable Integer id, @RequestBody Security entity) {
        return service.findById(id).map(existing -> {
            existing.setExchangeId(entity.getExchangeId());
            existing.setSymbol(entity.getSymbol());
            existing.setName(entity.getName());
            existing.setType(entity.getType());
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
