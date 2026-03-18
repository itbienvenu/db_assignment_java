package com.example.service;

import com.example.model.Participant;
import com.example.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository repository;

    public List<Participant> findAll() {
        return repository.findAll();
    }

    public Optional<Participant> findById(Integer id) {
        return repository.findById(id);
    }

    public Participant save(Participant entity) {
        return repository.save(entity);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
