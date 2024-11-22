package com.bits.pilani.deliveryservice.messagequeue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    // @RabbitListener(queues = "${queue.order-ready}")
    // public void handleOrderReady(int orderId) {
    //     System.out.println("Order ready for pickup: " + orderId);
        
    // }

    
}
