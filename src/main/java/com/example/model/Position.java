package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = \"Positions\")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = \"position_id\")
    private Integer positionId;
    @Column(name = \"participant_id\")
    private Integer participantId;
    @Column(name = \"security_id\")
    private Integer securityId;
    @Column(name = \"quantity\")
    private Integer quantity;
    @Column(name = \"average_price\")
    private BigDecimal averagePrice;
    @Column(name = \"entry_date\")
    private LocalDate entryDate;
    @Column(name = \"exit_date\")
    private LocalDate exitDate;

    // Getters and Setters
    public Integer getPositionId() { return positionId; }
    public void setPositionId(Integer positionId) { this.positionId = positionId; }
    public Integer getParticipantId() { return participantId; }
    public void setParticipantId(Integer participantId) { this.participantId = participantId; }
    public Integer getSecurityId() { return securityId; }
    public void setSecurityId(Integer securityId) { this.securityId = securityId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public BigDecimal getAveragePrice() { return averagePrice; }
    public void setAveragePrice(BigDecimal averagePrice) { this.averagePrice = averagePrice; }
    public LocalDate getEntryDate() { return entryDate; }
    public void setEntryDate(LocalDate entryDate) { this.entryDate = entryDate; }
    public LocalDate getExitDate() { return exitDate; }
    public void setExitDate(LocalDate exitDate) { this.exitDate = exitDate; }
}
