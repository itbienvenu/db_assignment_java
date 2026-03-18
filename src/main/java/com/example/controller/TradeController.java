package com.example.controller;

import com.example.model.Trade;
import com.example.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    @Autowired
    private TradeService service;

    @GetMapping
    public List<Trade> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Trade create(@RequestBody Trade entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trade> update(@PathVariable Integer id, @RequestBody Trade entity) {
        return service.findById(id).map(existing -> {
            existing.setBuyOrderId(entity.getBuyOrderId());
            existing.setSellOrderId(entity.getSellOrderId());
            existing.setQuantity(entity.getQuantity());
            existing.setPrice(entity.getPrice());
            existing.setTradeTimestamp(entity.getTradeTimestamp());
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
