package com.bits.pilani.deliveryservice.messagequeue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bits.pilani.deliveryservice.dto.DeliveryRequestDTO;
import com.bits.pilani.deliveryservice.dto.OrderConsumerObj;
import com.bits.pilani.deliveryservice.service.DeliveryService;

@Service
public class OrderConsumer {

    @Autowired
    DeliveryService deliveryService;

    @RabbitListener(queues = "${queue.order}")
    public void handleOrderReady(String orderConsumerString) {
        
        try {
            if (orderConsumerString.startsWith("\"") && orderConsumerString.endsWith("\"")) {
                // Remove the extra quotes
                orderConsumerString = orderConsumerString.substring(1, orderConsumerString.length() - 1);
            }
            String[] parts = orderConsumerString.split("\\|");

            int orderId = Integer.parseInt(parts[0]);

            LocalDateTime expectedTime = LocalDateTime.parse(parts[1]);
            OrderConsumerObj orderConsumerObj = new OrderConsumerObj(orderId, expectedTime);
            System.out.println("Order ready for pickup: " + orderConsumerObj.getOrderId());
        
            DeliveryRequestDTO deliveryRequestDTO = new DeliveryRequestDTO(orderConsumerObj.getOrderId(), orderConsumerObj.getExpectedTime());

            deliveryService.assignDelivery(deliveryRequestDTO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }

    
}
