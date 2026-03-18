package com.example.my_springboot_app.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TradeDTO {
    private Long tradeId;
    private Long buyOrderId;
    private Long sellOrderId;
    private Integer quantity;
    private BigDecimal price;
    private LocalDateTime tradeTimestamp;

    // Getters and Setters
    public Long getTradeId() { return tradeId; }
    public void setTradeId(Long tradeId) { this.tradeId = tradeId; }

    public Long getBuyOrderId() { return buyOrderId; }
    public void setBuyOrderId(Long buyOrderId) { this.buyOrderId = buyOrderId; }

    public Long getSellOrderId() { return sellOrderId; }
    public void setSellOrderId(Long sellOrderId) { this.sellOrderId = sellOrderId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public LocalDateTime getTradeTimestamp() { return tradeTimestamp; }
    public void setTradeTimestamp(LocalDateTime tradeTimestamp) { this.tradeTimestamp = tradeTimestamp; }
}
