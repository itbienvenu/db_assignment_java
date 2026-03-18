package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = \"Trades\")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = \"trade_id\")
    private Integer tradeId;
    @Column(name = \"buy_order_id\")
    private Integer buyOrderId;
    @Column(name = \"sell_order_id\")
    private Integer sellOrderId;
    @Column(name = \"quantity\")
    private Integer quantity;
    @Column(name = \"price\")
    private BigDecimal price;
    @Column(name = \"trade_timestamp\")
    private LocalDateTime tradeTimestamp;

    // Getters and Setters
    public Integer getTradeId() { return tradeId; }
    public void setTradeId(Integer tradeId) { this.tradeId = tradeId; }
    public Integer getBuyOrderId() { return buyOrderId; }
    public void setBuyOrderId(Integer buyOrderId) { this.buyOrderId = buyOrderId; }
    public Integer getSellOrderId() { return sellOrderId; }
    public void setSellOrderId(Integer sellOrderId) { this.sellOrderId = sellOrderId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public LocalDateTime getTradeTimestamp() { return tradeTimestamp; }
    public void setTradeTimestamp(LocalDateTime tradeTimestamp) { this.tradeTimestamp = tradeTimestamp; }
}
