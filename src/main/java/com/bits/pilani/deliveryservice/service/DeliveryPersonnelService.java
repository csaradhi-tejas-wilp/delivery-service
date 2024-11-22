package com.bits.pilani.deliveryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bits.pilani.deliveryservice.entity.DeliveryPersonnel;
import com.bits.pilani.deliveryservice.exception.CustomException;
import com.bits.pilani.deliveryservice.repository.DeliveryPersonnelRepo;

@Service
public class DeliveryPersonnelService {

    @Autowired
    private DeliveryPersonnelRepo deliveryPersonnelRepo;

    public DeliveryPersonnel addPersonnel(DeliveryPersonnel personnel) {
        return deliveryPersonnelRepo.save(personnel);
    }

    public DeliveryPersonnel getPersonnelById(int id) {
        return deliveryPersonnelRepo.findByDeliveryPersonId(id);
    }

    public DeliveryPersonnel updateRating(int id, float rating) throws CustomException {
        validateRating(rating);
        DeliveryPersonnel personnel = getPersonnelById(id);
        personnel.setTotalDeliveries(personnel.getTotalDeliveries() + 1);
        personnel.setOverallRating(
            (personnel.getOverallRating() * (personnel.getTotalDeliveries() - 1) + rating) /
            personnel.getTotalDeliveries()
        );
        return deliveryPersonnelRepo.save(personnel);
    }

    private void validateRating(float rating) throws CustomException{
        if(rating < 1.0 || rating > 5.0){
            throw new CustomException(HttpStatus.BAD_REQUEST, "Invalid rating provided. Please provide a rating between 1 and 5");
        }
    }
}

