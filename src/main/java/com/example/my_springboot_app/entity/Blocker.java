package com.example.my_springboot_app.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Blockers")
public class Blocker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blockerId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "blocker")
    private List<BlockingPolicy> policies;

    // Getters and Setters
    public Long getBlockerId() { return blockerId; }
    public void setBlockerId(Long blockerId) { this.blockerId = blockerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<BlockingPolicy> getPolicies() { return policies; }
    public void setPolicies(List<BlockingPolicy> policies) { this.policies = policies; }
}
