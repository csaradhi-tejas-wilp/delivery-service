package com.bits.pilani.deliveryservice.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bits.pilani.deliveryservice.convertor.DeliveryConvertor;
import com.bits.pilani.deliveryservice.dto.DeliveryRequestDTO;
import com.bits.pilani.deliveryservice.entity.DeliveryHistory;
import com.bits.pilani.deliveryservice.entity.DeliveryPersonnel;
import com.bits.pilani.deliveryservice.enums.DeliveryMessage;
import com.bits.pilani.deliveryservice.repository.DeliveryHistoryRepo;
import com.bits.pilani.deliveryservice.repository.DeliveryPersonnelRepo;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryHistoryRepo deliveryHistoryRepo;

    @Autowired
    private RejectedDeliveryService rejectedDeliveryService;

    @Autowired
    private DeliveryPersonnelRepo deliveryPersonnelRepo;

    public DeliveryHistory assignDelivery(DeliveryRequestDTO deliveryRequest) {
        deliveryRequest.setDelivered(false);
        deliveryRequest.setAccepted(false);
        deliveryRequest.setRejected(false);
        deliveryRequest.setMessage(DeliveryMessage.NOT_PICKED_UP);
        deliveryRequest.setStartTime(LocalDateTime.now());
        deliveryRequest.setEndTime(deliveryRequest.getEndTime());
        deliveryRequest.setExpectedTime(deliveryRequest.getExpectedTime());
        deliveryRequest.setDeliveryPersonId(assignDeliveryPersonnel());
        DeliveryHistory delivery = DeliveryConvertor.toDeliveryHistory(deliveryRequest);
        return deliveryHistoryRepo.save(delivery);
    }

    public DeliveryHistory acceptDelivery(int id) {
        DeliveryHistory delivery = deliveryHistoryRepo.findByDeliveryId(id);
        delivery.setAccepted(true);
        delivery.setMessage(DeliveryMessage.DELIVERY_ACCEPTED);

        DeliveryHistory savedDelivery = deliveryHistoryRepo.save(delivery);
        
        DeliveryPersonnel deliveryPersonnel = deliveryPersonnelRepo.findByDeliveryPersonId(savedDelivery.getDeliveryPersonId());
        deliveryPersonnel.setTakingDeliveries(false);
        deliveryPersonnelRepo.save(deliveryPersonnel);

        //remove all other deliveries with this delivery personnel id;
        unassignOtherDeliveries(savedDelivery.getDeliveryPersonId());
        return savedDelivery;
    }

    public DeliveryHistory rejectDelivery(int id) {
        DeliveryHistory delivery = deliveryHistoryRepo.findByDeliveryId(id);
        delivery.setMessage(DeliveryMessage.DELIVERY_REJECTED);
        delivery.setRejected(true);
        deliveryHistoryRepo.save(delivery);

        rejectedDeliveryService.logRejectedDelivery(delivery.getOrderId(), delivery.getDeliveryPersonId());
        DeliveryPersonnel deliveryPersonnel = deliveryPersonnelRepo.findByDeliveryPersonId(delivery.getDeliveryPersonId());
        deliveryPersonnel.setTakingDeliveries(true);
        deliveryPersonnelRepo.save(deliveryPersonnel);
        return delivery;
    }

    public DeliveryHistory completeDelivery(int id, float rating) {
        DeliveryHistory delivery = deliveryHistoryRepo.findByDeliveryId(id);
        delivery.setDelivered(true);
        delivery.setRating(rating);
        delivery.setMessage(DeliveryMessage.DELIVERED_SUCCESSFULLY);
        return deliveryHistoryRepo.save(delivery);
    }

    // private DeliveryHistory getDeliveryById(int id) {
    //     return deliveryHistoryRepo.findByDeliveryId(id);
    // }

    private int assignDeliveryPersonnel(){
        List<DeliveryPersonnel> availablePersonnel = deliveryPersonnelRepo.findByTakingDeliveriesTrue();

        if (availablePersonnel.isEmpty()) {
            throw new IllegalStateException("No available delivery personnel found.");
        }

        // Shuffle the list and return the first entry
        Collections.shuffle(availablePersonnel);

        return availablePersonnel.get(0).getDeliveryPersonId();
    }

    private void unassignOtherDeliveries(int deliveryPersonId){
        List<DeliveryHistory> deliveries = deliveryHistoryRepo.findPendingDeliveriesByDeliveryPersonId(deliveryPersonId);        
        deliveries.forEach(delivery -> {
            //TODO: Put the deliveries back in queue.
        });
    }

}
