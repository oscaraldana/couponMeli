package com.meliChallenge.coupon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.javatest.demo.dto.ItemDTO;
import org.junit.Test;


public class MachineTests {

    @Test
    public void whenAllItemsCostLessReturnAllItems(){

        List <ItemDTO> items = Machine.getItems();
        Integer totalItems = (int) items.stream().count();
        List <ItemDTO> calculatedItems = Machine.calculate(Machine.getItems(), Machine.getCouponValue());
        Integer totalCalculatedItems = (int) calculatedItems.stream().count();
        assertEquals(true, (totalItems == totalCalculatedItems));
    }


}
