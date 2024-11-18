package com.bits.pilani.deliveryservice.dao;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.bits.pilani.deliveryservice.entity.DeliveryDetailsEntity;

public interface DeliveryDao extends ListCrudRepository<DeliveryDetailsEntity, Integer> {
	
	Optional<DeliveryDetailsEntity> findById(int id);
	
	
}
