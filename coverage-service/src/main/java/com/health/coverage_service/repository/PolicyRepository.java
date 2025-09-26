package com.health.coverage_service.repository;

import org.springframework.stereotype.Repository;

import com.health.coverage_service.entity.Policy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy,Long>{
	 public List<Policy> findByPolicyHolder(String policyHolder);

}
