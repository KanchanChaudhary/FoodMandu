package com.kanchan.foodmandu.ui.home.model;

import java.util.ArrayList;
import java.util.List;

public class superViewmodel {

    static List<superViewmodel> superlist=new ArrayList<>();
    private int image;
    private String cafe;
    private String dish;
    private String location;
    private int icon;

    public superViewmodel(int image, String cafe, String dish, String location, int icon) {
        this.image = image;
        this.cafe = cafe;
        this.dish = dish;
        this.location = location;
        this.icon = icon;
    }

    public static List<superViewmodel> getSuperlist() {
        return superlist;
    }

    public static void setSuperlist(List<superViewmodel> superlist) {
        superViewmodel.superlist = superlist;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCafe() {
        return cafe;
    }

    public void setCafe(String cafe) {
        this.cafe = cafe;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
