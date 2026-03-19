package com.crimson.projectred.service;

import com.crimson.projectred.constant.NotificationTemplate;
import com.crimson.projectred.dto.events.NotificationEventDTO;
import com.crimson.projectred.dto.events.NotificationStatusDTO;
import com.crimson.projectred.enums.types.NotificationStatusTP;
import com.crimson.projectred.exception.cust.BusinessException;
import com.crimson.projectred.model.Order;
import com.crimson.projectred.model.NotificationEvent;
import com.crimson.projectred.repository.NotificationEventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final KafkaTemplate<String, NotificationEventDTO> kafkaTemplate;
    private final NotificationEventRepository notificationEventRepository;


    @Transactional
    public void sendConfirmationNotification(Order order) {

        NotificationEvent notificationEvent = new NotificationEvent();
        notificationEvent.setCustomer(order.getCustomer());
        notificationEvent.setOrder(order);
        notificationEvent.setNotificationStatusTP(NotificationStatusTP.ACTIVE);
        notificationEvent.setNotificationTemplate(NotificationTemplate.ORDER_CONFIRMATION);
        notificationEvent.setRetries(0);
        notificationEventRepository.save(notificationEvent);
        Map<String,Object> notificationData = new HashMap<>();
        notificationData.put("orderId", order.getOrderId());
        notificationData.put("orderTotal", order.getActualTotalPrice());
        notificationData.put("companyName", order.getShipping().getShipmentOption().getCompanyName());
        notificationData.put("daysForArrival", order.getShipping().getDaysForArrival());
        notificationData.put("shippingLink", order.getShipping().getShippingTrackingLink());
        notificationData.put("orderItems", order.getOrderItems().toString());
        notificationData.put("customerName", order.getCustomer().getFirstName());

        NotificationEventDTO event = new NotificationEventDTO(
                notificationEvent.getNotificationId(),
                order.getCustomer().getEmail(),
                order.getCustomer().getPhoneNumber(),
                notificationData,
                NotificationTemplate.ORDER_CONFIRMATION
        );

        kafkaTemplate.send("notification-events", order.getCustomer().getCustomerId().toString(), event);
    }
    @KafkaListener(topics = "notification-status-events",groupId = "notification-events")
    @Transactional
    public void consumeNotificationStatus(NotificationStatusDTO notificationStatusDTO) throws BusinessException {
        NotificationEvent event = notificationEventRepository.findById(notificationStatusDTO.notificationId()).orElseThrow(()-> new BusinessException("Event not Found"));
        if(notificationStatusDTO.status().equals("Success")){
            event.setNotificationStatusTP(NotificationStatusTP.SENT);
        }else if(event.getRetries() == 5){
            event.setNotificationStatusTP(NotificationStatusTP.CANCELLED);
        }else{
            event.setNotificationStatusTP(NotificationStatusTP.FAILED);
        }
        event.setRetries(event.getRetries()+1);
    }


}
