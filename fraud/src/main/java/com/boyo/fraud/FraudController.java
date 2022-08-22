package com.boyo.fraud;

//import com.boyo.clients.fraud.FraudCheckResponse;
import com.boyo.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {
    private final FraudCheckService fraudCheckService;


    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(
            @PathVariable("customerId") Integer customerId){
        log.info("fraud check for customer {}",customerId);
        var isFraud = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraud);
    }
}
