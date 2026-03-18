package com.example.service;

import com.example.model.Position;
import com.example.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    @Autowired
    private PositionRepository repository;

    public List<Position> findAll() {
        return repository.findAll();
    }

    public Optional<Position> findById(Integer id) {
        return repository.findById(id);
    }

    public Position save(Position entity) {
        return repository.save(entity);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
