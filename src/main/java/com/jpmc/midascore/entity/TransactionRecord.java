package com.jpmc.midascore.entity;

import jakarta.persistence.*;
import org.apache.catalina.User;

@Entity
public class TransactionRecord {

    @Id
    @GeneratedValue()
    private Long id;

    @JoinColumn(name = "sender_id", nullable = false)
    @ManyToOne
    private UserRecord sender;

    @JoinColumn(name ="recipient_id", nullable = false)
    @ManyToOne
    private UserRecord recipient;

    @Column(nullable = false)
    private float amount;

    public TransactionRecord() {
    }

    public TransactionRecord(Long id, UserRecord sender, UserRecord recipient, float amount) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionRecord{" +
                "id=" + id +
                ", senderId=" + sender +
                ", recipientId=" + recipient +
                ", amount=" + amount +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRecord getSenderId() {
        return sender;
    }

    public void setSenderId(UserRecord sender) {
        this.sender = sender;
    }

    public UserRecord getRecipient() {
        return recipient;
    }

    public void setRecipient(UserRecord recipient) {
        this.recipient = recipient;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
