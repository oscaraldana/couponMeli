package com.javatest.demo;

public class MeliItem {

    public String id, title, status;
    public Float price;

    public MeliItem(){}

    public MeliItem(String id, String title, Float price, String status){
        this.id = id;
        this.title = title;
        this.price = price;
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" + this.id.toString() + "," + this.title.toString() + "," + this.price.toString() + "," + this.status.toString() + "}";
    }

    public String getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getStatus(){
        return this.status;
    }
    public Float getPrice(){
        return this.price;
    }

}
