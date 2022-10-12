package com.meliChallenge.coupon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class CouponMeliResponse extends CouponMeli{
    @JsonProperty("total")
    private Float total;

    public CouponMeliResponse(Float total) {
        this.total = total;
    }

    public CouponMeliResponse(ArrayList<String> itemIds, Float total) {
        super(itemIds);
        this.total = total;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
