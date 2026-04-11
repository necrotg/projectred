package com.crimson.projectred.service;

import com.crimson.projectred.config.ApplicationProperties;
import com.crimson.projectred.constant.NotificationTemplate;
import com.crimson.projectred.dto.events.NotificationEventDTO;
import com.crimson.projectred.dto.events.NotificationStatusDTO;
import com.crimson.projectred.enums.types.NotificationStatusTP;
import com.crimson.projectred.exception.cust.BusinessException;
import com.crimson.projectred.model.Order;
import com.crimson.projectred.model.NotificationEvent;
import com.crimson.projectred.repository.NotificationEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final KafkaTemplate<String, NotificationEventDTO> kafkaTemplate;
    private final NotificationEventRepository notificationEventRepository;
    private final ApplicationProperties applicationProperties;

    @Transactional
    public void sendConfirmationNotification(Order order) {
        NotificationEvent notificationEvent = createNotificationEvent(order);
        Map<String,Object> notificationData = new HashMap<>();
        prepareNotificationData(order, notificationData);

        NotificationEventDTO event = new NotificationEventDTO(
                notificationEvent.getNotificationId(),
                order.getCustomer().getEmail(),
                order.getCustomer().getPhoneNumber(),
                notificationData,
                NotificationTemplate.ORDER_CONFIRMATION
        );
        ObjectMapper mapper = new ObjectMapper();
        String eventJson;
        try {
            eventJson = mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        notificationEvent.setEventData(eventJson);
        log.info("NotificationService::sendConfirmationNotification: Notification " + NotificationTemplate.ORDER_CONFIRMATION + " sent to topic " + applicationProperties.getNotificationTopic());
        kafkaTemplate.send(applicationProperties.getNotificationTopic(), order.getCustomer().getCustomerId().toString(), event);
    }
    @KafkaListener(topics = "${ordering.notification-status-topic}",groupId = "notification-events")
    @Transactional
    private void consumeNotificationStatus(NotificationStatusDTO notificationStatusDTO) throws BusinessException {
        NotificationEvent event = notificationEventRepository.findById(notificationStatusDTO.notificationId()).orElseThrow(()-> new BusinessException("Event not Found"));
        log.info("NotificationService::consumeNotificationStatus: Notification " + NotificationTemplate.ORDER_CONFIRMATION + " received from to topic " + applicationProperties.getNotificationStatusTopic() + " status: "+ notificationStatusDTO.status());
        if(notificationStatusDTO.status().equals("Success")){
            event.setNotificationStatusTP(NotificationStatusTP.SENT);
        }else if(event.getRetries() == 5){
            event.setNotificationStatusTP(NotificationStatusTP.CANCELLED);
        }else{
            event.setNotificationStatusTP(NotificationStatusTP.FAILED);
        }
        event.setRetries(event.getRetries()+1);
    }
    private NotificationEvent createNotificationEvent(Order order){
        NotificationEvent notificationEvent = new NotificationEvent();
        notificationEvent.setCustomer(order.getCustomer());
        notificationEvent.setOrder(order);
        notificationEvent.setNotificationStatusTP(NotificationStatusTP.ACTIVE);
        notificationEvent.setNotificationTemplate(NotificationTemplate.ORDER_CONFIRMATION);
        notificationEvent.setRetries(0);
        return notificationEventRepository.save(notificationEvent);
    }
    private void prepareNotificationData(Order order, Map<String,Object> notificationData){
        notificationData.put("orderId", order.getOrderId());
        notificationData.put("orderTotal", order.getActualTotalPrice());
        notificationData.put("companyName", order.getShipping().getShipmentOption().getCompanyName());
        notificationData.put("daysForArrival", order.getShipping().getDaysForArrival());
        notificationData.put("shippingLink", order.getShipping().getShippingTrackingLink());
        notificationData.put("orderItems", order.getOrderItems().toString());
        notificationData.put("customerName", order.getCustomer().getFirstName());
    }
}
