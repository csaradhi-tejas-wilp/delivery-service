package com.bits.pilani.deliveryservice.controller;

import static com.bits.pilani.deliveryservice.exception.CustomException.handleException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bits.pilani.deliveryservice.service.DeliveryService;
import com.bits.pilani.deliveryservice.to.DeliveryTO;
import com.bits.pilani.deliveryservice.exception.CustomException;
import com.bits.pilani.deliveryservice.security.Authorize;
import com.bits.pilani.deliveryservice.security.Role;
import com.bits.pilani.deliveryservice.to.ResponseTO;
import com.bits.pilani.deliveryservice.to.SuccessResponseTO;

@RestController
@ResponseBody
@RequestMapping("/delivery")
public class DeliveryServiceController {

	@Autowired
	DeliveryService deliveryService;

	@Authorize(roles = { Role.DELIVERY_PERSONNEL })
	@GetMapping("/{orderId}")
	public ResponseEntity<ResponseTO> getDeliveryDetailsByOrderId(@PathVariable int orderId) throws CustomException {

		try {
			var user = deliveryService.getDeliveryByOrderId(orderId);
			return SuccessResponseTO.create(user);
		} catch (CustomException e) {
			return handleException(e);
		}
	}

	@Authorize(roles = { Role.DELIVERY_PERSONNEL })
	@GetMapping
	public ResponseEntity<ResponseTO> getAllDeliveryDetails() throws CustomException {
		var user = deliveryService.getAllDeliveryDetails();
		return SuccessResponseTO.create(user);
	}

	@Authorize(roles = { Role.DELIVERY_PERSONNEL })
	@PostMapping
	public ResponseEntity<ResponseTO> newDeliveryDetails(@RequestBody DeliveryTO deliveryTO) throws CustomException {
		var user = deliveryService.newDeliveryDetails(deliveryTO);
		return SuccessResponseTO.create(user);
	}

	@Authorize(roles = { Role.DELIVERY_PERSONNEL })
	@PutMapping("/{orderId}")
	public ResponseEntity<ResponseTO> updateDeliveryDetails(@PathVariable int orderId,
			@RequestBody DeliveryTO deliveryTO) throws CustomException {
		try {
			var user = deliveryService.updateDeliveryByOrderId(deliveryTO, orderId);
			return SuccessResponseTO.create(user);
		} catch (CustomException e) {
			return handleException(e);
		}
	}
}
