package com.example.my_springboot_app.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Exchanges")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exchangeId;

    @Column(nullable = false)
    private String name;

    private String location;

    private LocalTime tradingStart;

    private LocalTime tradingEnd;

    @OneToMany(mappedBy = "exchange", cascade = CascadeType.ALL)
    private List<Security> securities;

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

    public List<Security> getSecurities() { return securities; }
    public void setSecurities(List<Security> securities) { this.securities = securities; }
}
