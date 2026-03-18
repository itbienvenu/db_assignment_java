package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = \"BlockingPolicies\")
public class BlockingPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = \"policy_id\")
    private Integer policyId;
    @Column(name = \"blocker_id\")
    private Integer blockerId;
    @Column(name = \"participant_id\")
    private Integer participantId;
    @Column(name = \"security_id\")
    private Integer securityId;
    @Column(name = \"restriction_type\")
    private String restrictionType;
    @Column(name = \"effective_from\")
    private LocalDate effectiveFrom;
    @Column(name = \"effective_to\")
    private LocalDate effectiveTo;

    // Getters and Setters
    public Integer getPolicyId() { return policyId; }
    public void setPolicyId(Integer policyId) { this.policyId = policyId; }
    public Integer getBlockerId() { return blockerId; }
    public void setBlockerId(Integer blockerId) { this.blockerId = blockerId; }
    public Integer getParticipantId() { return participantId; }
    public void setParticipantId(Integer participantId) { this.participantId = participantId; }
    public Integer getSecurityId() { return securityId; }
    public void setSecurityId(Integer securityId) { this.securityId = securityId; }
    public String getRestrictionType() { return restrictionType; }
    public void setRestrictionType(String restrictionType) { this.restrictionType = restrictionType; }
    public LocalDate getEffectiveFrom() { return effectiveFrom; }
    public void setEffectiveFrom(LocalDate effectiveFrom) { this.effectiveFrom = effectiveFrom; }
    public LocalDate getEffectiveTo() { return effectiveTo; }
    public void setEffectiveTo(LocalDate effectiveTo) { this.effectiveTo = effectiveTo; }
}
