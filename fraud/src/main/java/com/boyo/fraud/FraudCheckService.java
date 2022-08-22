package com.boyo.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
@AllArgsConstructor
public class FraudCheckService {
    private final FraudHistoryCheckRepository fraudHistoryCheckRepository;


    public boolean isFraudulentCustomer(Integer customerID){
        fraudHistoryCheckRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerID)
                        .iSFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}

