package com.health.coverage_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Transactional
    public void setPolicy(Policy policy) throws Exception {
    	if(policy == null) {
    		throw new Exception("Policy details are not present");
    	}
    	Policy tempPolicy = repository.findByPolicyNumber(policy.getPolicyNumber());
    	if(tempPolicy != null) {
    		throw new Exception("Policy number is already present");
    	}
    	repository.save(policy);
    	
    }
    
}
