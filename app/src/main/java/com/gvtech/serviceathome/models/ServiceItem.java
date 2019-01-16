package com.gvtech.serviceathome.models;

public class ServiceItem {

    private String id;
    private String name;
    private String imageUrl;
    private String desc;
    private float rating;

    public ServiceItem(String id, String name, String imageUrl, String desc, float rating){
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.desc = desc;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
