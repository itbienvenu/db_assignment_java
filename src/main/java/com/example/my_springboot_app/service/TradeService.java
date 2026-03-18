package com.example.my_springboot_app.service;

import com.example.my_springboot_app.dto.TradeDTO;
import com.example.my_springboot_app.entity.Trade;
import com.example.my_springboot_app.repository.TradeRepository;
import com.example.my_springboot_app.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeService {

    private final TradeRepository repository;
    private final OrderRepository orderRepository;

    public TradeService(TradeRepository repository, OrderRepository orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }

    public List<TradeDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TradeDTO getById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    public TradeDTO create(TradeDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public TradeDTO update(Long id, TradeDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setQuantity(dto.getQuantity());
            existing.setPrice(dto.getPrice());
            if (dto.getBuyOrderId() != null) {
                existing.setBuyOrder(orderRepository.findById(dto.getBuyOrderId()).orElse(null));
            }
            if (dto.getSellOrderId() != null) {
                existing.setSellOrder(orderRepository.findById(dto.getSellOrderId()).orElse(null));
            }
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private TradeDTO toDTO(Trade entity) {
        TradeDTO dto = new TradeDTO();
        dto.setTradeId(entity.getTradeId());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        dto.setTradeTimestamp(entity.getTradeTimestamp());
        if (entity.getBuyOrder() != null) dto.setBuyOrderId(entity.getBuyOrder().getOrderId());
        if (entity.getSellOrder() != null) dto.setSellOrderId(entity.getSellOrder().getOrderId());
        return dto;
    }

    private Trade toEntity(TradeDTO dto) {
        Trade entity = new Trade();
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        if (dto.getBuyOrderId() != null) {
            entity.setBuyOrder(orderRepository.findById(dto.getBuyOrderId()).orElse(null));
        }
        if (dto.getSellOrderId() != null) {
            entity.setSellOrder(orderRepository.findById(dto.getSellOrderId()).orElse(null));
        }
        return entity;
    }
}
