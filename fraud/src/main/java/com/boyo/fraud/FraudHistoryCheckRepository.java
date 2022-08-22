package com.boyo.fraud;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudHistoryCheckRepository extends JpaRepository<FraudCheckHistory, Integer> {
}
