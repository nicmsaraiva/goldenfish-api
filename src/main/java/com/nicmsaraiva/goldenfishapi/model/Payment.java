package com.nicmsaraiva.goldenfishapi.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "payment")
public class Payment {
    PaymentType paymentType;
    String value;
    Integer installments;

    public Payment(PaymentType paymentType, String value) {
        this.paymentType = paymentType;
        this.value = value;
        this.installments = 1;
    }
}
