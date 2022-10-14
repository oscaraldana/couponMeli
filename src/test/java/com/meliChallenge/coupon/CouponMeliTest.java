package com.meliChallenge.coupon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.meliChallenge.coupon.controller.CouponMeliController;
import com.meliChallenge.coupon.dto.ItemDTO;
import com.meliChallenge.coupon.model.*;
import com.meliChallenge.coupon.service.CouponMeliService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponMeliTest {

    @Autowired
    ApplicationContext context;
    @Test
    public void contextLoads() {

        CouponMeliService couponMeliService = context.getBean(CouponMeliService.class);
        assert (couponMeliService instanceof CouponMeliService);

        CouponMeliController couponMeliController = context.getBean(CouponMeliController.class);
        assert (couponMeliController instanceof CouponMeliController);

        CouponMeli coupon = new CouponMeli();
        assert (coupon instanceof CouponMeli);
        coupon.setItemIds(new ArrayList<String>());

        CouponMeliRequest couponMeliRequest = new CouponMeliRequest((float) 0);
        assert (couponMeliRequest instanceof CouponMeliRequest);

        CouponMeliRequest couponMeliRequest2= new CouponMeliRequest(new ArrayList<String>(), (float) 0);
        assert (couponMeliRequest2 instanceof CouponMeliRequest);
        couponMeliRequest2.setAmount((float) 10);

        CouponMeliResponse couponMeliResponse = new CouponMeliResponse(new ArrayList<String>(), (float) 0);
        assert (couponMeliResponse instanceof CouponMeliResponse);

        CouponMeliResponse couponMeliResponse2 = new CouponMeliResponse((float) 0);
        assert (couponMeliResponse2 instanceof CouponMeliResponse);
        couponMeliResponse.setTotal((float) 10);
    }


    @Test
    public void couponMeliServiceTestOK() {
        CouponMeliService couponService = context.getBean(CouponMeliService.class);
        assert (couponService instanceof CouponMeliService);
        CouponMeliRequest couponMeliRequest = new CouponMeliRequest(new ArrayList<>(), 500000f);
        couponMeliRequest.getItemIds().add("MCO587700488");
        couponMeliRequest.getItemIds().add("MCO587700497");
        couponMeliRequest.getItemIds().add("MCO587700501");
        couponMeliRequest.getItemIds().add("MCO458209042");
        couponMeliRequest.getItemIds().add("MCO558174274");
        couponService.calculate(couponMeliRequest);
    }

    @Test
    public void couponServiceTestOError() {
        CouponMeliService couponMeliService = context.getBean(CouponMeliService.class);
        assert (couponMeliService instanceof CouponMeliService);
        CouponMeliRequest couponRequest = new CouponMeliRequest(new ArrayList<String>(), 500000f);
        couponMeliService.calculate(couponRequest);
    }

    @Test
    public void couponServiceTestOException() {
        CouponMeliService couponMeliService = context.getBean(CouponMeliService.class);
        assert (couponMeliService instanceof CouponMeliService);
        CouponMeliRequest couponRequest = new CouponMeliRequest(null, 500000f);
        couponMeliService.calculate(couponRequest);
    }

    @Test
    public void couponServiceTestOException2() {
        CouponMeliService couponMeliService = context.getBean(CouponMeliService.class);
        assert (couponMeliService instanceof CouponMeliService);
        CouponMeliRequest couponMeliRequest = new CouponMeliRequest(new ArrayList<String>(), 500000f);
        couponMeliRequest.getItemIds().add("MCO-15397854");
        couponMeliService.calculate(couponMeliRequest);
    }

    @Test
    public void couponMeliControllerTest(){
        CouponMeliController couponMeliController = context.getBean(CouponMeliController.class);
        assert (couponMeliController instanceof CouponMeliController);
        CouponMeliRequest couponRequest = new CouponMeliRequest(new ArrayList<String>(), 500000f);
        couponRequest.getItemIds().add("MCO587700501");
        couponMeliController.calculate(couponRequest);
    }

    @Test
    public void couponUtilCalculateTest(){
        GetCouponItems couponMeliItems = new GetCouponItems();
        assert (couponMeliItems instanceof GetCouponItems);
        List<ItemDTO> itemsDTO = new ArrayList<>();
        for(int i = 1; i<=5; i++){
            ItemDTO item = new ItemDTO("MLA-"+i, "Item-"+i, 100000f*i, "active");
            itemsDTO.add(item);
        }
        CouponMeliResponse response = GetCouponItems.validateItems(itemsDTO, 500000f);
        System.out.println(response);
    }

    @Test
    public void couponUtilCalculateErrorTest(){
        GetCouponItems couponMeliItems = new GetCouponItems();
        assert (couponMeliItems instanceof GetCouponItems);List<ItemDTO> itemsDTO = null;
        GetCouponItems.validateItems(itemsDTO, 500000f);
    }

    @Test
    public void couponUtilPropertiesTest(){
        LoadConfigProperties couponMeliUtil = new LoadConfigProperties();
        assert (couponMeliUtil instanceof LoadConfigProperties);
        LoadConfigProperties.getPropertiesFile();
    }

}
