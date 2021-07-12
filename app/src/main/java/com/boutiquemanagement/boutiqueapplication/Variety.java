package com.boutiquemanagement.boutiqueapplication;

public class Variety {
    private int id;
    private String name;
    private Integer price;
    private String subcategory;
    private String category;
    private byte[] image;

    public Variety(String name, Integer price,  String subcategory,  String category, byte[] image, int id) {
        this.name = name;
        this.price = price;
        this.subcategory=subcategory;
        this.category= category;
        this.image = image;
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getSubcategory() {
        return subcategory;
    }
    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
}
