package com.gvtech.serviceathome.models;

public class BusinessService {

    private String id;
    private boolean isSelect;
    private String name;
    private String price;

    public BusinessService(String id, boolean isSelect, String name, String price){
        this.id = id;
        this.isSelect = isSelect;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
