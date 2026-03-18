package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = \"Exchanges\")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = \"exchange_id\")
    private Integer exchangeId;
    @Column(name = \"name\")
    private String name;
    @Column(name = \"location\")
    private String location;
    @Column(name = \"trading_start\")
    private LocalTime tradingStart;
    @Column(name = \"trading_end\")
    private LocalTime tradingEnd;

    // Getters and Setters
    public Integer getExchangeId() { return exchangeId; }
    public void setExchangeId(Integer exchangeId) { this.exchangeId = exchangeId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public LocalTime getTradingStart() { return tradingStart; }
    public void setTradingStart(LocalTime tradingStart) { this.tradingStart = tradingStart; }
    public LocalTime getTradingEnd() { return tradingEnd; }
    public void setTradingEnd(LocalTime tradingEnd) { this.tradingEnd = tradingEnd; }
}
