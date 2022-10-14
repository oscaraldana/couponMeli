package com.meliChallenge.coupon.model;

import com.meliChallenge.coupon.dto.ItemDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetCouponItems {

    public static CouponMeliResponse validateItems (List<ItemDTO> items, Float couponValue) {
        try{
            //Exclude values higher than coupon
            List<ItemDTO> itemDTOS = items.stream().filter(e->e.price <= couponValue).collect(Collectors.toList());

            //Exclude inactive products
            itemDTOS = itemDTOS.stream().filter(e -> e.status.equals("active")).collect(Collectors.toList());

            //Sort by price
            Map<String, Float> itemMap = itemDTOS.stream()
                    .collect(Collectors.toMap(ItemDTO::getId, ItemDTO::getPrice));

            List<String> couponItems = calculate(itemMap, couponValue);
            Float totalUsed = couponItems.stream()
                    .reduce(0f, (subtotal, id) ->
                            itemMap.getOrDefault(id, 0f) + subtotal, Float::sum);

            CouponMeliResponse couponMeliResponse = new CouponMeliResponse((ArrayList<String>) couponItems, totalUsed);
            return couponMeliResponse;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static List<String> calculate(Map<String, Float> items, Float amount) {
        Float usedCoupon = 0f;

        List<String> itemsSelected = new ArrayList<>();

        List<ItemDTO> itemsParsed = items.entrySet().stream()
                .map(item -> new ItemDTO(item.getKey(), null, item.getValue(), null))
                .collect(Collectors.toList());

        itemsParsed = itemsParsed.stream()
                .sorted(Comparator.comparing(item -> item.getPrice())).collect(Collectors.toList());

        for (ItemDTO item: itemsParsed) {
            if(usedCoupon <= amount ) {
                itemsSelected.add(item.getId());
                usedCoupon += item.getPrice();
            }
        }

        return itemsSelected;

    }
}
