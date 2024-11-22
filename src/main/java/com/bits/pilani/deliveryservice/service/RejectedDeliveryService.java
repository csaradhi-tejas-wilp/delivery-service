package com.bits.pilani.deliveryservice.service;

import com.bits.pilani.deliveryservice.entity.RejectedDelivery;
import com.bits.pilani.deliveryservice.repository.RejectedDeliveryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RejectedDeliveryService {

    @Autowired
    private RejectedDeliveryRepo rejectedDeliveryRepo;


    public void logRejectedDelivery(Integer orderId, Integer deliveryPersonId) {
        RejectedDelivery rejectedDelivery = new RejectedDelivery();
        rejectedDelivery.setOrderId(orderId);
        rejectedDelivery.setDeliveryPersonId(deliveryPersonId);
        rejectedDelivery.setRejectionTime(LocalDateTime.now());
        rejectedDeliveryRepo.save(rejectedDelivery);

        // Requeue the order using RabbitMQ (example, assuming RabbitMQ setup)
        // messageQueueService.sendToQueue(orderId);
    }
}

