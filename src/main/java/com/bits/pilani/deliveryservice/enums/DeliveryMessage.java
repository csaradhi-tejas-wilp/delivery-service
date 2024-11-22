package com.bits.pilani.deliveryservice.enums;

public enum DeliveryMessage {

    DELIVERY_ACCEPTED,      // Delivery was accepted by delivery personnel
    NOT_PICKED_UP,         // Default status when the delivery is created
    READY_FOR_PICKUP,      // Delivery is prepared and ready to be picked up by delivery personnel
    PICKED_UP,             // Delivery has been picked up by the assigned delivery personnel
    IN_TRANSIT,            // Delivery is currently being transported to the destination
    DELIVERED_SUCCESSFULLY, // Delivery has been successfully completed
    DELIVERY_FAILED,       // Delivery attempt failed (e.g., customer unavailable)
    CUSTOMER_NOT_AVAILABLE, // Delivery personnel could not reach the customer
    ORDER_CANCELLED,       // Order was canceled before delivery
    DELIVERY_REJECTED,     // Delivery was rejected by delivery personnel
    REASSIGNED,            // Delivery was reassigned to another delivery personnel
    RETURNED_TO_RESTAURANT, // Delivery was returned to the restaurant
    DELAYED,               // Delivery is delayed due to unforeseen reasons
    OUT_FOR_DELIVERY       // Delivery is on its way to the destination
}
