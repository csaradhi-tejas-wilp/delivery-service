package com.bits.pilani.deliveryservice.repository;

import com.bits.pilani.deliveryservice.entity.DeliveryPersonnel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPersonnelRepo extends JpaRepository<DeliveryPersonnel, Integer> {

    public DeliveryPersonnel findByDeliveryPersonId(int id);

    public List<DeliveryPersonnel> findByTakingDeliveriesTrue();

}
