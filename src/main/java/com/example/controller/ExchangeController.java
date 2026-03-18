package com.example.controller;

import com.example.model.Exchange;
import com.example.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService service;

    @GetMapping
    public List<Exchange> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exchange> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Exchange create(@RequestBody Exchange entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exchange> update(@PathVariable Integer id, @RequestBody Exchange entity) {
        return service.findById(id).map(existing -> {
            existing.setName(entity.getName());
            existing.setLocation(entity.getLocation());
            existing.setTradingStart(entity.getTradingStart());
            existing.setTradingEnd(entity.getTradingEnd());
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
