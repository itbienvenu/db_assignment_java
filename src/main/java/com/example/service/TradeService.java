package com.example.service;

import com.example.model.Trade;
import com.example.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private TradeRepository repository;

    public List<Trade> findAll() {
        return repository.findAll();
    }

    public Optional<Trade> findById(Integer id) {
        return repository.findById(id);
    }

    public Trade save(Trade entity) {
        return repository.save(entity);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
