package com.bits.pilani.deliveryservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "rejected_delivery" , schema="public")
public class RejectedDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reject_id")
    private Integer rejectId;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @Column(name = "delivery_person_id")
    private Integer deliveryPersonId;

    @Column(name = "rejection_time", nullable = false)
    private LocalDateTime rejectionTime;
}
