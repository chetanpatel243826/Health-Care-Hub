package com.health.coverage_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.coverage_service.entity.Policy;
import com.health.coverage_service.service.PolicyService;

@RestController
@RequestMapping("/coverage")
public class PolicyController {
	
	@Autowired
    private PolicyService policyService;

    @GetMapping
    public ResponseEntity<?> getAll() {
    	List<Policy> policyList = policyService.getAllPolicies();
    	if(policyList != null && !policyList.isEmpty()) {
    		return new ResponseEntity(policyList,HttpStatus.OK);
    	}
    	return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Policy> getById(@PathVariable Long id) {
        Policy policy = policyService.getPolicyById(id);
        return policy != null ? ResponseEntity.ok(policy) : ResponseEntity.notFound().build();
    }
    
    @PostMapping()
    public ResponseEntity<?> setPolicy(@RequestBody Policy policy) {
    	try {
    		policyService.setPolicy(policy);
    		return new ResponseEntity<String>("Policy Saved successfully",HttpStatus.OK);
    	} catch(Exception e) {
    		return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
    	}
    }
}
