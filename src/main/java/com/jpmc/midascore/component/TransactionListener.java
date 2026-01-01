package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    private final TransactionHandler transactionHandler;
    static final Logger logger = LoggerFactory.getLogger(TransactionListener.class);

    public TransactionListener(TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @KafkaListener( topics = "${general.kafka-topic}")
    public void listener(Transaction transaction){
        transactionHandler.handleTransaction(transaction);
    }
}
