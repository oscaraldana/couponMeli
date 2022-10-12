package com.javatest.demo.service;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.javatest.demo.MeliItem;
import com.javatest.demo.model.CouponMeliRequest;
import com.javatest.demo.model.CouponMeliResponse;
import com.javatest.demo.util.CouponMeliUtil;

import javafx.scene.chart.PieChart.Data;

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
    public RestTemplate rest() {
        return new RestTemplate();
    }

    public ResponseEntity calculate(CouponMeliRequest couponMeliRequest){
        try {
            Properties prop = CouponMeliUtil.getPropertiesFile();
            String url = prop.getProperty("URL_API_MELI_ITEMS");
            List <MeliItem> meliItems = getMeliItems(couponMeliRequest,url);
            System.out.println(meliItems.size());
            if (meliItems.size()>0) {
                CouponMeliResponse response = CouponMeliUtil.validateItems(meliItems, couponMeliRequest.getAmount());
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

    public List <MeliItem> getMeliItems(CouponMeliRequest couponMeliRequest, String url){
        try {
            List<MeliItem> meliItems = new ArrayList<>();
            for (int i = 0; i < couponMeliRequest.getItemIds().size(); i++){
                try {
                    ResponseEntity<String> result = restTemplate.getForEntity( url + couponMeliRequest.getItemIds().get(i) , String.class);
                    Gson gson = new Gson();
                    MeliItem meliItem = gson.fromJson(result.getBody(), MeliItem.class);
                    meliItems.add(meliItem);
                    
                } catch (Exception e) {
                    System.out.println("ERROR:: " + e.getMessage());
                    continue;
                }
            }
            return  meliItems;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



}

