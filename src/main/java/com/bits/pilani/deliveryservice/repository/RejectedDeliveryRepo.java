package com.bits.pilani.deliveryservice.repository;

import com.bits.pilani.deliveryservice.entity.RejectedDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RejectedDeliveryRepo extends JpaRepository<RejectedDelivery, Integer> {
}
