package com.meliChallenge.coupon.controller;

import com.meliChallenge.coupon.model.CouponMeliRequest;
import com.meliChallenge.coupon.service.CouponMeliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CouponMeliController {
     @Autowired
     CouponMeliService couponService;

    @PostMapping(value = "/coupon", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity calculate(@RequestBody CouponMeliRequest couponRequest){
        return couponService.calculate(couponRequest);
    }
}
