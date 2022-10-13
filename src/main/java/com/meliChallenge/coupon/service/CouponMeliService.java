package com.meliChallenge.coupon.service;

import com.google.gson.Gson;
import com.meliChallenge.coupon.dto.ItemDTO;
import com.meliChallenge.coupon.model.CouponMeliRequest;
import com.meliChallenge.coupon.model.CouponMeliResponse;
import com.meliChallenge.coupon.util.CouponMeliUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class CouponMeliService {
    @Lazy
    @Autowired
    RestTemplate restTemplate;

    @Bean
    private RestTemplate rest() {
        return new RestTemplate();
    }

    public ResponseEntity calculate(CouponMeliRequest couponMeliRequest){
        try {
            Properties prop = CouponMeliUtil.getPropertiesFile();
            String url = prop.getProperty("URL_API_MELI_ITEMS");
            List <ItemDTO> itemDTOS = getMeliItems(couponMeliRequest,url);
            System.out.println(itemDTOS.size());
            if (itemDTOS.size()>0) {
                CouponMeliResponse response = CouponMeliUtil.validateItems(itemDTOS, couponMeliRequest.getAmount());
                if (response.getTotal()== (float) 0){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND.value() + "-" +HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
                }else {
                    return new ResponseEntity<CouponMeliResponse>(response, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND.value() + "-" +HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.value() + "-" +HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    private List <ItemDTO> getMeliItems(CouponMeliRequest couponMeliRequest, String url){
        try {
            List<ItemDTO> itemsDTO = new ArrayList<>();
            for (int i = 0; i < couponMeliRequest.getItemIds().size(); i++){
                try {
                    ResponseEntity<String> result = restTemplate.getForEntity( url + couponMeliRequest.getItemIds().get(i) , String.class);
                    Gson gson = new Gson();
                    ItemDTO itemDTO = gson.fromJson(result.getBody(), ItemDTO.class);
                    itemsDTO.add(itemDTO);
                    
                } catch (Exception e) {
                    System.out.println("ERROR:: " + e.getMessage());
                    continue;
                }
            }
            return itemsDTO;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



}

