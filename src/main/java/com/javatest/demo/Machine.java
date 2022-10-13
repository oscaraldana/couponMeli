package com.javatest.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Machine {

    
    public static List <MeliItem> getItems(){

        MeliItem meliItem1 = new MeliItem("ML01", "Producto 1", 11000f, "OK");
        MeliItem meliItem2 = new MeliItem("ML02", "Producto 2", 200000f, "OK");
        MeliItem meliItem3 = new MeliItem("ML03", "Producto 3", 110000f, "OK");
        MeliItem meliItem4 = new MeliItem("ML04", "Producto 4", 200000f, "OK");
        MeliItem meliItem5 = new MeliItem("ML05", "Producto 5", 580000f, "OK");
        
        List <MeliItem> items = Arrays.asList(meliItem1, meliItem2, meliItem3, meliItem4, meliItem5);
        return items;
    }

    public static Float getCouponValue(){
        return 500000f;
    }

    public static List <MeliItem> calculate (List <MeliItem> items, Float couponValue){

        items.stream().forEach(System.out::println);
        System.out.println("================================");
        List <MeliItem>meliItems = items.stream().filter(e->e.price <= couponValue).collect(Collectors.toList()); //forEach(System.out::println)
        meliItems.forEach(System.out::println);
        return meliItems;
    }


    

}
