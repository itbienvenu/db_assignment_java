package com.example.my_springboot_app.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Trades")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tradeId;

    @ManyToOne
    @JoinColumn(name = "buy_order_id", nullable = false)
    private OrderEntity buyOrder;

    @ManyToOne
    @JoinColumn(name = "sell_order_id", nullable = false)
    private OrderEntity sellOrder;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 12, scale = 4)
    private BigDecimal price;

    private LocalDateTime tradeTimestamp = LocalDateTime.now();

    // Getters and Setters
    public Long getTradeId() { return tradeId; }
    public void setTradeId(Long tradeId) { this.tradeId = tradeId; }

    public OrderEntity getBuyOrder() { return buyOrder; }
    public void setBuyOrder(OrderEntity buyOrder) { this.buyOrder = buyOrder; }

    public OrderEntity getSellOrder() { return sellOrder; }
    public void setSellOrder(OrderEntity sellOrder) { this.sellOrder = sellOrder; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public LocalDateTime getTradeTimestamp() { return tradeTimestamp; }
    public void setTradeTimestamp(LocalDateTime tradeTimestamp) { this.tradeTimestamp = tradeTimestamp; }
}
