package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;

@Component
public class TransactionHandler {
    private final DatabaseConduit databaseConduit;

    public TransactionHandler(DatabaseConduit databaseConduit) {
        this.databaseConduit = databaseConduit;
    }

    public void handleTransaction(Transaction transaction){
        databaseConduit.add(transaction);
    }
}
