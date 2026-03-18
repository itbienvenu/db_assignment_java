package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = \"Blockers\")
public class Blocker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = \"blocker_id\")
    private Integer blockerId;
    @Column(name = \"name\")
    private String name;

    // Getters and Setters
    public Integer getBlockerId() { return blockerId; }
    public void setBlockerId(Integer blockerId) { this.blockerId = blockerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
