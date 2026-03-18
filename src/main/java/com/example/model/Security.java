package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = \"Securities\")
public class Security {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = \"security_id\")
    private Integer securityId;
    @Column(name = \"exchange_id\")
    private Integer exchangeId;
    @Column(name = \"symbol\")
    private String symbol;
    @Column(name = \"name\")
    private String name;
    @Column(name = \"type\")
    private String type;

    // Getters and Setters
    public Integer getSecurityId() { return securityId; }
    public void setSecurityId(Integer securityId) { this.securityId = securityId; }
    public Integer getExchangeId() { return exchangeId; }
    public void setExchangeId(Integer exchangeId) { this.exchangeId = exchangeId; }
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
