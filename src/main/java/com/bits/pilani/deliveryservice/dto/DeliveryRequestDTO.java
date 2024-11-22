package com.bits.pilani.deliveryservice.dto;

import java.time.Duration;
import java.time.LocalDateTime;

import com.bits.pilani.deliveryservice.enums.DeliveryMessage;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryRequestDTO {

    private Integer deliveryId;

    private Integer orderId;

    private Integer deliveryPersonId;

    private Boolean delivered;

    private DeliveryMessage message;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expectedTime;

    private Boolean rejected;

    private Boolean accepted;
}
