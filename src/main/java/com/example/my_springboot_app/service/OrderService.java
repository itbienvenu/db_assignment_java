package com.example.my_springboot_app.service;

import com.example.my_springboot_app.dto.OrderDTO;
import com.example.my_springboot_app.entity.OrderEntity;
import com.example.my_springboot_app.repository.OrderRepository;
import com.example.my_springboot_app.repository.ParticipantRepository;
import com.example.my_springboot_app.repository.SecurityRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final ParticipantRepository participantRepository;
    private final SecurityRepository securityRepository;

    public OrderService(OrderRepository repository, ParticipantRepository participantRepository, SecurityRepository securityRepository) {
        this.repository = repository;
        this.participantRepository = participantRepository;
        this.securityRepository = securityRepository;
    }

    public List<OrderDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public OrderDTO getById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    public OrderDTO create(OrderDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public OrderDTO update(Long id, OrderDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setOrderType(dto.getOrderType());
            existing.setQuantity(dto.getQuantity());
            existing.setPrice(dto.getPrice());
            if (dto.getParticipantId() != null) {
                existing.setParticipant(participantRepository.findById(dto.getParticipantId()).orElse(null));
            }
            if (dto.getSecurityId() != null) {
                existing.setSecurity(securityRepository.findById(dto.getSecurityId()).orElse(null));
            }
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private OrderDTO toDTO(OrderEntity entity) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(entity.getOrderId());
        dto.setOrderType(entity.getOrderType());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        dto.setOrderTimestamp(entity.getOrderTimestamp());
        if (entity.getParticipant() != null) {
            dto.setParticipantId(entity.getParticipant().getParticipantId());
            dto.setParticipantName(entity.getParticipant().getName());
        }
        if (entity.getSecurity() != null) {
            dto.setSecurityId(entity.getSecurity().getSecurityId());
            dto.setSecuritySymbol(entity.getSecurity().getSymbol());
        }
        return dto;
    }

    private OrderEntity toEntity(OrderDTO dto) {
        OrderEntity entity = new OrderEntity();
        entity.setOrderType(dto.getOrderType());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        if (dto.getParticipantId() != null) {
            entity.setParticipant(participantRepository.findById(dto.getParticipantId()).orElse(null));
        }
        if (dto.getSecurityId() != null) {
            entity.setSecurity(securityRepository.findById(dto.getSecurityId()).orElse(null));
        }
        return entity;
    }
}
