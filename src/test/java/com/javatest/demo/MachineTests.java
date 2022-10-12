package com.javatest.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

import org.junit.Test;


public class MachineTests {

    @Test
    public void whenAllItemsCostLessReturnAllItems(){

        List <MeliItem> items = Machine.getItems();
        Integer totalItems = (int) items.stream().count();
        List <MeliItem> calculatedItems = Machine.calculate(Machine.getItems(), Machine.getCouponValue());
        Integer totalCalculatedItems = (int) calculatedItems.stream().count();
        assertEquals(true, (totalItems == totalCalculatedItems));
    }


}
