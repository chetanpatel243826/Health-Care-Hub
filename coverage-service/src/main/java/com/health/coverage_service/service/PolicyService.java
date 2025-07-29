package com.health.coverage_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.coverage_service.entity.Policy;
import com.health.coverage_service.repository.PolicyRepository;

@Service
public class PolicyService {
    @Autowired
    private PolicyRepository repository;

    public List<Policy> getAllPolicies() {
        return repository.findAll();
    }

    public Policy getPolicyById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
