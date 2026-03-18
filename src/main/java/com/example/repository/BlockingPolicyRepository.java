package com.example.repository;

import com.example.model.BlockingPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockingPolicyRepository extends JpaRepository<BlockingPolicy, Integer> {
}
