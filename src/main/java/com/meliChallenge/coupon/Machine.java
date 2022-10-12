package com.meliChallenge.coupon;

import com.meliChallenge.coupon.dto.ItemDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Machine {

    
    public static List <ItemDTO> getItems(){

        ItemDTO itemDTO1 = new ItemDTO("ML01", "Producto 1", 11000f, "OK");
        ItemDTO itemDTO2 = new ItemDTO("ML02", "Producto 2", 200000f, "OK");
        ItemDTO itemDTO3 = new ItemDTO("ML03", "Producto 3", 110000f, "OK");
        ItemDTO itemDTO4 = new ItemDTO("ML04", "Producto 4", 200000f, "OK");
        ItemDTO itemDTO5 = new ItemDTO("ML05", "Producto 5", 580000f, "OK");
        
        List <ItemDTO> items = Arrays.asList(itemDTO1, itemDTO2, itemDTO3, itemDTO4, itemDTO5);
        return items;
    }

    public static Float getCouponValue(){
        return 500000f;
    }

    public static List <ItemDTO> calculate (List <ItemDTO> items, Float couponValue){

        items.stream().forEach(System.out::println);
        System.out.println("================================");
        List <ItemDTO> itemDTOS = items.stream().filter(e->e.price <= couponValue).collect(Collectors.toList()); //forEach(System.out::println)
        itemDTOS.forEach(System.out::println);
        return itemDTOS;
    }


    

}
