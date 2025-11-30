package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    static final Logger logger = LoggerFactory.getLogger(TransactionListener.class);

    @KafkaListener( topics = "${general.kafka-topic}")
    public void listener(Transaction transaction){
        logger.info(transaction.toString());
    }
}
