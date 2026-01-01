package com.jpmc.midascore.service;

import com.jpmc.midascore.component.DatabaseConduit;
import com.jpmc.midascore.controller.BalanceController;
import com.jpmc.midascore.foundation.Balance;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    private final DatabaseConduit databaseConduit;

    public BalanceService(DatabaseConduit databaseConduit){
        this.databaseConduit = databaseConduit;
    }

    public Balance getBalance(long userId){
        return new Balance(databaseConduit.getUserBalance(userId));
    }
}
