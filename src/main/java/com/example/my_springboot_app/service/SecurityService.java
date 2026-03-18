package com.example.my_springboot_app.service;

import com.example.my_springboot_app.dto.SecurityDTO;
import com.example.my_springboot_app.entity.Security;
import com.example.my_springboot_app.entity.Exchange;
import com.example.my_springboot_app.repository.SecurityRepository;
import com.example.my_springboot_app.repository.ExchangeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecurityService {

    private final SecurityRepository repository;
    private final ExchangeRepository exchangeRepository;

    public SecurityService(SecurityRepository repository, ExchangeRepository exchangeRepository) {
        this.repository = repository;
        this.exchangeRepository = exchangeRepository;
    }

    public List<SecurityDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public SecurityDTO getById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    public SecurityDTO create(SecurityDTO dto) {
        Security entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public SecurityDTO update(Long id, SecurityDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setSymbol(dto.getSymbol());
            existing.setName(dto.getName());
            existing.setType(dto.getType());
            if (dto.getExchangeId() != null) {
                Exchange exchange = exchangeRepository.findById(dto.getExchangeId()).orElse(null);
                existing.setExchange(exchange);
            }
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private SecurityDTO toDTO(Security entity) {
        SecurityDTO dto = new SecurityDTO();
        dto.setSecurityId(entity.getSecurityId());
        dto.setSymbol(entity.getSymbol());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        if (entity.getExchange() != null) {
            dto.setExchangeId(entity.getExchange().getExchangeId());
            dto.setExchangeName(entity.getExchange().getName());
        }
        return dto;
    }

    private Security toEntity(SecurityDTO dto) {
        Security entity = new Security();
        entity.setSymbol(dto.getSymbol());
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        if (dto.getExchangeId() != null) {
            Exchange exchange = exchangeRepository.findById(dto.getExchangeId()).orElse(null);
            entity.setExchange(exchange);
        }
        return entity;
    }
}
