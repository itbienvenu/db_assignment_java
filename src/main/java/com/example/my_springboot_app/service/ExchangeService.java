package com.example.my_springboot_app.service;

import com.example.my_springboot_app.dto.ExchangeDTO;
import com.example.my_springboot_app.entity.Exchange;
import com.example.my_springboot_app.repository.ExchangeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeService {

    private final ExchangeRepository repository;

    public ExchangeService(ExchangeRepository repository) {
        this.repository = repository;
    }

    public List<ExchangeDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ExchangeDTO getById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    public ExchangeDTO create(ExchangeDTO dto) {
        Exchange entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public ExchangeDTO update(Long id, ExchangeDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setLocation(dto.getLocation());
            existing.setTradingStart(dto.getTradingStart());
            existing.setTradingEnd(dto.getTradingEnd());
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ExchangeDTO toDTO(Exchange entity) {
        ExchangeDTO dto = new ExchangeDTO();
        dto.setExchangeId(entity.getExchangeId());
        dto.setName(entity.getName());
        dto.setLocation(entity.getLocation());
        dto.setTradingStart(entity.getTradingStart());
        dto.setTradingEnd(entity.getTradingEnd());
        return dto;
    }

    private Exchange toEntity(ExchangeDTO dto) {
        Exchange entity = new Exchange();
        entity.setName(dto.getName());
        entity.setLocation(dto.getLocation());
        entity.setTradingStart(dto.getTradingStart());
        entity.setTradingEnd(dto.getTradingEnd());
        return entity;
    }
}
