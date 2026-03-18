package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = \"Orders\")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = \"order_id\")
    private Integer orderId;
    @Column(name = \"participant_id\")
    private Integer participantId;
    @Column(name = \"security_id\")
    private Integer securityId;
    @Column(name = \"order_type\")
    private String orderType;
    @Column(name = \"quantity\")
    private Integer quantity;
    @Column(name = \"price\")
    private BigDecimal price;
    @Column(name = \"order_timestamp\")
    private LocalDateTime orderTimestamp;

    // Getters and Setters
    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public Integer getParticipantId() { return participantId; }
    public void setParticipantId(Integer participantId) { this.participantId = participantId; }
    public Integer getSecurityId() { return securityId; }
    public void setSecurityId(Integer securityId) { this.securityId = securityId; }
    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public LocalDateTime getOrderTimestamp() { return orderTimestamp; }
    public void setOrderTimestamp(LocalDateTime orderTimestamp) { this.orderTimestamp = orderTimestamp; }
}
