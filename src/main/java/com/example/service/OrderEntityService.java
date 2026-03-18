package com.example.service;

import com.example.model.OrderEntity;
import com.example.repository.OrderEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderEntityService {

    @Autowired
    private OrderEntityRepository repository;

    public List<OrderEntity> findAll() {
        return repository.findAll();
    }

    public Optional<OrderEntity> findById(Integer id) {
        return repository.findById(id);
    }

    public OrderEntity save(OrderEntity entity) {
        return repository.save(entity);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
