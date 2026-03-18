package com.example.my_springboot_app.dto;

import java.time.LocalTime;

public class ExchangeDTO {
    private Long exchangeId;
    private String name;
    private String location;
    private LocalTime tradingStart;
    private LocalTime tradingEnd;

    // Getters and Setters
    public Long getExchangeId() { return exchangeId; }
    public void setExchangeId(Long exchangeId) { this.exchangeId = exchangeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalTime getTradingStart() { return tradingStart; }
    public void setTradingStart(LocalTime tradingStart) { this.tradingStart = tradingStart; }

    public LocalTime getTradingEnd() { return tradingEnd; }
    public void setTradingEnd(LocalTime tradingEnd) { this.tradingEnd = tradingEnd; }
}
