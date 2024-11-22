package com.bits.pilani.deliveryservice.repository;

import com.bits.pilani.deliveryservice.entity.DeliveryHistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryHistoryRepo extends JpaRepository<DeliveryHistory, Integer> {

    public DeliveryHistory findByDeliveryId(int deliveryId);

    @Query("SELECT dh FROM DeliveryHistory dh " +
           "WHERE dh.deliveryPersonId = :deliveryPersonId " +
           "AND dh.accepted = false " +
           "AND dh.rejected = false")
    List<DeliveryHistory> findPendingDeliveriesByDeliveryPersonId(Integer deliveryPersonId);
}
