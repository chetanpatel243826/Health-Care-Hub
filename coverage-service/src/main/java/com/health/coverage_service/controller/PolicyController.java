package com.health.coverage_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.coverage_service.entity.Policy;
import com.health.coverage_service.service.PolicyService;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {
	
	@Autowired
    private PolicyService policyService;

    @GetMapping
    public List<Policy> getAll() {
        return policyService.getAllPolicies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Policy> getById(@PathVariable Long id) {
        Policy policy = policyService.getPolicyById(id);
        return policy != null ? ResponseEntity.ok(policy) : ResponseEntity.notFound().build();
    }
}
