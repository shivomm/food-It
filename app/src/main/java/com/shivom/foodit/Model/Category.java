package com.shivom.foodit.Model;

public class Category {

    public Category()
    {}

    public Category(String name, String image) {
        Name = name;
        Image = image;
    }

    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    private String Image;

}
