package com.example.service;

import com.example.model.Blocker;
import com.example.repository.BlockerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlockerService {

    @Autowired
    private BlockerRepository repository;

    public List<Blocker> findAll() {
        return repository.findAll();
    }

    public Optional<Blocker> findById(Integer id) {
        return repository.findById(id);
    }

    public Blocker save(Blocker entity) {
        return repository.save(entity);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
