package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRepository;
import com.jpmc.midascore.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConduit {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final IncentiveFetchService incentiveFetchService;

    public DatabaseConduit(UserRepository userRepository, TransactionRepository transactionRepository, IncentiveFetchService incentiveFetchService) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.incentiveFetchService = incentiveFetchService;

    }

    public void save(TransactionRecord transactionRecord){transactionRepository.save(transactionRecord);}
    public void save(UserRecord userRecord) {
        userRepository.save(userRecord);
    }

    private UserRecord isUser(long userId){
        return userRepository.findById(userId).orElse(null);
    }

    private boolean hasAmount(float amount, long senderId){
        UserRecord userRecord = userRepository.findById(senderId).orElse(null);
        return amount <= userRecord.getBalance();
    }

    public void add(Transaction transaction){
        long senderId = transaction.getSenderId();
        long recipientId = transaction.getRecipientId();
        float amount = transaction.getAmount();

        UserRecord sender = isUser(senderId);
        UserRecord reciever = isUser(recipientId);

        if( sender== null) {
            return;
        }

        if(reciever == null){
            return;
        }

        if(hasAmount(amount, senderId)){
            Incentive incentive = incentiveFetchService.fetchIncentive(transaction);
            sender.setBalance(sender.getBalance()-amount);
            reciever.setBalance(reciever.getBalance()+amount+incentive.getAmount());

            userRepository.save(sender);
            userRepository.save(reciever);
            TransactionRecord transactionRecord = new TransactionRecord();
            transactionRecord.setSenderId(sender);
            transactionRecord.setRecipient(reciever);
            transactionRecord.setAmount(amount);

            transactionRepository.save(transactionRecord);
        }
    }

    public float getUserBalance(long userId){
        return userRepository.findById(userId)
            .map(UserRecord :: getBalance).orElse(0f);
    }
}
