package com.bits.pilani.deliveryservice.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bits.pilani.deliveryservice.dto.DeliveryRequestDTO;
import com.bits.pilani.deliveryservice.entity.DeliveryHistory;
import com.bits.pilani.deliveryservice.service.DeliveryService;
import com.bits.pilani.deliveryservice.to.ResponseTO;
import com.bits.pilani.deliveryservice.to.SuccessResponseTO;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<ResponseTO> assignDelivery(@RequestBody DeliveryRequestDTO deliveryRequest) {
        return SuccessResponseTO.create(deliveryService.assignDelivery(deliveryRequest));
    }

    @PatchMapping("/{id}/accept")
    public ResponseEntity<ResponseTO> acceptDelivery(@PathVariable int id) {
        return SuccessResponseTO.create(deliveryService.acceptDelivery(id));
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<ResponseTO> rejectDelivery(@PathVariable int id) {
        return SuccessResponseTO.create(deliveryService.rejectDelivery(id));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<DeliveryHistory> completeDelivery(
        @PathVariable int id, @RequestParam float rating) {
        return ResponseEntity.ok(deliveryService.completeDelivery(id, rating));
    }
}

