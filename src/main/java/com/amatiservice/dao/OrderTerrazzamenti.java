package com.amatiservice.dao;

public class OrderTerrazzamenti {

    private String name;
    private String code;
    private String numberOfItems;
    private String price;

    public OrderTerrazzamenti(String name, String code, String numberOfItems,String price) {
        this.name = name;
        this.code = code;
        this.numberOfItems = numberOfItems;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(String numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    @Override
    public String toString() {
        return "OrderTerrazzamenti{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", price='" + price + '\'' +
                ", numberOfItems='" + numberOfItems + '\'' +
                '}';
    }
}
