package com.example.service;

import com.example.model.Exchange;
import com.example.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository repository;

    public List<Exchange> findAll() {
        return repository.findAll();
    }

    public Optional<Exchange> findById(Integer id) {
        return repository.findById(id);
    }

    public Exchange save(Exchange entity) {
        return repository.save(entity);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
