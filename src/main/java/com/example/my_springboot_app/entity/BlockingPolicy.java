package com.example.my_springboot_app.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "BlockingPolicies")
public class BlockingPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;

    @ManyToOne
    @JoinColumn(name = "blocker_id", nullable = false)
    private Blocker blocker;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "security_id", nullable = false)
    private Security security;

    @Column(nullable = false)
    private String restrictionType;

    private LocalDate effectiveFrom;

    private LocalDate effectiveTo;

    // Getters and Setters
    public Long getPolicyId() { return policyId; }
    public void setPolicyId(Long policyId) { this.policyId = policyId; }

    public Blocker getBlocker() { return blocker; }
    public void setBlocker(Blocker blocker) { this.blocker = blocker; }

    public Participant getParticipant() { return participant; }
    public void setParticipant(Participant participant) { this.participant = participant; }

    public Security getSecurity() { return security; }
    public void setSecurity(Security security) { this.security = security; }

    public String getRestrictionType() { return restrictionType; }
    public void setRestrictionType(String restrictionType) { this.restrictionType = restrictionType; }

    public LocalDate getEffectiveFrom() { return effectiveFrom; }
    public void setEffectiveFrom(LocalDate effectiveFrom) { this.effectiveFrom = effectiveFrom; }

    public LocalDate getEffectiveTo() { return effectiveTo; }
    public void setEffectiveTo(LocalDate effectiveTo) { this.effectiveTo = effectiveTo; }
}
