package com.meliChallenge.coupon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class CouponMeliRequest extends CouponMeli{
    @JsonProperty("amount")
    private Float amount;

    public CouponMeliRequest(Float amount) {
        this.amount = amount;
    }

    public CouponMeliRequest(ArrayList<String> itemIds, Float amount) {
        super(itemIds);
        this.amount = amount;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
