package com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago.dto;

public class MPPaymentDto {

    private String token;
    private float transaction_amount;
    private int installments;
    private String payment_method_id;
    private String issuerId;
    private MPPayerDto payer;
    private Long userId;
    private Long checkoutId;

    public MPPaymentDto() {
    }

    public MPPaymentDto(String token, float transaction_amount, int installments, String payment_method_id, MPPayerDto payer, Long userId, Long checkoutId, String issuerId) {
        this.token = token;
        this.transaction_amount = transaction_amount;
        this.installments = installments;
        this.payment_method_id = payment_method_id;
        this.payer = payer;
        this.userId = userId;
        this.checkoutId = checkoutId;
        this.issuerId = issuerId;
    }

    public String getToken() {
        return token;
    }

    public float getTransaction_amount() {
        return transaction_amount;
    }

    public int getInstallments() {
        return installments;
    }

    public String getPayment_method_id() {
        return payment_method_id;
    }

    public MPPayerDto getPayer() {
        return payer;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCheckoutId() {
        return checkoutId;
    }

    public String getIssuerId() {
        return issuerId;
    }
}
