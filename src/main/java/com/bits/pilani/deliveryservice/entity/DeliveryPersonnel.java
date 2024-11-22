package com.bits.pilani.deliveryservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "delivery_personnel" , schema="public")
public class DeliveryPersonnel {

    @Id
    @Column(nullable = false, name = "delivery_person_id")
    private Integer deliveryPersonId;

    @Column(nullable = false, name = "taking_deliveries")
    private Boolean takingDeliveries;

    @Column(nullable = false, name = "total_deliveries")
    private Integer totalDeliveries;

    @Column(nullable = false, name = "overall_rating")
    private Float overallRating;

}
