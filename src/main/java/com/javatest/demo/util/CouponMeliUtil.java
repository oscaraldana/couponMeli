package com.javatest.demo.util;

import com.javatest.demo.MeliItem;
import com.javatest.demo.model.CouponMeliResponse;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CouponMeliUtil {

    public static Properties getPropertiesFile() {
        InputStream inputStream;
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            inputStream = CouponMeliUtil.class.getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            return prop;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static CouponMeliResponse validateItems (List<MeliItem> items, Float couponValue) {
        try{
            //Exclude values higher than coupon
            List<MeliItem> meliItems = items.stream().filter(e->e.price <= couponValue).collect(Collectors.toList());
            //Exclude inactive products
            meliItems = meliItems.stream().filter(e->e.status.equals("active")).collect(Collectors.toList());
            //Sort by price
            meliItems = meliItems.stream().sorted(Comparator.comparing(item -> item.getPrice())).collect(Collectors.toList());
            
            CouponMeliResponse couponMeliResponse = new CouponMeliResponse(new ArrayList<String>(), (float) 0);

            for (MeliItem item:meliItems) {
                Float usedCoupon = 0f;
                if(usedCoupon <= couponValue ) {
                    couponMeliResponse.getItemIds().add(item.getId());
                    couponMeliResponse.setTotal(couponMeliResponse.getTotal() + item.getPrice());
                    usedCoupon +=   item.getPrice();
                }
            }
            return couponMeliResponse;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
