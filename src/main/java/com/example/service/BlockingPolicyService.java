package com.example.service;

import com.example.model.BlockingPolicy;
import com.example.repository.BlockingPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlockingPolicyService {

    @Autowired
    private BlockingPolicyRepository repository;

    public List<BlockingPolicy> findAll() {
        return repository.findAll();
    }

    public Optional<BlockingPolicy> findById(Integer id) {
        return repository.findById(id);
    }

    public BlockingPolicy save(BlockingPolicy entity) {
        return repository.save(entity);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
