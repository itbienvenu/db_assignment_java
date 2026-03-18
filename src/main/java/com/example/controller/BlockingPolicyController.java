package com.example.controller;

import com.example.model.BlockingPolicy;
import com.example.service.BlockingPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blockingpolicy")
public class BlockingPolicyController {

    @Autowired
    private BlockingPolicyService service;

    @GetMapping
    public List<BlockingPolicy> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlockingPolicy> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BlockingPolicy create(@RequestBody BlockingPolicy entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlockingPolicy> update(@PathVariable Integer id, @RequestBody BlockingPolicy entity) {
        return service.findById(id).map(existing -> {
            existing.setBlockerId(entity.getBlockerId());
            existing.setParticipantId(entity.getParticipantId());
            existing.setSecurityId(entity.getSecurityId());
            existing.setRestrictionType(entity.getRestrictionType());
            existing.setEffectiveFrom(entity.getEffectiveFrom());
            existing.setEffectiveTo(entity.getEffectiveTo());
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
