package com.bits.pilani.deliveryservice.messagequeue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bits.pilani.deliveryservice.entity.DeliveryHistory;
import com.bits.pilani.deliveryservice.repository.DeliveryHistoryRepo;

public class DeliveryActions {

    // @Autowired
    // private RabbitTemplate rabbitTemplate;

    // @Autowired
    // DeliveryHistoryRepo deliveryHistoryRepo;

    // public void rejectDelivery(int id) {
    //     DeliveryHistory delivery = deliveryHistoryRepo.findByDeliveryId(id);
    //     delivery.setDeliveryRejected(true);
    //     deliveryHistoryRepo.save(delivery);

    //     rabbitTemplate.convertAndSend("deliveryQueue", delivery.getOrderId());
    // }

    // @RabbitListener(queues = "deliveryQueue")
    // public void assignNewDelivery(Integer orderId) {
    //     // Logic to assign delivery to another personnel
    // }
}
