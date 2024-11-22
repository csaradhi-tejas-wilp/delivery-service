package com.bits.pilani.deliveryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bits.pilani.deliveryservice.entity.DeliveryPersonnel;
import com.bits.pilani.deliveryservice.exception.CustomException;
import com.bits.pilani.deliveryservice.service.DeliveryPersonnelService;
import com.bits.pilani.deliveryservice.to.ResponseTO;
import com.bits.pilani.deliveryservice.to.SuccessResponseTO;

@RestController
@RequestMapping("/delivery/personnel")
public class DeliveryPersonnelController {

    @Autowired
    private DeliveryPersonnelService deliveryPersonnelService;

    @PostMapping
    public ResponseEntity<ResponseTO> addPersonnel(@RequestParam int deliveryPersonId) {
        
        DeliveryPersonnel personnel = new DeliveryPersonnel();
        personnel.setDeliveryPersonId(deliveryPersonId);
        personnel.setOverallRating(0f);
        personnel.setTakingDeliveries(true);
        personnel.setTotalDeliveries(0);
        return SuccessResponseTO.create(deliveryPersonnelService.addPersonnel(personnel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTO> getPersonnel(@PathVariable int id) throws CustomException {
        DeliveryPersonnel personnel = deliveryPersonnelService.getPersonnelById(id);
        if(personnel == null){
            throw new CustomException(HttpStatus.NOT_FOUND, "Delivery person not found");
        }
        return SuccessResponseTO.create(personnel);
    }

    @PatchMapping("/{id}/rating")
    public ResponseEntity<ResponseTO> updateRating(
        @PathVariable int id, @RequestParam float rating) throws CustomException {
        return SuccessResponseTO.create(deliveryPersonnelService.updateRating(id, rating));
    }
}

