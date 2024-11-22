package com.bits.pilani.deliveryservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.bits.pilani.deliveryservice.enums.DeliveryMessage;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Getter
@Setter
@Table(name = "delivery_history" , schema="public")
public class DeliveryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Integer deliveryId;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @Column(name = "delivery_person_id")
    private Integer deliveryPersonId;

    @Column(name = "delivered", nullable = false)
    private Boolean delivered = false;

    @Column(name = "message", length = 255, nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryMessage message = DeliveryMessage.NOT_PICKED_UP;

    @Column(name = "accepted", nullable = false)
    private Boolean accepted = false;

    @Column(name = "start_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @Column(name = "expected_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expectedTime;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "rejected", nullable = false)
    private Boolean rejected = false;
}
