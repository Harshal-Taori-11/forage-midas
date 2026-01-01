package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IncentiveFetchService {

    private final RestTemplate restTemplate;

    @Value("${general.incentive-api-url}")
    private String incentiveUrl;

    public IncentiveFetchService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Incentive fetchIncentive(Transaction transaction){
        Incentive incentive;

        try{
            incentive = restTemplate.postForObject(
                    incentiveUrl,
                    transaction,
                    Incentive.class
            );
        }
        catch(Exception exp){
            throw new RuntimeException(exp);
        }


        return incentive;
    }
}
