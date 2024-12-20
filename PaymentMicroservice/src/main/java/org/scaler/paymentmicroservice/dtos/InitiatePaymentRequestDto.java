package org.scaler.paymentmicroservice.dtos;


import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class InitiatePaymentRequestDto {
    private Long orderId;
    private Long amount;

    public Long getOrderId() { return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
            this.amount = amount;
        }


}
