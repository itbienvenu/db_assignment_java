package com.example.my_springboot_app.service;

import com.example.my_springboot_app.dto.ParticipantDTO;
import com.example.my_springboot_app.entity.Participant;
import com.example.my_springboot_app.repository.ParticipantRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    private final ParticipantRepository repository;

    public ParticipantService(ParticipantRepository repository) {
        this.repository = repository;
    }

    public List<ParticipantDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ParticipantDTO getById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    public ParticipantDTO create(ParticipantDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public ParticipantDTO update(Long id, ParticipantDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setAddress(dto.getAddress());
            existing.setContactDetails(dto.getContactDetails());
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ParticipantDTO toDTO(Participant entity) {
        ParticipantDTO dto = new ParticipantDTO();
        dto.setParticipantId(entity.getParticipantId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setContactDetails(entity.getContactDetails());
        return dto;
    }

    private Participant toEntity(ParticipantDTO dto) {
        Participant entity = new Participant();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setContactDetails(dto.getContactDetails());
        return entity;
    }
}
