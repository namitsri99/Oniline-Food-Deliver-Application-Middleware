package com.cg.ofda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ofda.entity.BillEntity;

@Repository
public interface IBillRepository extends JpaRepository<BillEntity, Long>{
	
	List<BillEntity> findAllByBillId(Long bilId);

	
}