package com.bits.pilani.deliveryservice.convertor;

import com.bits.pilani.deliveryservice.dto.DeliveryRequestDTO;
import com.bits.pilani.deliveryservice.entity.DeliveryHistory;

public class DeliveryConvertor {

    public static DeliveryHistory toDeliveryHistory(DeliveryRequestDTO deliveryRequestDTO){
        DeliveryHistory deliveryHistory = new DeliveryHistory();

        deliveryHistory.setDeliveryId(deliveryRequestDTO.getDeliveryId());
        deliveryHistory.setOrderId(deliveryRequestDTO.getOrderId());
        deliveryHistory.setDelivered(deliveryRequestDTO.getDelivered());
        deliveryHistory.setAccepted(deliveryRequestDTO.getAccepted());
        deliveryHistory.setMessage(deliveryRequestDTO.getMessage());
        deliveryHistory.setDeliveryPersonId(deliveryRequestDTO.getDeliveryPersonId());
        deliveryHistory.setRejected(deliveryRequestDTO.getRejected());
        deliveryHistory.setStartTime(deliveryRequestDTO.getStartTime());
        deliveryHistory.setEndTime(deliveryRequestDTO.getEndTime());
        deliveryHistory.setExpectedTime(deliveryRequestDTO.getExpectedTime());

        return deliveryHistory;
    }
}
