package com.meliChallenge.coupon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.meliChallenge.coupon.dto.ItemDTO;
import com.meliChallenge.coupon.service.CouponMeliService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


public class CouponMeliTest {

    @Autowired
    ApplicationContext context;
    @Test
    void contextLoads() {
        CouponMeliService couponMeliService = context.getBean(CouponMeliService.class);
        assert (couponMeliService instanceof CouponMeliService);

        /*CouponController couponController = ac.getBean(CouponController.class);
        assert (couponController instanceof CouponController);

        Coupon coupon = new Coupon();
        assert (coupon instanceof Coupon);
        coupon.setItemIds(new ArrayList<String>());

        CouponRequest couponRequest = new CouponRequest((float) 0);
        assert (couponRequest instanceof CouponRequest);

        CouponRequest couponRequest1= new CouponRequest(new ArrayList<String>(), (float) 0);
        assert (couponRequest1 instanceof CouponRequest);
        couponRequest.setAmount((float) 10);

        CouponResponse couponResponse = new CouponResponse(new ArrayList<String>(), (float) 0);
        assert (couponResponse instanceof CouponResponse);

        CouponResponse couponResponse1 = new CouponResponse((float) 0);
        assert (couponResponse1 instanceof CouponResponse);
        couponResponse.setTotal((float) 10);*/
    }


}
