package com.boyo.clients.notification;


public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerName,
        String message
) {
}
