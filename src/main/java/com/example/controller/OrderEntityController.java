package com.example.controller;

import com.example.model.OrderEntity;
import com.example.service.OrderEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderEntityController {

    @Autowired
    private OrderEntityService service;

    @GetMapping
    public List<OrderEntity> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OrderEntity create(@RequestBody OrderEntity entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> update(@PathVariable Integer id, @RequestBody OrderEntity entity) {
        return service.findById(id).map(existing -> {
            existing.setParticipantId(entity.getParticipantId());
            existing.setSecurityId(entity.getSecurityId());
            existing.setOrderType(entity.getOrderType());
            existing.setQuantity(entity.getQuantity());
            existing.setPrice(entity.getPrice());
            existing.setOrderTimestamp(entity.getOrderTimestamp());
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
