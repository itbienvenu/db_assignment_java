package com.example.service;

import com.example.model.Security;
import com.example.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecurityService {

    @Autowired
    private SecurityRepository repository;

    public List<Security> findAll() {
        return repository.findAll();
    }

    public Optional<Security> findById(Integer id) {
        return repository.findById(id);
    }

    public Security save(Security entity) {
        return repository.save(entity);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
