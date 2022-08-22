package com.boyo.customer;

import com.boyo.advanced_message_queue.RabbitMQMessageProducer;
import com.boyo.clients.fraud.FraudCheckResponse;
import com.boyo.clients.fraud.FraudClient;
import com.boyo.clients.notification.NotificationClient;
import com.boyo.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomerService {

    private final FraudClient fraudClient;
    private final CustomerRepository customerRepository;

    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public String registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        //todo: check if email is valid
        //todo: check if email is not taken
        customerRepository.saveAndFlush(customer);
        //todo: check if fraudster
        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

//

        //todo: send notification
        //todo: make it async. i.e add to queue
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to tbthecoder...",
                        customer.getFirstName())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
        return "registered successfully";
    }
}
