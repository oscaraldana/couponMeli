package com.meliChallenge.coupon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class CouponMeli {

    @JsonProperty("item_ids")
    private ArrayList<String> itemIds;

    public CouponMeli() {
        this.itemIds = new ArrayList<String>();
    }

    public CouponMeli(ArrayList<String> itemIds) {
        this.itemIds = itemIds;
    }

    public ArrayList<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(ArrayList<String> itemIds) {
        this.itemIds = itemIds;
    }

}
